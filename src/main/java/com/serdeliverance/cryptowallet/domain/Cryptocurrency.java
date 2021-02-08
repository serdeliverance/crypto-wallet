package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cryptocurrency {
    private Long id;
    private String name;
    private String description;
    private String symbol;
}
