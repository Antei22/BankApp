package classes;

import java.util.LinkedHashMap;

public class Account 
{
    private static int nextId = 1001;

    private int id;
    private String owner;
    private float balance;
    private LinkedHashMap<Integer, Transaction> transactions;
    
    // Constructor 
    public Account(
        String owner
        ) {
            this.id = nextId++;
            this.owner = owner;
            this.balance = 0.0f;
            this.transactions = new LinkedHashMap<>();
        }
    
    // Getters

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public float getBalance() {
        return balance;
    }

    // Methods 

    public void deposit(float amount) {
        if (amount > 0) {
            balance += amount;
            Transaction t = new Transaction("deposit", amount);
            transactions.put(t.getId(), t);
            System.out.println("\nYour account has been credited with " + amount + "$.");
            System.out.println("Transaction details:");
            t.getTransaction();

        } else {
            System.out.println("ERROR! - The deposit amount must be positive.");
        }
    }

    public void withdraw(float amount) {
        if (amount > 0) {
            if (balance >= amount) {
            balance -= amount;
            Transaction t = new Transaction("withdraw", amount);
            transactions.put(t.getId(), t);
            System.out.println("\nYour account has been debited with " + amount + "$.");
            System.out.println("Transaction details:");
            t.getTransaction();
            } else {
                System.out.println("Transaction declined: insufficient funds.");
            }

        } else {
            System.out.println("ERROR! - The withdraw amount must be positive.");
        }
    }

    public void transfer(Account to, float amount) {
        if (amount > 0) {
            if (this.balance >= amount) {
            this.balance -= amount;
            Transaction tOut = new Transaction("transfer out", amount);
            this.transactions.put(tOut.getId(), tOut);
            
            to.balance += amount;
            Transaction tIn = new Transaction("transfer in", amount);
            to.transactions.put(tIn.getId(), tIn);
            
            System.out.println("\nTransfer completed succesfully");
            System.out.println("Transaction details:");
            tOut.getTransaction();
            } else {
                System.out.println("Transaction declined: insufficient funds.");
            }

        } else {
            System.out.println("ERROR! - The transfer amount must be positive.");
        }
    }

    public void showBalance() {
        System.out.printf("Balance: %.2f$ \n", balance);
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("\nNo transactions yet.");
        } else {
            System.out.println("\nTransaction history:");
            for (Transaction t : transactions.values()) {
                t.getTransaction();
            }
        }
    }

    public void findTransactionsById(int id) {
        Transaction t = transactions.get(id);
        if (t != null) {
            System.out.println("\n");
            t.getTransaction();
        } else {
            System.out.println("\nNo transaction found with ID " + id);
        }
    }

    public void findTransactionsByType(String type) {
        System.out.println("\n");
        int count = 0;
        for (Transaction t : transactions.values())  {
            if (t.getType().equalsIgnoreCase(type)) {
                t.getTransaction();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No transaction of type " + type + " found.");
        }
    }

    public void findTransactionsByTime(String date) {
        System.out.println("\n");
        int count = 0;
        for (Transaction t : transactions.values())  {
            if (t.getDate().toString().startsWith(date)) {
                t.getTransaction();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No transaction on " + date + " found.");
        }
    }

    public void findTransactionsBySumm(float min, float max) {
        System.out.println("\n");
        int count = 0;
        for (Transaction t : transactions.values())  {
            float amount = t.getAmount();
            if (amount >= min && amount <= max) {
                t.getTransaction();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No transaction found in this range.");
        }
    }
}