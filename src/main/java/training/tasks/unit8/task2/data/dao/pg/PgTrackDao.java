package training.tasks.unit8.task2.data.dao.pg;

import training.tasks.unit8.task2.data.dao.TrackDao;
import training.tasks.unit8.task2.data.dto.TrackDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PgTrackDao implements TrackDao {

    private DataSource dataSource;

    public PgTrackDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<TrackDto> findByName(String trackName) throws SQLException {
        Optional<TrackDto> result = Optional.empty();
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "SELECT id, position, name, album_id FROM unit8db.track" +
                            " WHERE name = ?;"
            );
            query.setString(1, trackName);
            final ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                final TrackDto track = TrackDto.builder()
                        .id(resultSet.getInt("id"))
                        .position(resultSet.getInt("position"))
                        .name(resultSet.getString("name"))
                        .albumId(resultSet.getInt("album_id"))
                        .build();
                result = Optional.of(track);
            }
        }
        return result;
    }

    @Override
    public int add(String albumName, String trackName, int position) throws SQLException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final String[] returnColumns = {"id"};
            final PreparedStatement query = connection.prepareStatement(
                    "INSERT INTO unit8db.track (id, position, name, album_id)" +
                            " VALUES (nextval('unit8db.track_seq'), ?, ?," +
                            " (SELECT id FROM unit8db.album WHERE name = ?));",
                    returnColumns
            );
            query.setInt(1, position);
            query.setString(2, trackName);
            query.setString(3, albumName);
            query.executeUpdate();
            final ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                result = generatedKeys.getInt(1);
            }
        }
        return result;
    }

    @Override
    public int delete(String albumName, int position) throws SQLException {
        int result = -1;
        try (Connection connection = dataSource.getConnection()) {
            final PreparedStatement query = connection.prepareStatement(
                    "DELETE FROM unit8db.track" +
                            " WHERE position = ?" +
                            " AND album_id = (SELECT id FROM unit8db.album WHERE name = ?);"
            );
            query.setInt(1, position);
            query.setString(2, albumName);
            result = query.executeUpdate();
        }
        return result;
    }
}
