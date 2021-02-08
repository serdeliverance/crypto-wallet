package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Portfolio {
    private User user;
    private List<CurrencyTotal> currencies;
    private BigDecimal totalInUSD;
    private LocalDateTime date;
}
