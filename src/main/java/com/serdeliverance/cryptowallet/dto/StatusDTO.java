package com.serdeliverance.cryptowallet.dto;

public class StatusDTO {

    private static final String OK_MSG = "ok";

    private String status;


    public static StatusDTO ok() {
        return new StatusDTO(OK_MSG);
    }

    private StatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
