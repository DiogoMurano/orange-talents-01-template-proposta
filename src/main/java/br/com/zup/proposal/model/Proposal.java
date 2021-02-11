package br.com.zup.proposal.model;

import br.com.zup.proposal.validation.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Document
    private String document;

    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @Positive
    private BigDecimal salary;

    public Proposal(String document, String email, String name, Address address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public Proposal() {
    }

    public Long getId() {
        return id;
    }
}