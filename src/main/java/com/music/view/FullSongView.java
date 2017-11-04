package com.music.view;

import com.music.DAO.SongDAO;
import com.music.model.SongEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FullSongView {
    private int id;
    private String name;
    private String band;
    private String genre;
    private String album;
    private String version;

    public FullSongView(int id, String name, String band, String genre, String album, String version) {
        this.id = id;
        this.name = name;
        this.band = band;
        this.genre = genre;
        this.album = album;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
