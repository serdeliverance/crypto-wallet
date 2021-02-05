package com.serdeliverance.cryptowallet.dto;

import java.util.Optional;

public class UserDTO {
    private Optional<Integer> id;
    private String username;
    private String email;

    public UserDTO() {
    }

    public UserDTO(Optional<Integer> id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Optional<Integer> getId() {
        return id;
    }

    public void setId(Optional<Integer> id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
