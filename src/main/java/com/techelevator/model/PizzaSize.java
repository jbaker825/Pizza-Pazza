package com.techelevator.model;

import java.math.BigDecimal;

public class PizzaSize {
    int pizzaSizeId;
    String sizeName;
    BigDecimal price;

    public PizzaSize() {

    }

    public PizzaSize(String sizeName, BigDecimal price) {
        this.sizeName = sizeName;
        this.price = price;
    }

    public int getPizzaSizeId() {
        return pizzaSizeId;
    }

    public void setPizzaSizeId(int pizzaSizeId) {
        this.pizzaSizeId = pizzaSizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
