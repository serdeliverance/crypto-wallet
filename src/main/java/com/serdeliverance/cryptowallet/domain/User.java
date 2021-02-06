package com.serdeliverance.cryptowallet.domain;

import java.util.Optional;

public class User {

    private Optional<Integer> id;
    private String username;
    private String password;
    private String email;

    /**
     * default not arguments contructor
     */
    public User() {}

    /**
     * Parameterized constructor
     *
     * @param id
     * @param username
     * @param password
     * @param email
     */
    public User(Optional<Integer> id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
