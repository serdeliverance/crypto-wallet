package com.serdeliverance.cryptowallet.clients;

import com.serdeliverance.cryptowallet.clients.response.CotizationResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
public class CoinmarketCapClient {

    private RestTemplate restTemplate;

    @Value("${coinmarketcap.api-key}")
    private String apiKey;

    @Value("${coinmarketcap.url}")
    private String url;

    public CotizationResponseDTO cotizations() {
        return restTemplate.getForObject(url, CotizationResponseDTO.class);
    }
}
