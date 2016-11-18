package training.tasks.unit7.task2;

import org.junit.Test;

public class ConcurPropertyReaderTest {

    private static final String TEST_PROPS = "unit5/task2/test.properties";

    private final String[] keys = {"key1", "key2", "key3", "key4", "key5", "key6", "key7"};

    @Test
    public void test() throws InterruptedException {
        final ConcurPropertyReader propertyReader1 = new ConcurPropertyReader(TEST_PROPS);
        final ConcurPropertyReader propertyReader2 = new ConcurPropertyReader(TEST_PROPS);

        final Runnable task1 = task(propertyReader1);
        final Runnable task2 = task(propertyReader2);

        final Thread thread1 = new Thread(task1);
        final Thread thread2 = new Thread(task2);

        thread1.setName("t1");
        thread2.setName("t2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();


    }

    private Runnable task(ConcurPropertyReader reader) {
        return new Runnable() {
            @Override
            public void run() {
                for (String key : keys) {
                    final String value = reader.readValue(key);
                    System.out.println(Thread.currentThread().getName() + " : " + value);
                }
            }
        };
    }


}