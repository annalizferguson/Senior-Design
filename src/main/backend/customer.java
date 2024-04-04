import java.sql.*;

public class Customer extends User{
    private String ssn; // social security number
    private String dob; // date of birth
    private String email;
    private String address;
    private String mailingAddress;
    private String phoneNumber;
    private String cellPhoneNumber;

    // constructor
    // Sends the user fields to the user superclass, sets everything else.
    // I'm not sure if this is how inheritance works in Java, it's been a while
    // since I've worked with Java inheritance.
    public Customer(String u, String p, String f, String l, String i, String ss, String d, String em, String ad, String ma, String ph, String ce) {
        super(u, p, f, l, i);  // sends the user fields to the super class

        ssn = ss;
        dob = d;
        email = em;
        address = ad;
        mailingAddress = ma;
        phoneNumber = ph;
        cellPhoneNumber = ce;
    }

    public Customer(){

    }

    // Get Methods
    public String getSsn(){
        return ssn;
    }

    public String getDob(){
        return dob;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){
        return address;
    }

    public String getMailingAddress(){
        return mailingAddress;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getCellPhoneNumber(){
        return cellPhoneNumber;
    }


    // viewReports
    // Shows the user a list of banking reports including deposits, withdrawals, transfers, etc.
    public String viewReports(){
        String url = "jdbc:mysql://localhost:3306/laravel_api";  // database url
        String user = "root";  // database username
        String pass = "kd(S(MavJCoLV1";  // database password

        String sql = "select * from transactions where customer_id = '" + this.getId() + "'";  // sql command

        String report = "";  // the list of transactions

        try(Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
            ResultSet items = statement.executeQuery(sql);
        )
        {
            while(items.next()){
                report += String.format("%-15s %-15s %-15s $%-14d $%-15d\n", items.getString(7),
                        items.getString(4), items.getString(3),
                        items.getInt(5), items.getInt(6));
            }
        }
        catch(SQLException e){
            System.out.println("View report DB error.");
        }

        if(report.isEmpty()){
            return "Report could not be generated.";
        }

        return String.format("%-15s %-15s %-15s %-15s %-15s\n", "Date", "Account", "Type", "Amount", "Remaining")
                + report;
    }

    // transferMoney
    // Transfers money from one account to another.
    // I'm not sure if this will work. It may have to be modified later.
    public String transferMoney(float amount, FinancialAccount accTo, FinancialAccount accFrom){
        int with = accFrom.withdraw(amount);  // holds the result code of the withdrawal

        if(with == 1){
            return "Insufficient funds.";
        }

        else if(with == 2){
            return "Money market account monthly transaction limit reached.";
        }

        else if(with != 0){
            return "Unknown error making withdrawal during transfer.";
        }

        int dep = accTo.deposit(amount);

        if(dep != 0){
            return "Unknown error making deposit during transaction.";
        }

        return "Transfer complete.";
    }

    // payBill
    // Allows the user to pay a bill.
    // I have it returning a PaidBill object, it can be changed later if it's not needed.
    public PaidBill payBill(float amount, FinancialAccount accountPaidFrom, UnpaidBill unpaidBill){
        float amountDue = unpaidBill.getCurrentAmountDue();  // gets the amount left to pay in bill

        if(amount > amountDue){  // checks if too much money was inputted
            amount = amountDue;  // changes the input to the amount due
        }

        if(accountPaidFrom.withdraw(amount) != 0){  // withdraws money from the account unless an error occurs
            System.out.println("Error withdrawing while making a bill payment.");
            return null;
        }

        System.out.println(amountDue - amount);
        unpaidBill.setCurrentAmountDue(amountDue - amount);  // subtracts the amount given from the amount due

        if(unpaidBill.getCurrentAmountDue() > 0){  // checks if the bill was only partially paid, return if so
            System.out.println("Bill partially paid.");
            return null;
        }

        // creates a new paid bill
        PaidBill tempBill = new PaidBill(this.getFirstName() + " " + this.getLastName(), this.getAddress(), unpaidBill.getAmountDue(), unpaidBill.getDueDate(), accountPaidFrom.getType());

        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        String sql1 = "insert into paid_bill (payee_name, address, amount, due_date, account_paid_from, customer_id) " +
                "values ('" + tempBill.getPayeeName() + "', '" + tempBill.getAddress() + "', '" +
                tempBill.getAmount() + "', '" + tempBill.getDateDue() + "', '" +
                tempBill.getAccountPaidFrom() + "', '" + this.getId() + "')";  // sql command for inserting the new paid bill
        String sql2 = "delete from unpaid_bill where unpaid_id = '" + unpaidBill.getUnpaidId() + "'";

        try(Connection connection = DriverManager.getConnection(url, user, pass);
            Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Error in bill payment.");
        }

        return tempBill;
    }


    // setSSN
    // Sets the social security number for the customer.
    // The id is the customer id. Perhaps it can be a customer object.
    public void setSSN(String num){
        if(updateDatabase(this.getId(), "ssn", num)){  //updates the database
            ssn = num;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("SSN error");
        }
    }

    // setDOB
    // Sets the date of birth for the customer.
    public void setDOB(String date){
        if(updateDatabase(this.getId(), "dob", date)){  //updates the database
            dob = date;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("DOB error");
        }
    }

    // setEmail
    // Sets the email for the customer.
    public void setEmail(String em){
        if(updateDatabase(this.getId(), "email", em)){  //updates the database
            email = em;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Email error");
        }
    }

    // setAddress
    // Sets the address for the customer.
    public void setAddress(String addr){
        if(updateDatabase(this.getId(), "address", addr)){  //updates the database
            address = addr;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Address error");
        }
    }

    // setMailingAddress
    // Sets the mailing address for the customer.
    public void setMailingAddress(String mAddr){
        if(updateDatabase(this.getId(), "mailing_address", mAddr)){  //updates the database
            mailingAddress = mAddr;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Mailing address error");
        }
    }

    // setPhoneNumber
    // Sets the phone number for the customer.
    public void setPhoneNumber(String phone){
        if(updateDatabase(this.getId(), "phone_number", phone)){  //updates the database
            phoneNumber = phone;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Phone number error");
        }
    }

    // setCellPhoneNumber
    // Sets the cell phone number for the customer.
    public void setCellPhoneNumber(String cPhone){
        if(updateDatabase(this.getId(), "cellphone_number", cPhone)){  //updates the database
            cellPhoneNumber = cPhone;  //sets the current object
        }
        else{  //if there was an error updating the database
            System.out.println("Cell phone error");
        }
    }

    private boolean updateDatabase(String row, String col, String field){
        String url = "jdbc:mysql://localhost:3306/laravel_api";  //database url
        String user = "root";  //database username
        String pass = "kd(S(MavJCoLV1";  //database password

        //the sql statement to send to the database
        String sql = "update customers set " + col + " = '" + field + "' where customer_id = " + row;

        //attempts to connect the driver to the database
        try (Connection connection = DriverManager.getConnection(url, user, pass);
             Statement statement = connection.createStatement();
        )
        {
            statement.executeUpdate(sql); //executes the sql statement if the driver connected
            return true;  //returns success
        }

        catch (SQLException e) {  //if the driver could not connect
            return false;  //returns not successful with the connection
        }
    }
}