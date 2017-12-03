package com.music.DAO;

import com.music.entity.UserEntity;

import java.util.List;

public interface UserDAO {
    UserEntity findById(int id);
    UserEntity findByName(String name);
    void save(UserEntity user);
    void delete (int id);
    List<UserEntity> findAll();
}
