package training.tasks.unit7.task1;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicBill {

    private AtomicLong balance;
    private AtomicLong limit;

    public AtomicBill(long balance) {
        this(balance, Long.MAX_VALUE);
    }

    public AtomicBill(long balance, long limit) {
        this.balance = new AtomicLong(balance);
        this.limit = new AtomicLong(limit);
    }

    public long getBalance() {
        return balance.get();
    }

    public boolean deposit(long amount) {
        final long val = balance.addAndGet(amount);
        if (val > limit.get()) {
            balance.addAndGet(-amount);
            return false;
        }

        return true;
    }

    public boolean withdraw(long amount) {
        final long val = balance.addAndGet(-amount);
        if (val < 0) {
            balance.addAndGet(amount);
            return false;
        }

        return true;
    }


}
