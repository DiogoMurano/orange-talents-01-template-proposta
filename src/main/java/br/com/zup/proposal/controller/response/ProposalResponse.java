package br.com.zup.proposal.controller.response;

import br.com.zup.proposal.model.enums.ProposalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProposalResponse {

    @JsonProperty
    private final UUID externalId;

    @JsonProperty
    private final LocalDateTime createdAt;

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
    private final CardResponse card;

    public ProposalResponse(UUID externalId, LocalDateTime createdAt, String document, String email, String name,
                            BigDecimal salary, ProposalStatus status, CardResponse card) {
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.document = document;
        this.email = email;
        this.name = name;
        this.salary = salary;
        this.status = status;
        this.card = card;
    }

    public static ProposalResponseBuilder builder() {
        return new ProposalResponseBuilder();
    }

    public static class ProposalResponseBuilder {

        private UUID externalId;
        private LocalDateTime createdAt;
        private String document;
        private String email;
        private String name;
        private BigDecimal salary;
        private ProposalStatus status;
        private CardResponse card;

        public ProposalResponseBuilder externalId(UUID externalId) {
            this.externalId = externalId;
            return this;
        }

        public ProposalResponseBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
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

        public ProposalResponseBuilder card(CardResponse card) {
            this.card = card;
            return this;
        }

        public ProposalResponse build() {
            return new ProposalResponse(externalId, createdAt, document, email, name, salary, status, card);
        }

    }

}
