package com.exercise.entities;

public class Limits {

    private final Double boundsDailyLimit = 1000.00;
    private Double boundUseInLimit;

    private final Double stockDailyLimit = 1000.00;
    private Double stockUseInLimit;

    private final Double dollarDailyLimit = 1000.00;
    private Double dollarUseInLimit;

    private final Double euroDailyLimit = 1000.00;
    private Double euroUseInLimit;

    private final int daysTobeAbleToSell = 30;

    //Getters
    public int getDaysTobeAbleToSell() {
        return daysTobeAbleToSell;
    }


    //Methods
    public boolean allowedByBoundLimit(Double amount) {
        boolean ok = false;
        if((boundUseInLimit + amount) < boundsDailyLimit) {
            boundUseInLimit += amount;
            ok = true;
        }
        return ok;
    }

    public boolean allowedByStockLimit(Double amount) {
        boolean ok = false;
        if((stockUseInLimit + amount) < stockDailyLimit) {
            stockUseInLimit += amount;
            ok = true;
        }
        return ok;
    }

    public boolean allowedByDollarLimit(Double amount) {
        boolean ok = false;
        if((dollarUseInLimit + amount) < dollarDailyLimit) {
            dollarUseInLimit += amount;
            ok = true;
        }
        return ok;
    }

    public boolean allowedByEuroLimit(Double amount) {
        boolean ok = false;
        if((euroUseInLimit + amount) < euroDailyLimit) {
            euroUseInLimit += amount;
            ok = true;
        }
        return ok;
    }

    public void restartLimits() {
        this.boundUseInLimit = 0.0;
        this.stockUseInLimit = 0.0;
        this.dollarUseInLimit = 0.0;
        this.euroUseInLimit = 0.0;
    }
}
