package com.serdeliverance.cryptowallet.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Portfolio {
    private User user;
    private List<CurrencyTotal> currencies;
    private BigDecimal totalInUSD;
    private LocalDateTime date;

    /**
     * Default no argument constructor
     */
    public Portfolio() {
    }

    /**
     * Parameterized constructor
     *
     * @param user
     * @param currencies
     * @param totalInUSD
     * @param date
     */
    public Portfolio(User user, List<CurrencyTotal> currencies, BigDecimal totalInUSD, LocalDateTime date) {
        this.user = user;
        this.currencies = currencies;
        this.totalInUSD = totalInUSD;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CurrencyTotal> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<CurrencyTotal> currencies) {
        this.currencies = currencies;
    }

    public BigDecimal getTotalInUSD() {
        return totalInUSD;
    }

    public void setTotalInUSD(BigDecimal totalInUSD) {
        this.totalInUSD = totalInUSD;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
