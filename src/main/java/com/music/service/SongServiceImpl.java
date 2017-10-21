package com.music.service;

import com.music.DAO.SongDAO;
import com.music.model.SongEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SongService")
@Transactional
public class SongServiceImpl implements SongService{

    @Autowired
    SongDAO songDAO;

    @Override
    public SongEntity findById(int id) {
        return songDAO.findById(id);
    }

    @Override
    public SongEntity findByName(String name) {
        return songDAO.findByName(name);
    }

    @Override
    public void save(SongEntity song) {
        songDAO.save(song);
    }

    @Override
    public void delete(int id) {
        songDAO.delete(id);
    }

    @Override
    public List<SongEntity> findAll() {
        return songDAO.findAll();
    }
}
