package com.techelevator.model;

public class Topping {
    int toppingId;
    String toppingName;
    boolean isPremium;
    boolean isAvailable;

    public Topping(int toppingId, String toppingName, boolean isPremium, boolean isAvailable) {
        this.toppingId = toppingId;
        this.toppingName = toppingName;
        this.isPremium = isPremium;
        this.isAvailable = isAvailable;
    }

    public Topping() {

    }

    public int getToppingId() {
        return toppingId;
    }

    public void setToppingId(int toppingId) {
        this.toppingId = toppingId;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
