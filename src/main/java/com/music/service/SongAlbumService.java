package com.music.service;

import com.music.entity.SongHasAlbumEntity;

public interface SongAlbumService {
    SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId);
    SongHasAlbumEntity find(int songId, int albumId);
    void save(SongHasAlbumEntity songHasAlbumEntity);
    void delete(int songId, int albumId);
    SongHasAlbumEntity findById(int id);
}
