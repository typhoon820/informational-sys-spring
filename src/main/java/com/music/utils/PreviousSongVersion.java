package com.music.utils;

import com.music.entity.SongEntity;

public class PreviousSongVersion {
    private static PreviousSongVersion previousSongVersion = null;
    private SongEntity songEntity;

    private PreviousSongVersion(){
    }

    public static PreviousSongVersion getInstance(){
        if(previousSongVersion == null){
            previousSongVersion = new PreviousSongVersion();
        }
        return previousSongVersion;
    }

    public SongEntity getSongEntity() {
        return songEntity;
    }

    public void setSongEntity(SongEntity songEntity) {
        this.songEntity = songEntity;
    }
}
