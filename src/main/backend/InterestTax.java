package main.backend;

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
        firstName = first;
    }

    public void setLastName(String last){
        lastName = last;
    }

    public void setAddress(String addr){
        address = addr;
    }

    public void setPhoneNumber(String phone){
        phoneNumber = phone;
    }

    public void setInterestIncome(double interest){
        interestIncome = interest;
    }

    public void setCustomerId(String cID){
        customerId = cID;
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
}
