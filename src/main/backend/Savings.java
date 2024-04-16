package main.backend;

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
    }
}
