package br.com.zup.proposal.model;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TravelNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private final UUID externalId = UUID.randomUUID();

    @NotNull
    private final LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @Embedded
    private Requester requester;

    @ManyToOne
    private Card card;

    @NotBlank
    private String destiny;

    @NotNull
    @Future
    private LocalDate travelFinishDate;

    public TravelNotification(Requester requester, Card card, String destiny, LocalDate travelFinishDate) {
        this.requester = requester;
        this.card = card;
        this.destiny = destiny;
        this.travelFinishDate = travelFinishDate;
    }

    @Deprecated
    public TravelNotification() {
    }

    public Long getId() {
        return id;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Requester getRequester() {
        return requester;
    }

    public Card getCard() {
        return card;
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getTravelFinishDate() {
        return travelFinishDate;
    }
}
