/* (C)2022 */
package com.serdeliverance.cryptowallet.clients;

import com.serdeliverance.cryptowallet.clients.response.ListingQuoteDTO;
import com.serdeliverance.cryptowallet.exceptions.RemoteApiException;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoinApiClient {

    private static final String API_KEY_HEADER = "X-CoinAPI-Key";

    private final RestTemplate restTemplate;

    @Value("${coinapi.api-key}")
    private String apiKey;

    @Value("${coinapi.url}")
    private String baseUrl;

    public List<ListingQuoteDTO> quotes() {
        log.info("Getting quotes from coinmarketcap");
        ResponseEntity<ListingQuoteDTO[]> response =
                restTemplate.exchange(
                        String.format("%s/v1/quotes/latest", baseUrl),
                        HttpMethod.GET,
                        addApiKeyHeader(apiKey),
                        ListingQuoteDTO[].class);
        return handleResponse(response);
    }

    private List<ListingQuoteDTO> handleResponse(ResponseEntity<ListingQuoteDTO[]> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Coinmarketcap responded with {} status code", response.getStatusCodeValue());
            throw new RemoteApiException("error communicating with coinmarketcap API");
        }
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    private HttpEntity<String> addApiKeyHeader(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(API_KEY_HEADER, value);
        return new HttpEntity<>(null, headers);
    }
}
