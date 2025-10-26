package classes;

import java.time.LocalDateTime;
import java.util.Random;

public class Transaction
{
    private static Random r = new Random();

    private final int id;
    private String type;
    private float amount;
    private LocalDateTime date;

    public Transaction(
        String type,
        float amount
        ) {
            this.id = 100_000_000 + r.nextInt(900_000_000);
            this.type = type;
            this.amount = amount;
            this.date = LocalDateTime.now();
        }
    
    public int getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public float getAmount() {
        return this.amount;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void getTransaction() {
        System.out.println(id + " | " + type + " | " + amount + "$ | " + date);
    }

}