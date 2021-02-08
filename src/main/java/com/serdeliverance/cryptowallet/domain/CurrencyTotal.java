package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyTotal {
    private Cryptocurrency cryptocurrency;
    private Integer amount;
}
