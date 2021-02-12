package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisRequest {

    @JsonProperty
    private final String documento;

    @JsonProperty
    private final String nome;

    @JsonProperty
    private final Long idProposta;

    public FinancialAnalysisRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }
}
