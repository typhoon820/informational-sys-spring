package com.music.DAO;

import com.music.model.ArtistEntity;
import com.music.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ArtistDAO")
public class ArtistDAOImpl extends AbstractDAO<Integer, ArtistEntity> implements ArtistDAO {
    @Override
    public ArtistEntity findById(int id) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("id",id));
        return (ArtistEntity) criteria.uniqueResult();
    }

    @Override
    public ArtistEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("firstname",name));
        return (ArtistEntity) criteria.uniqueResult();
    }

    @Override
    public void save(ArtistEntity artist) {
        persist(artist);
    }

    @Override
    public void delete(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM ARTIST WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<ArtistEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<ArtistEntity>)criteria.list();
    }
}
