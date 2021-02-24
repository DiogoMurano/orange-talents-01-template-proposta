package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BlockCardResponse {

    @JsonProperty("resultado")
    private final String result;

    public BlockCardResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
