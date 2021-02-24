package br.com.zup.proposal.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class ClientAssociateWalletResponse {

    @JsonProperty("resultado")
    @NotBlank
    private final String result;

    @JsonProperty
    @NotBlank
    private final String id;

    public ClientAssociateWalletResponse(String result, String id) {
        this.result = result;
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public String getId() {
        return id;
    }
}
