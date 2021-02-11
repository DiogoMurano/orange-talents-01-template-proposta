package br.com.zup.proposal.controller.request;

import br.com.zup.proposal.model.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AddressRequest {

    @JsonProperty
    @NotBlank
    private final String cep;

    @JsonProperty
    @NotBlank
    private final String street;

    @JsonProperty
    @NotBlank
    private final String district;

    @JsonProperty
    private final String complement;

    @JsonProperty
    @NotBlank
    private final String city;

    @JsonProperty
    @NotBlank
    private final String state;

    public AddressRequest(String cep, String street, String district, String complement, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Address toModel() {
        return new Address(cep, street, district, complement, city, state);
    }

}
