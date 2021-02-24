package br.com.zup.proposal.controller.request;

import br.com.zup.proposal.model.enums.WalletType;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociateWalletRequest {

    @JsonProperty
    @Email
    @NotBlank
    private final String email;

    @JsonProperty
    @NotNull
    private final WalletType wallet;

    public AssociateWalletRequest(String email, WalletType wallet) {
        this.email = email;
        this.wallet = wallet;
    }

    public String getEmail() {
        return email;
    }

    public WalletType getWallet() {
        return wallet;
    }
}
