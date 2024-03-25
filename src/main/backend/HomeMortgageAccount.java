package main.backend;

public class HomeMortgageAccount extends FinancialAccount {

    private double totalMortgage;
    private double mortgageLeft;
    private double monthlyDue;
    private double unpaidBalance;
    private int missedPayments;

    HomeMortgageAccount(double newTotalMortgage, double newMonthlyDue, double startingBalance) {
        create("0", "Home Mortgage", startingBalance);
        totalMortgage = newTotalMortgage;
        mortgageLeft = newTotalMortgage;
        monthlyDue = newMonthlyDue;
        unpaidBalance = 0;
        missedPayments = 0;
    }

    public void subtractBalance() {
        if (getBalance() > monthlyDue) {
            withdraw(monthlyDue);
            if (unpaidBalance > 0) {
                if (unpaidBalance > getBalance()) {
                    unpaidBalance = unpaidBalance - getBalance();
                    zeroOutBalance();
                }
            }
        } else { // not enough in balance for monthly due
            double amountLeft = monthlyDue - getBalance(); // get how much the customer was short
            addUnpaidBalance(amountLeft);                  // add that amount to the unpaid balance with 5% penalty
            zeroOutBalance();                              // empty out balance (since it was used to pay part of monthly amount)
        }
    }

    public void addUnpaidBalance(double amount) {
        unpaidBalance += amount * 1.05; // penalty of 5%
        missedPayments++;
        alertTellers();  // only alerts if a missed payment threshold is met, check function
    }

    public void alertTellers() {
        if (missedPayments > 5) { // arbitrary missed payment count that can be changed
            System.out.println("Tellers noted of 5 missed payments");
        }
    }



}
