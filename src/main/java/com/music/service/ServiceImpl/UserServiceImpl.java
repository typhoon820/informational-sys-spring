package com.music.service.ServiceImpl;

import com.music.DAO.UserDAO;
import com.music.DAO.UserStatusDAO;
import com.music.entity.UserEntity;
import com.music.entity.UserStatusEntity;
import com.music.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    UserStatusDAO statusDAO;

    @Override
    public UserEntity findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public UserEntity findByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public void save(UserEntity user) {
        UserStatusEntity ustat = statusDAO.findByName("User");
        user.setUserStatus(ustat);
        userDAO.save(user);
    }

    @Override
    public void delete(int id) {
        userDAO.delete(id);
    }

    @Override
    public List<UserEntity> findAll() {
        return userDAO.findAll();
    }
}
