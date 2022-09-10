/* (C)2022 */
package com.serdeliverance.cryptowallet.clients.response;

import java.util.Map;

public record ListingElementDTO(String name, String symbol, Map<String, QuoteDTO> quote) {}
