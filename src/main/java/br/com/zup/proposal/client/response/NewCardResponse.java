package br.com.zup.proposal.client.response;

import java.time.LocalDateTime;

public class NewCardResponse {

    private final String id;
    private final LocalDateTime emitidoEm;

    public NewCardResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }
}
