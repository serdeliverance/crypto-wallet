package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.domain.User;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyTotalDTO;
import com.serdeliverance.cryptowallet.dto.PorfolioDTO;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.serdeliverance.cryptowallet.domain.OperationType.BUY;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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
    void whenHasNoTransactionItShouldReturnEmptyPorfolio() {
        // given
        Integer userId = 1;

        when(userService.get(userId)).thenReturn(
            Optional.of(new User(Optional.of(1), "pepe", "pass1234", "pepe@gmail.com"))
        );

        when(transactionRepository.getByUser(userId)).thenReturn(EMPTY_LIST);

        // when
        PorfolioDTO result = portfolioService.getPortfolio(userId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getTotalInUSD()).isEqualTo(BigDecimal.ZERO);
        assertThat(result.getCurrencies()).isEqualTo(EMPTY_LIST);
    }

    @Test
    void whenUserHasTransactionsWithOneCurrencyItShouldReturnPorfolio() {
        // given
        Integer userId = 1;

        when(userService.get(userId)).thenReturn(
            Optional.of(new User(Optional.of(1), "pepe", "pass1234", "pepe@gmail.com"))
        );

        when(transactionRepository.getByUser(userId)).thenReturn(asList(
            new Transaction(23L, 1, 1, BigDecimal.valueOf(2), BUY, "2021-02-05T19:29:03.239"),
            new Transaction(26L, 1, 1, BigDecimal.ONE, BUY, "2021-02-06T19:29:03.239"))
        );
        when(cryptocurrencyService.quotes()).thenReturn(asList(
            new CurrencyQuoteDTO("Bitcoin", BigDecimal.valueOf(48081.979491230726)),
            new CurrencyQuoteDTO("Ethereum", BigDecimal.valueOf(1810.264184795378)),
            new CurrencyQuoteDTO("Tether", BigDecimal.valueOf(1.00048112681234)),
            new CurrencyQuoteDTO("Cardano", BigDecimal.valueOf(0.87957104579375)))
        );
        when(cryptocurrencyService.getByIdList(asList(1))).thenReturn(singletonList(new Cryptocurrency(1, "Bitcoin", "BTC")));

        // when
        PorfolioDTO result = portfolioService.getPortfolio(userId);

        // then
        BigDecimal expectedTotalInUsd = BigDecimal.valueOf(48081.979491230726).multiply(BigDecimal.valueOf(3));
        CurrencyTotalDTO expectedCurrency = new CurrencyTotalDTO("Bitcoin", BigDecimal.valueOf(3));

        assertThat(result).isNotNull();
        assertThat(result.getUserId()).isEqualTo(userId);
        assertThat(result.getCurrencies().get(0)).isEqualTo(expectedCurrency);
        assertThat(result.getTotalInUSD()).isEqualTo(expectedTotalInUsd);
    }

    @Test
    void whenUserHasTransactionsWithMultipleCurrenciesItShouldReturnPorfolio() {
        // given
        Integer userId = 1;

        BigDecimal bitcoinQuote = BigDecimal.valueOf(48081.979491230726);
        BigDecimal ethereumQuote = BigDecimal.valueOf(1810.264184795378);
        BigDecimal tetherQuote = BigDecimal.valueOf(1.00048112681234);

        when(userService.get(userId)).thenReturn(
            Optional.of(new User(Optional.of(1), "pepe", "pass1234", "pepe@gmail.com"))
        );

        when(transactionRepository.getByUser(userId)).thenReturn(asList(
            new Transaction(23L, 1, 1, BigDecimal.valueOf(2), BUY, "2021-02-05T19:29:03.239"),
            new Transaction(26L, 1, 1, BigDecimal.ONE, BUY, "2021-02-06T19:29:03.239"),
            new Transaction(27L, 1, 2, BigDecimal.ONE, BUY, "2021-02-06T19:29:03.239"),
            new Transaction(34L, 1, 3, BigDecimal.valueOf(2), BUY, "2021-02-06T19:29:03.239"),
            new Transaction(78L, 1, 2, BigDecimal.ONE, BUY, "2021-02-06T19:29:03.239"))
        );
        when(cryptocurrencyService.quotes()).thenReturn(asList(
            new CurrencyQuoteDTO("Bitcoin", bitcoinQuote),
            new CurrencyQuoteDTO("Ethereum", ethereumQuote),
            new CurrencyQuoteDTO("Tether", tetherQuote),
            new CurrencyQuoteDTO("Cardano", BigDecimal.valueOf(0.87957104579375)))
        );
        when(cryptocurrencyService.getByIdList(asList(1, 2, 3))).thenReturn(asList(
            new Cryptocurrency(1, "Bitcoin", "BTC"),
            new Cryptocurrency(2, "Ethereum", "ETH"),
            new Cryptocurrency(3, "Tether", "TET"))
        );

        // when
        PorfolioDTO result = portfolioService.getPortfolio(userId);

        CurrencyTotalDTO resultBitcoinTotal = result.getCurrencies().stream().filter(c -> c.getCurrency().equals("Bitcoin")).findFirst().get();
        CurrencyTotalDTO resultEthereumTotal = result.getCurrencies().stream().filter(c -> c.getCurrency().equals("Ethereum")).findFirst().get();
        CurrencyTotalDTO resultTetherTotal = result.getCurrencies().stream().filter(c -> c.getCurrency().equals("Tether")).findFirst().get();

        // then
        CurrencyTotalDTO expectedBitcoinTotal = new CurrencyTotalDTO("Bitcoin", BigDecimal.valueOf(3));
        CurrencyTotalDTO expectedEthereumTotal = new CurrencyTotalDTO("Ethereum", BigDecimal.valueOf(2));
        CurrencyTotalDTO expectedTetherTotal = new CurrencyTotalDTO("Tether", BigDecimal.valueOf(2));

        BigDecimal expectedBitcoinInUsd = expectedBitcoinTotal.getAmount().multiply(bitcoinQuote);
        BigDecimal expectedEthereumInUsd = expectedEthereumTotal.getAmount().multiply(ethereumQuote);
        BigDecimal expectedTetherInUsd = expectedTetherTotal.getAmount().multiply(tetherQuote);

        BigDecimal expectedTotalInUsd = expectedBitcoinInUsd.add(expectedEthereumInUsd).add(expectedTetherInUsd);

        assertThat(result).isNotNull();
        assertThat(resultBitcoinTotal).isEqualTo(expectedBitcoinTotal);
        assertThat(resultEthereumTotal).isEqualTo(expectedEthereumTotal);
        assertThat(resultTetherTotal).isEqualTo(expectedTetherTotal);
        assertThat(result.getTotalInUSD()).isEqualTo(expectedTotalInUsd);
    }
}
