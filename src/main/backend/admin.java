import java.sql.*;

public class Admin extends User{
    public Admin(String u, String p, String f, String l, String i) {
        super(u, p, f, l, i);
    }

    // createTellerAccount
    // Creates teller accounts.
    public Teller createTellerAccount(String user, String pass, String first, String last, String id){
        return new Teller(user, pass, first, last, id);
    }

    // deleteTellerAccount
    // Deletes teller accounts.
    public void deleteTellerAccount(String id){

    }

    // modifyTellerAccount
    // Changes information of the given telller.
    public void modifyTellerAccount(String id){

    }

    // viewTellerAccount
    // Shows teller account info of the given teller.
    public void viewTellerAccount(String id){

    }

    // viewCustomerBankAccount
    // Shows customer account info.
    public void viewCustomerBankAccount(String id){

    }
}