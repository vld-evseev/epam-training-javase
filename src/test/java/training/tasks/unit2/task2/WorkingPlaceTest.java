package training.tasks.unit2.task2;

import org.junit.Test;
import training.tasks.unit2.task2.stationery.*;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class WorkingPlaceTest {

    @Test
    public void workingPlaceTest() {
        WorkingPlace place = new WorkingPlace();
        place.addStationary(new Pen("Parker", 8.5));
        place.addStationary(new Pen("MontBlanc", 12.4));
        place.addStationary(new Notepad("Leuchtturm", 19.5));
        place.addStationary(new Pencil("Graph Gear", 10.7));
        place.addStationary(new Paper("Ballet", 2.8));

        assertEquals(totalPrice(place).toString(), "53.9");
    }

    private static BigDecimal totalPrice(WorkingPlace place) {
        BigDecimal total = BigDecimal.ZERO;
        for (Stationery stationery : place.getStationeries()) {
            total = total.add(stationery.getPrice());
        }
        return total;
    }

}