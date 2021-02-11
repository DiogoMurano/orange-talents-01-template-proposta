package br.com.zup.proposal.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Address {

    @NotBlank
    private String cep;

    @NotBlank
    private String street;

    @NotBlank
    private String district;

    private String complement;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    public Address(String cep, String street, String district, String complement, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.district = district;
        this.complement = complement;
        this.city = city;
        this.state = state;
    }

    public Address() {
    }
}
