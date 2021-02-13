package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.PorfolioDTO;
import com.serdeliverance.cryptowallet.services.PortfolioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolios")
@Slf4j
public class PortfolioController {

    private PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public PorfolioDTO getPortfolio(Integer userId) {
        log.info("Getting crypto portfolio for user: {}", userId);
        return portfolioService.getPortfolio(userId);
    }
}
