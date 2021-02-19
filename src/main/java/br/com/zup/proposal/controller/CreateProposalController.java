package br.com.zup.proposal.controller;

import br.com.zup.proposal.client.CardCreationClient;
import br.com.zup.proposal.client.FinancialAnalysisClient;
import br.com.zup.proposal.client.request.CreateCardRequest;
import br.com.zup.proposal.client.request.FinancialAnalysisRequest;
import br.com.zup.proposal.client.response.FinancialAnalysisResponse;
import br.com.zup.proposal.controller.request.ProposalRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.model.enums.ProposalStatus;
import br.com.zup.proposal.repository.ProposalRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CreateProposalController {

    private final ProposalRepository proposalRepository;
    private final FinancialAnalysisClient analysisClient;
    private final CardCreationClient cardClient;

    private final Gson gson;

    @Autowired
    public CreateProposalController(ProposalRepository proposalRepository,
                                    FinancialAnalysisClient analysisClient, CardCreationClient cardClient, Gson gson) {
        this.proposalRepository = proposalRepository;
        this.analysisClient = analysisClient;
        this.cardClient = cardClient;

        this.gson = gson;
    }

    @PostMapping
    public ResponseEntity<?> createNewProposal(@RequestBody @Valid ProposalRequest request,
                                               UriComponentsBuilder builder) {

        Proposal proposal = request.toModel();

        if (proposalRepository.existsByDocument(proposal.getDocument())) {
            return ResponseEntity.unprocessableEntity()
                    .body(new ErrorResponse("There is already a proposal for that document."));
        }

        FinancialAnalysisResponse response;

        try {
            response = analysisClient
                    .consult(new FinancialAnalysisRequest(proposal.getDocument(), proposal.getName(), proposal.getId()));
        } catch (FeignException.UnprocessableEntity e) {
            response = gson.fromJson(e.contentUTF8(), FinancialAnalysisResponse.class);
        }

        proposal.setStatus(response.getResultadoSolicitacao().getStatus());
        proposalRepository.save(proposal);

        if (proposal.getStatus() == ProposalStatus.ELIGIBLE) {
            cardClient.create(new CreateCardRequest(proposal.getDocument(), proposal.getName(), proposal.getId()));
        }

        URI location = builder.path("/api/v1/proposal/{id}").buildAndExpand(proposal.getExternalId().toString()).toUri();
        return ResponseEntity.created(location).build();
    }

}
