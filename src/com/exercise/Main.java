package com.exercise;

import com.exercise.entities.Bank;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            Bank bank = new Bank();
            bank.createPorfolio();
            bank.transact("Dollar", 300.25, "Compra");
            bank.calculatePnLPerInvestment();
            //bank.buildPortfolioBasedOnTransactions("12312312312"); //Se comenta por no contar con el id actualmente.
            //bank.calculatePorfolioValue("123123", "Dollar"); //Se comenta por no contar con el id actualmente.
            bank.restarLimist();
        }
        catch (Exception e)
        {
            throw e;
        }
    }
}
