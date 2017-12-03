package com.music.DAO;

import com.music.entity.ArtistEntity;

import java.util.List;


public interface ArtistDAO {
    ArtistEntity findById(int id);
    ArtistEntity findByName(String name);
    void save(ArtistEntity artist);
    void del (ArtistEntity artist);
    List<ArtistEntity> findAll();
    void svOrUpdate(ArtistEntity artistEntity);

}
