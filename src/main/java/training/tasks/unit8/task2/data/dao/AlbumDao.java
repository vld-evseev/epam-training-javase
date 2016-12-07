package training.tasks.unit8.task2.data.dao;

import training.tasks.unit8.task2.data.dto.AlbumDto;
import training.tasks.unit8.task2.data.dto.TrackDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AlbumDao {

    Optional<AlbumDto> findByName(String albumName) throws SQLException;

    List<TrackDto> getTrackList(String albumName) throws SQLException;

    int add(String artistName, String albumName) throws SQLException;

    int delete(String artistName, String albumName) throws SQLException;
}
