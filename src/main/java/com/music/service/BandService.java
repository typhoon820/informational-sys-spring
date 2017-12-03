package com.music.service;

import com.music.entity.BandEntity;

import java.util.List;

public interface BandService {
    BandEntity findById(int id);
    BandEntity findByName(String name);
    void save(BandEntity band);
    void delete (BandEntity bandEntity);
    List<BandEntity> findAll();
    void update(BandEntity bandEntity);
}
