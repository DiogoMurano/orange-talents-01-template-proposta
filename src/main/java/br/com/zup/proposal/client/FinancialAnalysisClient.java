package br.com.zup.proposal.client;

import br.com.zup.proposal.client.request.ClientFinancialAnalysisRequest;
import br.com.zup.proposal.client.response.ClientFinancialAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "financialAnalysis", url = "${proposal.client.financialanalysis.url}")
public interface FinancialAnalysisClient {

    @PostMapping("/api/solicitacao")
    ClientFinancialAnalysisResponse consult(@RequestBody ClientFinancialAnalysisRequest request);

}
