package com.music.DAO;

import com.music.entity.SongHasAlbumEntity;

public interface SongAlbumDAO {
    SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId);
    void update(SongHasAlbumEntity songHasAlbumEntity);
    SongHasAlbumEntity find(int songId, int albumId);
    void save(SongHasAlbumEntity songHasAlbumEntity);
    void delete (int songId, int albumId);
    SongHasAlbumEntity findById(int id);
}
