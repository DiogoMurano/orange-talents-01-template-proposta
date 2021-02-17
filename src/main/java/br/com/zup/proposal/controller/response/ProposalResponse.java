package br.com.zup.proposal.controller.response;

import br.com.zup.proposal.model.enums.CardStatus;
import br.com.zup.proposal.model.enums.ProposalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class ProposalResponse {

    @JsonProperty
    private final UUID externalId;

    @JsonProperty
    private final String document;

    @JsonProperty
    private final String email;

    @JsonProperty
    private final String name;

    @JsonProperty
    private final BigDecimal salary;

    @JsonProperty
    private final ProposalStatus status;

    @JsonProperty
    private final CardStatus cardStatus;

    @JsonProperty
    private final CardResponse card;

    public ProposalResponse(UUID externalId, String document, String email, String name,
                            BigDecimal salary, ProposalStatus status, CardStatus cardStatus, CardResponse card) {
        this.externalId = externalId;
        this.document = document;
        this.email = email;
        this.name = name;
        this.salary = salary;
        this.status = status;
        this.cardStatus = cardStatus;
        this.card = card;
    }

    public static ProposalResponseBuilder builder() {
        return new ProposalResponseBuilder();
    }

    public static class ProposalResponseBuilder {

        private UUID externalId;
        private String document;
        private String email;
        private String name;
        private BigDecimal salary;
        private ProposalStatus status;
        private CardStatus cardStatus;
        private CardResponse card;

        public ProposalResponseBuilder externalId(UUID externalId) {
            this.externalId = externalId;
            return this;
        }

        public ProposalResponseBuilder document(String document) {
            this.document = document;
            return this;
        }

        public ProposalResponseBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ProposalResponseBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProposalResponseBuilder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public ProposalResponseBuilder status(ProposalStatus status) {
            this.status = status;
            return this;
        }

        public ProposalResponseBuilder cardStatus(CardStatus cardStatus) {
            this.cardStatus = cardStatus;
            return this;
        }

        public ProposalResponseBuilder card(CardResponse card) {
            this.card = card;
            return this;
        }

        public ProposalResponse build() {
            return new ProposalResponse(externalId, document, email, name, salary, status, cardStatus, card);
        }

    }

}
