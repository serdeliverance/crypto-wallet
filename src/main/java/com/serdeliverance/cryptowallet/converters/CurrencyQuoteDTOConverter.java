/* (C)2022 */
package com.serdeliverance.cryptowallet.converters;

import com.serdeliverance.cryptowallet.clients.response.ListingQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import java.util.List;
import java.util.stream.Collectors;

public class CurrencyQuoteDTOConverter {

    public static List<CurrencyQuoteDTO> convertFromResponse(List<ListingQuoteDTO> response) {
        return response.stream()
                .map(elem -> new CurrencyQuoteDTO(elem.getSymbolId(), elem.getAskPrice()))
                .collect(Collectors.toList());
    }
}
