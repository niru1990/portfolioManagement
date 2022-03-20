package com.exercise.entities.factoryimpl;

import com.exercise.entities.*;
import com.exercise.exceptions.CapitalizableException;

public class CapitalizableFactory {

    public static Capitalizable create(String type) throws Exception {
        try{

            switch (type) {
                case "Stock":
                    return new Stock();
                case "Bound":
                    return new Bound();
                case "Dollar":
                    return new Dollar();
                case "Euro":
                    return new Euro();
                default:
                    throw new CapitalizableException("It does not deal with these types of products.");
            }

        }
        catch (Exception e) {
            throw e;
        }
    }
}
