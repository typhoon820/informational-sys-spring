package com.music.DAO;

import com.music.model.SongEntity;
import com.music.model.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("SongDAO")
public class SongDAOImpl extends AbstractDAO<Integer, SongEntity> implements SongDAO{
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
        public void delete(int id) {
            Query query = getSession().createSQLQuery("DELETE FROM SONG WHERE id = :id");
            query.setInteger("id", id);
            query.executeUpdate();
        }

        @Override
        public List<SongEntity> findAll() {
            Criteria criteria = createEntityCreteria();
            return (List<SongEntity>)criteria.list();
        }
}
