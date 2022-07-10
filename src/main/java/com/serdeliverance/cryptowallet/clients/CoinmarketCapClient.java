/* (C)2022 */
package com.serdeliverance.cryptowallet.clients;

import com.serdeliverance.cryptowallet.clients.response.ListingQuotesResponseDTO;
import com.serdeliverance.cryptowallet.exceptions.RemoteApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoinmarketCapClient {

    private static final String API_KEY_HEADER = "X-CMC_PRO_API_KEY";

    private final RestTemplate restTemplate;

    @Value("${coinmarketcap.api-key}")
    private String apiKey;

    @Value("${coinmarketcap.url}")
    private String url;

    public ListingQuotesResponseDTO quotes() {
        log.info("Getting quotes from coinmarketcap");
        ResponseEntity<ListingQuotesResponseDTO> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        addApiKeyHeader(apiKey),
                        ListingQuotesResponseDTO.class);
        return handleResponse(response);
    }

    private ListingQuotesResponseDTO handleResponse(
            ResponseEntity<ListingQuotesResponseDTO> response) {
        if (response.getStatusCode() != HttpStatus.OK) {
            log.error("Coinmarketcap responded with {} status code", response.getStatusCodeValue());
            throw new RemoteApiException("error communicating with coinmarketcap API");
        }
        return response.getBody();
    }

    private HttpEntity<String> addApiKeyHeader(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(API_KEY_HEADER, value);
        return new HttpEntity<>(null, headers);
    }
}
