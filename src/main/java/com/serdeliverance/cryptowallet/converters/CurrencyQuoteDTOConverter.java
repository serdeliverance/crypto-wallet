/* (C)2022 */
package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.clients.response.ListingQuotesResponseDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyQuoteDTOConverter {

  private static final String USD_CURRENCY = "USD";

  public static List<CurrencyQuoteDTO> convertFromResponse(ListingQuotesResponseDTO response) {
    return response.getData().stream()
        .map(
            elem ->
                new CurrencyQuoteDTO(elem.getName(), elem.getQuote().get(USD_CURRENCY).getPrice()))
        .collect(Collectors.toList());
  }
}
