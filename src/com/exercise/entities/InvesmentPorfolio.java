package com.exercise.entities;

import com.exercise.exceptions.BusinessException;
import com.exercise.entities.factoryimpl.CapitalizableFactory;
import com.exercise.entities.factoryimpl.StretegyFactory;
import com.exercise.interfaces.Strategy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class InvesmentPorfolio {

    private final String id;
    private List<Capitalizable> capitalizables;
    private List<Transaction> transactions;
    private final Limits limit;

    public InvesmentPorfolio(Limits limit){
        this.id = String.valueOf((int)(Math.random()*(99999999+1)));
        this.capitalizables = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.limit = limit;
    }

    //Getters
    public String getId() {
        return id;
    }

    public List<Capitalizable> getCapitalizables() {
        return capitalizables;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    //Setters
    public void setCapitalizables(List<Capitalizable> capitalizables) {
        this.capitalizables = capitalizables;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    //Methods
    public Double calculatePresentValue(String money) throws Exception {
        try {
            Strategy f = StretegyFactory.create(money);
            return f.priceIn(this.capitalizables);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public List<Double>  calculatePnLPerInvestment(){

        List<Double> PnLs = new ArrayList<>();

        for(int i = 0; i < capitalizables.size(); i++) {
            PnLs.add(capitalizables.get(i).getAmount() *
                    (capitalizables.get(i).getActualPrice() - capitalizables.get(i).getPurcharsePrice()));
        }

        return PnLs;
    }

    public void transact(String product, Double amount, String type) throws Exception {
        try {
            if(amount.equals(0))
                throw new BusinessException("Transactions with 0 units are not allowed.");

            if (allowedByLimit(amount, product)) {
                Capitalizable capitalizable = CapitalizableFactory.create(product);
                capitalizable.setAmount(amount);
                Transaction transaction;

                if(type.equals("Compra"))
                    transaction = new Transaction(type, capitalizable);
                else if(type.equals("Venta")) {
                    if(hasSalableQuantity(product, amount))
                        transaction = new Transaction(type, capitalizable);
                    else
                        throw new BusinessException("You do not have the necessary number of products to sell");
                }
                else
                    throw new BusinessException("It is not possible to carry out a transaction other than a " +
                            "'Compra' or a 'Venta'");

                transactions.add(transaction);

                if(type.equals("Compra")) {
                    if (transaction.getCapitalizable().getAmount() < 0)
                        transaction.getCapitalizable().setAmount(transaction.getCapitalizable().getAmount() * -1);
                    capitalizables.add(capitalizable);
                }
                else {
                    if(transaction.getCapitalizable().getAmount() > 0)
                        transaction.getCapitalizable().setAmount(transaction.getCapitalizable().getAmount() * -1);
                    capitalizables.add(transaction.getCapitalizable());
                }
            }
            else
                throw new BusinessException("Transaction not allowed due to limit exceeded");
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void buildPortfolioBasedOnTransactions() {
        capitalizables = new ArrayList<>();

        for(int i = 0; i < transactions.size(); i++) {
            if(transactions.get(i).getType().equals("Compra")) {
                if (transactions.get(i).getCapitalizable().getAmount() < 0)
                    transactions.get(i).getCapitalizable().setAmount(transactions.get(i).getCapitalizable().getAmount()
                            * -1);
                capitalizables.add(transactions.get(i).getCapitalizable());
            }
            else {
                if(transactions.get(i).getCapitalizable().getAmount() > 0)
                    transactions.get(i).getCapitalizable().setAmount(transactions.get(i).getCapitalizable().getAmount()
                                    * -1);
                capitalizables.add(transactions.get(i).getCapitalizable());
            }
        }
    }

    public boolean allowedByLimit(Double amount, String product) throws BusinessException {

        boolean response;
        switch (product) {
            case "Stock":
                response = limit.allowedByStockLimit(amount);
                break;
            case "Bound":
                response = limit.allowedByBoundLimit(amount);
                break;
            case "Dollar":
                response = limit.allowedByDollarLimit(amount);
                break;
            case "Euro":
                response = limit.allowedByEuroLimit(amount);
                break;
            default:
                throw new BusinessException("It does not deal with these types of products.");
        }
        return  response;
    }

    public void restartLimits() {
        limit.restartLimits();
    }

    private boolean hasSalableQuantity(String product, Double amount) throws ParseException {
        Double haveQuantity;
        haveQuantity = 0.0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date now = dateFormat.parse(Calendar.getInstance().getTime().toString());

        for(int i =0; i < transactions.size(); i++) {

            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, limit.getDaysTobeAbleToSell());
            Date limitDate = c.getTime();

            boolean dateOk = (limitDate.after(now));

            if(transactions.get(i).getType().equals("Compra") &&
                transactions.get(i).getCapitalizable().getClass().toString().equals(product) &&
                dateOk)
                haveQuantity += transactions.get(i).getCapitalizable().getAmount();

            if(transactions.get(i).getType().equals("Venta") &&
               transactions.get(i).getCapitalizable().getClass().toString().equals(product) &&
               dateOk)
                haveQuantity += transactions.get(i).getCapitalizable().getAmount();
        }

        return (haveQuantity >= amount);
    }
}
