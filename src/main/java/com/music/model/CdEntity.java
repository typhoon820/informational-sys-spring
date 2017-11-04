package com.music.model;

import javax.persistence.*;

@Entity
@Table(name = "cd", schema = "mydb", catalog = "")
public class CdEntity extends AbstractModel{
    private int id;
    private Integer copiesAvailable;

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
    @Column(name = "copies_available", nullable = true)
    public Integer getCopiesAvailable() {
        return copiesAvailable;
    }

    public void setCopiesAvailable(Integer copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CdEntity cdEntity = (CdEntity) o;

        if (id != cdEntity.id) return false;
        if (copiesAvailable != null ? !copiesAvailable.equals(cdEntity.copiesAvailable) : cdEntity.copiesAvailable != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (copiesAvailable != null ? copiesAvailable.hashCode() : 0);
        return result;
    }
}
