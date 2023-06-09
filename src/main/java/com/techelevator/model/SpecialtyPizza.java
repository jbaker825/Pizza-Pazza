package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class SpecialtyPizza {
    int specialtyId;
    String pizzaName;
    int crustId;
    int sauceId;
    int sizeId;
    List<Topping> toppings;
    BigDecimal price;
    boolean isAvailable;
    String description;
    String image;
    boolean squareCut;

    public SpecialtyPizza() {
    }

    public SpecialtyPizza(String pizzaName, int crustId, int sauceId, int sizeId, List<Topping> toppings, BigDecimal price, boolean isAvailable, String description, String image, boolean squareCut) {
        this.pizzaName = pizzaName;
        this.crustId = crustId;
        this.sauceId = sauceId;
        this.sizeId = sizeId;
        this.toppings = toppings;
        this.price = price;
        this.isAvailable = isAvailable;
        this.description = description;
        this.image = image;
        this.squareCut = squareCut;
    }

    public SpecialtyPizza(String pizzaName, int crustId, int sauceId, int sizeId, List<Topping> toppings, BigDecimal price, boolean isAvailable) {
//        this.specialtyId = specialtyId;
        this.pizzaName = pizzaName;
        this.crustId = crustId;
        this.sauceId = sauceId;
        this.sizeId = sizeId;
        this.toppings = toppings;
        this.price = price;
        this.isAvailable = isAvailable;
    }

    public int getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(int specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getCrustId() {
        return crustId;
    }

    public void setCrustId(int crustId) {
        this.crustId = crustId;
    }

    public int getSauceId() {
        return sauceId;
    }

    public void setSauceId(int sauceId) {
        this.sauceId = sauceId;
    }

    public int getSizeId() {
        return sizeId;
    }

    public void setSizeId(int sizeId) {
        this.sizeId = sizeId;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isSquareCut() {
        return squareCut;
    }

    public void setSquareCut(boolean squareCut) {
        this.squareCut = squareCut;
    }
}
