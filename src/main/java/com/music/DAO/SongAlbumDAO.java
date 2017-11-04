package com.music.DAO;

import com.music.model.SongHasAlbumEntity;

public interface SongAlbumDAO {
    SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId);
}
