package br.com.zup.proposal.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Requester {

    @NotBlank
    private String ipAddress;

    @NotBlank
    private String userAgent;

    public Requester(String ipAddress, String userAgent) {
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    @Deprecated
    public Requester() {
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
