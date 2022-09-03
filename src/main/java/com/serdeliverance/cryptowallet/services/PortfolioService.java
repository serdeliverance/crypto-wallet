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
import java.util.Map;
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
        List<Transaction> transactions = transactionRepository.getByUser(userId);
        return !transactions.isEmpty()
                ? buildPorfolio(userId, transactions)
                : emptyPortfolio(userId, LocalDateTime.now());
    }

    private PortfolioDTO buildPorfolio(Integer userId, List<Transaction> transactions) {
        log.debug("Building crypto portfolio");
        Map<String, BigDecimal> quotesInUSD =
                cryptocurrencyService.quotes().stream()
                        .collect(
                                Collectors.toMap(
                                        CurrencyQuoteDTO::getCrypto,
                                        CurrencyQuoteDTO::getQuoteInUsd));
        Map<Integer, String> cryptoMap =
                cryptocurrencyService
                        .getByIdList(
                                transactions.stream()
                                        .map(Transaction::getCryptocurrencyId)
                                        .distinct()
                                        .collect(Collectors.toList()))
                        .stream()
                        .collect(Collectors.toMap(Cryptocurrency::getId, Cryptocurrency::getName));
        List<CurrencyTotalDTO> currencies =
                transactions.stream()
                        .collect(groupingBy(Transaction::getCryptocurrencyId))
                        .entrySet()
                        .stream()
                        .map(
                                entry ->
                                        new CurrencyTotalDTO(
                                                cryptoMap.get(entry.getKey()),
                                                entry.getValue().stream()
                                                        .map(Transaction::getAmount)
                                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                        .collect(Collectors.toList());
        BigDecimal totalInUSD =
                currencies.stream()
                        .map(
                                crypto ->
                                        crypto.getAmount()
                                                .multiply(quotesInUSD.get(crypto.getCurrency())))
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
        BigDecimal currencyTotal =
                transactionRepository.getByUser(userId).stream()
                        .filter(
                                tx ->
                                        tx.getCryptocurrencyId()
                                                .equals(
                                                        cryptocurrencyService
                                                                .getByName(cryptocurrency)
                                                                .getId()))
                        .map(Transaction::getAmount)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (currencyTotal.compareTo(amount) < 0)
            throw new InvalidOperationException("Insufficient funds for transference/selling");
    }
}
