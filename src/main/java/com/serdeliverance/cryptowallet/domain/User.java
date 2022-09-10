/* (C)2022 */
package com.serdeliverance.cryptowallet.domain;

import java.util.Optional;

public record User(Optional<Integer> id, String username, String password, String email) {}
