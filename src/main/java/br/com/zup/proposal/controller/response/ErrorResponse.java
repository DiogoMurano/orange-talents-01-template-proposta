package br.com.zup.proposal.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ErrorResponse {

    @JsonProperty
    private final LocalDateTime time;

    @JsonProperty
    private final String message;

    public ErrorResponse(String message) {
        this.message = message;

        this.time = LocalDateTime.now();
    }
}
