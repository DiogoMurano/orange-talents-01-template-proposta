package br.com.zup.proposal.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

    @NotNull
    private final LocalDateTime blockedAt = LocalDateTime.now();

    @NotNull
    @Embedded
    private Requester requester;

    @OneToOne
    private Card card;

    public Block(@NotNull Requester requester, Card card) {
        this.requester = requester;
        this.card = card;
    }

    @Deprecated
    public Block() {
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    public Requester getBlockRequester() {
        return requester;
    }

    public Card getCard() {
        return card;
    }
}
