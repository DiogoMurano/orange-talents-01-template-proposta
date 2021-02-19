package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateCardRequest {

    @JsonProperty
    private final String documento;

    @JsonProperty
    private final String nome;

    @JsonProperty
    private final String idProposta;

    public CreateCardRequest(String documento, String nome, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta.toString();
    }
}
