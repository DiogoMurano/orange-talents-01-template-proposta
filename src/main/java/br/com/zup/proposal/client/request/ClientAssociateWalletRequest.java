package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientAssociateWalletRequest {

    @JsonProperty("email")
    private final String email;

    @JsonProperty("wallet")
    private final String wallet;

    public ClientAssociateWalletRequest(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }
}
