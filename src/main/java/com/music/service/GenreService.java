package com.music.service;

import com.music.model.GenreEntity;

import java.util.List;

public interface GenreService {

    GenreEntity findById(int id);
    GenreEntity findByName(String name);
    void save(GenreEntity album);
    void delete (int id);
    List<GenreEntity> findAll();
}
