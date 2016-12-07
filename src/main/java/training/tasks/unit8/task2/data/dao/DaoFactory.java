package training.tasks.unit8.task2.data.dao;

public interface DaoFactory {

    ArtistDao getArtistDao();

    AlbumDao getAlbumDao();

    TrackDao getTrackDao();

}
