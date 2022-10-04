/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.util.Optional;

public record UpdateUserDTO(Optional<Integer> id, String username, String password, String email) {}
