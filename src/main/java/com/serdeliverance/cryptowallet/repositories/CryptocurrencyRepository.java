package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CryptocurrencyRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Cryptocurrency> getByIdList(List<Integer> ids) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", ids);
        return namedParameterJdbcTemplate
                .query("SELECT ID, NAME, SYMBOL FROM CRYPTOCURRENCY WHERE ID IN (:ids)",
                        parameters,
                        (rs, rowNum) -> new Cryptocurrency(rs.getInt("id"), rs.getString("name"), rs.getString("symbol")));
    }
}
