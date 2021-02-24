package br.com.zup.proposal.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociateWalletRequest {

    @JsonProperty
    @Email
    @NotBlank
    private final String email;

    @JsonProperty
    @NotBlank
    private final String wallet;

    public AssociateWalletRequest(String email, String wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public String getWallet() {
        return wallet;
    }
}
