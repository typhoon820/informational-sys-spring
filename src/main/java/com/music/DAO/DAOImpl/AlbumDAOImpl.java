package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.AlbumDAO;
import com.music.entity.AlbumEntity;
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
    public void del(AlbumEntity album) {
//        Query query = getSession().createSQLQuery("DELETE FROM album WHERE id = :id");
//        query.setInteger("id", id);
//        query.executeUpdate();
        delete(album);

    }

    @Override
    public List<AlbumEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<AlbumEntity>)criteria.list();
    }
}
