package training.tasks.unit2.task3;

import org.junit.Test;
import training.tasks.unit2.task2.stationery.*;

import static org.junit.Assert.assertEquals;

public class NoviceSuiteTest {

    @Test
    public void noviceSuiteTest() {
        NoviceSuite noviceSuite = new NoviceSuite();
        noviceSuite.addTool(new Notepad("Notepad", 0));
        noviceSuite.addTool(new Paper("Paper", 0));
        noviceSuite.addTool(new Pen("Pen", 0));
        noviceSuite.addTool(new Pencil("Pencil", 0));

        assertEquals(noviceSuite.getAllTools().size(), 4);

        for (Stationery stationery : noviceSuite.getAllTools()) {
            System.out.println(stationery.getName());
        }
    }

}