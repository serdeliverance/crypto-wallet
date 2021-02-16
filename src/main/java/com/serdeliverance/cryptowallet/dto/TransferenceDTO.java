package com.serdeliverance.cryptowallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenceDTO {
    private Integer issuer;
    private Integer receiver;
    private String cryptocurrency;
    private BigDecimal amount;
}
