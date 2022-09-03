/* (C)2022 */
package com.serdeliverance.cryptowallet.clients.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingQuotesResponseDTO {
  private List<ListingElementDTO> data;
}
