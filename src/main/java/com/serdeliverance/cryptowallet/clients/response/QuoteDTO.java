/* (C)2022 */
package com.serdeliverance.cryptowallet.clients.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDTO {
    private BigDecimal price;
}
