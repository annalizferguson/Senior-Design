package main.backend;

public class MoneyMarket extends FinancialAccount {
    private double interestRate;
    private int transactionCount;

    private int transactionLimit = 6;

    MoneyMarket(double newInterestRate, double startingBalance) {
        create("0", "Money Market", startingBalance);
        interestRate = newInterestRate;
        transactionCount = 0;
    }

    public int deposit(double amount) {
        if (transactionCount == transactionLimit) {
            System.out.println("Deposit Failed: Transaction Limit Already Reached");
            return 2;
        } else {
            super.deposit(amount);
            transactionCount++;
            return 0;
        }
    }

    public int withdraw(double amount) {
        if (transactionCount == transactionLimit) {
            System.out.println("Deposit Failed: Transaction Limit Already Reached");
            return 2;
        } else {
            int result = super.withdraw(amount);
            if (result == 1) {
                return 1;
            } else {
                transactionCount++;
                return 0;
            }
        }
    }



    public void addInterest() {
        double addedAmount = getBalance() * interestRate;
        deposit(addedAmount);
    }

}
