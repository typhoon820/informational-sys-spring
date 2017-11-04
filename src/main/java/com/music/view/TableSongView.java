package com.music.view;

import com.music.model.AbstractModel;
import com.music.utils.Annotations.Getter;

public class TableSongView extends AbstractModel {
    private int id;
    private String name;
    private String genre;
    private String band;

    public TableSongView(int id, String name, String genre, String band) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.band = band;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //@Getter(num)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }
}
