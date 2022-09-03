/* (C)2022 */
package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CryptocurrencyRepository {

  private final JdbcTemplate jdbcTemplate;
  private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  private final RowMapper<Cryptocurrency> mapper =
      (rs, rowNum) ->
          new Cryptocurrency(rs.getInt("id"), rs.getString("name"), rs.getString("symbol"));

  public List<Cryptocurrency> getByIdList(List<Integer> ids) {
    SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
    return namedParameterJdbcTemplate.query(
        "SELECT ID, NAME, SYMBOL FROM CRYPTOCURRENCY WHERE ID IN (:ids)", parameters, mapper);
  }

  public Optional<Cryptocurrency> getByName(String name) {
    return jdbcTemplate
        .query("SELECT ID, NAME, SYMBOL FROM CRYPTOCURRENCY WHERE NAME = ?", mapper, name)
        .stream()
        .findFirst();
  }
}
