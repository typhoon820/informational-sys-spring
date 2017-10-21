package com.music.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SONG", schema = "mydb", catalog = "")
public class SongEntity extends AbstractModel {
    private int id;
    private String name;
    private BandEntity band;
    private GenreEntity genre;
    private List<AlbumEntity> albums;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST,
                                                    CascadeType.MERGE})
    @JoinTable(name ="SONG_has_ALBUM",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    public List<AlbumEntity> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumEntity> albums) {
        this.albums = albums;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "band_id")
    public BandEntity getBand() {
        return band;
    }

    public void setBand(BandEntity band) {
        this.band = band;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongEntity that = (SongEntity) o;

        if (id != that.id) return false;
        if (band != that.band) return false;
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
