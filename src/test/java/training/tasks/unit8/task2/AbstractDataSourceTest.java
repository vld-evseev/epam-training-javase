package training.tasks.unit8.task2;

import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import training.tasks.unit8.task2.data.dao.pg.PgDaoFactory;
import training.tasks.unit8.task2.data.dto.AlbumDto;
import training.tasks.unit8.task2.data.dto.ArtistDto;
import training.tasks.unit8.task2.data.dto.TrackDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractDataSourceTest {

    private final Flyway flyway;

    protected abstract DataSource getDataSource();

    protected abstract void dropSchema() throws SQLException;

    AbstractDataSourceTest() {
        flyway = new Flyway();
        flyway.setDataSource(getDataSource());
        flyway.setLocations("db.migrate");
    }

    @Before
    public void before() throws SQLException {
        dropSchema();
        flyway.migrate();
    }

    @After
    public void after() throws SQLException {
        flyway.clean();
        dropSchema();
    }

    @Test
    public void artistDaoTest() throws SQLException {

        PgDaoFactory factory = new PgDaoFactory(getDataSource());
        final Optional<ArtistDto> artist = factory.getArtistDao().findByName("Bob Dylan");
        if (artist.isPresent()) {
            System.out.println(artist.get().getId() + " : " + artist.get().getName());
        }

        final List<AlbumDto> albums = factory.getArtistDao().getAllAlbums("Bob Dylan");
        for (AlbumDto album : albums) {
            System.out.printf("%s : [id: %d, title: %s]\n",
                    album.getArtist().getName(),
                    album.getId(),
                    album.getName()
            );
        }
        assertEquals(albums.size(), 3);

        final int newGeneratedKey = factory.getArtistDao().add("Can");
        assertEquals(newGeneratedKey, 4);

        final Optional<ArtistDto> newArtist = factory.getArtistDao().findByName("Can");
        assertEquals(newArtist.get().getName(), "Can");

        System.out.println(newArtist.get().getId() + " : " + newArtist.get().getName());

        final int deleted = factory.getArtistDao().deleteByName("Ramones");
        System.out.println(deleted);

        try (Connection connection = getDataSource().getConnection()) {
            final PreparedStatement selectArtists = connection.prepareStatement("SELECT id, name FROM unit8db.artist");
            final ResultSet resultSet = selectArtists.executeQuery();

            while (resultSet.next()) {
                System.out.printf("artist[id: %d, name: %s]\n", resultSet.getInt("id"), resultSet.getString("name"));
            }
        }
    }

    @Test
    public void albumDaoTest() throws SQLException {
        PgDaoFactory factory = new PgDaoFactory(getDataSource());
        final Optional<AlbumDto> album = factory.getAlbumDao().findByName("The Piper at the Gates of Dawn");
        assertTrue(album.isPresent());

        assertEquals(album.get().getName(), "The Piper at the Gates of Dawn");

        final List<TrackDto> trackList = factory.getAlbumDao().getTrackList("The Piper at the Gates of Dawn");
        assertEquals(trackList.size(), 5);
        for (TrackDto trackDto : trackList) {
            System.out.println(trackDto);
        }
        final List<TrackDto> emptyTrackList = factory.getAlbumDao().getTrackList("null");
        assertEquals(emptyTrackList.size(), 0);

        final int newAlbum = factory.getAlbumDao().add("Pink Floyd", "The Wall");
        System.out.println(newAlbum);

        final Optional<AlbumDto> albumTheWall = factory.getAlbumDao().findByName("The Wall");
        assertTrue(albumTheWall.isPresent());

        final int fakeAlbum = factory.getAlbumDao().add("fake artist", "fake album");
        System.out.println(fakeAlbum);

        final int delAlbum = factory.getAlbumDao().delete("Bob Dylan", "Freewheeling Bob Dylan");
        System.out.println(delAlbum);
    }

    @Test
    public void trackDaoTest() throws SQLException {
        final PgDaoFactory factory = new PgDaoFactory(getDataSource());

        final Optional<TrackDto> foundTrack = factory.getTrackDao().findByName("Flaming");
        assertTrue(foundTrack.isPresent());

        final int addedTrack = factory.getTrackDao()
                .add("The Piper at the Gates of Dawn", "Take Up Thy Stethoscope and Walk", 6);
        System.out.println(addedTrack);

        final int deletedTrack = factory.getTrackDao().delete("The Piper at the Gates of Dawn", 1);
        System.out.println(deletedTrack);

        final List<TrackDto> trackList = factory.getAlbumDao().getTrackList("The Piper at the Gates of Dawn");
        for (TrackDto trackDto : trackList) {
            System.out.println(trackDto);
        }
    }

}
