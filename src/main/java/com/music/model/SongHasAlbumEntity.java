package com.music.model;

import javax.persistence.*;

@Entity
@Table(name = "song_has_album", schema = "mydb")
public class SongHasAlbumEntity {
    private int songId;
    private int albumId;
    private int id;
    private String version;



    @Basic
    @Column(name = "song_id", nullable = false)
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Basic
    @Column(name = "album_id", nullable = false)
    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (version != null ? !version.equals(that.version) : that.version != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = songId;
        result = 31 * result + albumId;
        result = 31 * result + id;
        result = 31 * result + (version != null ? version.hashCode() : 0);
        return result;
    }
}
