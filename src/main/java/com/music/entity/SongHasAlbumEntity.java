package com.music.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;

@Entity
@Table(name = "song_has_album", schema = "mydb")
public class SongHasAlbumEntity {
    @Transient
    private int songId;
    @Transient
    private int albumId;
    private int id;
    private String version;

    private AlbumEntity album;
    private SongEntity song;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @ManyToOne
    @JoinColumn(name = "album_id")
    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }

    @ManyToOne
    @JoinColumn(name = "song_id")
    public SongEntity getSong() {
        return song;
    }

    public void setSong(SongEntity song) {
        this.song = song;
    }

    @Transient
    @Basic
    @Column(name ="song_id")
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Transient
    @Basic
    @Column(name ="album_id")
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Basic
    @Column(name = "version", nullable = true, length = 45)
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongHasAlbumEntity that = (SongHasAlbumEntity) o;

        if (songId != that.songId) return false;
        if (albumId != that.albumId) return false;
        if (id != that.id) return false;
        return version.equals(that.version);
    }

    @Override
    public int hashCode() {
        int result = songId;
        result = 31 * result + albumId;
        result = 31 * result + id;
        result = 31 * result + version.hashCode();
        return result;
    }

    @Transient
    public StringProperty songProperty(){
        return new SimpleStringProperty(song.getName());
    }

    @Transient
    public StringProperty albumProperty(){
        return new SimpleStringProperty(album.getName());
    }
}
