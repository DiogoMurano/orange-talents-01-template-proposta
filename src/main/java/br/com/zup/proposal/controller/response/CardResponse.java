package br.com.zup.proposal.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class CardResponse {

    @JsonProperty
    private final UUID id;

    @JsonProperty
    private final String cardNumber;

    @JsonProperty
    private final LocalDateTime createdAt;

    public CardResponse(UUID id, String cardNumber, LocalDateTime createdAt) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    public static CardResponseBuilder builder() {
        return new CardResponseBuilder();
    }

    public static class CardResponseBuilder {

        private UUID id;
        private String cardNumber;
        private LocalDateTime createdAt;

        public CardResponseBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public CardResponseBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CardResponse build() {
            return new CardResponse(id, cardNumber, createdAt);
        }

    }
}
