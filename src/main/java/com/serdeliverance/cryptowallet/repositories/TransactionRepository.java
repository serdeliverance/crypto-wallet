package com.serdeliverance.cryptowallet.repositories;

import com.serdeliverance.cryptowallet.domain.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {
    // TODO
    public List<Transaction> getByUser(Integer userId) {
        return null;
    }
}
