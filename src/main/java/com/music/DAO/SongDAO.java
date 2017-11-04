package com.music.DAO;


import com.music.model.AlbumEntity;
import com.music.model.SongEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SongDAO {
    SongEntity findById(int id);
    SongEntity findByName(String name);
    void save(SongEntity song);
    void delete (int id);
    List<SongEntity> findAll();
    void update(SongEntity song);
}
