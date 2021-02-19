package br.com.zup.proposal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
public class Biometry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

    @NotNull
    @ManyToOne
    private Card card;

    @NotBlank
    private String fingerprint;

    public Biometry(Card card, String fingerprint) {
        this.card = card;
        this.fingerprint = fingerprint;
    }

    @Deprecated
    public Biometry() {
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Card getCard() {
        return card;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
