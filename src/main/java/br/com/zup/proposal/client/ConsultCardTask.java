package br.com.zup.proposal.client;

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

import java.text.MessageFormat;
import java.util.List;

@Component
public class ConsultCardTask {

    private final ProposalRepository proposalRepository;
    private final CardRepository cardRepository;

    private final CardCreationClient cardCreationClient;

    private static final Logger logger = LoggerFactory.getLogger(ConsultCardTask.class);

    @Autowired
    public ConsultCardTask(ProposalRepository proposalRepository, CardRepository cardRepository, CardCreationClient cardCreationClient) {
        this.proposalRepository = proposalRepository;
        this.cardRepository = cardRepository;

        this.cardCreationClient = cardCreationClient;
    }

    @Scheduled(fixedDelayString = "${proposal.consult-card.delay}")
    public void consultCardCreation() {
        List<Proposal> proposals = proposalRepository.findAllByStatusAndCard(ProposalStatus.ELIGIBLE, null);

        proposals.forEach(proposal -> {
            try {
                NewCardResponse consult = cardCreationClient.consult(proposal.getId().toString());
                Card card = new Card(consult.getId(), consult.getEmitidoEm(), proposal);
                cardRepository.save(card);

                proposal.associateCard(card);
                proposalRepository.save(proposal);

                logger.info("Card ****.****.****." + card.getNumber().substring(card.getNumber().length() - 4) +
                        " associated with proposal " + proposal.getExternalId().toString());
            } catch (FeignException.InternalServerError e) {
                logger.info(MessageFormat.format("Failed to query card to proposal {0}", proposal.getId()));
                e.printStackTrace();
            }
        });
    }

}
