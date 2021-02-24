package br.com.zup.proposal.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ClientNotifyCardRequest {

    @JsonProperty("sistemaResponsavel")
    private final String destiny;

    @JsonProperty("validoAte")
    private final LocalDate validate;

    public ClientNotifyCardRequest(String destiny, LocalDate validate) {
        this.destiny = destiny;
        this.validate = validate;
    }
}
