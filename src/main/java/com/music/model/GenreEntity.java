package com.music.model;

import com.music.utils.Annotations.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genre", schema = "mydb")
public class GenreEntity extends AbstractModel {
    private int id;
    private String genre;
    private List<SongEntity> songsOfGenre = new ArrayList<>();

    @OneToMany(mappedBy = "genre")
    public List<SongEntity> getSongsOfGenre() {
        return songsOfGenre;
    }

    public void setSongsOfGenre(List<SongEntity> songsOfGenre) {
        this.songsOfGenre = songsOfGenre;
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
    @Column(name = "genre", nullable = false, length = 40)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (id != that.id) return false;
        if (genre != null ? !genre.equals(that.genre) : that.genre != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }

    @Transient
    @Getter(num = 1)
    public String getG(){
        return genre;
    }

    @Transient
    @Getter(num = 2)
    public List<String> getS(){
        List<String> res = new ArrayList<>();
        if (songsOfGenre.size()!=0) {
            for (SongEntity s : songsOfGenre) {
                res.add(s.getName());
            }
        }
        return res;
    }

}
