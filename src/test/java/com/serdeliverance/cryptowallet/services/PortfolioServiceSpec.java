package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceSpec {

    @Mock
    private UserService userService;

    @Mock
    private CryptocurrencyService cryptocurrencyService;

    @Mock
    private TransactionRepository transactionRepository;

    private PortfolioService portfolioService;

    @BeforeEach
    void setup() {
        portfolioService = new PortfolioService(userService, cryptocurrencyService, transactionRepository);
    }

    @Test
    void whenUserTransactionsIsEmptyItShouldReturnEmptyPorfolio() {
        // TODO
    }

    @Test
    void whenUserHasTransactionsItShouldReturnPorfolio() {
        // TODO
    }
}
