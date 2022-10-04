/* (C)2022 */
package com.serdeliverance.cryptowallet.dto;

import java.util.Optional;

public record UserDTO(Optional<Integer> id, String username, String email) {}
