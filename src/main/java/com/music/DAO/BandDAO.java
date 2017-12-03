package com.music.DAO;

import com.music.entity.AlbumEntity;
import com.music.entity.BandEntity;

import java.util.List;

public interface BandDAO {
    BandEntity findById(int id);
    BandEntity findByName(String name);
    void save(BandEntity band);
    void del(BandEntity band);
    List<BandEntity> findAll();
    void update(BandEntity bandEntity);
}
