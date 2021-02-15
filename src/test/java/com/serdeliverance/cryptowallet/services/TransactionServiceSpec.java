package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceSpec {

    @Mock
    private UserService userService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private CryptocurrencyService cryptocurrencyService;

    private TransactionService transactionService;

    @BeforeEach
    void setup() {
        transactionService = new TransactionService(transactionRepository, cryptocurrencyService, userService);
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

        when(transactionRepository.getByUser(userId)).thenReturn(EMPTY_LIST);

        // when
        List<TransactionDTO> result = transactionService.getHistory(userId);

        // then
        assert(result).isEmpty();
    }

    @Test
    public void whenUserTransactionsItShouldReturnTransactionHistory() {
        // TODO
        // given

        // when

        // then
    }
}
