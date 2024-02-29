package main.backend;

import java.math.BigDecimal;

public class FinancialAccount {
    private String id;
    private String type;
    private double balance;
    private int accountNumber;

    FinancialAccount() {
        id = "";
        type = "";
        balance = 0;
        accountNumber = 0;
    }

    public String getId() {
        return id;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void zeroOutBalance() { balance = 0; }


    /**
     * creates new financial account and adds it to database
     * @param newId
     * @param newType
     * @param newBalance
     */
    public void create(String newId, String newType, double newBalance) {
        id = newId;
        type = newType;
        balance = newBalance;
        accountNumber = 0; // thinking we could random generate this?
    }

    /**
     * Deletes the account from the customer's account and from the database
     */
    public void delete() {

    }

    /**
     * Deposits money into account
     */
    public int deposit(double amount) {
        balance += amount;
        // rounds balance to UP two decimal spaces
        balance = BigDecimal.valueOf(balance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return 0;
    }

    /**
     * withdraws money from account
     * returns 0 for success, 1 for not enough money
     * @param amount
     * @return
     */
    public int withdraw(double amount) {
        if (balance - amount < 0) {
            System.out.println("Not enough money in account to withdraw this amount.");
            return 1;
        } else {
            balance -= amount;
            return 0;
        }
    }

}
