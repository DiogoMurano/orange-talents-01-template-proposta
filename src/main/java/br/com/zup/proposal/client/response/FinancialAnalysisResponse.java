package br.com.zup.proposal.client.response;

import br.com.zup.proposal.model.enums.ProposalStatus;

public class FinancialAnalysisResponse {

    private final String documento;
    private final String nome;
    private final Result resultadoSolicitacao;
    private final Long idProposta;

    public FinancialAnalysisResponse(String documento, String nome, Result resultadoSolicitacao, Long idProposta) {
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

    public Long getIdProposta() {
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
