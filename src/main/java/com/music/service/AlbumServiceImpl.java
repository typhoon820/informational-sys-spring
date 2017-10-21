package com.music.service;

import com.music.DAO.AlbumDAO;
import com.music.DAO.UserDAO;
import com.music.model.AlbumEntity;
import com.music.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("AlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;

    @Override
    public AlbumEntity findById(int id) {
        return albumDAO.findById(id);
    }

    @Override
    public AlbumEntity findByName(String name) {
        return albumDAO.findByName(name);
    }

    @Override
    public void save(AlbumEntity album) {
        albumDAO.save(album);
    }

    @Override
    public void delete(int id) {
        albumDAO.delete(id);
    }

    @Override
    public List<AlbumEntity> findAll() {
        return albumDAO.findAll();
    }
}
