package com.techelevator.dao;

import com.techelevator.model.Pizza;
import com.techelevator.model.SpecialtyPizza;
import com.techelevator.model.Topping;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcToppingDao implements ToppingDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcToppingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Topping> getAllToppings() {
        List<Topping> toppings = new ArrayList<>();

        String sql = "SELECT * FROM toppings;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while (result.next()) {
             toppings.add(mapRow(result));
        }

        return toppings;
    }

    @Override
    public Topping getToppingById(int toppingId) {
        Topping topping = null;
        String sql = "SELECT * FROM toppings WHERE topping_id = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, toppingId);
        if (result.next()) {
            topping = mapRow(result);
        }

        return topping;
    }

    @Override
    public int findIdByTopping(Topping topping) {
        int toppingId = 0;

        String sql = "SELECT * FROM toppings WHERE topping_name = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, topping.getToppingName());
        if (result.next()) {
            topping = mapRow(result);
            toppingId = topping.getToppingId();
        }


        return toppingId;
    }

    @Override
    public boolean createTopping(String toppingName, boolean isPremium, boolean isAvailable) {
        int newToppingId;
        String sql = "INSERT INTO toppings (topping_name, is_premium, is_available) " +
                "VALUES (?,?,?) returning topping_id;";

        try {
            newToppingId = jdbcTemplate.queryForObject(sql, Integer.class, toppingName, isPremium, isAvailable );
        } catch (DataAccessException e) {
            return false;
        }

        return true;

    }

    @Override
    public boolean updateTopping(Topping topping) {
        boolean success = false;
        String sql = "UPDATE toppings SET topping_name = ?, is_premium = ?, is_available = ? " +
                "WHERE topping_id = ?;";
        int linesAffected = jdbcTemplate.update(sql, topping.getToppingName(), topping.isPremium(), topping.isAvailable(), topping.getToppingId());

        if(linesAffected ==1) {
            success = true;
        }


        return success;
    }

    @Override
    public boolean deleteTopping(int toppingId) {
        String sql = "DELETE FROM specialty_topping WHERE topping_id = ?; " +
                "DELETE FROM pizza_topping WHERE topping_id = ?; " +
                "DELETE FROM toppings WHERE topping_id = ? ;";

        boolean success = false;
        int linesAffected = jdbcTemplate.update(sql, toppingId, toppingId, toppingId);
        if (linesAffected>0) {
            success = true;
        }
        return success;

    }

    @Override
    public List<Topping> getSpecialtyPizzaToppings(SpecialtyPizza specialtyPizza) {
        List<Topping> specialtyPizzaToppings = new ArrayList<>();

        String sql = "SELECT * FROM toppings AS t " +
                "JOIN specialty_topping AS st ON st.topping_id = t.topping_id " +
                "JOIN specialty_pizza AS sp ON sp.specialty_id = st.specialty_id " +
                "WHERE sp.specialty_id = ? ;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, specialtyPizza.getSpecialtyId());

        while (result.next()) {
            specialtyPizzaToppings.add(mapRow(result));
        }
        return specialtyPizzaToppings;
    }

    @Override
    public List<Topping> getPizzaToppings(Pizza pizza) {
        List<Topping> specialtyPizzaToppings = new ArrayList<>();

        String sql = "SELECT * FROM toppings AS t " +
                "JOIN pizza_topping AS pt ON pt.topping_id = t.topping_id " +
                "JOIN pizza AS p ON p.pizza_id = pt.pizza_id " +
                "WHERE p.pizza_id = ? ;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, pizza.getPizzaId());

        while (result.next()) {
            specialtyPizzaToppings.add(mapRow(result));
        }
        return specialtyPizzaToppings;
    }

    private Topping mapRow(SqlRowSet rowSet) {
        Topping topping = new Topping();
        topping.setToppingId(rowSet.getInt("topping_id"));
        topping.setToppingName(rowSet.getString("topping_name"));
        topping.setPremium(rowSet.getBoolean("is_premium"));
        topping.setAvailable(rowSet.getBoolean("is_available"));
        return topping;
    }


}
