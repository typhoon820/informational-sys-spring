package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.SongDAO;
import com.music.entity.SongEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SongDAO")
public class SongDAOImpl extends AbstractDAO<Integer, SongEntity> implements SongDAO {
        @Override
        public SongEntity findById(int id) {
            Criteria criteria = createEntityCreteria();
            criteria.add(Restrictions.eq("id",id));
            return (SongEntity) criteria.uniqueResult();
        }

        @Override
        public SongEntity findByName(String name) {
            Criteria criteria = createEntityCreteria();
            criteria.add(Restrictions.eq("name",name));
            return (SongEntity) criteria.uniqueResult();
        }

        @Override
        public void save(SongEntity song) {
            persist(song);
        }

        @Override
        public void del(SongEntity song) {
//            Query query = getSession().createSQLQuery("DELETE FROM song WHERE id = :id");
//            query.setInteger("id", id);
//            query.executeUpdate();
            delete(song);
        }

        @Override
        public List<SongEntity> findAll() {
            Criteria criteria = getSession().createCriteria(SongEntity.class);
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return (List<SongEntity>)criteria.list();
        }

        @Override
        public void update(SongEntity song){
            getSession().update(song);

        }
}
