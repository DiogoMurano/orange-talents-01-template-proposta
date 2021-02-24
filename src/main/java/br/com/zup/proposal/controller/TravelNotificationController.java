package br.com.zup.proposal.controller;

import br.com.zup.proposal.client.CardClient;
import br.com.zup.proposal.client.request.ClientNotifyCardRequest;
import br.com.zup.proposal.controller.request.TravelNotificationRequest;
import br.com.zup.proposal.controller.response.ErrorResponse;
import br.com.zup.proposal.model.Card;
import br.com.zup.proposal.model.Requester;
import br.com.zup.proposal.model.TravelNotification;
import br.com.zup.proposal.repository.CardRepository;
import br.com.zup.proposal.repository.TravelNotificationRepository;
import br.com.zup.proposal.shared.ClientHostResolver;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/card/")
public class TravelNotificationController {

    private final CardRepository cardRepository;
    private final TravelNotificationRepository notificationRepository;

    private final CardClient cardClient;

    @Autowired
    public TravelNotificationController(CardRepository cardRepository,
                                        TravelNotificationRepository notificationRepository, CardClient cardClient) {
        this.cardRepository = cardRepository;
        this.notificationRepository = notificationRepository;
        this.cardClient = cardClient;
    }

    @PostMapping("/{id}/notification")
    @Transactional(timeout = 5000)
    public ResponseEntity<?> sendNewNotification(@PathVariable UUID id, @RequestBody @Valid TravelNotificationRequest request,
                                               HttpServletRequest servletRequest) {
        Card card = cardRepository.findByExternalId(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found."));

        if (card.isBlocked()) {
            return ResponseEntity.unprocessableEntity().body(new ErrorResponse("This card is blocked."));
        }

        ClientHostResolver clientHostResolver = new ClientHostResolver(servletRequest);
        String ipAddress = clientHostResolver.resolve();
        String userAgent = servletRequest.getHeader("User-Agent");

        try {
            cardClient.notify(card.getNumber(), new ClientNotifyCardRequest(request.getDestiny(), request.getFinishTravelDate()));
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "An error occurred while sending the notification.");
        }

        Requester requester = new Requester(ipAddress, userAgent);
        TravelNotification travelNotification = new TravelNotification(requester,
                card, request.getDestiny(), request.getFinishTravelDate());

        notificationRepository.save(travelNotification);

        return ResponseEntity.ok().build();
    }

}
