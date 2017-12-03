package com.music.service;

import com.music.entity.ArtistEntity;

import java.util.List;

public interface ArtistService {
    ArtistEntity findById(int id);
    ArtistEntity findByName(String name);
    void save(ArtistEntity artist);
    void delete (ArtistEntity artistEntity);
    List<ArtistEntity> findAll();
    void saveOrUpdate(ArtistEntity artistEntity);
    void update(ArtistEntity artist);
}
