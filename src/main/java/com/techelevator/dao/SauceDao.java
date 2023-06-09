package com.techelevator.dao;

import com.techelevator.model.Sauce;

import java.math.BigDecimal;
import java.util.List;

public interface SauceDao {
    List<Sauce> getAllSauces();
    Sauce getSauceById(int sauceId);
    int findIdBySauce(Sauce sauce);
    boolean createSauce(String sauceName, BigDecimal price);
    boolean updateSauce(Sauce sauce);
    boolean deleteSauce(int sauceId);

}
