package com.exercise.entities;

import com.exercise.entities.observerimpl.ExchangeImpl;
import com.exercise.interfaces.Cash;

public class Euro extends Capitalizable implements Cash {

    public Euro() {
        super();
        update();
        this.setPurcharsePrice(this.getActualPrice());
    }

    @Override
    public void update() {
        this.setActualPrice(ExchangeImpl.euroExchange);
    }
}
