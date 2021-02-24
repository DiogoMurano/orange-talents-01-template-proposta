package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientBlockCardResponse {

    @JsonProperty("resultado")
    private final String result;

    public ClientBlockCardResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
