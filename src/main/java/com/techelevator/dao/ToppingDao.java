package com.techelevator.dao;


import com.techelevator.model.Pizza;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;

import java.math.BigDecimal;
import java.util.List;

public interface ToppingDao {

    List<Topping> getAllToppings();
    Topping getToppingById(int toppingId);
    int findIdByTopping(Topping topping);
    boolean createTopping(String toppingName, boolean isPremium, boolean isAvailable);
    boolean updateTopping(Topping topping);
    boolean deleteTopping(int toppingId);
    List<Topping> getSpecialtyPizzaToppings(SpecialtyPizza specialtyPizza);
    List<Topping> getPizzaToppings(Pizza pizza);
}
