package com.music.DAO;


import com.music.entity.SongEntity;

import java.util.List;

public interface SongDAO {
    SongEntity findById(int id);
    SongEntity findByName(String name);
    void save(SongEntity song);
    void del (SongEntity song);
    List<SongEntity> findAll();
    void update(SongEntity song);
}
