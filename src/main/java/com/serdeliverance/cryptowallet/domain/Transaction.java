package com.serdeliverance.cryptowallet.domain;

import java.math.BigDecimal;

public class Transaction {

    private Long id;
    private User user;
    private Cryptocurrency cryptocurrency;
    private BigDecimal amount;
    private OperationType type;

    /**
     * Default no arguments constructor
     */
    public Transaction() {
    }

    /**
     * parameterized constructor
     *
     * @param id
     * @param user
     * @param cryptocurrency
     * @param amount
     * @param type
     */
    public Transaction(Long id, User user, Cryptocurrency cryptocurrency, BigDecimal amount, OperationType type) {
        this.id = id;
        this.user = user;
        this.cryptocurrency = cryptocurrency;
        this.amount = amount;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}
