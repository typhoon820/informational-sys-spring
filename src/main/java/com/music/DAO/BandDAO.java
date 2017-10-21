package com.music.DAO;

import com.music.model.AlbumEntity;
import com.music.model.BandEntity;
import org.omg.CORBA.INTERNAL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BandDAO {
    BandEntity findById(int id);
    BandEntity findByName(String name);
    void save(BandEntity band);
    void delete (int id);
    List<BandEntity> findAll();
}
