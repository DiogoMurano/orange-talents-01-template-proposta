package br.com.zup.proposal.controller;

import br.com.zup.proposal.client.FinancialAnalysisClient;
import br.com.zup.proposal.client.request.FinancialAnalysisRequest;
import br.com.zup.proposal.client.response.FinancialAnalysisResponse;
import br.com.zup.proposal.controller.request.ProposalRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.repository.ProposalRepository;
import com.google.gson.Gson;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

    private final Gson gson;

    private static final Logger logger = LoggerFactory.getLogger(ProposalController.class);

    @Autowired
    public CreateProposalController(ProposalRepository proposalRepository,
                                    FinancialAnalysisClient analysisClient, Gson gson) {
        this.proposalRepository = proposalRepository;
        this.analysisClient = analysisClient;

        this.gson = gson;
    }

    @PostMapping
    @Transactional
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
                    .consult(new FinancialAnalysisRequest(proposal.getDocument(), proposal.getName(), proposal.getExternalIdToString()));
        } catch (FeignException.UnprocessableEntity e) {
            response = gson.fromJson(e.contentUTF8(), FinancialAnalysisResponse.class);
        }

        proposal.setStatus(response.getResultadoSolicitacao().getStatus());
        proposalRepository.save(proposal);

        logger.info("Proposal {} successfully created", proposal.getExternalId().toString());

        URI location = builder.path("/api/v1/proposal/{id}").buildAndExpand(proposal.getExternalId().toString()).toUri();
        return ResponseEntity.created(location).build();
    }

}
