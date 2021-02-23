package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisRequest {

    @JsonProperty("documento")
    private final String document;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("idProposta")
    private final String idProposal;

    public FinancialAnalysisRequest(String document, String name, String idProposal) {
        this.document = document;
        this.name = name;
        this.idProposal = idProposal;
    }
}
