package main.backend;

import java.sql.*;

public class Savings extends FinancialAccount {
    private double interestRate; // interest rate in decimal
    Savings(double interestRatePercent, double startingBalance, String cID) {
        interestRate = interestRatePercent / 100; // convert percent to decimal
        create("0", "Savings", startingBalance, cID);
    }

    /**
     * Applies interest to the current amount in baalcne
     */
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

        String sql2 = "update interest_info set interest_income = " + interestToAdd + " where customer_id = " + getCustomerID();

        if(interestToAdd == -1){  // checks if the interest to add was calculated and added
            return;
        }

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
