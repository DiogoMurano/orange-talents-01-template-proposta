package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientBlockCardRequest {

    @JsonProperty("sistemaResponsavel")
    private final String responsibleSystem;

    public ClientBlockCardRequest(String responsibleSystem) {
        this.responsibleSystem = responsibleSystem;
    }
}
