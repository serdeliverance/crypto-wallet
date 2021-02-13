package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.clients.CoinmarketCapClient;
import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.repositories.CryptocurrencyRepository;
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
    private CryptocurrencyRepository cryptocurrencyRepository;

    public List<CurrencyQuoteDTO> quotes() {
        log.info("Getting quotes");
        return convertFromResponse(coinmarketCapClient.quotes());
    }

    public List<Cryptocurrency> getByIdList(List<Integer> ids) {
        log.info("Getting all cryptocurrencies with ids: {}", ids);
        return cryptocurrencyRepository.getByIdList(ids);
    }
}
