package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.ArtistBandDAO;
import com.music.entity.ArtistBandEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ArtistBandDAO")
public class ArtistBandDAOImpl extends AbstractDAO<Integer, ArtistBandEntity> implements ArtistBandDAO {
    @Override
    public List<ArtistBandEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<ArtistBandEntity>)criteria.list();
    }
    public void save(ArtistBandEntity artistBandEntity){
        persist(artistBandEntity);
    }

    @Override
    public ArtistBandEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(ArtistBandEntity.class);
        criteria.add(Restrictions.eq("id", id));
        return (ArtistBandEntity)criteria.uniqueResult();
    }

    public void saveOrUpdate(ArtistBandEntity artistBandEntity){
        getSession().update(artistBandEntity);
    }

}
