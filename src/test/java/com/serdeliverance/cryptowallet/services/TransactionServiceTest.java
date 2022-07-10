/* (C)2022 */
package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.exceptions.InvalidOperationException;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.serdeliverance.cryptowallet.domain.OperationType.BUY;
import static com.serdeliverance.cryptowallet.domain.OperationType.SELL;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock private UserService userService;

    @Mock private TransactionRepository transactionRepository;

    @Mock private CryptocurrencyService cryptocurrencyService;

    @Mock private PortfolioService portfolioService;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService =
                new TransactionService(
                        transactionRepository,
                        cryptocurrencyService,
                        userService,
                        portfolioService);
    }

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
    public void whenUserHasOneTransactionItShouldReturnTransactionHistory() {
        // given
        Integer userId = 1;

        when(transactionRepository.getByUser(userId))
                .thenReturn(
                        singletonList(
                                new Transaction(
                                        12L,
                                        1,
                                        1,
                                        BigDecimal.valueOf(2),
                                        BUY,
                                        "2021-02-05T19:28:43.111")));

        when(cryptocurrencyService.getByIdList(singletonList(1)))
                .thenReturn(List.of(new Cryptocurrency(1, "Bitcoin", "BTC")));

        // when
        List<TransactionDTO> result = transactionService.getHistory(userId);

        // then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getCryptocurrency()).isEqualTo("Bitcoin");
    }

    @Test
    public void whenUserTransactionsItShouldReturnTransactionHistory() {
        // given
        Integer userId = 1;

        when(transactionRepository.getByUser(userId))
                .thenReturn(
                        asList(
                                new Transaction(
                                        12L,
                                        1,
                                        1,
                                        BigDecimal.valueOf(2),
                                        BUY,
                                        "2021-02-05T19:28:43.111"),
                                new Transaction(
                                        13L,
                                        1,
                                        1,
                                        BigDecimal.valueOf(1),
                                        SELL,
                                        "2021-02-05T19:28:43.111"),
                                new Transaction(
                                        14L,
                                        1,
                                        2,
                                        BigDecimal.valueOf(1),
                                        BUY,
                                        "2021-02-05T19:28:43.111")));

        when(cryptocurrencyService.getByIdList(asList(1, 2)))
                .thenReturn(
                        asList(
                                new Cryptocurrency(1, "Bitcoin", "BTC"),
                                new Cryptocurrency(2, "Ethereum", "ETH")));

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
    public void whenUserAmountIsInvalidItShouldThrowInvalidaOperationException() {
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
