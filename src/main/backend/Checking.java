package main.backend;

public class Checking extends FinancialAccount {
    Checking(double startingBalance, String cID) {
        create("1", "Checking", startingBalance, cID);
    }
}

