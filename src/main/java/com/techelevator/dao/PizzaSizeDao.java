package com.techelevator.dao;

import com.techelevator.model.PizzaSize;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaSizeDao {

    List<PizzaSize> getAllPizzaSizes();
    PizzaSize getPizzaSizeById(int pizzaSizeId);
    int findIdByPizzaSize(PizzaSize pizzaSize);
    boolean createPizzaSize(String pizzaSizeName, BigDecimal price);
    boolean updatePizzaSize(PizzaSize pizzaSize);
    boolean deletePizzaSize(int pizzaSizeId);
}
