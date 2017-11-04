package com.music.DAO;

import com.music.model.AlbumEntity;
import com.music.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AlbumDAO")
public class AlbumDAOImpl extends AbstractDAO<Integer, AlbumEntity> implements AlbumDAO {
    @Override
    public AlbumEntity findById(int id) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("id",id));
        return (AlbumEntity) criteria.uniqueResult();
    }

    @Override
    public AlbumEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("name",name));
        return (AlbumEntity) criteria.uniqueResult();
    }

    @Override
    public void save(AlbumEntity album) {
        persist(album);
    }

    @Override
    public void delete(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM album WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<AlbumEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<AlbumEntity>)criteria.list();
    }
}
