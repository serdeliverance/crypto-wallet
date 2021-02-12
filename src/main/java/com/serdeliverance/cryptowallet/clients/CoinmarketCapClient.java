package com.serdeliverance.cryptowallet.clients;

import com.serdeliverance.cryptowallet.clients.response.CotizationResponseDTO;
import com.serdeliverance.cryptowallet.exceptions.RemoteApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
public class CoinmarketCapClient {

    private static final String API_KEY_HEADER = "X-CMC_PRO_API_KEY";

    private RestTemplate restTemplate;

    @Value("${coinmarketcap.api-key}")
    private String apiKey;

    @Value("${coinmarketcap.url}")
    private String url;

    public CotizationResponseDTO cotizations() {
        log.info("Getting cotizations from coinmarketcap");
        ResponseEntity<CotizationResponseDTO> response = restTemplate.exchange(url, HttpMethod.GET, createEntityWithHeader(API_KEY_HEADER, apiKey), CotizationResponseDTO.class);
        return handleResponse(response);
    }

    private CotizationResponseDTO handleResponse(ResponseEntity<CotizationResponseDTO> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Coinmarketcap responded with {} status code", response.getStatusCodeValue());
            throw new RemoteApiException("error communicating with coinmarketcap API");
        }
        return response.getBody();
    }

    private HttpEntity<String> createEntityWithHeader(String header, String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(header, value);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return entity;
    }
}
