package br.com.zup.proposal.model;

import br.com.zup.proposal.model.enums.WalletType;

import javax.persistence.*;
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

    @Enumerated(EnumType.STRING)
    @NotNull
    private WalletType type;

    public Wallet(Card card, String email, WalletType type) {
        this.card = card;
        this.email = email;
        this.type = type;
    }

    @Deprecated
    public Wallet() {
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

    public WalletType getType() {
        return type;
    }
}
