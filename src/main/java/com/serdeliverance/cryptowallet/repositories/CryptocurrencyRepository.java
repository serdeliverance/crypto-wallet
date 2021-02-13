package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class CryptocurrencyRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CryptocurrencyRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private RowMapper<Cryptocurrency> cryptocurrencyRowMapper =
            (rs, rowNum) -> new Cryptocurrency(rs.getInt("id"), rs.getString("name"), rs.getString("symbol"));

    public List<Cryptocurrency> getByIdList(List<Integer> ids) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate
                .query("SELECT ID, NAME, SYMBOL FROM CRYPTOCURRENCY WHERE ID IN (:ids)",
                        parameters, cryptocurrencyRowMapper);
    }
}
