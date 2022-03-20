package com.exercise.entities;

import com.exercise.exceptions.BusinessException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Transaction {

    private String id;
    private String type;
    private Capitalizable capitalizable;
    private Date creationDate;

    public Transaction(String type, Capitalizable capitalizable) throws BusinessException, ParseException {
        try {
            if (type == "Compra" || type == "Venta")
                this.type = type;
            else
                throw new BusinessException("Type of transaction not allowed");

            this.id = String.valueOf((int)(Math.random()*(99999999+1)));

            this.capitalizable = capitalizable;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            this.creationDate = dateFormat.parse(Calendar.getInstance().getTime().toString());
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    //Getters
    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Capitalizable getCapitalizable() {
        return capitalizable;
    }

    public Date getCreationDate() { return creationDate; }

    //Setters
    public void setId(String id ) { this.id = id; }
    public void setCapitalizable(Capitalizable capitalizable) {
        this.capitalizable = capitalizable;
    }

}