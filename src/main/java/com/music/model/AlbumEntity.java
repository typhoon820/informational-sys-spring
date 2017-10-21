package com.music.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ALBUM", schema = "mydb")
public class AlbumEntity extends AbstractModel{
    private int id;
    private String name;
    private List<SongEntity> songsInAlbum;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name ="SONG_has_ALBUM",
                joinColumns = @JoinColumn(name = "album_id"),
                inverseJoinColumns = @JoinColumn(name = "song_id"))
    public List<SongEntity> getSongsInAlbum() {
        return songsInAlbum;
    }

    public void setSongsInAlbum(List<SongEntity> songsInAlbum) {
        this.songsInAlbum = songsInAlbum;
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
    @Column(name = "name", nullable = true, length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
