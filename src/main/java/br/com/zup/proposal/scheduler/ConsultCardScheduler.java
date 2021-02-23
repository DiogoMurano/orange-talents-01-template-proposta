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

import javax.transaction.Transactional;
import java.text.MessageFormat;
import java.util.List;

@Component
public class ConsultCardScheduler {

    private final ProposalRepository proposalRepository;
    private final CardRepository cardRepository;
    private final CardCreationClient cardCreationClient;

    private static final Logger logger = LoggerFactory.getLogger(ConsultCardScheduler.class);

    @Autowired
    public ConsultCardScheduler(ProposalRepository proposalRepository, CardRepository cardRepository,
                                CardCreationClient cardCreationClient) {
        this.proposalRepository = proposalRepository;
        this.cardRepository = cardRepository;
        this.cardCreationClient = cardCreationClient;
    }

    @Scheduled(fixedDelayString = "${proposal.consult-card.delay}")
    @Transactional
    public void consultCardCreation() {
        List<Proposal> proposals = proposalRepository.findTop5ByStatusOrderByCreatedAtAsc(ProposalStatus.ELIGIBLE);

        for (Proposal proposal : proposals) {
            try {
                NewCardResponse consult = cardCreationClient.consult(proposal.getExternalIdToString());
                Card card = new Card(consult.getId(), consult.getCreatedAt(), proposal);
                cardRepository.save(card);

                proposal.attachCard(card);
                proposalRepository.save(proposal);

                logger.info("Card {} associated with proposal {}", card.getBlinkCardNumber(), proposal.getExternalId().toString());
            } catch (FeignException.InternalServerError e) {
                logger.info("Failed to query card to proposal {}", proposal.getExternalId().toString());
            }
        }
    }

}
