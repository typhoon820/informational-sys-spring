package com.music.service;

import com.music.model.BandEntity;

import java.util.List;

public interface BandService {
    BandEntity findById(int id);
    BandEntity findByName(String name);
    void save(BandEntity band);
    void delete (int id);
    List<BandEntity> findAll();
}
