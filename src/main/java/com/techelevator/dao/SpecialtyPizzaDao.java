package com.techelevator.dao;

import com.techelevator.model.SpecialtyPizza;

import java.math.BigDecimal;
import java.util.List;

public interface SpecialtyPizzaDao {
    List<SpecialtyPizza> getAllSpecialtyPizzas();
    SpecialtyPizza getSpecialtyPizzaById(int specialtyPizzaId);
    int findIdBySpecialtyPizza(SpecialtyPizza specialtyPizza);
    int createSpecialtyPizza(SpecialtyPizza specialtyPizza);
    boolean updateSpecialtyPizza(SpecialtyPizza specialtyPizza);
    boolean deleteSpecialtyPizza(int specialtyPizzaId);

}
