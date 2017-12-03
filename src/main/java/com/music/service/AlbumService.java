package com.music.service;

import com.music.entity.AlbumEntity;

import java.util.List;

public interface AlbumService {
    AlbumEntity findById(int id);
    AlbumEntity findByName(String name);
    void save(AlbumEntity album);
    void delete (AlbumEntity albumEntity);
    List<AlbumEntity> findAll();
}
