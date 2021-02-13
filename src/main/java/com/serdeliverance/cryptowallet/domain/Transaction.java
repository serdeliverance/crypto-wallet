package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long id;
    private Integer userId;
    private Integer cryptocurrencyId;
    private BigDecimal amount;
    private OperationType operationType;
    private String transactionDate;
}
