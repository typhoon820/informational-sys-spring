package com.music.service;

import com.music.DAO.UserStatusDAO;
import com.music.model.UserStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserStatusSercice")
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusDAO userStatusDAO;

    @Override
    public UserStatusEntity findByName(String name) {
        return userStatusDAO.findByName(name);
    }
}
