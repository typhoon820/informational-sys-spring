package com.music.DAO;

import com.music.model.AlbumEntity;
import com.music.model.GenreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GenreDAO{

    GenreEntity findById(int id);
    GenreEntity findByName(String name);
    void save(GenreEntity album);
    void delete (int id);
    List<GenreEntity> findAll();
}
