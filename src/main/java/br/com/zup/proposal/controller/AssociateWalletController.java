package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.AssociateWalletRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Wallet;
import br.com.zup.proposal.repository.CardRepository;
import br.com.zup.proposal.repository.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card/")
public class AssociateWalletController {

    private CardRepository cardRepository;
    private WalletRepository walletRepository;

    @PostMapping("/{id}/wallet")
    @Transactional(timeout = 5000)
    public ResponseEntity<?> createNewBiometry(@PathVariable UUID id, AssociateWalletRequest request,
                                               UriComponentsBuilder builder) {

        Card card = cardRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));

        if(card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("This card is blocked."));
        }

        if (walletRepository.existsByCardAndWallet(card, request.getWallet())) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("Card already associated with this wallet."));
        }

        Wallet wallet = new Wallet(card, request.getEmail(), request.getWallet());
        walletRepository.save(wallet);

        URI location = builder.path("/api/v1/card/{cardId}/wallet/{walletId}")
                .buildAndExpand(card.getExternalId(), wallet.getExternalId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
