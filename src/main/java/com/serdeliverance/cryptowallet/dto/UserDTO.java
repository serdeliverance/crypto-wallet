/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Optional<Integer> id;
    private String username;
    private String email;
}
