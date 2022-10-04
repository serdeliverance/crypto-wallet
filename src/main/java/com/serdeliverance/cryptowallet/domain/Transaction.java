/* (C)2022 */
package com.serdeliverance.cryptowallet.domain;

import java.math.BigDecimal;

public record Transaction(
    Long id,
    Integer userId,
    Integer cryptocurrencyId,
    BigDecimal amount,
    OperationType operationType,
    String transactionDate) {}
