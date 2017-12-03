package com.music.service.ServiceImpl;

import com.music.DAO.UserStatusDAO;
import com.music.entity.UserStatusEntity;
import com.music.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserStatusService")
@Transactional
public class UserStatusServiceImpl implements UserStatusService {

    @Autowired
    UserStatusDAO userStatusDAO;

    @Override
    public UserStatusEntity findByName(String name) {
        return userStatusDAO.findByName(name);
    }
}
