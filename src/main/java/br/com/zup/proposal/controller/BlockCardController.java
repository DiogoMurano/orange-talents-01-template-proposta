package br.com.zup.proposal.controller;

import br.com.zup.proposal.client.CardClient;
import br.com.zup.proposal.client.request.BlockCardRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Block;
import br.com.zup.proposal.model.Requester;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.repository.BlockRepository;
import br.com.zup.proposal.repository.CardRepository;
import br.com.zup.proposal.shared.ClientHostResolver;
import com.google.gson.Gson;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card/")
public class BlockCardController {

    private final static String APP_NAME = "proposal-api";

    private final CardRepository cardRepository;
    private final BlockRepository blockRepository;

    private final CardClient cardClient;

    private final Gson gson;

    @Autowired
    public BlockCardController(CardRepository cardRepository, BlockRepository blockRepository,
                               CardClient cardClient, Gson gson) {
        this.cardRepository = cardRepository;
        this.blockRepository = blockRepository;
        this.cardClient = cardClient;

        this.gson = gson;
    }

    @PostMapping("/{id}/block")
    @Transactional(timeout = 5000)
    public ResponseEntity<?> createNewBiometry(@PathVariable UUID id, HttpServletRequest request,
                                               UriComponentsBuilder builder) {
        Card card = cardRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));

        if (card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("This card is already blocked."));
        }

        ClientHostResolver clientHostResolver = new ClientHostResolver(request);

        String ipAddress = clientHostResolver.resolve();
        String userAgent = request.getHeader("User-Agent");

        try {
            cardClient.block(card.getNumber(), new BlockCardRequest(APP_NAME));
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "The card couldn't be blocked.");
        }

        Requester requester = new Requester(ipAddress, userAgent);
        Block block = new Block(requester, card);

        card.attachBlock(block);

        blockRepository.save(block);
        cardRepository.save(card);

        URI location = builder.path("/api/v1/card/{cardId}/block}")
                .buildAndExpand(card.getExternalId().toString(), block.getExternalId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
