package com.serdeliverance.cryptowallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {

    private Integer userId;
    private String cryptocurrency;

    @Min(value = 1, message = "Amount in usd must be valid")
    private BigDecimal amountInUsd;
}
