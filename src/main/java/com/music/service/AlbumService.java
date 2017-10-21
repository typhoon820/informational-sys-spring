package com.music.service;

import com.music.model.AlbumEntity;

import java.util.List;

public interface AlbumService {
    AlbumEntity findById(int id);
    AlbumEntity findByName(String name);
    void save(AlbumEntity album);
    void delete (int id);
    List<AlbumEntity> findAll();
}
