package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockCardRequest {

    @JsonProperty
    private final String responsibleSystem;

    public BlockCardRequest(String responsibleSystem) {
        this.responsibleSystem = responsibleSystem;
    }
}
