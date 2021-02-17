package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.response.CardResponse;
import br.com.zup.proposal.controller.response.ProposalResponse;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.repository.ProposalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/proposal")
public class ProposalController {

    private final ProposalRepository proposalRepository;

    @Autowired
    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProposalResponse> findById(@PathVariable UUID id) {
        Proposal proposal = proposalRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposal not found."));
        Card card = proposal.getCard();

        ProposalResponse.ProposalResponseBuilder builder = ProposalResponse.builder()
                .externalId(proposal.getExternalId())
                .document(proposal.getDocument())
                .email(proposal.getEmail())
                .name(proposal.getName())
                .salary(proposal.getSalary())
                .status(proposal.getStatus())
                .cardStatus(proposal.getCardStatus());

        if (card != null) {
            builder.card(CardResponse.builder()
                    .cardNumber(card.getNumber())
                    .createdAt(card.getCreatedAt())
                    .build());
        }

        ProposalResponse response = builder.build();
        return ResponseEntity.ok(response);
    }

}
