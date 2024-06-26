package com.cvicse.domain;

import com.cvicse.domain.enumeration.ReturnsStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Qualityreturns.
 */
@Entity
@Table(name = "qualityreturns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Qualityreturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "qualityreturnsname")
    private String qualityreturnsname;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "qualitytype")
    private Integer qualitytype;

    @Column(name = "returnstime")
    private LocalDate returnstime;

    @Enumerated(EnumType.STRING)
    @Column(name = "returnsstatus")
    private ReturnsStatus returnsstatus;

    @JsonIgnoreProperties(value = { "qualityreturns", "creatorid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "qualityreturns")
    private Qualityobjectives qualityobjectives;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Qualityreturns id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualityreturnsname() {
        return this.qualityreturnsname;
    }

    public Qualityreturns qualityreturnsname(String qualityreturnsname) {
        this.setQualityreturnsname(qualityreturnsname);
        return this;
    }

    public void setQualityreturnsname(String qualityreturnsname) {
        this.qualityreturnsname = qualityreturnsname;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Qualityreturns starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Qualityreturns endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getQualitytype() {
        return this.qualitytype;
    }

    public Qualityreturns qualitytype(Integer qualitytype) {
        this.setQualitytype(qualitytype);
        return this;
    }

    public void setQualitytype(Integer qualitytype) {
        this.qualitytype = qualitytype;
    }

    public LocalDate getReturnstime() {
        return this.returnstime;
    }

    public Qualityreturns returnstime(LocalDate returnstime) {
        this.setReturnstime(returnstime);
        return this;
    }

    public void setReturnstime(LocalDate returnstime) {
        this.returnstime = returnstime;
    }

    public ReturnsStatus getReturnsstatus() {
        return this.returnsstatus;
    }

    public Qualityreturns returnsstatus(ReturnsStatus returnsstatus) {
        this.setReturnsstatus(returnsstatus);
        return this;
    }

    public void setReturnsstatus(ReturnsStatus returnsstatus) {
        this.returnsstatus = returnsstatus;
    }

    public Qualityobjectives getQualityobjectives() {
        return this.qualityobjectives;
    }

    public void setQualityobjectives(Qualityobjectives qualityobjectives) {
        if (this.qualityobjectives != null) {
            this.qualityobjectives.setQualityreturns(null);
        }
        if (qualityobjectives != null) {
            qualityobjectives.setQualityreturns(this);
        }
        this.qualityobjectives = qualityobjectives;
    }

    public Qualityreturns qualityobjectives(Qualityobjectives qualityobjectives) {
        this.setQualityobjectives(qualityobjectives);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Qualityreturns)) {
            return false;
        }
        return getId() != null && getId().equals(((Qualityreturns) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Qualityreturns{" +
            "id=" + getId() +
            ", qualityreturnsname='" + getQualityreturnsname() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", qualitytype=" + getQualitytype() +
            ", returnstime='" + getReturnstime() + "'" +
            ", returnsstatus='" + getReturnsstatus() + "'" +
            "}";
    }
}
