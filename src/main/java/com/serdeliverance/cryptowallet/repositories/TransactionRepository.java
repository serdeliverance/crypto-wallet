package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.OperationType;
import com.serdeliverance.cryptowallet.domain.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

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

    public void saveTransaction(Integer userId, Integer cryptocurrencyId, BigDecimal amount, OperationType operationType, LocalDateTime transactionDate) {
        jdbcTemplate.update("INSERT INTO TRANSACTION(USER_ID, CRYPTO_CURRENCY_ID, AMOUNT, OPERATION_TYPE, TRANSACTION_DATE) VALUES(?, ?, ?, ?, ?)",
                userId, cryptocurrencyId, amount, operationType.name(), transactionDate.toString());
    }
}
