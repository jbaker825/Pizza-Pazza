package com.techelevator.dao;

import com.techelevator.model.DaoException;
import com.techelevator.model.SpecialtyPizza;
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
public class JdbcSpecialtyPizzaDao implements SpecialtyPizzaDao{

    @Autowired
    ToppingDao toppingDao;

    private JdbcTemplate jdbcTemplate;
    public JdbcSpecialtyPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<SpecialtyPizza> getAllSpecialtyPizzas() {

        List<SpecialtyPizza> specialtyPizzas = new ArrayList<>();

        String sql = "SELECT * FROM specialty_pizza;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while (result.next()) {
            specialtyPizzas.add(mapRow(result));
        }

        return specialtyPizzas;
    }

    @Override
    public SpecialtyPizza getSpecialtyPizzaById(int specialtyPizzaId) {
        SpecialtyPizza specialtyPizza = null;
        String sql = "SELECT * FROM specialty_pizza WHERE specialty_id = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, specialtyPizzaId);
        if (result.next()) {
            specialtyPizza = mapRow(result);
        }

        return specialtyPizza;
    }

    @Override
    public int findIdBySpecialtyPizza(SpecialtyPizza specialtyPizza) {
        int specialtyPizzaId = 0;

        String sql = "SELECT * FROM specialty_pizza WHERE pizza_name = ?; ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, specialtyPizza.getPizzaName());
        if (result.next()) {
            specialtyPizza = mapRow(result);
            specialtyPizzaId = specialtyPizza.getSpecialtyId();
        }

        return specialtyPizzaId;
    }

    @Override
    public int createSpecialtyPizza(SpecialtyPizza specialtyPizza) {
        int newSpecialtyPizzaId;
        boolean result = false;

        String sql = "INSERT INTO specialty_pizza (pizza_name, crust_id, sauce_id, pizza_size_id, price, is_available, description, image, is_square_cut) " +
                "VALUES (?,?,?,?,?,?,?,?,?) returning specialty_id;";

        try {
            newSpecialtyPizzaId = jdbcTemplate.queryForObject(sql, Integer.class, specialtyPizza.getPizzaName(),
                    specialtyPizza.getCrustId(), specialtyPizza.getSauceId(), specialtyPizza.getSizeId(), specialtyPizza.getPrice(), specialtyPizza.isAvailable(),
                    specialtyPizza.getDescription(), specialtyPizza.getImage(), specialtyPizza.isSquareCut());
            for(Topping topping : specialtyPizza.getToppings()){
                String toppingSql = "INSERT INTO specialty_topping (specialty_id, topping_id) " +
                        "VALUES (?,?) ";
                jdbcTemplate.update(toppingSql, newSpecialtyPizzaId, topping.getToppingId());
            }
            result = true;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }

        return newSpecialtyPizzaId;
    }

    @Override
    public boolean updateSpecialtyPizza(SpecialtyPizza specialtyPizza) {
        boolean success = false;
        String sql = "DELETE FROM specialty_topping WHERE specialty_id = ?; " +
                "UPDATE specialty_pizza SET pizza_name = ?, crust_id = ?, sauce_id = ?, pizza_size_id = ?, price = ?, is_available = ?, description = ?, image = ?, is_square_cut = ? " +
                "WHERE specialty_id = ?;";


        int linesAffected = jdbcTemplate.update(sql, specialtyPizza.getSpecialtyId(), specialtyPizza.getPizzaName(),
                specialtyPizza.getCrustId(), specialtyPizza.getSauceId(), specialtyPizza.getSizeId(),
                specialtyPizza.getPrice(), specialtyPizza.isAvailable(),
                specialtyPizza.getDescription(), specialtyPizza.getImage(), specialtyPizza.isSquareCut(), specialtyPizza.getSpecialtyId());

        for(Topping topping : specialtyPizza.getToppings()){
            String toppingSql = "INSERT INTO specialty_topping (specialty_id, topping_id) " +
                    "VALUES (?,?) ";
            jdbcTemplate.update(toppingSql, specialtyPizza.getSpecialtyId(), topping.getToppingId());
        }
        if(linesAffected ==1) {
            success = true;
        }

        return success;
    }

    @Override
    public boolean deleteSpecialtyPizza(int specialtyPizzaId) {
        String sql = "DELETE FROM specialty_topping WHERE specialty_id = ?; " +
                "DELETE FROM specialty_pizza WHERE specialty_id = ?;";

        boolean success = false;
        int linesAffected = jdbcTemplate.update(sql, specialtyPizzaId, specialtyPizzaId);
        if (linesAffected>0) {
            success = true;
        }
        return success;
    }


    private SpecialtyPizza mapRow(SqlRowSet rowSet) {
        SpecialtyPizza specialtyPizza = new SpecialtyPizza();
        specialtyPizza.setSpecialtyId(rowSet.getInt("specialty_id"));
        specialtyPizza.setPizzaName(rowSet.getString("pizza_name"));
        specialtyPizza.setCrustId(rowSet.getInt("crust_id"));
        specialtyPizza.setSauceId(rowSet.getInt("sauce_id"));
        specialtyPizza.setSizeId(rowSet.getInt("pizza_size_id"));
        specialtyPizza.setPrice(rowSet.getBigDecimal("price"));
        specialtyPizza.setAvailable(rowSet.getBoolean("is_available"));
        specialtyPizza.setDescription(rowSet.getString("description"));
        specialtyPizza.setImage(rowSet.getString("image"));
        specialtyPizza.setToppings(toppingDao.getSpecialtyPizzaToppings(specialtyPizza));
        return specialtyPizza;
    }
}
