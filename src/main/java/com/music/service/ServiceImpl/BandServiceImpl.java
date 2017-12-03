package com.music.service.ServiceImpl;

import com.music.DAO.BandDAO;
import com.music.entity.BandEntity;
import com.music.service.BandService;
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
    public void delete(BandEntity bandEntity) {
        bandDAO.del(bandEntity);
    }

    @Override
    public List<BandEntity> findAll() {
        return bandDAO.findAll();
    }

    @Override
    public void update(BandEntity bandEntity) {
        bandDAO.update(bandEntity);
    }
}
