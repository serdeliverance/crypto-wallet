/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;

public record CurrencyQuoteDTO(String crypto, BigDecimal quoteInUsd) {}
