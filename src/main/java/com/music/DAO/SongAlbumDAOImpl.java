package com.music.DAO;

import com.music.model.SongHasAlbumEntity;
import org.apache.maven.artifact.versioning.Restriction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("SongAlbumDAO")
public class SongAlbumDAOImpl extends AbstractDAO<Integer, SongHasAlbumEntity> implements SongAlbumDAO{
    @Override
    public SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId) {
        Criteria criteria = getSession().createCriteria(SongHasAlbumEntity.class);
        criteria.add(Restrictions.eq("songId", songId));
        criteria.add(Restrictions.eq("albumId", albumId));
        return (SongHasAlbumEntity) criteria.uniqueResult();
    }
}
