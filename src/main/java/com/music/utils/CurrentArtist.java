package com.music.utils;

import com.music.entity.ArtistBandEntity;
import com.music.entity.ArtistEntity;
import com.music.entity.SongEntity;
import com.music.entity.SongHasAlbumEntity;

public class CurrentArtist {

    private static CurrentArtist currentArtist = null;
    private ArtistEntity artistEntity;
    private ArtistBandEntity artistBandEntity;


    public void setArtistBandEntity(ArtistBandEntity artistBandEntity) {
        this.artistBandEntity = artistBandEntity;
    }

    public static void setCurrentArtist(CurrentArtist currentArtist) {
        CurrentArtist.currentArtist = currentArtist;
    }

    private CurrentArtist(){
    }

    public static CurrentArtist getInstance(){
        if(currentArtist == null){
            currentArtist = new CurrentArtist();
        }
        return currentArtist;
    }

    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
    }
}
