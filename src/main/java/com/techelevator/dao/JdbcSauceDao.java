package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.DaoException;
import com.techelevator.model.Sauce;
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
public class JdbcSauceDao implements SauceDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcSauceDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sauce> getAllSauces() {
        List<Sauce> sauces = new ArrayList<>();
        String sql = "SELECT * FROM sauce;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                sauces.add(mapRowToSauce(results));
            }
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return sauces;
    }
    @Override
    public Sauce getSauceById(int sauceId) {
        String sql = "SELECT * FROM sauce WHERE sauce_id = ?;";
        Sauce sauce = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sauceId);
            if(results.next()){
                sauce = mapRowToSauce(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return sauce;
    }

    @Override
    public int findIdBySauce(Sauce sauce) {
        return 0;
    }

    @Override
    public boolean createSauce(String sauceName, BigDecimal price) {
        String sql = "INSERT INTO sauce (sauce_name, price) VALUES (?, ?) RETURNING sauce_id;";
        Sauce newSauce;
        Integer newSauceId;
        try{
            newSauceId = jdbcTemplate.queryForObject(sql, Integer.class, sauceName, price);
            newSauce = getSauceById(newSauceId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }  catch (Exception e){
            System.err.println("Unknown error updating the sauce, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean updateSauce(Sauce sauce) {
        String sql = "UPDATE sauce SET sauce_name = ?, price = ? WHERE sauce_id = ?;";
        try{
            jdbcTemplate.update(sql, sauce.getSauceName(), sauce.getPrice(), sauce.getSauceId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the sauce, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean deleteSauce(int sauceId) {
        String sql = "DELETE FROM sauce WHERE sauce_id = ?";
        try {
            jdbcTemplate.update(sql, sauceId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the sauce, please contact your system administrator");
        }
        return true;
    }

    private Sauce mapRowToSauce(SqlRowSet rs) {
        Sauce sauce = new Sauce();
        sauce.setSauceId(rs.getInt("sauce_id"));
        sauce.setSauceName(rs.getString("sauce_name"));
        sauce.setPrice(rs.getBigDecimal("price"));
        return sauce;
    }
}
