package com.music.DAO;

import com.music.model.BandEntity;
import com.music.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("BandDAO")
public class BandDAOImpl extends AbstractDAO<Integer, BandEntity> implements BandDAO {
    @Override
    public BandEntity findById(int id) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("id",id));
        return (BandEntity) criteria.uniqueResult();
    }

    @Override
    public BandEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("name",name));
        return (BandEntity) criteria.uniqueResult();
    }

    @Override
    public void save(BandEntity band) {
        persist(band);
    }

    @Override
    public void delete(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM band WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<BandEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<BandEntity>)criteria.list();
    }
}
