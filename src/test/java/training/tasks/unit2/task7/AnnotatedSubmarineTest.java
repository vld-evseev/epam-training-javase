package training.tasks.unit2.task7;

import org.junit.Test;

import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;

public class AnnotatedSubmarineTest {
    @Test
    public void start() throws Exception {
        AnnotatedSubmarine submarine = new AnnotatedSubmarine();
        submarine.startWithAnnotation();

        Class clazz = AnnotatedSubmarine.class;
        Annotation[] annotations = clazz.getAnnotations();

        for (Annotation annotation : annotations) {
            if (annotation instanceof CustomAnnotation) {
                CustomAnnotation cAnnotation = (CustomAnnotation) annotation;
                assertEquals(cAnnotation.value(), "annotated");
            }
        }
    }

}