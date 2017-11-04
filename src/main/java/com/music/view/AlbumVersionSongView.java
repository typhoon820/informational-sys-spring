package com.music.view;

public class AlbumVersionSongView {
    private int id;
    private String album;
    private String version;

    public AlbumVersionSongView(int id, String album, String version) {
        this.id = id;
        this.album = album;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
