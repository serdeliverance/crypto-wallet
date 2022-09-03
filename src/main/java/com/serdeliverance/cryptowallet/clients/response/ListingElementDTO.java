/* (C)2022 */
package com.serdeliverance.cryptowallet.clients.response;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingElementDTO {
  private String name;
  private String symbol;
  private Map<String, QuoteDTO> quote;
}
