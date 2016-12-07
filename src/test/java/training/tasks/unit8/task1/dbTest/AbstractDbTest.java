package training.tasks.unit8.task1.dbTest;

import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;

public abstract class AbstractDbTest {

    @Data
    @Builder
    protected static class DbConfiguration {
        private final String url;
        private final String username;
        private final String password;
    }

    protected abstract DbConfiguration getDbConfiguration() throws ClassNotFoundException, IOException;

    @Test
    public void test() throws SQLException, ClassNotFoundException, IOException {
        final DbConfiguration config = getDbConfiguration();

        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
            final Statement statement = connection.createStatement();
            createAndPopulateSchema(statement);

            final ResultSet resultSet = statement.executeQuery("SELECT id, name FROM unit8schema.artist");

            while (resultSet.next()) {
                System.out.printf("AuthorDto[id: %d, name: %s]\n", resultSet.getInt("id"), resultSet.getString("name"));
            }


            PreparedStatement selectPreparedStatement =
                    connection.prepareStatement("SELECT name FROM unit8schema.artist" +
                            " WHERE id = ?");

            final String artist1 = getArtistNameById(selectPreparedStatement, 1);
            assertEquals(artist1, "Bob Dylan");
            System.out.println(artist1);

            PreparedStatement updatePreparedStatement =
                    connection.prepareStatement("UPDATE unit8schema.artist SET name = ?" +
                            " WHERE id = ?");

            updateArtistNameById(updatePreparedStatement, 2, "James Hetfield");
            final String artist2 = getArtistNameById(selectPreparedStatement, 2);
            assertEquals(artist2, "James Hetfield");
            System.out.println(artist2);

            PreparedStatement insertPreparedStatement =
                    connection.prepareStatement("INSERT INTO unit8schema.artist(id, name) " +
                            "VALUES (?, ?)");
            insertNewArtist(insertPreparedStatement, 7, "Ozzy Osbourne");

            final ResultSet resultSet2 = statement.executeQuery("SELECT id, name FROM unit8schema.artist");

            while (resultSet2.next()) {
                System.out.printf("Artist[id: %d, name: %s]\n", resultSet2.getInt("id"), resultSet2.getString("name"));
            }
        }
    }


    private void createAndPopulateSchema(Statement statement) throws SQLException {
        statement.execute("CREATE SCHEMA unit8schema;");

        statement.execute(
                "CREATE TABLE unit8schema.artist ( id INTEGER PRIMARY KEY, name VARCHAR(255) NOT NULL);"
        );

        statement.executeUpdate(
                "INSERT  INTO unit8schema.artist(id, name)" +
                        " SELECT 1, 'Bob Dylan'" +
                        " UNION ALL SELECT 2, 'Jimi Hendrix'" +
                        " UNION ALL SELECT 3, 'Bruce Springsteen'" +
                        " UNION ALL SELECT 4, 'Neil Young'" +
                        " UNION ALL SELECT 5, 'John Lennon'" +
                        " UNION ALL SELECT 6, 'Patti Smith'"
        );
    }

    private String getArtistNameById(PreparedStatement statement, int id) throws SQLException {
        statement.setInt(1, id);
        final ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getString("name");
    }

    private void updateArtistNameById(PreparedStatement statement, int id, String name) throws SQLException {
        statement.setInt(2, id);
        statement.setString(1, name);
        statement.executeUpdate();
    }

    private void insertNewArtist(PreparedStatement statement, int id, String name) throws SQLException {
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.executeUpdate();
    }
}
