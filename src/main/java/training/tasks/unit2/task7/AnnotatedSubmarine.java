package training.tasks.unit2.task7;

import training.tasks.unit2.task6.NuclearSubmarine;

/**
 * Represents an annotated subclass of NuclearSubmarine
 */

@CustomAnnotation(value = "annotated")
public class AnnotatedSubmarine extends NuclearSubmarine {

    public void startWithAnnotation() {
        start();
    }

}
