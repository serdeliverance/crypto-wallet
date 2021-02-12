package com.serdeliverance.cryptowallet.clients.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ListingElementDTO {
    private String name;
    private String symbol;
    private Map<String, QuoteDTO> quote;
}
