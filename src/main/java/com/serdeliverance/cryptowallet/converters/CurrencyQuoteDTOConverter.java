/* (C)2022 */
package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.clients.response.ListingQuotesResponseDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import java.util.List;

public class CurrencyQuoteDTOConverter {

  private static final String USD_CURRENCY = "USD";

  public static List<CurrencyQuoteDTO> convertFromResponse(ListingQuotesResponseDTO response) {
    return response.data().stream()
        .map(elem -> new CurrencyQuoteDTO(elem.name(), elem.quote().get(USD_CURRENCY).price()))
        .toList();
  }
}
