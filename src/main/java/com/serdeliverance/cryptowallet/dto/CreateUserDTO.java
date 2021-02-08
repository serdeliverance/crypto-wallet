package com.serdeliverance.cryptowallet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDTO {
    private Optional<Integer> id;
    private String username;
    private String password;
    private String email;
}
