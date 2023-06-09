package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Pizza {
    int pizzaId;
    int saleId;
    int crustId;
    int sauceId;
    int sizeId;
    List<Topping> toppings;
    BigDecimal price;
    boolean squareCut;

    public Pizza() {
    }

    public Pizza(int pizzaId, int saleId, int crustId, int sauceId, int sizeId, List<Topping> toppings, BigDecimal price, boolean squareCut) {
        this.pizzaId = pizzaId;
        this.saleId = saleId;
        this.crustId = crustId;
        this.sauceId = sauceId;
        this.sizeId = sizeId;
        this.toppings = toppings;
        this.price = price;
        this.squareCut = squareCut;
    }

    public Pizza(int saleId, int crustId, int sauceId, int sizeId, List<Topping> toppings, BigDecimal price, boolean squareCut) {
        this.saleId = saleId;
        this.crustId = crustId;
        this.sauceId = sauceId;
        this.sizeId = sizeId;
        this.toppings = toppings;
        this.price = price;
        this.squareCut = squareCut;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isSquareCut() {
        return squareCut;
    }

    public void setSquareCut(boolean squareCut) {
        this.squareCut = squareCut;
    }
}
