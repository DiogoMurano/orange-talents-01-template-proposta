package br.com.zup.proposal.controller.request;

import br.com.zup.proposal.model.Biometry;
import br.com.zup.proposal.model.Card;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class BiometryRequest {

    @JsonProperty
    @NotBlank
    @Pattern(regexp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$\n", message = "Text must be in base64")
    private final String text;

    public BiometryRequest(@NotBlank String text) {
        this.text = text;
    }

    public Biometry toModel(Card card) {
        return new Biometry(card, text);
    }
}
