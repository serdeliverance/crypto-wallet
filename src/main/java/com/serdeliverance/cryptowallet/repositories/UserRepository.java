/* (C)2022 */
package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private final RowMapper<User> userRowMapper =
      (rs, rowNum) ->
          new User(
              Optional.of(rs.getInt("ID")),
              rs.getString("USERNAME"),
              rs.getString("PASSWORD"),
              rs.getString("EMAIL"));

  public Optional<User> find(Integer id) {
    return jdbcTemplate
        .query(
            "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE ENABLED = TRUE AND" + " ID = ?",
            userRowMapper,
            id)
        .stream()
        .findFirst();
  }

  public List<User> getAll() {
    return jdbcTemplate.query(
        "SELECT ID, USERNAME, PASSWORD, EMAIL FROM USERS WHERE ENABLED = TRUE", userRowMapper);
  }

  public void save(User user) {
    jdbcTemplate.update(
        "INSERT INTO USERS(USERNAME, PASSWORD, EMAIL) VALUES(?, ?, ?)",
        user.username(),
        user.password(),
        user.email());
  }

  public void update(User user) {
    jdbcTemplate.update(
        "UPDATE USERS SET USERNAME = ?, PASSWORD = ?, EMAIL = ? WHERE ID = ?",
        user.username(),
        user.password(),
        user.email(),
        user.id().get()); // FIXME get() invocation
  }

  public void delete(Integer userId) {
    jdbcTemplate.update("UPDATE USERS SET ENABLED = FALSE WHERE ID = ?", userId);
  }
}
