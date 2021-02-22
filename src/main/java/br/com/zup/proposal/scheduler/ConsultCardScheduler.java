package br.com.zup.proposal.scheduler;

import br.com.zup.proposal.client.CardCreationClient;
import br.com.zup.proposal.client.request.CreateCardRequest;
import br.com.zup.proposal.client.response.NewCardResponse;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.model.enums.ProposalStatus;
import br.com.zup.proposal.repository.CardRepository;
import br.com.zup.proposal.repository.ProposalRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.MessageFormat;
import java.util.List;

@Component
public class ConsultCardScheduler {

    private final ProposalRepository proposalRepository;
    private final CardRepository cardRepository;
    private final CardCreationClient cardCreationClient;

    private final TransactionTemplate transactionTemplate;

    private static final Logger logger = LoggerFactory.getLogger(ConsultCardScheduler.class);

    @Autowired
    public ConsultCardScheduler(ProposalRepository proposalRepository, CardRepository cardRepository,
                                CardCreationClient cardCreationClient, TransactionTemplate transactionTemplate) {
        this.proposalRepository = proposalRepository;
        this.cardRepository = cardRepository;
        this.cardCreationClient = cardCreationClient;
        this.transactionTemplate = transactionTemplate;

        this.transactionTemplate.setTimeout(2000);
    }

    @Scheduled(fixedDelayString = "${proposal.consult-card.delay}")
    public void consultCardCreation() {
        List<Proposal> proposals = proposalRepository.findTop5ByStatusOrderByCreatedAtAsc(ProposalStatus.ELIGIBLE);

        for (Proposal proposal : proposals) {
            transactionTemplate.execute(transactionStatus -> {
                try {
                    NewCardResponse consult = cardCreationClient.create(new CreateCardRequest(proposal.getDocument(),
                            proposal.getName(), proposal.getExternalId()));
                    Card card = new Card(consult.getId(), consult.getCreatedAt(), proposal);
                    cardRepository.save(card);

                    proposal.attachCard(card);
                    proposalRepository.save(proposal);

                    logger.info("Card " + card.getBlinkCardNumber() +
                            " associated with proposal " + proposal.getExternalId().toString());
                } catch (FeignException.InternalServerError e) {
                    logger.info(MessageFormat.format("Failed to query card to proposal {0}", proposal.getId()));
                    return false;
                }
                return true;
            });
        }

    }

}
