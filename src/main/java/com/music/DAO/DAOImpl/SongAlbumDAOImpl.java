package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.SongAlbumDAO;
import com.music.entity.SongHasAlbumEntity;
import com.sun.org.apache.regexp.internal.RE;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.net.CacheRequest;

@Repository("SongAlbumDAO")
public class SongAlbumDAOImpl extends AbstractDAO<Integer, SongHasAlbumEntity> implements SongAlbumDAO {
    @Override
    public SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId) {
        Criteria criteria = getSession().createCriteria(SongHasAlbumEntity.class);
        criteria.add(Restrictions.eq("songId", songId));
        criteria.add(Restrictions.eq("albumId", albumId));

        return (SongHasAlbumEntity) criteria.uniqueResult();
    }

    @Override
    public void update(SongHasAlbumEntity songHasAlbumEntity) {
        update(songHasAlbumEntity);
    }

    @Override
    public SongHasAlbumEntity find(int songId, int albumId) {
        Criteria criteria = getSession().createCriteria(SongHasAlbumEntity.class);
        criteria.add(Restrictions.eq("songId", songId));
        criteria.add(Restrictions.eq("albumId", albumId));
        return (SongHasAlbumEntity)criteria.uniqueResult();
    }

    @Override
    public void save(SongHasAlbumEntity songHasAlbumEntity) {
        persist(songHasAlbumEntity);
//        Query query = getSession().createSQLQuery("INSERT into song_has_album (song_id, album_id, version) " +
//                "VALUES (:s_id, :a_id, :ver)");
//        query.setInteger("s_id",songHasAlbumEntity.getSongId());
//
//        query.setInteger("a_id",songHasAlbumEntity.getAlbumId());
//        query.setString("ver", songHasAlbumEntity.getVersion());
//        query.executeUpdate();
    }

    @Override
    public void delete(int songId, int albumId) {
        Query query = getSession().createSQLQuery("DELETE from song_has_album " +
                "where song_id=:songId and album_id = :albumId");
        query.setInteger("songId",songId);
        query.setInteger("albumId", albumId);
        query.executeUpdate();
    }

    @Override
    public SongHasAlbumEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(SongHasAlbumEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (SongHasAlbumEntity) criteria.uniqueResult();
    }

}
