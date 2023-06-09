package com.techelevator.dao;

import com.techelevator.model.Crust;

import java.math.BigDecimal;
import java.util.List;

public interface CrustDao {

    List<Crust> getAllCrusts();
    Crust getCrustById(int crustId);
    int findIdByCrustName(String crustName);
    boolean createCrust(String crustName, BigDecimal price);
    boolean updateCrust(Crust crust);
    boolean deleteCrust(int crustId);


}
