package br.com.zup.proposal.client;

import br.com.zup.proposal.client.response.NewCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cardCreation", url = "${proposal.client.financialanalysis.url}")
public interface CardCreationClient {

    @GetMapping("/api/cartoes")
    NewCardResponse consult(@RequestParam("idProposta") Long id);

}
