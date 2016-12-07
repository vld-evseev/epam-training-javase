package training.tasks.unit8.task2;

import org.junit.BeforeClass;
import org.postgresql.ds.PGPoolingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PostgresDataSourceTest extends AbstractDataSourceTest {

    private static DataSource dataSource;

    @BeforeClass
    public static void startup() {
        PGPoolingDataSource dataSource = new PGPoolingDataSource();
        dataSource.setServerName("localhost");
        dataSource.setDatabaseName("unit8db");
        dataSource.setUser("postgres");
        dataSource.setPassword("pass");
        dataSource.setInitialConnections(2);
        dataSource.setMaxConnections(4);
        PostgresDataSourceTest.dataSource = dataSource;
    }

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @Override
    protected void dropSchema() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement statement =
                    connection.prepareStatement("DROP SCHEMA IF EXISTS unit8db CASCADE;");
            statement.execute();
        }
    }
}
