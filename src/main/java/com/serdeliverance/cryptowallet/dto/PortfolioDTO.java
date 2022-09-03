/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDTO {

    private Integer userId;
    private List<CurrencyTotalDTO> currencies;
    private BigDecimal totalInUSD;
    private LocalDateTime date;

    public static PortfolioDTO emptyPortfolio(Integer userId, LocalDateTime date) {
        return new PortfolioDTO(userId, Collections.emptyList(), BigDecimal.ZERO, date);
    }
}
