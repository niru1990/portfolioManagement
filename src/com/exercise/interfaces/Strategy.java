package com.exercise.interfaces;

import com.exercise.entities.Capitalizable;

import java.util.List;

public interface Strategy {

    public Double priceIn(List<Capitalizable> products) throws Exception;
}
