package main.backend;

public class Savings extends FinancialAccount {
    private double interestRate;
    Savings(double newInterestRate, double startingBalance) {
        interestRate = newInterestRate;
        create("0", "Savings", startingBalance);
    }

    public void addInterest() {
        double addedAmount = getBalance() * interestRate;
        deposit(addedAmount);
    }
}
