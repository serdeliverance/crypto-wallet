/* (C)2022 */
package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.PortfolioDTO;
import com.serdeliverance.cryptowallet.services.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolios")
@RequiredArgsConstructor
@Slf4j
public class PortfolioController {

  private final PortfolioService portfolioService;

  @GetMapping("/{userId}")
  public PortfolioDTO getPortfolio(@PathVariable("userId") Integer userId) {
    log.info("Getting crypto portfolio for user: {}", userId);
    return portfolioService.getPortfolio(userId);
  }
}
