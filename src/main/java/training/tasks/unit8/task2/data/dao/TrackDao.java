package training.tasks.unit8.task2.data.dao;

import training.tasks.unit8.task2.data.dto.TrackDto;

import java.sql.SQLException;
import java.util.Optional;

public interface TrackDao {

    Optional<TrackDto> findByName(String trackName) throws SQLException;

    int add(String albumName, String trackName, int position) throws SQLException;

    int delete(String albumName, int position) throws SQLException;

}
