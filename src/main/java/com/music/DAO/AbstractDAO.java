package com.music.DAO;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public class AbstractDAO<PK extends Serializable, E> {

    private final Class<PK> persistenceClass;

    @Autowired
    SessionFactory _sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.persistenceClass = (Class<PK>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession(){
        return _sessionFactory.getCurrentSession();
    }

    public void persist(E entity){
        getSession().persist(entity);
    }

    public void delete(E entity){
        getSession().delete(entity);
    }

    protected Criteria createEntityCreteria(){
        return getSession().createCriteria(persistenceClass);
    }
}
