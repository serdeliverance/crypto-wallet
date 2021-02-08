package com.serdeliverance.cryptowallet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Optional<Integer> id;
    private String username;
    private String password;
    private String email;
}
