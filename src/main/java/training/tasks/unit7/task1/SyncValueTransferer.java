package training.tasks.unit7.task1;

public class SyncValueTransferer {

    public SyncValueTransferer() {
    }

    public synchronized void transfer(SyncBill from, SyncBill to, long amount) {
        final SyncBill bill1;
        final SyncBill bill2;

        System.out.println("-----------------------");
        System.out.println("Before:");
        System.out.println("b1: " + from.getBalance());
        System.out.println("b2: " + to.getBalance());

        if (from.getId() < to.getId()) {
            bill1 = from;
            bill2 = to;
        } else {
            bill1 = to;
            bill2 = from;
        }

        synchronized (bill1) {
            synchronized (bill2) {
                if (from.withdraw(amount)) {
                    if (!to.deposit(amount)) {
                        from.deposit(amount);
                    }
                }
            }
        }

        System.out.println("+++++++++++++++++++++");
        System.out.println("After:");
        System.out.println("amount: " + amount);
        System.out.println("b1: " + bill1.getBalance());
        System.out.println("b2: " + bill2.getBalance());

    }

}
