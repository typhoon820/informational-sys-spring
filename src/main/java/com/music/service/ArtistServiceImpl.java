package com.music.service;

import com.music.DAO.ArtistDAO;
import com.music.model.ArtistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ArtistService")
@Transactional
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO artistDAO;

    @Override
    public ArtistEntity findById(int id) {
        return artistDAO.findById(id);
    }

    @Override
    public ArtistEntity findByName(String name) {
        return artistDAO.findByName(name);
    }

    @Override
    public void save(ArtistEntity artist) {
        artistDAO.save(artist);
    }

    @Override
    public void delete(int id) {
        artistDAO.delete(id);
    }

    @Override
    public List<ArtistEntity> findAll() {
        return artistDAO.findAll();
    }
}
