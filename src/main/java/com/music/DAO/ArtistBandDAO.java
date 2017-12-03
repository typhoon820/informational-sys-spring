package com.music.DAO;

import com.music.entity.ArtistBandEntity;

import java.util.List;

public interface ArtistBandDAO {
    List<ArtistBandEntity> findAll();
    void saveOrUpdate(ArtistBandEntity artistBandEntity);
    void save(ArtistBandEntity artistBandEntity);
    ArtistBandEntity findById(int id);
    //List<ArtistBandEntity> findByArtistID(int id);
}
