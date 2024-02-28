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
    public static void main(String[] args) {
        Savings testAccount = new Savings(0.00502, 2343.15);
        testAccount.addInterest();
    }
}
