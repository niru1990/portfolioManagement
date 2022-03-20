package com.exercise.entities;

public abstract class Capitalizable {

    private Double amount; //cantidad
    private Double actualPrice; //precio actual
    private Double purcharsePrice; //precio de compra

    //Getters
    public Double getAmount() {
        return amount;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public Double getPurcharsePrice() {
        return purcharsePrice;
    }

    //Setters
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setPurcharsePrice(Double purcharsePrice) {
        this.purcharsePrice = purcharsePrice;
    }
}
