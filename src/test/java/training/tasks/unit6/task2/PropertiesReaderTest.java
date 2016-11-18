package training.tasks.unit6.task2;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class PropertiesReaderTest {

    private static final String TEST_PROPS = "unit5/task2/test.properties";

    @Test
    public void read() throws Exception {
        PropertiesReader propertiesReader = new PropertiesReader(TEST_PROPS);

        Map<String, String> props = propertiesReader.read();
        props.put("key1", "value666");
        final Set<Map.Entry<String, String>> entries = props.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

    }

}