package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<User> find(Integer id) {
        String query = "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE ID = ?";
        User user = jdbcTemplate.queryForObject(
                query, User.class, id);

        return Optional.ofNullable(user);
    }
}
