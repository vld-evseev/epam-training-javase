package training.tasks.unit4.task4;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializedMoviesDatabaseTest {

    private final static String OUTPUT_FILES_PATH = "out/unit4/task4/";
    private final static String EXTENSION = ".sz";

    @Test
    public void test() {
        List<Movie> moviesCollection = populateMoviesCollection();

        Serializer serializer = new Serializer();
        for (Movie movie : moviesCollection) {
            serializer.writeTo(movie, OUTPUT_FILES_PATH + movie.getName() + EXTENSION);
        }

        File[] files = new File(OUTPUT_FILES_PATH).listFiles();

        List<Movie> deserializedMovies = new ArrayList<>();
        Deserializer deserializer = new Deserializer();
        for (File file : files) {
            Object obj = deserializer.read(file.getAbsolutePath());
            if (obj instanceof Movie) {
                Movie deserializedMovie = (Movie) obj;
                deserializedMovies.add(deserializedMovie);
            }
        }

        assertEquals(moviesCollection, deserializedMovies);
        assertEquals(deserializedMovies.get(0).getYear(), "1990");

        /*
            Edit one movie from deserialized list,
            write it back, then deserialize it again
         */
        Movie movie = deserializedMovies.get(0);
        movie.setYear("2000");
        movie.addActor(new Actor("new Actor", "male"));

        serializer.writeTo(movie, OUTPUT_FILES_PATH + movie.getName() + EXTENSION);

        Object obj = deserializer.read(OUTPUT_FILES_PATH + movie.getName() + EXTENSION);
        if (obj instanceof Movie) {
            Movie deserialized = (Movie) obj;
            assertEquals(deserialized.getYear(), "2000");
            assertEquals(deserialized.getActorList().size(), 4);

            System.out.println(deserialized.toString());
        }
    }

    private List<Movie> populateMoviesCollection() {
        Actor actor1 = new Actor("Actor1", "male");
        Actor actor2 = new Actor("Actor2", "male");
        Actor actor3 = new Actor("Actress3", "female");
        Actor actor4 = new Actor("Actor4", "male");
        Actor actor5 = new Actor("Actress5", "female");

        Movie movie1 = new Movie("Movie1", "1990");
        movie1.addActor(actor1);
        movie1.addActor(actor2);
        movie1.addActor(actor5);
        movie1.setGenre("Horror");

        Movie movie2 = new Movie("Movie2", "2011");
        movie2.addActor(actor3);
        movie2.addActor(actor1);
        movie2.addActor(actor4);
        movie2.setGenre("Road Movie");

        Movie movie3 = new Movie("Movie3", "1953");
        movie3.addActor(actor1);
        movie3.addActor(actor3);
        movie3.addActor(actor5);
        movie3.setGenre("Adventure");

        List<Movie> movies = new ArrayList<>();
        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);

        return movies;
    }

}