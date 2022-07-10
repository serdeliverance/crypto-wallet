/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferenceDTO {
    private Integer issuer;
    private Integer receiver;
    private String cryptocurrency;
    private BigDecimal amount;
}
