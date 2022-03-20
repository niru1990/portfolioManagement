package com.exercise.entities.observerimpl;

import com.exercise.interfaces.Cash;
import com.exercise.interfaces.ExchangeService;

import java.util.ArrayList;
import java.util.List;

public class ExchangeImpl implements ExchangeService {

    private List<Cash> observers;
    public static Double dollarExchange;
    public static Double euroExchange;

    //Getters
    public List<Cash> getObservers() {
        return observers;
    }

    public static Double getDollarExchange() {
        return dollarExchange;
    }

    public static Double getEuroExchange() {
        return euroExchange;
    }

    //Setters
    public void setObservers(List<Cash> observers) {
        this.observers = observers;
    }

    public static void setDollarExchange(Double dollarExchange) {
        ExchangeImpl.dollarExchange = dollarExchange;
    }

    public static void setEuroExchange(Double euroExchange) {
        ExchangeImpl.euroExchange = euroExchange;
    }

    //Constructor
    public ExchangeImpl() {
        this.observers = new ArrayList<>();
    }

    //Methods
    @Override
    public void add(Cash cash) {
        observers.add(cash);
    }

    @Override
    public void delete(Cash cash) {
        observers.remove(cash);
    }

    @Override
    public void notificar() {
        for(int i = 0; i < observers.size(); i++) {
                observers.get(i).update();
        }
    }
}
