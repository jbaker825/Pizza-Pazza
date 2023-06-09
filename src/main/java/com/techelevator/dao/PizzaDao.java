package com.techelevator.dao;

import com.techelevator.model.Pizza;
import com.techelevator.model.SpecialtyPizza;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaDao {

    List<Pizza> getAllPizzas();
    Pizza getPizzaById(int pizzaId);
    int findIdByPizza(Pizza pizza);
    boolean createPizza(Pizza pizza);
    boolean updatePizza(Pizza pizza);
    boolean deletePizza(int pizzaId);
}
