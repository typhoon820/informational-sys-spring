package com.music.DAO;

import com.music.model.GenreEntity;
import com.music.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("GenreDAO")
public class GenreDAOImpl extends AbstractDAO<Integer, GenreEntity> implements GenreDAO {
    @Override
    public GenreEntity findById(int id) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("id",id));
        return (GenreEntity) criteria.uniqueResult();
    }

    @Override
    public GenreEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("genre",name));
        return (GenreEntity) criteria.uniqueResult();
    }

    @Override
    public void save(GenreEntity genre) {
        persist(genre);
    }

    @Override
    public void delete(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM genre WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<GenreEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<GenreEntity>)criteria.list();
    }
}
