package com.music.DAO;

import com.music.entity.AlbumEntity;

import java.util.List;


public interface AlbumDAO {
    AlbumEntity findById(int id);
    AlbumEntity findByName(String name);
    void save(AlbumEntity album);
    void del (AlbumEntity album);
    List<AlbumEntity> findAll();
}
