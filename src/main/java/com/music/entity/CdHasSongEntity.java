package com.music.entity;

import javax.persistence.*;

@Entity
@Table(name = "cd_has_song", schema = "mydb")
public class CdHasSongEntity {
    private int cdId;
    private int songId;
    private int id;

    @Basic
    @Column(name = "CD_ID", nullable = false)
    public int getCdId() {
        return cdId;
    }

    public void setCdId(int cdId) {
        this.cdId = cdId;
    }

    @Basic
    @Column(name = "SONG_ID", nullable = false)
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdHasSongEntity that = (CdHasSongEntity) o;

        if (cdId != that.cdId) return false;
        if (songId != that.songId) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cdId;
        result = 31 * result + songId;
        result = 31 * result + id;
        return result;
    }
}
