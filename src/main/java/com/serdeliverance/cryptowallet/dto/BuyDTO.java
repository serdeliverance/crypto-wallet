/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;

public record BuyDTO(
    Integer userId,
    String cryptocurrency,
    @Min(value = 1, message = "Amount in usd must be valid") BigDecimal amounInUsd) {}
