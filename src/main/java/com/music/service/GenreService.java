package com.music.service;

import com.music.entity.GenreEntity;

import java.util.List;

public interface GenreService {

    GenreEntity findById(int id);
    GenreEntity findByName(String name);
    void save(GenreEntity album);
    void delete (int id);
    List<GenreEntity> findAll();
}
