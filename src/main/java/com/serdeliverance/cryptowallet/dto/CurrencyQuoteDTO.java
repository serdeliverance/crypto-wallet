/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyQuoteDTO {
    private String crypto;
    private BigDecimal quoteInUsd;
}
