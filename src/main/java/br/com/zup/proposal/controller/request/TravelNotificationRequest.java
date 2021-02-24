package br.com.zup.proposal.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TravelNotificationRequest {

    @JsonProperty
    @NotBlank
    private final String destiny;

    @JsonProperty
    @NotNull
    @Future
    private final LocalDate finishTravelDate;

    public TravelNotificationRequest(String destiny, LocalDate finishTravelDate) {
        this.destiny = destiny;
        this.finishTravelDate = finishTravelDate;
    }

    public String getDestiny() {
        return destiny;
    }

    public LocalDate getFinishTravelDate() {
        return finishTravelDate;
    }
}
