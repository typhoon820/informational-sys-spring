package com.music.service.ServiceImpl;

import com.music.DAO.SongAlbumDAO;
import com.music.DAO.SongDAO;
import com.music.entity.AlbumEntity;
import com.music.entity.SongEntity;
import com.music.entity.SongHasAlbumEntity;
import com.music.service.SongService;
import com.music.view.AlbumVersionSongView;
import com.music.view.TableSongView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("SongService")
@Transactional
public class SongServiceImpl implements SongService {

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
    public void delete(SongEntity songEntity) {
        songDAO.del(songEntity);
    }

    @Override
    public List<SongEntity> findAll() {
        return songDAO.findAll();
    }



    //В формочке будет на каждое поле-коллекцию открываться новая формочкка, где уже будет
    //вызываться новая вьюха с подробностями вроде этой
//    public List<AlbumVersionSongView> albumVersionSongView(int id){
//        List<AlbumVersionSongView> view = new ArrayList<>();
//        SongEntity s = songDAO.findById(id);
//        for (AlbumEntity a: s.getAlbums()){
//            view.add(new AlbumVersionSongView(s.getId(),
//                    a.getName(),
//                    songAlbumDAO.songVersionByAlbumId(s.getId(), a.getId()).getVersion()));
//        }
//        return view;
//    }

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

    @Override
    public void setVersionToAlbum(SongEntity songEntity, AlbumEntity albumEntity, String version) {
        //SongHasAlbumEntity s = new SongHasAlbumEntity();
        //songAlbumDAO.update(songEntity.getId(), albumEntity.getId(), version);
    }

}
