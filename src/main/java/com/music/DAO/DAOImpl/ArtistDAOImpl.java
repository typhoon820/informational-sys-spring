package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.ArtistDAO;
import com.music.controller.MenuController;
import com.music.entity.ArtistEntity;
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
        String[] restr = name.split(" ");
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("firstname",restr[0]));
        criteria.add(Restrictions.eq("lastname", restr[1]));
        return (ArtistEntity) criteria.uniqueResult();
    }

    @Override
    public void save(ArtistEntity artist) {
        persist(artist);
    }

    @Override
    public void del(ArtistEntity artist) {
//        Query query = getSession().createSQLQuery("DELETE FROM artist WHERE id = :id");
//        query.setInteger("id", id);
//        query.executeUpdate();
        delete(artist);
    }

    @Override
    public List<ArtistEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<ArtistEntity>)criteria.list();
    }

    @Override
    public void svOrUpdate(ArtistEntity artistEntity){
        getSession().update(artistEntity);
    }
}
