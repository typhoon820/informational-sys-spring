package com.music.entity;

import com.music.utils.Annotations.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artist", schema = "mydb")
public class ArtistEntity extends AbstractModel {
    private int id;
    private String firstname;
    private String lastname;
    private String specialization;
    private List<ArtistBandEntity> bands = new ArrayList<>();


//    @ManyToMany(cascade = {CascadeType.PERSIST,
//            CascadeType.MERGE})
//    @JoinTable(name = "artist_band",
//            joinColumns = @JoinColumn(name = "artist_id"),
//            inverseJoinColumns = @JoinColumn(name = "author_id"))
    @OneToMany(mappedBy = "artistId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<ArtistBandEntity> getBands() {
        return bands;
    }

    public void setBands(List<ArtistBandEntity> bands) {
        this.bands = bands;
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
    @Column(name = "firstname", nullable = false, length = 45)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = false, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "specialization", nullable = false, length = 45)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistEntity that = (ArtistEntity) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (specialization != null ? !specialization.equals(that.specialization) : that.specialization != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (specialization != null ? specialization.hashCode() : 0);
        return result;
    }


    @Transient
    @Getter(num = 1)
    public String getN(){
        return firstname;
    }

    @Transient
    @Getter(num = 2)
    public String getL(){
        return lastname;
    }


    @Transient
    @Getter(num = 3)
    public String getSp(){
        return specialization;
    }
    @Transient
    @Getter(num = 4)
    public List<String> getB(){
        List<String> res = new ArrayList<>();
        if (bands.size()!=0) {
            for (ArtistBandEntity b : bands) {
                res.add(b.getAuthorId().getName());
            }
        }
        return res;
    }
}
