package com.techelevator.dao;

import com.techelevator.model.Sale;

import java.math.BigDecimal;
import java.util.List;

public interface SaleDao {
    List<Sale> getAllSales();
    Sale getSaleById(int saleId);
    int findIdBySale(Sale sale);
    int createSale(Sale sale);
    boolean updateSale(Sale sale);
    boolean deleteSale(int saleId);
}
