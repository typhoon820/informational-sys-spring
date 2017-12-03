package com.music.service.ServiceImpl;

import com.music.DAO.ArtistDAO;
import com.music.entity.ArtistEntity;
import com.music.service.ArtistService;
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
    public void delete(ArtistEntity artistEntity) {
        artistDAO.del(artistEntity);
    }

    @Override
    public List<ArtistEntity> findAll() {
        return artistDAO.findAll();
    }

    @Override
    public void saveOrUpdate(ArtistEntity artistEntity) {
        artistDAO.svOrUpdate(artistEntity);
    }

    @Override
    public void update(ArtistEntity artist) {
        artistDAO.svOrUpdate(artist);
    }
}
