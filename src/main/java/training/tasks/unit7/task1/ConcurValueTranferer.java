package training.tasks.unit7.task1;

public class ConcurValueTranferer {

    public void transfer(AtomicBill from, AtomicBill to, long amount) {
        if (from.withdraw(amount)) {
            if (!to.deposit(amount)) {
                from.deposit(amount);
            }
        }
    }

}
