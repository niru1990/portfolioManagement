package com.exercise.entities;

import com.exercise.entities.observerimpl.ExchangeImpl;
import com.exercise.interfaces.Cash;

public class Dollar extends Capitalizable implements Cash {

    public Dollar() {
        super();
        update();
        this.setPurcharsePrice(this.getActualPrice());
    }

    @Override
    public void update() {
        this.setActualPrice(ExchangeImpl.dollarExchange);
    }
}
