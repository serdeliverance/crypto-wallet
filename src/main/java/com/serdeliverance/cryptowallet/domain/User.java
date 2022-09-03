/* (C)2022 */
package com.serdeliverance.cryptowallet.domain;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Optional<Integer> id;
  private String username;
  private String password;
  private String email;
}
