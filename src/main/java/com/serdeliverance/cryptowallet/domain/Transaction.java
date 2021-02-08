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
    private User user;
    private Cryptocurrency cryptocurrency;
    private BigDecimal amount;
    private OperationType type;
}
