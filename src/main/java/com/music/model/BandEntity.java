package com.music.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "BAND", schema = "mydb", catalog = "")
public class BandEntity extends AbstractModel {
    private int id;
    private String name;
    private List<SongEntity> songs;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name ="ARTIST_BAND",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    public List<ArtistEntity> getCurrentArtists() {
        return currentArtists;
    }

    public void setCurrentArtists(List<ArtistEntity> currentArtists) {
        this.currentArtists = currentArtists;
    }

    private List<ArtistEntity> currentArtists;


    @OneToMany(mappedBy = "band")
    public List<SongEntity> getSongs() {
        return songs;
    }

    public void setSongs(List<SongEntity> songs) {
        this.songs = songs;
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

        BandEntity that = (BandEntity) o;

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
