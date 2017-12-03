package com.music.DAO.DAOImpl;

import com.music.DAO.AbstractDAO;
import com.music.DAO.UserStatusDAO;
import com.music.entity.UserStatusEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("UserStatusDAO")
public class UserStatusDAOImpl extends AbstractDAO<Integer, UserStatusEntity> implements UserStatusDAO {
    @Override
    public UserStatusEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("status",name));
        return (UserStatusEntity)criteria.uniqueResult();
    }
}
