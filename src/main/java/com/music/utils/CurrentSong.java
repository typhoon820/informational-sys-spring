package com.music.utils;

import com.music.controller.AbstractController;
import com.music.entity.AbstractModel;
import com.music.entity.SongEntity;
import com.music.entity.SongHasAlbumEntity;

public class CurrentSong {
    private static CurrentSong currentSong = null;
    private SongEntity songEntity;
    private SongHasAlbumEntity songHasAlbumEntity;

    public static CurrentSong getCurrentSong() {
        return currentSong;
    }

    public void setSongHasAlbumEntity(SongHasAlbumEntity songHasAlbumEntity) {
        this.songHasAlbumEntity = songHasAlbumEntity;
    }

    public static void setCurrentSong(CurrentSong currentSong) {
        CurrentSong.currentSong = currentSong;
    }

    private CurrentSong(){
    }

    public static CurrentSong getInstance(){
        if(currentSong == null){
            currentSong = new CurrentSong();
        }
        return currentSong;
    }

    public SongEntity getSongEntity() {
        return songEntity;
    }

    public void setSongEntity(SongEntity songEntity) {
        this.songEntity = songEntity;
    }
}
