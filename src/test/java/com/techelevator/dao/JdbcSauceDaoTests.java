package com.techelevator.dao;

import com.techelevator.model.PizzaSize;
import com.techelevator.model.Sauce;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcSauceDaoTests extends BaseDaoTests{

    private JdbcSauceDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcSauceDao(jdbcTemplate);
    }

    @Test
    public void getAllSaucesTest(){
        int expected = 4;
        int actual = sut.getAllSauces().size();
        Assert.assertEquals("Get all sauces should return correct list size", expected, actual);
    }

    @Test
    public void getSauceByIdTest(){
        String expected = "Regular";
        String actual = sut.getSauceById(1).getSauceName();
        Assert.assertEquals("Sauce names should match", expected, actual);
    }

    @Test
    public void createSauceTest(){
        boolean expected = true;
        boolean actual = sut.createSauce("Test Sauce", new BigDecimal("1.0"));
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void updateSauceTest(){
        Sauce testSauce = new Sauce("TEST",new BigDecimal("10.0"));
        boolean expected = true;
        boolean actual = sut.updateSauce(testSauce);
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void deleteSauceTest(){
        int sizeId = 1;
        boolean expected = true;
        boolean actual = sut.deleteSauce(1);
        Assert.assertEquals("Should return true", expected, actual);
    }
}
