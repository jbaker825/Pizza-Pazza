package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.DaoException;
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
public class JdbcCrustDao implements CrustDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcCrustDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Crust> getAllCrusts() {
        List<Crust> crusts = new ArrayList<>();
        String sql = "SELECT * FROM crust;";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                crusts.add(mapRowToCrust(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return crusts;
    }

    @Override
    public Crust getCrustById(int crustId) {
        String sql = "SELECT * FROM crust WHERE crust_id = ?;";
        Crust crust = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, crustId);
            if(results.next()){
                crust = mapRowToCrust(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return crust;
    }

    @Override
    public int findIdByCrustName(String crustName) {
        return 0;
    }

    @Override
    public boolean createCrust(String crustName, BigDecimal price) {
        String sql = "INSERT INTO crust (crust_name, price) VALUES (?, ?) RETURNING crust_id;";
        Crust newCrust;
        Integer newCrustId;
        try{
            newCrustId = jdbcTemplate.queryForObject(sql, Integer.class, crustName, price);
            newCrust = getCrustById(newCrustId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }  catch (Exception e){
            System.err.println("Unknown error updating the crust, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean updateCrust(Crust crust) {
        String sql = "UPDATE crust SET crust_name = ?, price = ? WHERE crust_id = ?;";
        try{
            jdbcTemplate.update(sql, crust.getCrustName(), crust.getPrice(), crust.getCrustId());
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the crust, please contact your system administrator");
        }
        return true;
    }

    @Override
    public boolean deleteCrust(int crustId) {
        String sql = "DELETE FROM crust WHERE crust_id = ?";
        try {
            jdbcTemplate.update(sql, crustId);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        } catch (Exception e){
            System.err.println("Unknown error updating the crust, please contact your system administrator");
        }
        return true;
    }

    private Crust mapRowToCrust(SqlRowSet rs) {
        Crust crust = new Crust();
        crust.setCrustId(rs.getInt("crust_id"));
        crust.setCrustName(rs.getString("crust_name"));
        crust.setPrice(rs.getBigDecimal("price"));
        return crust;
    }
}
