package training.tasks.unit3.task1;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

public class CrazyLoggerTest {

    @Test
    public void test() {
        CrazyLogger logger = new CrazyLogger();
        logger.log("aaaaaa");
        logger.log("bbbbb");
        logger.log("some ERROR occurred at the point " + ThreadLocalRandom.current().nextInt());
        logger.log("some error occurred at the point " + ThreadLocalRandom.current().nextInt());
        logger.log("some error occurred at the point " + ThreadLocalRandom.current().nextInt());

        List<String> searchCase1 = logger.search("error");
        List<String> searchCase2 = logger.search("aaa");
        List<String> searchCase3 = logger.search("13");

        assertTrue(searchCase1.size() == 3);
        for (String str : searchCase1) {
            System.out.println(str);
        }
        System.out.println("----------------");

        assertTrue(searchCase2.size() == 1);
        for (String str : searchCase2) {
            System.out.println(str);
        }
        System.out.println("----------------");

        assertTrue(searchCase3.size() > 0);
        for (String str : searchCase3) {
            System.out.println(str);
        }
        System.out.println("----------------");

    }

}