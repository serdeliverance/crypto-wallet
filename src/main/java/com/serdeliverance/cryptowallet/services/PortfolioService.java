package com.serdeliverance.cryptowallet.services;

import com.serdeliverance.cryptowallet.domain.Cryptocurrency;
import com.serdeliverance.cryptowallet.domain.Transaction;
import com.serdeliverance.cryptowallet.dto.CurrencyQuoteDTO;
import com.serdeliverance.cryptowallet.dto.CurrencyTotalDTO;
import com.serdeliverance.cryptowallet.dto.PorfolioDTO;
import com.serdeliverance.cryptowallet.exceptions.ResourceNotFoundException;
import com.serdeliverance.cryptowallet.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
@AllArgsConstructor
public class PortfolioService {

    private UserService userService;
    private CryptocurrencyService cryptocurrencyService;
    private TransactionRepository transactionRepository;

    public PorfolioDTO getPortfolio(Integer userId) {
        log.info("Getting portfolio for userId: {}", userId);
        validateUser(userId);
        return buildPorfolio(userId, transactionRepository.getByUser(userId));
    }

    private PorfolioDTO buildPorfolio(Integer userId, List<Transaction> transactions) {
        log.debug("Building crypto portfolio");
        Map<String, BigDecimal> quotesMap = cryptocurrencyService.quotes().stream()
                .collect(Collectors.toMap(CurrencyQuoteDTO::getCrypto, crypto -> crypto.getQuoteInUsd()));
        Map<Integer, String> cryptoMap = cryptocurrencyService
                .getByIdList(transactions.stream().map(tx -> tx.getCryptocurrencyId()).distinct().collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(Cryptocurrency::getId, crypto -> crypto.getName()));
        List<CurrencyTotalDTO> currencies = transactions.stream()
                .collect(groupingBy(Transaction::getCryptocurrencyId))
                .entrySet().stream()
                .map(entry ->
                        new CurrencyTotalDTO(
                                cryptoMap.get(entry.getKey()),
                                entry.getValue().stream()
                                        .map(tx -> tx.getAmount())
                                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .collect(Collectors.toList());
        BigDecimal totalInUSD = currencies.stream().map(crypto -> crypto.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new PorfolioDTO(userId, currencies, totalInUSD, LocalDateTime.now());
    }

    private void validateUser(Integer userId) {
        log.info("Validating user {}", userId);
        userService.get(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }
}
