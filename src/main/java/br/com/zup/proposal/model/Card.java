package br.com.zup.proposal.model;

import br.com.zup.proposal.model.enums.CardStatus;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

    @NotBlank
    private String number;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @OneToOne
    private Proposal proposal;

    @NotNull
    @OneToMany
    private final Set<Biometry> biometrics = Collections.emptySet();

    @NotNull
    @OneToMany
    private final Set<TravelNotification> notifications = Collections.emptySet();

    @NotNull
    @Enumerated(EnumType.STRING)
    private CardStatus status = CardStatus.WORKING;

    public Card(String number, LocalDateTime createdAt, Proposal proposal) {
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

    public UUID getExternalId() {
        return externalId;
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

    public Set<Biometry> getBiometrics() {
        return biometrics;
    }

    public Set<TravelNotification> getNotifications() {
        return notifications;
    }

    public String getBlinkCardNumber() {
        return "****.****.****." + getNumber().substring(getNumber().length() - 4);
    }

    public void attachBlock() {
        this.status = CardStatus.BLOCKED;
    }

    public boolean isBlocked() {
        return this.status == CardStatus.BLOCKED;
    }
}
