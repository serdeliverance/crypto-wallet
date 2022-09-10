/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;

public record SellDTO(Integer userId, String cryptocurrency, BigDecimal amount) {}
