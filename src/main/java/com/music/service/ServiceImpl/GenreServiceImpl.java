package com.music.service.ServiceImpl;

import com.music.DAO.GenreDAO;
import com.music.entity.GenreEntity;
import com.music.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("GenreService")
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreDAO genreDAO;

    @Override
    public GenreEntity findById(int id) {
        return genreDAO.findById(id);
    }

    @Override
    public GenreEntity findByName(String name) {
        return genreDAO.findByName(name);
    }

    @Override
    public void save(GenreEntity album) {
        genreDAO.save(album);
    }

    @Override
    public void delete(int id) {
        genreDAO.delete(id);
    }

    @Override
    public List<GenreEntity> findAll() {
        return genreDAO.findAll();
    }
}
