package com.music.DAO;

import com.music.model.AlbumEntity;
import com.music.model.ArtistEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ArtistDAO {
    ArtistEntity findById(int id);
    ArtistEntity findByName(String name);
    void save(ArtistEntity artist);
    void delete (int id);
    List<ArtistEntity> findAll();
}
