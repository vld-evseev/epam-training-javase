package training.tasks.unit8.task2.data.dao;

import training.tasks.unit8.task2.data.dto.AlbumDto;
import training.tasks.unit8.task2.data.dto.ArtistDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ArtistDao {

    Optional<ArtistDto> findByName(String artistName) throws SQLException;

    List<AlbumDto> getAllAlbums(String artistName) throws SQLException;

    int add(String artistName) throws SQLException;

    int deleteByName(String artistName) throws SQLException;
}
