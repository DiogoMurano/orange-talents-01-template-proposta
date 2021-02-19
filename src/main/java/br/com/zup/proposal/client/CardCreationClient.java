package br.com.zup.proposal.client;

import br.com.zup.proposal.client.request.CreateCardRequest;
import br.com.zup.proposal.client.response.NewCardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "cardCreation", url = "${proposal.client.card-creation.url}")
public interface CardCreationClient {

    @PostMapping("/api/cartoes")
    NewCardResponse create(@RequestBody CreateCardRequest request);

}
