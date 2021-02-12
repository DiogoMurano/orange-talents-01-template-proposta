package br.com.zup.proposal.client;

import br.com.zup.proposal.client.request.FinancialAnalysisRequest;
import br.com.zup.proposal.client.response.FinancialAnalysisResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "financialAnalysis", url = "http://localhost:9999")
public interface FinancialAnalysisClient {

    @PostMapping("/api/solicitacao")
    FinancialAnalysisResponse consult(@RequestBody FinancialAnalysisRequest request);

}
