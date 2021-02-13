package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.OperationType;
import com.serdeliverance.cryptowallet.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@AllArgsConstructor
public class TransactionRepository {

    private JdbcTemplate jdbcTemplate;

    public List<Transaction> getByUser(Integer userId) {

        return jdbcTemplate.query("SELECT ID, USER_ID, CRYPTO_CURRENCY_ID, AMOUNT, OPERATION_TYPE, TRANSACTION_DATE FROM TRANSACTION WHERE USER_ID = ?",
                (rs, rowNum) ->
                        new Transaction(
                                rs.getLong("id"),
                                rs.getInt("user_id"),
                                rs.getInt("crypto_currency_id"),
                                rs.getBigDecimal("amount"),
                                OperationType.valueOf(rs.getString("operation_type")),
                                rs.getString("transaction_date")),
                userId);
    }
}
