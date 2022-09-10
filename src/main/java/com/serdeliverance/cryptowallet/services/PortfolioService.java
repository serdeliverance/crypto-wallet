/* (C)2022 */
package com.serdeliverance.cryptowallet.services;

import static com.serdeliverance.cryptowallet.dto.PortfolioDTO.emptyPortfolio;
import static java.util.stream.Collectors.groupingBy;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyTotalDTO;
import com.serdeliverance.cryptowallet.dto.PortfolioDTO;
import com.serdeliverance.cryptowallet.exceptions.InvalidOperationException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {

  private final UserService userService;
  private final CryptocurrencyService cryptocurrencyService;
  private final TransactionRepository transactionRepository;

  public PortfolioDTO getPortfolio(Integer userId) {
    log.info("Getting portfolio for userId: {}", userId);
    userService.validateUser(userId);
    var transactions = transactionRepository.getByUser(userId);
    return !transactions.isEmpty()
        ? buildPorfolio(userId, transactions)
        : emptyPortfolio(userId, LocalDateTime.now());
  }

  private PortfolioDTO buildPorfolio(Integer userId, List<Transaction> transactions) {
    log.debug("Building crypto portfolio");
    var quotesInUSD =
        cryptocurrencyService.quotes().stream()
            .collect(Collectors.toMap(CurrencyQuoteDTO::crypto, CurrencyQuoteDTO::quoteInUsd));
    var cryptoMap =
        cryptocurrencyService
            .getByIdList(
                transactions.stream().map(Transaction::cryptocurrencyId).distinct().toList())
            .stream()
            .collect(Collectors.toMap(Cryptocurrency::id, Cryptocurrency::name));
    var currencies =
        transactions.stream().collect(groupingBy(Transaction::cryptocurrencyId)).entrySet().stream()
            .map(
                entry ->
                    new CurrencyTotalDTO(
                        cryptoMap.get(entry.getKey()),
                        entry.getValue().stream()
                            .map(Transaction::amount)
                            .reduce(BigDecimal.ZERO, BigDecimal::add)))
            .toList();
    var totalInUSD =
        currencies.stream()
            .map(crypto -> crypto.amount().multiply(quotesInUSD.get(crypto.currency())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    return new PortfolioDTO(userId, currencies, totalInUSD, LocalDateTime.now());
  }

  public void validateFunds(Integer userId, String cryptocurrency, BigDecimal amount) {
    log.info(
        "Validating funds for selling/transferring data. userId={}, cryptocurrency={},"
            + " amount={}",
        userId,
        cryptocurrency,
        amount);
    var currencyTotal =
        transactionRepository.getByUser(userId).stream()
            .filter(
                tx ->
                    tx.cryptocurrencyId()
                        .equals(cryptocurrencyService.getByName(cryptocurrency).id()))
            .map(Transaction::amount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    if (currencyTotal.compareTo(amount) < 0)
      throw new InvalidOperationException("Insufficient funds for transference/selling");
  }
}
