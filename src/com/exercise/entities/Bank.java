package com.exercise.entities;

import com.exercise.exceptions.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class Bank {

    private List<InvesmentPorfolio> invesmentPorfolios;

    //Getters
    public List<InvesmentPorfolio> getInvesmentPorfolios() {
        return invesmentPorfolios;
    }

    //Setters
    public void setInvesmentPorfolios(List<InvesmentPorfolio> invesmentPorfolios) {
        this.invesmentPorfolios = invesmentPorfolios;
    }

    //Methods
    public void createPorfolio() {
        Limits limit = new Limits();
        InvesmentPorfolio porfolio = new InvesmentPorfolio(limit);
        invesmentPorfolios.add(porfolio);
    }

    public void transact(String product, Double amount, String type) throws Exception {
        try {

            if(product.isEmpty() || product.isBlank())
                throw new BusinessException("You must select a valid product to be able to transact");

            for (int i = 0; i < invesmentPorfolios.size(); i++) {
                invesmentPorfolios.get(i).transact(product, amount, type);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public List<List<Double>> calculatePnLPerInvestment() {
        List<List<Double>> response = new ArrayList<>();
        try {
            for (int i = 0; i < invesmentPorfolios.size(); i++) {
                response.add(invesmentPorfolios.get(i).calculatePnLPerInvestment());
            }
            return  response;
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void buildPortfolioBasedOnTransactions(String id) throws Exception {
        InvesmentPorfolio invesmentPorfolio = findPorfolio(id);

        if(invesmentPorfolio != null) {
            invesmentPorfolio.buildPortfolioBasedOnTransactions();
        }
        else
            throw new Exception("The informed portfolio does not exist");
    }

    public Double calculatePorfolioValue(String id, String money) throws Exception {
        InvesmentPorfolio invesmentPorfolio = findPorfolio(id);
        Double value = 0.0;
        if(invesmentPorfolio != null) {
            value = invesmentPorfolio.calculatePresentValue(money);
        }
        else
            throw new Exception("The informed portfolio does not exist");
        return value;
    }

    private InvesmentPorfolio findPorfolio(String id) {
        InvesmentPorfolio invesmentPorfolio = null;

        for(int i = 0; i < invesmentPorfolios.size(); i++) {
            invesmentPorfolio = invesmentPorfolios.get(i);
            if(invesmentPorfolio.getId().equals(id))
                break;
        }

        return invesmentPorfolio;
    }

    public void restarLimist() {
        for(int i = 0; i < invesmentPorfolios.size(); i++) {
            invesmentPorfolios.get(i).restartLimits();
        }
    }

}
