package com.music.DAO;

import com.music.model.AlbumEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface AlbumDAO {
    AlbumEntity findById(int id);
    AlbumEntity findByName(String name);
    void save(AlbumEntity album);
    void delete (int id);
    List<AlbumEntity> findAll();
}
