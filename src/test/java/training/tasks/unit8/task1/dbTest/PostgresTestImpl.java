package training.tasks.unit8.task1.dbTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import ru.yandex.qatools.embed.postgresql.PostgresExecutable;
import ru.yandex.qatools.embed.postgresql.PostgresProcess;
import ru.yandex.qatools.embed.postgresql.PostgresStarter;
import ru.yandex.qatools.embed.postgresql.config.PostgresConfig;

import java.io.IOException;
import java.util.Arrays;

public class PostgresTestImpl extends AbstractDbTest {

    private static PostgresProcess process;

    private final static String name = "unit8_postgres";
    private final static String username = "user";
    private final static String password = "pass";
    private static String url;

    @BeforeClass
    public static void startup() throws IOException {
        final PostgresStarter<PostgresExecutable, PostgresProcess> runtime = PostgresStarter.getDefaultInstance();
        final PostgresConfig config = PostgresConfig.defaultWithDbName(name, username, password);
        // pass info regarding encoding, locale, collate, ctype, instead of setting global environment settings
        config.getAdditionalInitDbParams().addAll(Arrays.asList(
                "-E", "UTF-8",
                "--locale=en_US.UTF-8",
                "--lc-collate=en_US.UTF-8",
                "--lc-ctype=en_US.UTF-8"
        ));
        PostgresExecutable exec = runtime.prepare(config);
        process = exec.start();

        url = String.format("jdbc:postgresql://%s:%s/%s",
                config.net().host(),
                config.net().port(),
                config.storage().dbName()
        );

        System.out.println(url);
    }

    @AfterClass
    public static void teardown() {
        process.stop();
    }

    @Override
    protected DbConfiguration getDbConfiguration() throws ClassNotFoundException, IOException {
        Class.forName("org.postgresql.Driver");
        return DbConfiguration.builder()
                .url(url)
                .username(username)
                .password(password)
                .build();
    }
}
