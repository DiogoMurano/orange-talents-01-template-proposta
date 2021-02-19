package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisRequest {

    @JsonProperty("documento")
    private final String document;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("idProposta")
    private final Long idProposal;

    public FinancialAnalysisRequest(String document, String name, Long idProposal) {
        this.document = document;
        this.name = name;
        this.idProposal = idProposal;
    }
}
