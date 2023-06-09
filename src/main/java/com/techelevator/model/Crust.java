package com.techelevator.model;

import java.math.BigDecimal;

public class Crust {
    int crustId;
    String crustName;
    BigDecimal price;

    public Crust() {
    }

    public Crust(String crustName, BigDecimal price) {
        this.crustName = crustName;
        this.price = price;
    }

    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public String getCrustName() {
        return crustName;
    }

    public void setCrustName(String crustName) {
        this.crustName = crustName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
