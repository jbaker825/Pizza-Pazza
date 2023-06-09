package com.techelevator.dao;

import com.techelevator.model.Crust;
import com.techelevator.model.PizzaSize;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcPizzaSizeDaoTest extends BaseDaoTests {

    private JdbcPizzaSizeDao sut;

    @Before
    public void setup(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcPizzaSizeDao(jdbcTemplate);
    }

    @Test
    public void getAllSizesTest(){
        int expected = 4;
        int actual = sut.getAllPizzaSizes().size();
        Assert.assertEquals("Get all sizes should return correct list size", expected, actual);
    }

    @Test
    public void getSizesByIdTest(){
        String expected = "XL";
        String actual = sut.getPizzaSizeById(4).getSizeName();
        Assert.assertEquals("Size names should match", expected, actual);
    }

    @Test
    public void createSizeTest(){
        boolean expected = true;
        boolean actual = sut.createPizzaSize("Test Size", new BigDecimal("10.0"));
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void updateSizeTest(){
        PizzaSize testSize = new PizzaSize("TEST",new BigDecimal("10.0"));
        boolean expected = true;
        boolean actual = sut.updatePizzaSize(testSize);
        Assert.assertEquals("Should return true", expected, actual);
    }

    @Test
    public void deleteSizeTest(){
        int sizeId = 1;
        boolean expected = true;
        boolean actual = sut.deletePizzaSize(1);
        Assert.assertEquals("Should return true", expected, actual);
    }
}
