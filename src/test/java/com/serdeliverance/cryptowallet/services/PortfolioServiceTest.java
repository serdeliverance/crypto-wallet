/* (C)2022 */
package com.serdeliverance.cryptowallet.services;

import static com.serdeliverance.cryptowallet.domain.OperationType.*;
import static java.util.Arrays.asList;
import static java.util.Collections.EMPTY_LIST;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyTotalDTO;
import com.serdeliverance.cryptowallet.dto.PortfolioDTO;
import com.serdeliverance.cryptowallet.exceptions.InvalidOperationException;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

  @Mock private UserService userService;

  @Mock private CryptocurrencyService cryptocurrencyService;

  @Mock private TransactionRepository transactionRepository;

  private PortfolioService portfolioService;

  @BeforeEach
  void setup() {
    portfolioService =
        new PortfolioService(userService, cryptocurrencyService, transactionRepository);
  }

  @Test
  void whenHasNoTransactionItShouldReturnEmptyPorfolio() {
    // given
    Integer userId = 1;

    when(transactionRepository.getByUser(userId)).thenReturn(List.of());

    // when
    PortfolioDTO result = portfolioService.getPortfolio(userId);

    // then
    assertThat(result).isNotNull();
    assertThat(result.totalInUSD()).isEqualTo(BigDecimal.ZERO);
    assertThat(result.currencies()).isEqualTo(EMPTY_LIST);
  }

  @Test
  void whenUserHasTransactionsWithOneCurrencyItShouldReturnPorfolio() {
    // given
    Integer userId = 1;

    when(transactionRepository.getByUser(userId))
        .thenReturn(
            asList(
                new Transaction(
                    23L, 1, 1, BigDecimal.valueOf(2), DEPOSIT, "2021-02-05T19:29:03.239"),
                new Transaction(26L, 1, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239")));
    when(cryptocurrencyService.quotes())
        .thenReturn(
            asList(
                new CurrencyQuoteDTO("Bitcoin", BigDecimal.valueOf(48081.979491230726)),
                new CurrencyQuoteDTO("Ethereum", BigDecimal.valueOf(1810.264184795378)),
                new CurrencyQuoteDTO("Tether", BigDecimal.valueOf(1.00048112681234)),
                new CurrencyQuoteDTO("Cardano", BigDecimal.valueOf(0.87957104579375))));
    when(cryptocurrencyService.getByIdList(List.of(1)))
        .thenReturn(singletonList(new Cryptocurrency(1, "Bitcoin", "BTC")));

    // when
    PortfolioDTO result = portfolioService.getPortfolio(userId);

    // then
    BigDecimal expectedTotalInUsd =
        BigDecimal.valueOf(48081.979491230726).multiply(BigDecimal.valueOf(3));
    CurrencyTotalDTO expectedCurrency = new CurrencyTotalDTO("Bitcoin", BigDecimal.valueOf(3));

    assertThat(result).isNotNull();
    assertThat(result.userId()).isEqualTo(userId);
    assertThat(result.currencies().get(0)).isEqualTo(expectedCurrency);
    assertThat(result.totalInUSD()).isEqualTo(expectedTotalInUsd);
  }

  @Test
  void whenUserHasTransactionsWithMultipleCurrenciesItShouldReturnPorfolio() {
    // given
    Integer userId = 1;

    BigDecimal bitcoinQuote = BigDecimal.valueOf(48081.979491230726);
    BigDecimal ethereumQuote = BigDecimal.valueOf(1810.264184795378);
    BigDecimal tetherQuote = BigDecimal.valueOf(1.00048112681234);

    when(transactionRepository.getByUser(userId))
        .thenReturn(
            asList(
                new Transaction(
                    23L, 1, 1, BigDecimal.valueOf(2), DEPOSIT, "2021-02-05T19:29:03.239"),
                new Transaction(26L, 1, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239"),
                new Transaction(27L, 1, 2, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239"),
                new Transaction(
                    34L, 1, 3, BigDecimal.valueOf(2), DEPOSIT, "2021-02-06T19:29:03.239"),
                new Transaction(78L, 1, 2, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239")));
    when(cryptocurrencyService.quotes())
        .thenReturn(
            asList(
                new CurrencyQuoteDTO("Bitcoin", bitcoinQuote),
                new CurrencyQuoteDTO("Ethereum", ethereumQuote),
                new CurrencyQuoteDTO("Tether", tetherQuote),
                new CurrencyQuoteDTO("Cardano", BigDecimal.valueOf(0.87957104579375))));
    when(cryptocurrencyService.getByIdList(asList(1, 2, 3)))
        .thenReturn(
            asList(
                new Cryptocurrency(1, "Bitcoin", "BTC"),
                new Cryptocurrency(2, "Ethereum", "ETH"),
                new Cryptocurrency(3, "Tether", "TET")));

    // when
    PortfolioDTO result = portfolioService.getPortfolio(userId);

    CurrencyTotalDTO resultBitcoinTotal =
        result.currencies().stream()
            .filter(c -> c.currency().equals("Bitcoin"))
            .findFirst()
            .get(); // FIXME get() invocation
    CurrencyTotalDTO resultEthereumTotal =
        result.currencies().stream()
            .filter(c -> c.currency().equals("Ethereum"))
            .findFirst()
            .get(); // FIXME get() invocation
    CurrencyTotalDTO resultTetherTotal =
        result.currencies().stream()
            .filter(c -> c.currency().equals("Tether"))
            .findFirst()
            .get(); // FIXME get() invocation

    // then
    CurrencyTotalDTO expectedBitcoinTotal = new CurrencyTotalDTO("Bitcoin", BigDecimal.valueOf(3));
    CurrencyTotalDTO expectedEthereumTotal =
        new CurrencyTotalDTO("Ethereum", BigDecimal.valueOf(2));
    CurrencyTotalDTO expectedTetherTotal = new CurrencyTotalDTO("Tether", BigDecimal.valueOf(2));

    BigDecimal expectedBitcoinInUsd = expectedBitcoinTotal.amount().multiply(bitcoinQuote);
    BigDecimal expectedEthereumInUsd = expectedEthereumTotal.amount().multiply(ethereumQuote);
    BigDecimal expectedTetherInUsd = expectedTetherTotal.amount().multiply(tetherQuote);

    BigDecimal expectedTotalInUsd =
        expectedBitcoinInUsd.add(expectedEthereumInUsd).add(expectedTetherInUsd);

    assertThat(result).isNotNull();
    assertThat(resultBitcoinTotal).isEqualTo(expectedBitcoinTotal);
    assertThat(resultEthereumTotal).isEqualTo(expectedEthereumTotal);
    assertThat(resultTetherTotal).isEqualTo(expectedTetherTotal);
    assertThat(result.totalInUSD()).isEqualTo(expectedTotalInUsd);
  }

  @Test
  public void whenUserNotExistsItShouldThrowResourceNotFoundException() {
    // given
    Integer userId = 12;

    doThrow(ResourceNotFoundException.class).when(userService).validateUser(userId);

    // then
    assertThrows(ResourceNotFoundException.class, () -> portfolioService.getPortfolio(userId));
  }

  @Test
  public void whenUserHasNotEnoughFundsValidateShouldThrowException() {
    // given
    when(cryptocurrencyService.getByName("Bitcoin"))
        .thenReturn(new Cryptocurrency(1, "Bitcoin", "BTC"));
    when(transactionRepository.getByUser(2))
        .thenReturn(
            asList(
                new Transaction(23L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-05T19:29:03.239"),
                new Transaction(26L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239"),
                new Transaction(27L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239")));

    // when/then
    assertThrows(
        InvalidOperationException.class,
        () -> portfolioService.validateFunds(2, "Bitcoin", BigDecimal.valueOf(10)));
  }

  @Test
  public void whenUserHasFundsItShouldReturnVoid() {
    // given
    when(cryptocurrencyService.getByName("Bitcoin"))
        .thenReturn(new Cryptocurrency(1, "Bitcoin", "BTC"));
    when(transactionRepository.getByUser(2))
        .thenReturn(
            asList(
                new Transaction(23L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-05T19:29:03.239"),
                new Transaction(26L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239"),
                new Transaction(27L, 2, 1, BigDecimal.ONE, DEPOSIT, "2021-02-06T19:29:03.239")));

    // when/then
    portfolioService.validateFunds(2, "Bitcoin", BigDecimal.ONE);
  }
}
