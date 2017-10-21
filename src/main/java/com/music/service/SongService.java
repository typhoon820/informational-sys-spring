package com.music.service;

import com.music.model.SongEntity;

import java.util.List;

public interface SongService {
    SongEntity findById(int id);
    SongEntity findByName(String name);
    void save(SongEntity song);
    void delete (int id);
    List<SongEntity> findAll();
}
