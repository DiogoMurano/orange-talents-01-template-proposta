package br.com.zup.proposal.client.response;

import br.com.zup.proposal.model.enums.ProposalStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FinancialAnalysisResponse {

    @JsonProperty
    private final String documento;

    @JsonProperty
    private final String nome;

    @JsonProperty
    private final Result resultadoSolicitacao;

    @JsonProperty
    private final String idProposta;

    public FinancialAnalysisResponse(String documento, String nome, Result resultadoSolicitacao, String idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Result getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public enum Result {
        COM_RESTRICAO(ProposalStatus.NOT_ELIGIBLE),
        SEM_RESTRICAO(ProposalStatus.ELIGIBLE);

        private final ProposalStatus status;

        Result(ProposalStatus status) {
            this.status = status;
        }

        public ProposalStatus getStatus() {
            return status;
        }
    }

}
