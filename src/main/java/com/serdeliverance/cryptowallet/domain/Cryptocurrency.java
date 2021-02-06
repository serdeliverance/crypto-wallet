package com.serdeliverance.cryptowallet.domain;

public class Cryptocurrency {
    private Long id;
    private String name;
    private String description;
    private String symbol;

    /**
     * Default no argument constructor
     */
    public Cryptocurrency() {
    }

    /**
     * Parameterized constructor
     *
     * @param id
     * @param name
     * @param description
     * @param symbol
     */
    public Cryptocurrency(Long id, String name, String description, String symbol) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.symbol = symbol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
