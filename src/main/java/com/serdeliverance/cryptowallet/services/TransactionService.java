/* (C)2022 */
package com.serdeliverance.cryptowallet.services;

import static com.serdeliverance.cryptowallet.domain.OperationType.*;
import static java.util.stream.Collectors.toMap;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.TransactionDTO;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    var transactions = transactionRepository.getByUser(userId);
    return transactions.isEmpty() ? List.of() : buildHistory(transactions);
  }

  private List<TransactionDTO> buildHistory(List<Transaction> transactions) {
    var cryptoMap =
        cryptocurrencyService
            .getByIdList(
                transactions.stream().map(Transaction::cryptocurrencyId).distinct().toList())
            .stream()
            .collect(toMap(Cryptocurrency::id, Cryptocurrency::name));

    return transactions.stream()
        .map(
            tx ->
                new TransactionDTO(
                    tx.id(),
                    cryptoMap.get(tx.cryptocurrencyId()),
                    tx.amount(),
                    tx.operationType().name(),
                    tx.transactionDate()))
        .toList();
  }

  @Transactional
  public void transfer(Integer issuer, Integer receiver, String cryptocurrency, BigDecimal amount) {
    log.info(
        "Transferring {} {} from user: {} to user: {}", amount, cryptocurrency, issuer, receiver);
    userService.validateUser(issuer);
    userService.validateUser(receiver);
    portfolioService.validateFunds(issuer, cryptocurrency, amount);
    Integer cryptoCurrencyId = cryptocurrencyService.getByName(cryptocurrency).id();
    transactionRepository.saveTransaction(
        issuer,
        cryptoCurrencyId,
        amount.multiply(BigDecimal.valueOf(-1)),
        WITHDRAW,
        LocalDateTime.now());
    transactionRepository.saveTransaction(
        receiver, cryptoCurrencyId, amount, DEPOSIT, LocalDateTime.now());
  }

  public void buy(Integer userId, String cryptocurrency, BigDecimal amountInUsd) {
    log.info(
        "Buying {} for an amount of {} dollars by user: {}", cryptocurrency, amountInUsd, userId);
    userService.validateUser(userId);
    Integer cryptoCurrencyId = cryptocurrencyService.getByName(cryptocurrency).id();
    BigDecimal quote = cryptocurrencyService.getQuote(cryptocurrency).quoteInUsd();
    transactionRepository.saveTransaction(
        userId,
        cryptoCurrencyId,
        amountInUsd.divide(quote, 10, RoundingMode.CEILING),
        WITHDRAW,
        LocalDateTime.now());
  }

  public void sell(Integer userId, String cryptocurrency, BigDecimal amount) {
    log.info("Selling {} {} by user: {}", amount, cryptocurrency, userId);
    userService.validateUser(userId);
    portfolioService.validateFunds(userId, cryptocurrency, amount);
    transactionRepository.saveTransaction(
        userId,
        cryptocurrencyService.getByName(cryptocurrency).id(),
        amount.multiply(BigDecimal.valueOf(-1)),
        SELL,
        LocalDateTime.now());
  }
}
