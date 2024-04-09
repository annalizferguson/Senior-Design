package main.backend;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.*;

public class FinancialAccount {
    private String id;
    private String type;
    private double balance;
    private int accountNumber;
    private String customerID;

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
    public String getCustomerID(){ return customerID; }

    public void zeroOutBalance() { balance = 0; }



    /**
     * creates new financial account and adds it to database
     * @param newId
     * @param newType
     * @param newBalance
     */
    public void create(String newId, String newType, double newBalance, String cID) {
        id = newId;
        type = newType;
        balance = newBalance;
        accountNumber = 0; // thinking we could random generate this?
        customerID = cID;
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

        updateTransaction(amount, balance, "Deposit");

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
            if(updateTransaction(amount, balance - amount, "Withdraw")){
                balance -= amount;
                return 0;
            }
            return 1;
        }
    }

    // updateTransaction
    // Updates the transactions table and the given financial account table.
    protected boolean updateTransaction(double amount, double newBalance, String transType){
        Date currentDate = new Date();  //used to store the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");  //US date format
        String date = dateFormat.format(currentDate);  //holds the date in a string

        String db = "";

        if(this.type.equals("Checking")){  //checks for each account type to see which database to store into
            db = "checking_acc";
        }

        else if(this.type.equals("Savings")){
            db = "savings_acc";
        }

        else if(this.type.equals("Money Market")){
            db = "moneymarket_acc";
        }

        else if(this.type.equals("Credit Card")){
            db = "creditcard_acc";
        }

        else if(this.type.equals("Home Mortgage")){
            db = "homemortgage_acc";
        }

        else{
            System.out.println("Error with type in updateTransaction: " + this.type);
            return false;
        }

        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        String sql1 = "update " + db + " set balance = " + newBalance + " where customer_id = '" + customerID + "'";  //needs work
        String sql2 = "insert into transactions (customer_id, transaction_type, account_type, amount, new_balance, " +
                "transaction_date) values ('" + customerID + "', '" + transType + "', '" + type + "', " + amount +
                ", " + newBalance + ", '" + date + "')";

        try(Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(sql1);  //updates the account table
            statement.executeUpdate(sql2);  //adds to the transaction table
            return true;  //returns success
        }
        catch(SQLException e){
            e.printStackTrace();  //prints error
            return false;  //returs false, cannot update database
        }
    }
}
