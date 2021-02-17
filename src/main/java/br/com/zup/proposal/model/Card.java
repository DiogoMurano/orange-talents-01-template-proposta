package br.com.zup.proposal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String number;

    @NotBlank
    private LocalDateTime createdAt;

    @OneToOne
    private Proposal proposal;

    public Card(@NotBlank String number, LocalDateTime createdAt, Proposal proposal) {
        this.number = number;
        this.createdAt = createdAt;
        this.proposal = proposal;
    }

    @Deprecated
    public Card() {
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Proposal getProposal() {
        return proposal;
    }
}
