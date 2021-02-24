package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotifyCardResponse {

    @JsonProperty("resultado")
    private final String result;

    public NotifyCardResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
