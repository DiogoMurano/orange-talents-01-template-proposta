package br.com.zup.proposal.client;

import br.com.zup.proposal.client.request.BlockCardRequest;
import br.com.zup.proposal.client.response.BlockCardResponse;
import br.com.zup.proposal.client.response.NewCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cardCreation", url = "${proposal.client.card-creation.url}")
public interface CardClient {

    @GetMapping("/api/cartoes")
    NewCardResponse consult(@RequestParam("idProposta") String id);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    BlockCardResponse block(@PathVariable String id, @RequestBody BlockCardRequest request);


}
