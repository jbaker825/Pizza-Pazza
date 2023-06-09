package com.techelevator.model;

import java.math.BigDecimal;

public class Sale {
    int saleId;
    String saleStatus;
    BigDecimal saleTotal;
    boolean isDelivery;
    String customerAddress;
    int ccLastFour;

    public Sale() {
        this.saleStatus = "created";
        this.saleTotal = new BigDecimal("0.00");
        this.isDelivery = true;
        this.customerAddress = "Not Entered";
        this.ccLastFour = 0000;
    }

    public Sale(int saleId, String saleStatus, BigDecimal saleTotal, boolean isDelivery, String customerAddress, int ccLastFour) {
        this.saleId = saleId;
        this.saleStatus = saleStatus;
        this.saleTotal = saleTotal;
        this.isDelivery = isDelivery;
        this.customerAddress = customerAddress;
        this.ccLastFour = ccLastFour;
    }

    public Sale(String saleStatus, BigDecimal saleTotal, boolean isDelivery, String customerAddress, int ccLastFour) {
        this.saleStatus = saleStatus;
        this.saleTotal = saleTotal;
        this.isDelivery = isDelivery;
        this.customerAddress = customerAddress;
        this.ccLastFour = ccLastFour;
    }



    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public BigDecimal getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(BigDecimal saleTotal) {
        this.saleTotal = saleTotal;
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCcLastFour() {
        return ccLastFour;
    }

    public void setCcLastFour(int ccLastFour) {
        this.ccLastFour = ccLastFour;
    }
}
