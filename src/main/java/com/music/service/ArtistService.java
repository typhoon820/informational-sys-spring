package com.music.service;

import com.music.model.ArtistEntity;

import java.util.List;

public interface ArtistService {
    ArtistEntity findById(int id);
    ArtistEntity findByName(String name);
    void save(ArtistEntity artist);
    void delete (int id);
    List<ArtistEntity> findAll();
}
