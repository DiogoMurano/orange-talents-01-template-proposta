package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class ClientCreateCardRequest {

    @JsonProperty("documento")
    private final String document;

    @JsonProperty("nome")
    private final String name;

    @JsonProperty("idProposta")
    private final String idProposal;

    public ClientCreateCardRequest(String document, String name, UUID idProposal) {
        this.document = document;
        this.name = name;
        this.idProposal = idProposal.toString();
    }
}
