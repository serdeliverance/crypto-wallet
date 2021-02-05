package com.serdeliverance.cryptowallet.domain;

public class CurrencyTotal {
    private Cryptocurrency cryptocurrency;
    private Integer amount;

    /**
     * Default no argument constructor
     */
    public CurrencyTotal() {
    }

    /**
     * Parameterized constructor
     *
     * @param cryptocurrency
     * @param amount
     */
    public CurrencyTotal(Cryptocurrency cryptocurrency, Integer amount) {
        this.cryptocurrency = cryptocurrency;
        this.amount = amount;
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
