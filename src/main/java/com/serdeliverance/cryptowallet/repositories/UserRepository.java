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
        return jdbcTemplate.query(
                "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE ID = ?",
                (rs, rowNum) -> new User(Optional.of(rs.getInt("ID")), rs.getString("USERNAME"), rs.getString("PASSWORD"), rs.getString("EMAIL")),
                    id).stream().findFirst();
    }
}
