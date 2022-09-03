/* (C)2022 */
package com.serdeliverance.cryptowallet.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

  private Long id;
  private Integer userId;
  private Integer cryptocurrencyId;
  private BigDecimal amount;
  private OperationType operationType;
  private String transactionDate;
}
