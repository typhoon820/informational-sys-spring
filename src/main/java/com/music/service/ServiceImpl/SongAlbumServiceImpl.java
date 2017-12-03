package com.music.service.ServiceImpl;

import com.music.DAO.SongAlbumDAO;
import com.music.entity.SongHasAlbumEntity;
import com.music.service.SongAlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("SongAlbumService")
@Transactional
public class SongAlbumServiceImpl implements SongAlbumService {

    @Autowired
    SongAlbumDAO songAlbumDAO;

    @Override
    public SongHasAlbumEntity songVersionByAlbumId(int songId, int albumId) {
        return songAlbumDAO.songVersionByAlbumId(songId, albumId);
    }

    @Override
    public SongHasAlbumEntity find(int songId, int albumId) {
        return songAlbumDAO.find(songId,albumId);
    }

    @Override
    public void save(SongHasAlbumEntity songHasAlbumEntity) {
        songAlbumDAO.save(songHasAlbumEntity);
    }

    @Override
    public void delete(int songId, int albumId) {
        songAlbumDAO.delete(songId, albumId);
    }

    @Override
    public SongHasAlbumEntity findById(int id) {
        return songAlbumDAO.findById(id);
    }
}
