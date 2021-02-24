package br.com.zup.proposal.client;

import br.com.zup.proposal.client.request.ClientAssociateWalletRequest;
import br.com.zup.proposal.client.request.ClientBlockCardRequest;
import br.com.zup.proposal.client.request.ClientNotifyCardRequest;
import br.com.zup.proposal.client.response.ClientAssociateWalletResponse;
import br.com.zup.proposal.client.response.ClientBlockCardResponse;
import br.com.zup.proposal.client.response.ClientNewCardResponse;
import br.com.zup.proposal.client.response.ClientNotifyCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cardCreation", url = "${proposal.client.card-creation.url}")
public interface CardClient {

    @GetMapping("/api/cartoes")
    ClientNewCardResponse consult(@RequestParam("idProposta") String id);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    ClientBlockCardResponse block(@PathVariable String id, @RequestBody ClientBlockCardRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    ClientNotifyCardResponse notify(@PathVariable String id, @RequestBody ClientNotifyCardRequest request);

    @PostMapping("/api/cartoes/{id}/avisos")
    ClientAssociateWalletResponse associate(@PathVariable String id, @RequestBody ClientAssociateWalletRequest request);


}
