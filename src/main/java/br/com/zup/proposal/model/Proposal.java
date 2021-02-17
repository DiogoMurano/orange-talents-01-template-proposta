package br.com.zup.proposal.model;

import br.com.zup.proposal.model.enums.CardStatus;
import br.com.zup.proposal.model.enums.ProposalStatus;
import br.com.zup.proposal.validation.Document;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

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

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProposalStatus status = ProposalStatus.NOT_ELIGIBLE;

    @OneToOne
    private Card card;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private CardStatus cardStatus = CardStatus.UNCREATED;

    public Proposal(String document, String email, String name, Address address, BigDecimal salary) {
        this.document = document;
        this.email = email;
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    @Deprecated
    public Proposal() {
    }

    public void associateCard(@Valid Card card) {
        this.card = card;
        this.cardStatus = CardStatus.CREATED;
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public ProposalStatus getStatus() {
        return status;
    }

    public Card getCard() {
        return card;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setStatus(ProposalStatus status) {
        this.status = status;
    }

}
