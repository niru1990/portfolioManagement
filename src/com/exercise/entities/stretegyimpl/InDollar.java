package com.exercise.entities.stretegyimpl;

import com.exercise.entities.Capitalizable;
import com.exercise.entities.observerimpl.ExchangeImpl;
import com.exercise.interfaces.Strategy;

import java.util.List;

public class InDollar implements Strategy {
    @Override
    public Double priceIn(List<Capitalizable> products) throws Exception {

        Double prices;
        prices = 0.0;
        for(int i =  0; i < products.size(); i++) {
            switch (products.get(i).getClass().toString()) {
                case "Stock":
                case "Bound":
                    prices += products.get(i).getAmount() * ExchangeImpl.getDollarExchange();
                    break;
                case "Dollar":
                    prices += products.get(i).getAmount() *  products.get(i).getActualPrice();
                    break;
                case "Euro":
                    prices += (ExchangeImpl.getEuroExchange() / ExchangeImpl.getDollarExchange()) * products.get(i).getAmount();
                    break;
                default:
                    throw new Exception("It does not deal with these types of products.");
            }
        }
        return prices;
    }
}
