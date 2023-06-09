package com.techelevator.model;

import java.math.BigDecimal;

public class Sauce {
    int sauceId;
    String sauceName;
    BigDecimal price;

    public Sauce() {
    }

    public Sauce(String sauceName, BigDecimal price) {
        this.sauceName = sauceName;
        this.price = price;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
