package com.exercise.entities.factoryimpl;

import com.exercise.entities.stretegyimpl.InDollar;
import com.exercise.entities.stretegyimpl.InEuro;
import com.exercise.exceptions.StrategyException;
import com.exercise.interfaces.Strategy;

public class StretegyFactory {

    public static Strategy create(String type) throws Exception {
        try {
            switch (type) {
                case "Dollar":
                    return new InDollar();
                case "Euro":
                    return new InEuro();
                default:
                    throw new StrategyException("It is not allowed to calculate the value of the portfolio " +
                            "in that currency");
            }
        }
        catch (Exception e) {
            throw e;
        }
    }

}
