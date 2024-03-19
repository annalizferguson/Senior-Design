package main.backend;

public class customer extends user {
    String ssn; // social security number
    String dob; // date of birth
    String email;
    String address;
    String mailingAddress;
    String phoneNumber;
    String cellPhoneNumber;

    // constructor
    // Sends the user fields to the user superclass, sets everything else.
    // I'm not sure if this is how inheritance works in Java, it's been a while
    // since I've worked with Java inheritance.
    public customer(String u, String p, String f, String l, String i, String ss, String d, String em, String ad, String ma, String ph, String ce) {
        super(u, p, f, l, i);  // sends the user fields to the super class

        ssn = ss;
        dob = d;
        email = em;
        address = ad;
        mailingAddress = ma;
        phoneNumber = ph;
        cellPhoneNumber = ce;
    }

    // viewReports
    // Shows the user a list of banking reports including deposits, withdrawals, transfers, etc.
    public void viewReports(){

    }

    // transferMoney
    // Transfers money from one account to another.
    public void transferMoney(){

    }

    // payBill
    // Allows the user to pay a bill.
    public void payBill(){

    }


    // setSSN
    // Sets the social security number for the customer.
    // The id is the customer id. Perhaps it can be a customer object.
    public void setSSN(String num){
        ssn = num;
    }

    // setDOB
    // Sets the date of birth for the customer.
    public void setDOB(String date){
        dob = date;
    }

    // setEmail
    // Sets the email for the customer.
    public void setEmail(String em){
        email = em;
    }

    // setAddress
    // Sets the address for the customer.
    public void setAddress(String addr){
        address = addr;
    }

    // setMailingAddress
    // Sets the mailing address for the customer.
    public void setMailingAddress(String mAddr){
        mailingAddress = mAddr;
    }

    // setPhoneNumber
    // Sets the phone number for the customer.
    public void setPhoneNumber(String phone){
        phoneNumber = phone;
    }

    // setCellPhoneNumber
    // Sets the cell phone number for the customer.
    public void setCellPhoneNumber(String cPhone){
        cellPhoneNumber = cPhone;
    }
}