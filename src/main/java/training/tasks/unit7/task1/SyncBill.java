package training.tasks.unit7.task1;

import java.util.concurrent.atomic.AtomicInteger;

public class SyncBill {

    private long balance;
    private long limit;
    private final static AtomicInteger gen = new AtomicInteger(0);
    private final int id = gen.incrementAndGet();

    public SyncBill(long balance) {
        this(balance, Long.MAX_VALUE);
    }

    public SyncBill(long balance, long limit) {
        this.balance = balance;
        this.limit = limit;
    }

    public long getBalance() {
        return balance;
    }

    public synchronized boolean deposit(long amount) {
        final long local = this.balance;
        if (limit - amount < local) {
            return false;
        }

        this.balance = local + amount;
        return true;
    }

    public synchronized boolean withdraw(long amount) {
        final long local = this.balance;
        if (local < amount) {
            return false;
        }

        balance = local - amount;
        return true;
    }

    public int getId() {
        return id;
    }
}
