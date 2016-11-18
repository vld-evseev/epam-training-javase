package training.tasks.unit7.task1;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SyncValueTransfererTest {

    private static final String TEST_TRANSFER_DATA = "unit7/task1/transfer_data.txt";
    private final Object flag = new Object();

    @Test
    public void test2() {
        List<Long> data = readTransferData();
        long b1 = 1000;
        long b2 = 1000;

        for (Long val : data) {
            if (val < 0) {
                if (b1 - val > 0) {
                    b1 -= Math.abs(val);
                    b2 += Math.abs(val);
                }
            } else {
                if (b2 - val > 0) {
                    b1 += Math.abs(val);
                    b2 -= Math.abs(val);
                }
            }
        }

        System.out.println("b1 = " + b1 + "; b2 = " + b2);

    }

    /*
        Если значение > 0 -> списываем со 2го счета, переводим на 1й
        Если значение < 0 -> списываем со 1го счета, переводим на 2й
     */

    @Test
    public void syncTest() throws InterruptedException {
        final SyncBill bill1 = new SyncBill(1000, 10000);
        final SyncBill bill2 = new SyncBill(1000, 5000);


        List<Long> data = readTransferData();

        List<Long> subDataList1 = Collections.synchronizedList(data.subList(0, data.size() / 2));
        List<Long> subDataList2 = Collections.synchronizedList(data.subList(data.size() / 2, data.size()));

        System.out.println(subDataList1);
        System.out.println(subDataList2);

        System.out.println("from = " + bill1.getBalance());
        System.out.println("to = " + bill2.getBalance());

        Runnable transferTask1 = syncTransfer(bill1, bill2, subDataList1);
        Runnable transferTask2 = syncTransfer(bill1, bill2, subDataList2);


        Thread thread1 = new Thread(transferTask1);
        Thread thread2 = new Thread(transferTask2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("bill1 = " + bill1.getBalance());
        System.out.println("bill2 = " + bill2.getBalance());

    }

    private Runnable syncTransfer(SyncBill bill1, SyncBill bill2, List<Long> values) {
        return new Runnable() {
            @Override
            public void run() {
                SyncValueTransferer transferer = new SyncValueTransferer();

                synchronized (flag) {
                    for (Long value : values) {
                        if (value > 0) {
                            transferer.transfer(bill2, bill1, Math.abs(value));
                        } else {
                            transferer.transfer(bill1, bill2, Math.abs(value));
                        }
                    }
                }
            }
        };
    }

    @Test
    public void concurTest() throws InterruptedException {
        List<Long> data = readTransferData();

        AtomicBill bill1 = new AtomicBill(1000);
        AtomicBill bill2 = new AtomicBill(100);

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurValueTranferer tranferer = new ConcurValueTranferer();
                for (int i = 0; i < 100; i++) {
                    tranferer.transfer(bill1, bill2, 1);
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurValueTranferer tranferer = new ConcurValueTranferer();
                for (int i = 0; i < 100; i++) {
                    tranferer.transfer(bill2, bill1, 1);
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(bill1.getBalance());
        System.out.println(bill2.getBalance());

    }


    private List<Long> readTransferData() {
        final List<Long> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path(TEST_TRANSFER_DATA)))) {
            while (br.ready()) {
                final String line = br.readLine();
                Long value = null;
                try {
                    value = Long.valueOf(line);
                } catch (NumberFormatException ignored) {
                }
                if (value != null) {
                    data.add(value);
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return data;
    }

    private String path(String filePath) {
        try {
            final URI io = ClassLoader.getSystemResource(filePath).toURI();
            return io.getPath();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(filePath + " is incorrect");
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }
}

