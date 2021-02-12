package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.services.CryptocurrencyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cryptocurrencies")
@Slf4j
@AllArgsConstructor
public class CryptocurrencyController {

    private CryptocurrencyService cryptocurrencyService;

    @GetMapping("/quotes")
    public List<CurrencyQuoteDTO> quotes() {
        log.info("Getting quotes");
        return cryptocurrencyService.getAll();
    }
}
