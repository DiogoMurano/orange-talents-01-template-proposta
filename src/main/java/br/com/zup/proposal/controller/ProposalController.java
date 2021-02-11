package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.ProposalRequest;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.repository.ProposalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/proposal")
public class ProposalController {

    private final ProposalRepository proposalRepository;

    public ProposalController(ProposalRepository proposalRepository) {
        this.proposalRepository = proposalRepository;
    }

    @PostMapping
    public ResponseEntity<?> createNewProposal(@RequestBody @Valid ProposalRequest request,
                                               UriComponentsBuilder builder) {

        Proposal proposal = request.toModel();
        proposalRepository.save(proposal);

        URI location = builder.path("/api/v1/proposal/{id}").buildAndExpand(proposal.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
