package com.music.entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "artist_band", schema = "mydb")
public class ArtistBandEntity {
    private ArtistEntity artistId;
    private BandEntity authorId;
    private int id;
    private Date joinDate;
    private Date leaveDate;


    @ManyToOne
    @JoinColumn(name="artist_ID")
    public ArtistEntity getArtistId() {
        return artistId;
    }

    public void setArtistId(ArtistEntity artistId) {
        this.artistId = artistId;
    }


    @ManyToOne
    @JoinColumn(name="author_ID")
    public BandEntity getAuthorId() {
        return authorId;
    }

    public void setAuthorId(BandEntity authorId) {
        this.authorId = authorId;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "join_date", nullable = true)
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "leave_date", nullable = true)
    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtistBandEntity that = (ArtistBandEntity) o;

        if (artistId != that.artistId) return false;
        if (authorId != that.authorId) return false;
        if (id != that.id) return false;
        if (joinDate != null ? !joinDate.equals(that.joinDate) : that.joinDate != null) return false;
        if (leaveDate != null ? !leaveDate.equals(that.leaveDate) : that.leaveDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 4;
        result = 31 * result;
        result = 31 * result + id;
        result = 31 * result + (joinDate != null ? joinDate.hashCode() : 0);
        result = 31 * result + (leaveDate != null ? leaveDate.hashCode() : 0);
        return result;
    }

    @Transient
    public StringProperty authorIdProperty(){
        return new SimpleStringProperty(authorId.getName());
    }

    @Transient
    public StringProperty firstnameProperty(){
        return new SimpleStringProperty(artistId.getFirstname());
    }

    @Transient
    public StringProperty lastnameProperty(){
        return new SimpleStringProperty(artistId.getLastname());
    }

    @Transient
    public StringProperty specializationProperty(){
        return new SimpleStringProperty(artistId.getSpecialization());
    }
}
