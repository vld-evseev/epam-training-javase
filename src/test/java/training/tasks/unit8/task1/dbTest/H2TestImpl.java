package training.tasks.unit8.task1.dbTest;

public class H2TestImpl extends AbstractDbTest {


    @Override
    protected DbConfiguration getDbConfiguration() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DbConfiguration.builder()
                .url("jdbc:h2:mem:unit8_mem")
                .username("user")
                .password("pass")
                .build();
    }
}
