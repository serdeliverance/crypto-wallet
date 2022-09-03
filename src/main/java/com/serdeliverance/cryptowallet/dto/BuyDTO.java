/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {

  private Integer userId;
  private String cryptocurrency;

  @Min(value = 1, message = "Amount in usd must be valid")
  private BigDecimal amountInUsd;
}
