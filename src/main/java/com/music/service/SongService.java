package com.music.service;

import com.music.entity.AlbumEntity;
import com.music.entity.SongEntity;
import com.music.view.TableSongView;

import java.util.List;

public interface SongService {
    SongEntity findById(int id);
    SongEntity findByName(String name);
    void save(SongEntity song);
    void delete (SongEntity songEntity);
    List<SongEntity> findAll();
    List<TableSongView> tableSongViewAll();
    void update(SongEntity song);
    void setVersionToAlbum(SongEntity songEntity, AlbumEntity albumEntity, String version);
}
