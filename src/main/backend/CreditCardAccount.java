package main.backend;

public class CreditCardAccount extends FinancialAccount{

    private double amountDue;
    private double interestRate;
    private double penaltyInterest;
    private double penaltyFee;
    private int missedPayments;

    CreditCardAccount(double newAmountDue, double newInterestRatePercent, double newPenaltyInterestPercent, double newPenaltyFee, double startingBalance) {
        create("1", "Credit Card", startingBalance);
        amountDue = newAmountDue;
        interestRate = newInterestRatePercent / 100;           // convert percent to decimal equivalent
        penaltyInterest = newPenaltyInterestPercent / 100;     // convert percent to decimal equivalent
        penaltyFee = newPenaltyFee;
        missedPayments = 0;
    }

    public void subtractBalance() {
        if (amountDue < getBalance()) {
            withdraw(amountDue);
        } else {
            amountDue = amountDue - getBalance();  // remove as much of amount due as you can from balance
            zeroOutBalance();                      // zero out balance
        }
    }

    public void addAmountDue(double charge) {
        amountDue += charge * interestRate;
    }

    public void addUnpaidBalance(String type) {
        if (type.equals("interest rate")) {
            amountDue = amountDue * penaltyInterest;
        } else if (type.equals("fee")) {
            amountDue = amountDue + penaltyFee;
        }
        missedPayments++;
    }

    public void alertTellers() {
        if (missedPayments > 5) { // arbitrary missed payment count that can be changed
            System.out.println("Tellers noted of 5 missed payments");
        }
    }

}
