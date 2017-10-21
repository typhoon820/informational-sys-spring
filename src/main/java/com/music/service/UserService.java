package com.music.service;

import com.music.model.UserEntity;

import java.util.List;

public interface UserService  {
    UserEntity findById(int id);
    UserEntity findByName(String name);
    void save(UserEntity user);
    void delete (int id);
    List<UserEntity> findAll();
}
