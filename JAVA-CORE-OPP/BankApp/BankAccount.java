package BankApp;

public class BankAccount {

    private int accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;

    public BankAccount(int accountNumber,
                       String accountHolderName,
                       String accountType,
                       double balance) {

        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = balance;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public String getAccountType() {
        return accountType;
    }
    public double getBalance() {
        return balance;
    }
    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount >balance) {
            throw new InsufficientBalanceException("Insufficient balance in account " + this.accountNumber);
        }
        balance -= amount;
    }
     public void displayAccountDetails() {

        System.out.println("---------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Account Type   : " + accountType);
        System.out.println("Balance        : Rs." + balance);
    }
}


