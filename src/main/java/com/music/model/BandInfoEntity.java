package com.music.model;

import javax.persistence.*;
import java.sql.Date;

//@Entity
//Table(name = "BAND_INFO", schema = "mydb")
public class BandInfoEntity {
    private int aId;
    private String aName;
    private String aLastname;
    private String aSpecialization;
    private Date abJoindate;
    private Date abLeavedate;
    private int bId;
    private String bName;

    @Basic
    @Column(name = "a_id", nullable = false)
    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    @Basic
    @Column(name = "a_name", nullable = false, length = 45)
    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    @Basic
    @Column(name = "a_lastname", nullable = false, length = 45)
    public String getaLastname() {
        return aLastname;
    }

    public void setaLastname(String aLastname) {
        this.aLastname = aLastname;
    }

    @Basic
    @Column(name = "a_specialization", nullable = false, length = 45)
    public String getaSpecialization() {
        return aSpecialization;
    }

    public void setaSpecialization(String aSpecialization) {
        this.aSpecialization = aSpecialization;
    }

    @Basic
    @Column(name = "ab_joindate", nullable = true)
    public Date getAbJoindate() {
        return abJoindate;
    }

    public void setAbJoindate(Date abJoindate) {
        this.abJoindate = abJoindate;
    }

    @Basic
    @Column(name = "ab_leavedate", nullable = true)
    public Date getAbLeavedate() {
        return abLeavedate;
    }

    public void setAbLeavedate(Date abLeavedate) {
        this.abLeavedate = abLeavedate;
    }

    @Basic
    @Column(name = "b_id", nullable = false)
    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    @Basic
    @Column(name = "b_name", nullable = true, length = 60)
    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BandInfoEntity that = (BandInfoEntity) o;

        if (aId != that.aId) return false;
        if (bId != that.bId) return false;
        if (aName != null ? !aName.equals(that.aName) : that.aName != null) return false;
        if (aLastname != null ? !aLastname.equals(that.aLastname) : that.aLastname != null) return false;
        if (aSpecialization != null ? !aSpecialization.equals(that.aSpecialization) : that.aSpecialization != null)
            return false;
        if (abJoindate != null ? !abJoindate.equals(that.abJoindate) : that.abJoindate != null) return false;
        if (abLeavedate != null ? !abLeavedate.equals(that.abLeavedate) : that.abLeavedate != null) return false;
        if (bName != null ? !bName.equals(that.bName) : that.bName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aId;
        result = 31 * result + (aName != null ? aName.hashCode() : 0);
        result = 31 * result + (aLastname != null ? aLastname.hashCode() : 0);
        result = 31 * result + (aSpecialization != null ? aSpecialization.hashCode() : 0);
        result = 31 * result + (abJoindate != null ? abJoindate.hashCode() : 0);
        result = 31 * result + (abLeavedate != null ? abLeavedate.hashCode() : 0);
        result = 31 * result + bId;
        result = 31 * result + (bName != null ? bName.hashCode() : 0);
        return result;
    }

    private int id;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
