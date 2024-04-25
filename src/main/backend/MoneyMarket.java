package main.backend;

import java.sql.*;

public class MoneyMarket extends FinancialAccount {
    private double interestRate;
    private int transactionCount;

    private int transactionLimit = 6;

    MoneyMarket(double newInterestRate, double startingBalance, String cID) {
        create("0", "Money Market", startingBalance, cID);
        interestRate = newInterestRate;
        transactionCount = 0;
    }

    public int deposit(double amount) {
        if (transactionCount == transactionLimit) {
            System.out.println("Deposit Failed: Transaction Limit Already Reached");
            return 2;
        } else {
            super.deposit(amount);
            transactionCount++;
            return 0;
        }
    }

    public int withdraw(double amount) {
        if (transactionCount == transactionLimit) {
            System.out.println("Deposit Failed: Transaction Limit Already Reached");
            return 2;
        } else {
            int result = super.withdraw(amount);
            if (result == 1) {
                return 1;
            } else {
                transactionCount++;
                return 0;
            }
        }
    }



    public void addInterest() {
        double addedAmount = getBalance() * interestRate;
        deposit(addedAmount);


        double interestToAdd = -1;

        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        //the sql statement to send to the database
        String sql1 = "select interest_income from interest_info where customer_id = '" + getCustomerID() + "'";

        //attempts to connect the driver to the database
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
        )
        {
            ResultSet resultSet = statement.executeQuery(sql1);

            if(resultSet.next()){
                interestToAdd = addedAmount;
                interestToAdd += resultSet.getDouble("interest_income");
            }
        } catch (SQLException e) {  //if the driver could not connect
            System.out.println("Error getting interest from database.");
        }

        if(interestToAdd == -1){  // checks if the interest to add was calculated and added
            return;
        }

        String sql2 = "update interest_info set interest_income = " + interestToAdd + " where customer_id = " + getCustomerID();

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(sql2);
        } catch (SQLException e) {  //if the driver could not connect
            System.out.println("Error adding interest to database.");
        }
    }
}
