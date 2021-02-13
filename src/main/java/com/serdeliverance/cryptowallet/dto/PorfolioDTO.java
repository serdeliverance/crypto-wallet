package com.serdeliverance.cryptowallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PorfolioDTO {

    private Integer userId;
    private List<CurrencyTotalDTO> currencies;
    private BigDecimal totalInUSD;
    private LocalDateTime date;
}
