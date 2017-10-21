package com.music.DAO;

import com.music.model.UserEntity;
import com.music.model.UserStatusEntity;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("UserStatusDAO")
public class UserStatusDAOImpl extends AbstractDAO<Integer, UserStatusEntity> implements UserStatusDAO{
    @Override
    public UserStatusEntity findByName(String name) {
        Criteria criteria = createEntityCreteria();
        criteria.add(Restrictions.eq("status",name));
        return (UserStatusEntity)criteria.uniqueResult();
    }
}
