package br.com.zup.proposal.controller;

import br.com.zup.proposal.controller.request.TravelNotificationRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Requester;
import br.com.zup.proposal.model.TravelNotification;
import br.com.zup.proposal.repository.CardRepository;
import br.com.zup.proposal.repository.TravelNotificationRepository;
import br.com.zup.proposal.shared.ClientHostResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card/")
public class TravelNotificationController {

    private final CardRepository cardRepository;
    private final TravelNotificationRepository notificationRepository;

    @Autowired
    public TravelNotificationController(CardRepository cardRepository, TravelNotificationRepository notificationRepository) {
        this.cardRepository = cardRepository;
        this.notificationRepository = notificationRepository;
    }

    @PostMapping("/{id}/notification")
    @Transactional
    public ResponseEntity<?> createNewBiometry(@PathVariable UUID id, @RequestBody @Valid TravelNotificationRequest request,
                                               HttpServletRequest servletRequest, UriComponentsBuilder builder) {

        Card card = cardRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));

        if (card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("This card is blocked."));
        }

        ClientHostResolver clientHostResolver = new ClientHostResolver(servletRequest);

        String ipAddress = clientHostResolver.resolve();
        String userAgent = servletRequest.getHeader("User-Agent");

        Requester requester = new Requester(ipAddress, userAgent);

        TravelNotification travelNotification = new TravelNotification(requester,
                card, request.getDestiny(), request.getFinishTravelDate());

        notificationRepository.save(travelNotification);

        return ResponseEntity.ok().build();
    }

}
