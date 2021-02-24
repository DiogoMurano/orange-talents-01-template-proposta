package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientNotifyCardResponse {

    @JsonProperty("resultado")
    private final String result;

    public ClientNotifyCardResponse(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

}
