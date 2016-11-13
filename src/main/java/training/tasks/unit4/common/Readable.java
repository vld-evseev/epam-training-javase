package training.tasks.unit4.common;

import java.net.URI;
import java.net.URISyntaxException;

public interface Readable {

    void read();

    default String path(String filePath) {
        try {
            final URI io = ClassLoader.getSystemResource(filePath).toURI();
            return io.getPath();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

}
