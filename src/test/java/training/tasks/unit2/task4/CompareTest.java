package training.tasks.unit2.task4;

import org.junit.Test;
import training.tasks.unit2.task2.stationery.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareTest {

    @Test
    public void compareTest() {
        List<Stationery> listByName = createToolList();
        Collections.sort(listByName);

        System.out.println("BY NAME");
        printList(listByName);

        List<Stationery> listByPrice = createToolList();
        System.out.println("\nBY PRICE");
        Collections.sort(listByPrice, new Stationery.PRICE_COMPARATOR());
        printList(listByPrice);

        List<Stationery> listByNameAndPrice = createToolList();
        System.out.println("\nBY NAME AND PRICE");
        Collections.sort(listByNameAndPrice, new Stationery.NAME_AND_PRICE_COMPARATOR());
        printList(listByNameAndPrice);
    }

    private void printList(List<Stationery> list) {
        for (Stationery stationery : list) {
            System.out.println(stationery.toString());
        }
    }

    private List<Stationery> createToolList() {
        List<Stationery> tools = new ArrayList<>();
        tools.add(new Pen("Pen1", 10.5));
        tools.add(new Pen("Pen2", 12.3));
        tools.add(new Pen("Pen3", 8.5));
        tools.add(new Paper("Paper", 2.1));
        tools.add(new Pencil("Pencil1", 4.5));
        tools.add(new Pencil("Pencil2", 1.9));
        tools.add(new Notepad("Notepad1", 15.8));
        tools.add(new Notepad("Notepad2", 20.1));
        tools.add(new Notepad("Notepad3", 5));
        return tools;
    }


}
