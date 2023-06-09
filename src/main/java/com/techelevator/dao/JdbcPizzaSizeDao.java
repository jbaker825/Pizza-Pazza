package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.DaoException;
import com.techelevator.model.PizzaSize;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPizzaSizeDao implements PizzaSizeDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcPizzaSizeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PizzaSize> getAllPizzaSizes() {
        List<PizzaSize> sizes = new ArrayList<>();
        String sql = "SELECT * FROM pizza_size;";
        try{
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                sizes.add(mapRowToSize(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return sizes;
    }

    @Override
    public PizzaSize getPizzaSizeById(int pizzaSizeId) {
        String sql = "SELECT * FROM pizza_size WHERE pizza_size_id = ?;";
        PizzaSize size = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaSizeId);
            if(results.next()){
                size = mapRowToSize(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }

        return size;
    }

    @Override
    public int findIdByPizzaSize(PizzaSize pizzaSize) {
        return 0;
    }

    @Override
    public boolean createPizzaSize(String pizzaSizeName, BigDecimal price) {
        String sql = "INSERT INTO pizza_size (size_name, price) VALUES (?, ?) RETURNING pizza_size_id;";
        PizzaSize newSize;
        Integer newSizeId;
        try{
            newSizeId = jdbcTemplate.queryForObject(sql, Integer.class, pizzaSizeName, price);
            newSize = getPizzaSizeById(newSizeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }  catch (Exception e){
            System.err.println("Unknown error updating the pizza size, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean updatePizzaSize(PizzaSize pizzaSize) {
        String sql = "UPDATE pizza_size SET size_name = ?, price = ? WHERE pizza_size_id = ?;";
        try{
            jdbcTemplate.update(sql, pizzaSize.getSizeName(), pizzaSize.getPrice(), pizzaSize.getPizzaSizeId());
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the pizza size, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean deletePizzaSize(int pizzaSizeId) {
        String sql = "DELETE FROM pizza_size WHERE pizza_size_id = ?";
        try{
            jdbcTemplate.update(sql, pizzaSizeId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the pizza size, please contact your system administrator");
        }
        return true;
    }

    private PizzaSize mapRowToSize(SqlRowSet rs) {
        PizzaSize size = new PizzaSize();
        size.setPizzaSizeId(rs.getInt("pizza_size_id"));
        size.setSizeName(rs.getString("size_name"));
        size.setPrice(rs.getBigDecimal("price"));
        return size;
    }
}
