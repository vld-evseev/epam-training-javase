package training.tasks.unit8.task2.data.dao.pg;

import training.tasks.unit8.task2.data.dao.ArtistDao;
import training.tasks.unit8.task2.data.dto.AlbumDto;
import training.tasks.unit8.task2.data.dto.ArtistDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PgArtistDao implements ArtistDao {

    private DataSource dataSource;

    public PgArtistDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<ArtistDto> findByName(String artistName) throws SQLException {
        Optional<ArtistDto> artistResult = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection
                    .prepareStatement("SELECT id, name FROM unit8db.artist WHERE name = ?");
            query.setString(1, artistName);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final ArtistDto artistDto = ArtistDto.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
                artistResult = Optional.of(artistDto);
            }
        }
        return artistResult;
    }

    @Override
    public List<AlbumDto> getAllAlbums(String artistName) throws SQLException {
        List<AlbumDto> results = new LinkedList<>();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT al.id AS album_id," +
                            " al.name AS album_name," +
                            " ar.id AS artist_id," +
                            " ar.name AS artist_name " +
                            " FROM unit8db.album al" +
                            " JOIN unit8db.artist ar ON " +
                            " al.artist_id = ar.id" +
                            " WHERE al.artist_id = (SELECT id FROM unit8db.artist WHERE name = ?);");

            query.setString(1, artistName);
            final ResultSet resultSet = query.executeQuery();

            while (resultSet.next()) {
                final ArtistDto artist = ArtistDto.builder()
                        .id(resultSet.getInt("artist_id"))
                        .name(resultSet.getString("artist_name"))
                        .build();

                final AlbumDto album = AlbumDto.builder()
                        .id(resultSet.getInt("album_id"))
                        .name(resultSet.getString("album_name"))
                        .artist(artist)
                        .build();

                results.add(album);
            }
        }

        return results;
    }

    @Override
    public int add(String artistName) throws SQLException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO unit8db.artist (id, name)" +
                            " VALUES (nextval('unit8db.artist_seq'), ?);"
                    , returnColumns
            );
            query.setString(1, artistName);
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        }
        return result;
    }

    @Override
    public int deleteByName(String artistName) throws SQLException {
        int deleted;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM unit8db.artist" +
                            " WHERE id = (SELECT id FROM unit8db.artist WHERE name = ?);"
            );

            query.setString(1, artistName);
            deleted = query.executeUpdate();
        }
        return deleted;
    }
}
