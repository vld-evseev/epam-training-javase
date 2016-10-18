package training.tasks.unit2.task3;

import training.tasks.unit2.task2.stationery.Stationery;

import java.util.ArrayList;
import java.util.List;

public class NoviceSuite {

    private List<Stationery> stationeryList = new ArrayList<>();

    public void addTool(Stationery tool) {
        stationeryList.add(tool);
    }

    public void removeTool(Stationery tool) {
        stationeryList.remove(tool);
    }

    public List<Stationery> getAllTools() {
        return stationeryList;
    }

}
