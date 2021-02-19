package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NewCardResponse {

    @JsonProperty("id")
    private final String id;

    @JsonProperty("emitidoEm")
    private final LocalDateTime createdAt;

    public NewCardResponse(String id, LocalDateTime createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
