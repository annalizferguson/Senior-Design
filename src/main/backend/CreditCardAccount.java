package main.backend;

public class CreditCardAccount extends FinancialAccount{

    private double amountDue;
    private double interestRate; // interest rate in decimal
    private double penaltyInterest;
    private double penaltyFee;
    private int missedPayments;

    CreditCardAccount(double newAmountDue, double newInterestRatePercent, double newPenaltyInterestPercent, double newPenaltyFee, double startingBalance, String cID) {
        create("1", "Credit Card", startingBalance, cID);
        amountDue = newAmountDue;
        interestRate = newInterestRatePercent / 100;           // convert percent to decimal equivalent
        penaltyInterest = newPenaltyInterestPercent / 100;     // convert percent to decimal equivalent
        penaltyFee = newPenaltyFee;
        missedPayments = 0;
    }

    /**
     * subtracts the amount due from the balance
     * if there isn't enough in the balance for the full amount due, remove as much as you can and leave the rest in amountDue
     */
    public void subtractBalance() {
        if (amountDue < getBalance()) {
            withdraw(amountDue);
        } else {
            amountDue = amountDue - getBalance();  // remove as much of amount due as you can from balance
            zeroOutBalance();                      // zero out balance
        }
    }

    /**
     * adds to the amount due from a credit card usage with the interestRate applied
     * @param charge - how much the credit card was charged
     */
    public void addAmountDue(double charge) {
        amountDue += charge * interestRate;
    }

    /**
     * applies a penalty to the unpaid balance determined by flat fee or by a percentage
     * @param type
     */
    public void addUnpaidBalance(String type) {
        if (type.equals("interest rate")) {
            amountDue = amountDue * penaltyInterest;
        } else if (type.equals("fee")) {
            amountDue = amountDue + penaltyFee;
        }
        missedPayments++;
    }

    /**
     * checks if missedPayments reaches a certain threshold and alerts tellers if it does
     * currently only prints a log
     */
    public void alertTellers() {
        if (missedPayments > 5) { // arbitrary missed payment count that can be changed
            System.out.println("Tellers noted of 5 missed payments");
        }
    }

}
