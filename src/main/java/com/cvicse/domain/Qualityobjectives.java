package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Qualityobjectives.
 */
@Entity
@Table(name = "qualityobjectives")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Qualityobjectives implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "qualityobjectivesname")
    private String qualityobjectivesname;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "qualityobjectives" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Qualityreturns qualityreturns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Qualityobjectives id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualityobjectivesname() {
        return this.qualityobjectivesname;
    }

    public Qualityobjectives qualityobjectivesname(String qualityobjectivesname) {
        this.setQualityobjectivesname(qualityobjectivesname);
        return this;
    }

    public void setQualityobjectivesname(String qualityobjectivesname) {
        this.qualityobjectivesname = qualityobjectivesname;
    }

    public Long getYear() {
        return this.year;
    }

    public Qualityobjectives year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Qualityobjectives createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Qualityobjectives creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Qualityobjectives secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Qualityobjectives auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Qualityreturns getQualityreturns() {
        return this.qualityreturns;
    }

    public void setQualityreturns(Qualityreturns qualityreturns) {
        this.qualityreturns = qualityreturns;
    }

    public Qualityobjectives qualityreturns(Qualityreturns qualityreturns) {
        this.setQualityreturns(qualityreturns);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Qualityobjectives creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Qualityobjectives auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Qualityobjectives)) {
            return false;
        }
        return getId() != null && getId().equals(((Qualityobjectives) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Qualityobjectives{" +
            "id=" + getId() +
            ", qualityobjectivesname='" + getQualityobjectivesname() + "'" +
            ", year=" + getYear() +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
