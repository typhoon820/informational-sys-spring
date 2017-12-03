package com.music.utils;

import com.music.entity.ArtistBandEntity;

import java.util.List;

public class CurrentArtistBandList {
    private static CurrentArtistBandList currentArtistBandList = null;
    private List<ArtistBandEntity> artistBandEntityList;

    private CurrentArtistBandList(){
    }

    public static CurrentArtistBandList getInstance(){
        if (currentArtistBandList == null){
            currentArtistBandList = new CurrentArtistBandList();
        }
        return currentArtistBandList;
    }

    public List<ArtistBandEntity> getArtistBandEntityList() {
        return artistBandEntityList;
    }

    public void setArtistBandEntityList(List<ArtistBandEntity> artistBandEntityList) {
        this.artistBandEntityList = artistBandEntityList;
    }
}
