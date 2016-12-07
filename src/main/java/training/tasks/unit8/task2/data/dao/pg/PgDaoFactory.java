package training.tasks.unit8.task2.data.dao.pg;

import training.tasks.unit8.task2.data.dao.AlbumDao;
import training.tasks.unit8.task2.data.dao.ArtistDao;
import training.tasks.unit8.task2.data.dao.DaoFactory;
import training.tasks.unit8.task2.data.dao.TrackDao;

import javax.sql.DataSource;

public class PgDaoFactory implements DaoFactory {

    private DataSource dataSource;

    public PgDaoFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ArtistDao getArtistDao() {
        return new PgArtistDao(dataSource);
    }

    @Override
    public AlbumDao getAlbumDao() {
        return new PgAlbumDao(dataSource);
    }

    @Override
    public TrackDao getTrackDao() {
        return new PgTrackDao(dataSource);
    }
}
