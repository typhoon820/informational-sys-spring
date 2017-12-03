package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.BandDAO;
import com.music.entity.BandEntity;
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
    public void del(BandEntity band) {
//        Query query = getSession().createSQLQuery("DELETE FROM band WHERE id = :id");
//        query.setInteger("id", id);
//        query.executeUpdate();
        delete(band);
    }

    @Override
    public List<BandEntity> findAll() {
        Criteria criteria = createEntityCreteria();
        return (List<BandEntity>)criteria.list();
    }

    @Override
    public void update(BandEntity bandEntity) {
        getSession().update(bandEntity);
    }
}
