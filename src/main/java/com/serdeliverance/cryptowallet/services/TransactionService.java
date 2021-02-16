package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static com.serdeliverance.cryptowallet.domain.OperationType.BUY;
import static com.serdeliverance.cryptowallet.domain.OperationType.TRANSFERENCE;
import static java.util.Collections.EMPTY_LIST;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CryptocurrencyService cryptocurrencyService;
    private final UserService userService;
    private final PortfolioService portfolioService;

    public List<TransactionDTO> getHistory(Integer userId) {
        log.info("Getting transaction history for userId: {}", userId);
        userService.validateUser(userId);
        List<Transaction> transactions = transactionRepository.getByUser(userId);
        return transactions.isEmpty() ? EMPTY_LIST : buildHistory(transactions);
    }

    private List<TransactionDTO> buildHistory(List<Transaction> transactions) {
        Map<Integer, String> cryptoMap = cryptocurrencyService
                .getByIdList(transactions.stream().map(Transaction::getCryptocurrencyId).distinct().collect(toList()))
                .stream()
                .collect(toMap(Cryptocurrency::getId, Cryptocurrency::getName));

        return transactions.stream()
                .map(tx -> new TransactionDTO(tx.getId(), cryptoMap.get(tx.getCryptocurrencyId()), tx.getAmount(), tx.getOperationType().name(), tx.getTransactionDate()))
                .collect(toList());
    }

    public void transfer(Integer issuer, Integer receiver, String cryptocurrency, BigDecimal amount) {
        userService.validateUser(issuer);
        userService.validateUser(receiver);
        portfolioService.validateTransference(issuer, cryptocurrency, amount);
        Integer cryptoCurrencyId = cryptocurrencyService.getByName(cryptocurrency).getId();
        transactionRepository.transfer(issuer, cryptoCurrencyId, amount.multiply(BigDecimal.valueOf(-1)), TRANSFERENCE, LocalDateTime.now());
        transactionRepository.transfer(receiver, cryptoCurrencyId, amount, BUY, LocalDateTime.now());
    }
}
