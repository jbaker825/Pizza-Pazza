package com.techelevator.dao;

import com.techelevator.model.Crust;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcCrustDaoTests extends BaseDaoTests{

    private JdbcCrustDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcCrustDao(jdbcTemplate);
    }

    @Test
    public void getAllCrustsTest(){
        int expected = 4;
        int actual = sut.getAllCrusts().size();
        Assert.assertEquals("Get all crusts should return correct list size", expected, actual);
    }

    @Test
    public void getCrustsByIdTest(){
        String expected = "Thin Crust";
        String actual = sut.getCrustById(1).getCrustName();
        Assert.assertEquals("Crusts names should match", expected, actual);
    }

    @Test
    public void createCrustTest(){
        boolean expected = true;
        boolean actual = sut.createCrust("Test Crust", new BigDecimal("10.0"));
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void updateCrustTest(){
        Crust testCrust = new Crust("TEST",new BigDecimal("10.0"));
        boolean expected = true;
        boolean actual = sut.updateCrust(testCrust);
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void deleteCrustTest(){
        int crustId = 1;
        boolean expected = true;
        boolean actual = sut.deleteCrust(1);
        Assert.assertEquals("Should return true", expected, actual);
    }
}
