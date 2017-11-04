package com.music.model;

import com.music.utils.Annotations.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "band", schema = "mydb")
public class BandEntity extends AbstractModel {
    private int id;
    private String name;
    private List<SongEntity> songs = new ArrayList<>();
    private List<ArtistEntity> currentArtists = new ArrayList<>();
//    private List<ArtistBandEntity> previousArtists = new ArrayList<>();
//
//    @OneToMany(mappedBy = "artistId")
//    public List<ArtistBandEntity> getPreviousArtists() {
//        List<ArtistBandEntity> res = new ArrayList<>();
//        for(ArtistBandEntity ab: previousArtists){
//            if (ab.getLeaveDate() != null){
//                res.add(ab);
//            }
//        }
//        return res;
//    }
//
//    public void setPreviousArtists(List<ArtistBandEntity> previousArtists) {
//        this.previousArtists = previousArtists;
//    }

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "artist_band",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    public List<ArtistEntity> getCurrentArtists() {
        return currentArtists;
    }

    public void setCurrentArtists(List<ArtistEntity> currentArtists) {
        this.currentArtists = currentArtists;
    }



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


    @Transient
    @Getter(num = 1)
    public String getN(){
        return name;
    }

    @Transient
    @Getter(num = 2)
    public List<String> getS(){
        List<String> res = new ArrayList<>();
        if (songs.size()!=0) {
            for (SongEntity s : songs) {
                res.add(s.getName());
            }
        }
        return res;
    }
    @Transient
    @Getter(num = 3)
    public List<String> getB(){
        List<String> res = new ArrayList<>();
        if (currentArtists.size()!=0) {
            for (ArtistEntity b : currentArtists) {
                res.add(b.getFirstname());
            }
        }
        return res;
    }


}
