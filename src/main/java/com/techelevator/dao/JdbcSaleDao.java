package com.techelevator.dao;

import com.techelevator.model.DaoException;
import com.techelevator.model.Sale;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSaleDao implements SaleDao{
    private JdbcTemplate jdbcTemplate;
    public JdbcSaleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Sale> getAllSales() {
        List<Sale> sales = new ArrayList<>();

        String sql = "SELECT * FROM sale;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

        while (result.next()) {
            sales.add(mapRow(result));
        }

        return sales;
    }

    @Override
    public Sale getSaleById(int saleId) {
        Sale sale = null;
        String sql = "SELECT * FROM sale WHERE sale_id = ? ; ";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, saleId);

        if (result.next()) {
            sale = mapRow(result);
        }
        return sale;

    }

    @Override
    public int findIdBySale(Sale sale) {
        return 0;
    }

    @Override
    public int createSale(Sale sale) {
        Integer saleId;
        boolean result = false;

        String sql = "INSERT INTO sale (sale_status, sale_total, is_delivery, customer_address, cc_last_four) " +
                "VALUES (?,?,?,?,?) returning sale_id;";

        try {
            saleId = jdbcTemplate.queryForObject(sql, Integer.class, sale.getSaleStatus(),
                    sale.getSaleTotal(), sale.isDelivery(), sale.getCustomerAddress(), sale.getCcLastFour());
            if (saleId > 0){
                result = true;
            }

        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }

        return saleId;
    }

    @Override
    public boolean updateSale(Sale sale) {
        boolean success = false;
        String sql = "UPDATE sale SET sale_status = ?, sale_total = ?, is_delivery = ?, customer_address = ?, cc_last_four = ? " +
                "WHERE sale_id = ?;";


        int linesAffected = jdbcTemplate.update(sql, sale.getSaleStatus(),
                sale.getSaleTotal(), sale.isDelivery(), sale.getCustomerAddress(), sale.getCcLastFour(), sale.getSaleId());

        if(linesAffected ==1) {
            success = true;
        }

        return success;
    }

    @Override
    public boolean deleteSale(int saleId) {
        String sql = "DELETE FROM pizza_topping WHERE pizza_id = ?; " +
                "DELETE FROM pizza WHERE sale_id = ?; " +
                "DELETE FROM sale WHERE sale_id = ?;";

        boolean success = false;
        int linesAffected = jdbcTemplate.update(sql, saleId, saleId);
        if (linesAffected>0) {
            success = true;
        }
        return success;
    }

    private Sale mapRow(SqlRowSet rowSet) {
        Sale sale = new Sale();
        sale.setSaleId(rowSet.getInt("sale_id"));
        sale.setSaleStatus(rowSet.getString("sale_status"));
        sale.setSaleTotal(rowSet.getBigDecimal("sale_total"));
        sale.setDelivery(rowSet.getBoolean("is_delivery"));
        sale.setCustomerAddress(rowSet.getString("customer_address"));
        sale.setCcLastFour(rowSet.getInt("cc_last_four"));

        return sale;
    }
}
