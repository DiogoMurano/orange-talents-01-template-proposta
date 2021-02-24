package br.com.zup.proposal.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

    @ManyToOne
    @NotNull
    private Card card;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String wallet;

    public Wallet(Card card, String email, String wallet) {
        this.card = card;
        this.email = email;
        this.wallet = wallet;
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

    public String getEmail() {
        return email;
    }

    public String getWallet() {
        return wallet;
    }
}
