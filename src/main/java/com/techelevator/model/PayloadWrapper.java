package com.techelevator.model;

import java.util.List;

public class PayloadWrapper {
    List<Pizza> pizzas;
    Sale sale;

    public PayloadWrapper() {
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
