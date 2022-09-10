/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.math.BigDecimal;

public record TransferenceDTO(
    Integer issuer, Integer receiver, String cryptocurrency, BigDecimal amount) {}
