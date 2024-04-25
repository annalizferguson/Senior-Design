package main.backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InterestTax {
    private static final String bankName = "Charger United Credit Union";
    private static final String bankAddr = "301 Sparkman Drive\nHuntsville, AL 35899";  // this is UAH's mailing addr
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private double interestIncome;
    private String customerId;

    public InterestTax(String first, String last, String addr, String phone, double interest, String cID){
        firstName = first;
        lastName = last;
        address = addr;
        phoneNumber = phone;
        interestIncome = interest;
        customerId = cID;
    }

    // addInterestIncome
    // Adds on interest income to the current interest income.
    public void addInterest(double interest){
        interestIncome += interest;
    }

    // checkInterest
    // Returns true if interest income is $10 or more.
    // A form only gets sent if $10 or more is earned in interest.
    public boolean checkInterest(){
        return interestIncome >= 10;
    }

    public void setFirstName(String first){
        if(updateDatabase(customerId, "firstname", first)){
            firstName = first;
        }
        else{
            System.out.println("Could not update firstname in interestTax.");
        }
    }

    public void setLastName(String last){
        if(updateDatabase(customerId, "lastname", last)){
            lastName = last;
        }
        else{
            System.out.println("Could not update lastname in interestTax.");
        }
    }

    public void setAddress(String addr){
        if(updateDatabase(customerId, "address", addr)){
            address = addr;
        }
        else{
            System.out.println("Could not update address in interestTax.");
        }
    }

    public void setPhoneNumber(String phone){
        if(updateDatabase(customerId, "phone_number", phone)){
            phoneNumber = phone;
        }
        else{
            System.out.println("Could not update phone number in interestTax.");
        }
    }

    public void setInterestIncome(double interest){
        if(updateDatabase(customerId, "interest_income", interest)){
            interestIncome = interest;
        }
        else{
            System.out.println("Could not update interest in interestTax.");
        }
    }

    public void setCustomerId(String cID){
        if(updateDatabase(customerId, "phone_number", cID)){
            customerId = cID;
        }
        else{
            System.out.println("Could not update customer id in interestTax.");
        }
    }

    public String getBankName(){
        return bankName;
    }

    public String getBankAddr(){
        return bankAddr;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getAddress(){
        return address;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public double getInterestIncome(){
        return interestIncome;
    }

    public String getCustomerId(){
        return customerId;
    }

    private boolean updateDatabase(String row, String col, String field) {
        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        //the sql statement to send to the database
        String sql = "update interest_info set " + col + " = '" + field + "' where customer_id = " + row;

        //attempts to connect the driver to the database
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql); //executes the sql statement if the driver connected
            return true;  //returns success
        } catch (SQLException e) {  //if the driver could not connect
            return false;  //returns not successful with the connection
        }
    }

    private boolean updateDatabase(String row, String col, double field) {
        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        //the sql statement to send to the database
        String sql = "update interest_info set " + col + " = " + field + " where customer_id = " + row;

        //attempts to connect the driver to the database
        try (
                Connection connection = DriverManager.getConnection(url, user, pass);
                Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(sql); //executes the sql statement if the driver connected
            return true;  //returns success
        } catch (SQLException e) {  //if the driver could not connect
            return false;  //returns not successful with the connection
        }
    }
}
