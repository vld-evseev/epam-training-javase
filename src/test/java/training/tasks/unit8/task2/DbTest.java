package training.tasks.unit8.task2;

import org.junit.Test;

import java.sql.*;

public class DbTest {

    @Test
    public void test1() throws SQLException, ClassNotFoundException {
        final String url = "jdbc:postgresql://localhost:5432/postgres";
        final String user = "postgres";
        final String pass = "pass";

        Class.forName("org.postgresql.Driver");


        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            final PreparedStatement dropSchema = connection.prepareStatement("DROP SCHEMA IF EXISTS unit8db CASCADE;");
            dropSchema.execute();

            final PreparedStatement createSchema = connection.prepareStatement("CREATE SCHEMA unit8db;");

            createSchema.execute();
            final PreparedStatement createArtistTable = connection.prepareStatement(
                    "CREATE TABLE unit8db.artist (id INTEGER PRIMARY KEY, name VARCHAR(255) NOT NULL);"
            );

            createArtistTable.execute();

            final PreparedStatement insertAuthor = connection.prepareStatement(
                    "INSERT INTO unit8db.artist(id, name) VALUES (1, 'Bob Dylan');"
            );

            final int cnt = insertAuthor.executeUpdate();
            System.out.println(cnt);

            final PreparedStatement selectArtists = connection.prepareStatement("SELECT id, name FROM unit8db.artist;");
            final ResultSet resultSet = selectArtists.executeQuery();

            while (resultSet.next()) {
                System.out.printf("artist[id: %d, name: %s]", resultSet.getInt("id"), resultSet.getString("name"));
            }


        }
    }
}