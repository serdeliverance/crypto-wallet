/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;

public record TransactionDTO(
    Long id, String cryptocurrency, BigDecimal amount, String operationType, String date) {}
