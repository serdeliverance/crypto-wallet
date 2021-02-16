package com.serdeliverance.cryptowallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusDTO {

    private static final String OK_MSG = "ok";

    private String status;

    public static StatusDTO ok() {
        return new StatusDTO(OK_MSG);
    }
}
