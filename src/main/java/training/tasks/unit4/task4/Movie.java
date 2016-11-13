package training.tasks.unit4.task4;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class Movie implements Serializable {

    private String name;
    private String year;
    private String genre;
    private Set<Actor> actorList = new HashSet<>();

    public Movie(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public void addActor(Actor actor) {
        actorList.add(actor);
    }

    public void removeActor(Actor actor) {
        actorList.remove(actor);
    }

}
