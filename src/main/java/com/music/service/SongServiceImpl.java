package com.music.service;

import com.music.DAO.SongAlbumDAO;
import com.music.DAO.SongDAO;
import com.music.model.AlbumEntity;
import com.music.model.SongEntity;
import com.music.view.AlbumVersionSongView;
import com.music.view.FullSongView;
import com.music.view.TableSongView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("SongService")
@Transactional
public class SongServiceImpl implements SongService{

    @Autowired
    SongDAO songDAO;

    @Autowired
    SongAlbumDAO songAlbumDAO;


    @Override
    public SongEntity findById(int id) {
        return songDAO.findById(id);
    }

    @Override
    public SongEntity findByName(String name) {
        return songDAO.findByName(name);
    }

    @Override
    public void save(SongEntity song) {
        songDAO.save(song);
    }

    @Override
    public void delete(int id) {
        songDAO.delete(id);
    }

    @Override
    public List<SongEntity> findAll() {
        return songDAO.findAll();
    }



    //В формочке будет на каждое поле-коллекцию открываться новая формочкка, где уже будет
    //вызываться новая вьюха с подробностями вроде этой
    public List<AlbumVersionSongView> albumVersionSongView(int id){
        List<AlbumVersionSongView> view = new ArrayList<>();
        SongEntity s = songDAO.findById(id);
        for (AlbumEntity a: s.getAlbums()){
            view.add(new AlbumVersionSongView(s.getId(),
                    a.getName(),
                    songAlbumDAO.songVersionByAlbumId(s.getId(), a.getId()).getVersion()));
        }
        return view;
    }

    public List<TableSongView> tableSongViewAll(){
        List<TableSongView> res = new ArrayList<>();
        for (SongEntity s: songDAO.findAll()){
            res.add(new TableSongView(s.getId(),
                    s.getName(),
                    s.getGenre().getGenre(),
                    s.getBand().getName()));
        }
        return res;
    }

    public void update(SongEntity song){
        songDAO.update(song);
    }

}
