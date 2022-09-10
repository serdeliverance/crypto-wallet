/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

public record StatusDTO(String status) {

  private static final String OK_MSG = "ok";

  public static StatusDTO ok() {
    return new StatusDTO(OK_MSG);
  }
}
