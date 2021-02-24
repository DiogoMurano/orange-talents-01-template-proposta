package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.BiometryRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Biometry;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.repository.BiometryRepository;
import br.com.zup.proposal.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card/")
public class CreateBiometryController {

    private final BiometryRepository biometryRepository;
    private final CardRepository cardRepository;

    @Autowired
    public CreateBiometryController(BiometryRepository biometryRepository, CardRepository cardRepository) {
        this.biometryRepository = biometryRepository;
        this.cardRepository = cardRepository;
    }

    @PostMapping("/{id}/biometry")
    public ResponseEntity<?> createNewBiometry(@PathVariable UUID id, @Valid @RequestBody BiometryRequest request,
                                               UriComponentsBuilder builder) {

        Card card = cardRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));

        if (card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("This card is blocked."));
        }

        Biometry biometry = request.toModel(card);
        biometryRepository.save(biometry);

        URI location = builder.path("/api/v1/card/{cardId}/biometry/{biometryId}")
                .buildAndExpand(card.getExternalId().toString(), biometry.getExternalId().toString()).toUri();
        return ResponseEntity.created(location).build();
    }

}
