/* (C)2022 */
package com.serdeliverance.cryptowallet.api;

import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.services.CryptocurrencyService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cryptocurrencies")
@RequiredArgsConstructor
@Slf4j
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    @GetMapping("/quotes")
    public List<CurrencyQuoteDTO> quotes() {
        log.info("Getting quotes");
        return cryptocurrencyService.quotes();
    }
}
