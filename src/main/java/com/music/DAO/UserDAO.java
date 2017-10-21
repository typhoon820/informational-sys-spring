package com.music.DAO;

import com.music.model.AlbumEntity;
import com.music.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserDAO {
    UserEntity findById(int id);
    UserEntity findByName(String name);
    void save(UserEntity user);
    void delete (int id);
    List<UserEntity> findAll();
}
