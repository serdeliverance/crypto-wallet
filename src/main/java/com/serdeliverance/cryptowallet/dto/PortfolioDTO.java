/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record PortfolioDTO(
    Integer userId, List<CurrencyTotalDTO> currencies, BigDecimal totalInUSD, LocalDateTime date) {

  public static PortfolioDTO emptyPortfolio(Integer userId, LocalDateTime date) {
    return new PortfolioDTO(userId, Collections.emptyList(), BigDecimal.ZERO, date);
  }
}
