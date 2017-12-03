package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.UserDAO;
import com.music.entity.UserEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserDAO")
public class UserDAOImpl extends AbstractDAO<Integer, UserEntity> implements UserDAO {

    @Override
    public UserEntity findById(int id) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("id",id));
        return (UserEntity)criteria.uniqueResult();
    }

    @Override
    public UserEntity findByName(String name) {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        criteria.add(Restrictions.eq("login",name));
        return (UserEntity)criteria.uniqueResult();
    }

    @Override
    public void save(UserEntity user) {
        persist(user);
    }

    @Override
    public void delete(int id) {
        Query query = getSession().createSQLQuery("DELETE FROM user WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @Override
    public List<UserEntity> findAll() {
        Criteria criteria = getSession().createCriteria(UserEntity.class);
        return (List<UserEntity>)criteria.list();
    }
}
