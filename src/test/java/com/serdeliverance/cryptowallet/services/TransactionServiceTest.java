/* (C)2022 */
package com.serdeliverance.cryptowallet.services;

import static com.serdeliverance.cryptowallet.domain.OperationType.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.exceptions.InvalidOperationException;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

  @Mock private UserService userService;

  @Mock private TransactionRepository transactionRepository;

  @Mock private CryptocurrencyService cryptocurrencyService;

  @Mock private PortfolioService portfolioService;

  @InjectMocks private TransactionService transactionService;

  @Test
  public void whenUserNotExistsItShouldThrowResourceNotFoundException() {
    // given
    Integer userId = 12;

    doThrow(ResourceNotFoundException.class).when(userService).validateUser(userId);

    // then
    assertThrows(ResourceNotFoundException.class, () -> transactionService.getHistory(userId));
  }

  @Test
  public void whenUserHasNoTransactionItShouldReturnEmptyList() {
    // given
    Integer userId = 2;

    when(transactionRepository.getByUser(userId)).thenReturn(List.of());

    // when
    List<TransactionDTO> result = transactionService.getHistory(userId);

    // then
    assertThat(result).isEmpty();
  }

  @Test
  public void whenUserHasTransactionsItShouldReturnTransactionHistory() {
    // given
    Integer userId = 1;

    when(transactionRepository.getByUser(userId))
        .thenReturn(
            asList(
                new Transaction(
                    12L, 1, 1, BigDecimal.valueOf(2), DEPOSIT, "2021-02-05T19:28:43.111"),
                new Transaction(
                    13L, 1, 1, BigDecimal.valueOf(1), WITHDRAW, "2021-02-05T19:28:43.111"),
                new Transaction(
                    14L, 1, 2, BigDecimal.valueOf(1), DEPOSIT, "2021-02-05T19:28:43.111")));

    when(cryptocurrencyService.getByIdList(asList(1, 2)))
        .thenReturn(
            asList(
                new Cryptocurrency(1, "Bitcoin", "BTC"), new Cryptocurrency(2, "Ethereum", "ETH")));

    // when
    List<TransactionDTO> result = transactionService.getHistory(userId);

    // then
    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(3);
  }

  @Test
  public void whenTransferWithInvalidUserItShouldThrowInvalidOperationException() {
    // given
    doNothing().when(userService).validateUser(12);
    doThrow(ResourceNotFoundException.class).when(userService).validateUser(2);

    // when/then
    assertThrows(
        ResourceNotFoundException.class,
        () -> transactionService.transfer(12, 2, "Bitcoin", BigDecimal.ONE));
  }

  @Test
  public void whenUserHasNoFundsItShouldThrowInvalidaOperationException() {
    // given
    doThrow(InvalidOperationException.class)
        .when(portfolioService)
        .validateFunds(12, "Bitcoin", BigDecimal.ONE);

    // when/then
    assertThrows(
        InvalidOperationException.class,
        () -> transactionService.transfer(12, 2, "Bitcoin", BigDecimal.ONE));
  }

  @Test
  public void whenUserTransferItShouldTransferOk() {
    // given
    when(cryptocurrencyService.getByName("Bitcoin"))
        .thenReturn(new Cryptocurrency(1, "Bitcoin", "BTC"));

    // when/then
    transactionService.transfer(12, 2, "Bitcoin", BigDecimal.ONE);
  }
}
