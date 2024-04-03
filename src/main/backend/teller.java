package main.backend;

import java.sql.*;

public class Teller extends User {
    public Teller(String u, String p, String f, String l, String i) {
        super(u, p, f, l, i);
    }

    // createCustomerFinancialAccount
    // Creates financial accounts for customers.
    // 0: Checking, 1: Savings, 2: Money Market, 3: Home Mortgage, 4: Credit Card
    public void createCustomerFinancialAccount(int type, String id) {
        if (type == 0) {  // checking

        } else if (type == 1) {  // savings

        } else if (type == 2) {  // money market

        } else if (type == 3) {  // home mortgage

        } else if (type == 4) {  // credit card

        } else {  // incorrect type

        }
    }

    // deleteCustomerFinancialAccount
    // Deletes financial accounts for customers.
    // 0: Checking, 1: Savings, 2: Money Market, 3: Home Mortgage, 4: Credit Card
    public void deleteCustomerFinancialAccount(int type, String id) {
        if (type == 0) {  // checking

        } else if (type == 1) {  // savings

        } else if (type == 2) {  // money market

        } else if (type == 3) {  // home mortgage

        } else if (type == 4) {  // credit card

        } else {  // incorrect type

        }
    }

    // createCustomerBankAccount
    // Creates bank accounts for customers.
    // 0: Checking, 1: Savings, 2: Money Market, 3: Home Mortgage, 4: Credit Card
    public void createCustomerBankAccount(int type, String id) {
        if (type == 0) {  // checking

        } else if (type == 1) {  // savings

        } else if (type == 2) {  // money market

        } else if (type == 3) {  // home mortgage

        } else if (type == 4) {  // credit card

        } else {  // incorrect type

        }
    }

    // deleteCustomerBankAccount
    // Deletes bank accounts for customers.
    // 0: Checking, 1: Savings, 2: Money Market, 3: Home Mortgage, 4: Credit Card
    public void deleteCustomerBankAccount(int type, String id) {
        if (type == 0) {  // checking

        } else if (type == 1) {  // savings

        } else if (type == 2) {  // money market

        } else if (type == 3) {  // home mortgage

        } else if (type == 4) {  // credit card

        } else {  // incorrect type

        }
    }

    // viewCustomerBankAccount
    // Shows the bank account info of a customer.
    public void viewCustomerBankAccount() {

    }

    // setSSN
    // Sets the social security number for a customer.
    // The id is the customer id. Perhaps it can be a customer object.
    public void setSSN(String num, String id) {

    }

    // setDOB
    // Sets the date of birth for a customer.
    public void setDOB(String date, String id) {

    }

    // setEmail
    // Sets the email for a customer.
    public void setEmail(String email, String id) {

    }

    // setAddress
    // Sets the address for a customer.
    public void setAddress(String addr, String id) {

    }

    // setMailingAddress
    // Sets the mailing address for a customer.
    public void setMailingAddress(String mAddr, String id) {

    }

    // setPhoneNumber
    // Sets the phone number for a customer.
    public void setPhoneNumber(String phone, String id) {

    }

    // setCellPhoneNumber
    // Sets the cell phone number for a customer.
    public void setCellPhoneNumber(String cPhone, String id) {

    }

    // recordDeposit
    // Records a deposit into a customer finanical account.
    // Acts like a customer came into the physical bank with money and made a deposit.
    // acc is the financial account to deposit money into.
    public void recordDeposit(float amount, String acc, String id) {

    }

    // recordWithdrawal
    // Records a withdrawal from a customer financial account.
    // Returns 1 if successful, 0 if there is insufficient funds.
    public int recordWithdrawal(float amount, String acc, String id) {
        return 0;
    }
}