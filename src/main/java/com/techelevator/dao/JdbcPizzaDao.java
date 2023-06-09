package com.techelevator.dao;


import com.techelevator.model.DaoException;
import com.techelevator.model.Pizza;
import com.techelevator.model.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPizzaDao implements PizzaDao{
    @Autowired
    ToppingDao toppingDao;

    private JdbcTemplate jdbcTemplate;
    public JdbcPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Pizza> getAllPizzas() {
        List<Pizza> pizzas = new ArrayList<>();

        String sql = "SELECT * FROM pizza;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while (result.next()) {
            pizzas.add(mapRow(result));
        }

        return pizzas;
    }

    @Override
    public Pizza getPizzaById(int pizzaId) {
        Pizza pizza = null;
        String sql = "SELECT * FROM pizza WHERE pizza_id = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, pizzaId);
        if (result.next()) {
            pizza = mapRow(result);
        }

        return pizza;
    }

    @Override
    public int findIdByPizza(Pizza pizza) {
        return 0;
    }

    @Override
    public boolean createPizza(Pizza pizza) {
        int newPizzaId;
        boolean result = false;

        String sql = "INSERT INTO pizza (sale_id, crust_id, sauce_id, pizza_size_id, price, is_square_cut) " +
                "VALUES (?,?,?,?,?,?) returning pizza_id;";
        System.out.println(pizza.getSizeId());
        try {
            newPizzaId = jdbcTemplate.queryForObject(sql, Integer.class, pizza.getSaleId(),
                    pizza.getCrustId(), pizza.getSauceId(), pizza.getSizeId(), pizza.getPrice(), pizza.isSquareCut());
            for(Topping topping : pizza.getToppings()){
                String toppingSql = "INSERT INTO pizza_topping (pizza_id, topping_id) " +
                        "VALUES (?,?) ";
                jdbcTemplate.update(toppingSql, newPizzaId, topping.getToppingId());
            }
            result = true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }

        return result;
    }

    @Override
    public boolean updatePizza(Pizza pizza) {
        boolean success = false;
        String sql = "UPDATE pizza SET sale_id = ?, crust_id = ?, sauce_id = ?, pizza_size_id = ?, price = ?, is_square_cut = ? " +
                "WHERE pizza_id = ?;";


        int linesAffected = jdbcTemplate.update(sql, pizza.getSaleId(),
                pizza.getCrustId(), pizza.getSauceId(), pizza.getSizeId(), pizza.getPrice(), pizza.getPizzaId(), pizza.isSquareCut());

        if(linesAffected ==1) {
            success = true;
        }

        return success;
    }

    @Override
    public boolean deletePizza(int pizzaId) {
        String sql = "DELETE FROM pizza_topping WHERE pizza_id = ?; " +
                     "DELETE FROM pizza WHERE pizza_id = ?;";


        boolean success = false;
        int linesAffected = jdbcTemplate.update(sql, pizzaId, pizzaId);
        if (linesAffected>0) {
            success = true;
        }
        return success;
    }

    private Pizza mapRow(SqlRowSet rowSet) {
        Pizza pizza = new Pizza();
        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setSaleId(rowSet.getInt("sale_id"));
        pizza.setCrustId(rowSet.getInt("crust_id"));
        pizza.setSauceId(rowSet.getInt("sauce_id"));
        pizza.setSizeId(rowSet.getInt("pizza_size_id"));
        pizza.setPrice(rowSet.getBigDecimal("price"));
        pizza.setToppings(toppingDao.getPizzaToppings(pizza));
        pizza.setSquareCut(rowSet.getBoolean("is_square_cut"));
        return pizza;
    }
}
