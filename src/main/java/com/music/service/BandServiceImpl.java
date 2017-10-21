package com.music.service;

import com.music.DAO.BandDAO;
import com.music.model.BandEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("BandService")
@Transactional
public class BandServiceImpl implements BandService {

    @Autowired
    BandDAO bandDAO;

    @Override
    public BandEntity findById(int id) {
        return bandDAO.findById(id);
    }

    @Override
    public BandEntity findByName(String name) {
        return bandDAO.findByName(name);
    }

    @Override
    public void save(BandEntity band) {
        bandDAO.save(band);
    }

    @Override
    public void delete(int id) {
        bandDAO.delete(id);
    }

    @Override
    public List<BandEntity> findAll() {
        return bandDAO.findAll();
    }
}
