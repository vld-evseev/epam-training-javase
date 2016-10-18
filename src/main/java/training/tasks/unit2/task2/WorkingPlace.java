package training.tasks.unit2.task2;

import training.tasks.unit2.task2.stationery.Stationery;

import java.util.ArrayList;
import java.util.List;

public class WorkingPlace {

    private List<Stationery> stationeries;

    public WorkingPlace() {
        stationeries = new ArrayList<>();
    }

    public List<Stationery> getStationeries() {
        return stationeries;
    }

    public void addStationary(Stationery stationery) {
        stationeries.add(stationery);
    }


}
