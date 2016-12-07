package training.tasks.unit8.task2.data.dao.pg;

import training.tasks.unit8.task2.data.dao.AlbumDao;
import training.tasks.unit8.task2.data.dto.AlbumDto;
import training.tasks.unit8.task2.data.dto.ArtistDto;
import training.tasks.unit8.task2.data.dto.TrackDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PgAlbumDao implements AlbumDao {

    private DataSource dataSource;

    public PgAlbumDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<AlbumDto> findByName(String albumName) throws SQLException {
        Optional<AlbumDto> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT al.id AS al_id," +
                            " al.name AS al_name," +
                            " al.artist_id AS al_artist_id," +
                            " ar.id AS ar_id," +
                            " ar.name AS ar_name" +
                            " FROM unit8db.album al" +
                            " JOIN unit8db.artist ar " +
                            " ON al.artist_id = ar.id" +
                            " WHERE al.id = (SELECT id FROM unit8db.album WHERE name = ?);"
            );

            query.setString(1, albumName);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final ArtistDto artist = ArtistDto.builder()
                        .id(resultSet.getInt("ar_id"))
                        .name(resultSet.getString("ar_name"))
                        .build();
                final AlbumDto album = AlbumDto.builder()
                        .id(resultSet.getInt("al_id"))
                        .name(resultSet.getString("al_name"))
                        .artist(artist)
                        .build();
                result = Optional.of(album);
            }
        }
        return result;
    }

    @Override
    public List<TrackDto> getTrackList(String albumName) throws SQLException {
        List<TrackDto> trackList = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT tr.id AS tr_id," +
                            " tr.position AS tr_pos," +
                            " tr.name AS tr_name," +
                            " tr.album_id AS tr_album_id" +
                            " FROM unit8db.track tr" +
                            " JOIN unit8db.album al" +
                            " ON tr.album_id = al.id" +
                            " WHERE al.id = (SELECT id FROM unit8db.album WHERE name = ?);"
            );
            query.setString(1, albumName);
            final ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                final TrackDto trackDto = TrackDto.builder()
                        .id(resultSet.getInt("tr_id"))
                        .position(resultSet.getInt("tr_pos"))
                        .name(resultSet.getString("tr_name"))
                        .albumId(resultSet.getInt("tr_album_id"))
                        .build();
                trackList.add(trackDto);
            }
        }
        return trackList;
    }

    @Override
    public int add(String artistName, String albumName) throws SQLException {
        int result = -1;
        if (artistExists(artistName)) {
            try (Connection connection = dataSource.getConnection()) {
                final String[] returnColumns = {"id"};
                final PreparedStatement query = connection.prepareStatement(
                        "INSERT INTO unit8db.album(id, name, artist_id)" +
                                " VALUES (nextval('unit8db.album_seq')," +
                                " ?," +
                                " (SELECT id FROM unit8db.artist WHERE name = ?));",
                        returnColumns
                );
                query.setString(1, albumName);
                query.setString(2, artistName);
                query.executeUpdate();
                final ResultSet generatedKeys = query.getGeneratedKeys();
                if (generatedKeys.next()) {
                    result = generatedKeys.getInt(1);
                }
            }
        }
        return result;
    }

    @Override
    public int delete(String artistName, String albumName) throws SQLException {
        int result = -1;
        if (artistExists(artistName)) {
            deleteTrackList(albumName);
            try (Connection connection = dataSource.getConnection()) {
                final PreparedStatement query = connection.prepareStatement(
                        "DELETE FROM unit8db.album" +
                                " WHERE id = (SELECT id FROM unit8db.album WHERE name = ?);"
                );
                query.setString(1, albumName);
                result = query.executeUpdate();
            }
        }
        return result;
    }

    private void deleteTrackList(String albumName) throws SQLException {
        final PgDaoFactory factory = new PgDaoFactory(dataSource);
        final List<TrackDto> trackList = factory.getAlbumDao().getTrackList(albumName);
        for (TrackDto track : trackList) {
            factory.getTrackDao().delete(albumName, track.getPosition());
        }
    }

    private boolean artistExists(String artistName) throws SQLException {
        final PgDaoFactory factory = new PgDaoFactory(dataSource);
        final Optional<ArtistDto> artist = factory.getArtistDao().findByName(artistName);
        return artist.isPresent();
    }
}
