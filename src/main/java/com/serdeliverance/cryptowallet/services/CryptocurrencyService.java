package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.clients.CoinmarketCapClient;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.serdeliverance.cryptowallet.converters.CurrencyQuoteDTOConverter.convertFromResponse;

@Service
@AllArgsConstructor
@Slf4j
public class CryptocurrencyService {

    private CoinmarketCapClient coinmarketCapClient;

    public List<CurrencyQuoteDTO> getAll() {
        log.info("Getting cotizations");
        return convertFromResponse(coinmarketCapClient.cotizations());
    }
}
