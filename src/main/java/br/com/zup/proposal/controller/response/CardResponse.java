package br.com.zup.proposal.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CardResponse {

    @JsonProperty
    private final String cardNumber;

    @JsonProperty
    private final LocalDateTime createdAt;

    public CardResponse(String cardNumber, LocalDateTime createdAt) {
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    public static CardResponseBuilder builder() {
        return new CardResponseBuilder();
    }

    public static class CardResponseBuilder {

        private String cardNumber;
        private LocalDateTime createdAt;

        public CardResponseBuilder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public CardResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CardResponse build() {
            return new CardResponse(cardNumber, createdAt);
        }

    }
}
