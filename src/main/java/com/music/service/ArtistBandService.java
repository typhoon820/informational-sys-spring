package com.music.service;

import com.music.entity.ArtistBandEntity;
import com.music.entity.ArtistEntity;
import com.music.entity.BandEntity;

import java.util.List;

public interface ArtistBandService {
    List<ArtistBandEntity> findAll();
    ArtistBandEntity findById(int id);
    BandEntity findCurrentBand(ArtistEntity artistEntity);
    List<ArtistEntity> findCurrentArtists(BandEntity bandEntity);
    List<ArtistEntity> findPreviousArtists(BandEntity bandEntity);
    void save(ArtistBandEntity artistBandEntity);
    void saveOrUpdate(ArtistBandEntity artistBandEntity);
    List<BandEntity> findPreviousBands(ArtistEntity artistEntity);
    List<ArtistBandEntity> findPrevArtistBand(ArtistEntity artistEntity);
    List<ArtistBandEntity> findCurrentArtistBand(BandEntity bandEntity);
    List<ArtistBandEntity> findPreviousArtistBand(BandEntity bandEntity);

}
