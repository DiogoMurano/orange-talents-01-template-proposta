package br.com.zup.proposal.controller.request;

import br.com.zup.proposal.model.Proposal;
import br.com.zup.proposal.validation.Document;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ProposalRequest {

    @JsonProperty
    @NotBlank
    @Document
    private final String document;

    @JsonProperty
    @NotBlank
    private final String email;

    @JsonProperty
    @NotBlank
    private final String name;

    @JsonProperty
    @NotNull
    private final AddressRequest address;

    @JsonProperty
    @NotNull
    @Positive
    private final BigDecimal salary;

    public ProposalRequest(String document, String email, String name, AddressRequest address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal toModel() {
        return new Proposal(document, email, name, address.toModel(), salary);
    }
}
