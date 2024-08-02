package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UnQualityAudit.
 */
@Entity
@Table(name = "un_quality_audit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnQualityAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "unqualityname")
    private String unqualityname;

    @Column(name = "unqualitytype")
    private Integer unqualitytype;

    @Column(name = "belongunitid")
    private String belongunitid;

    @Column(name = "belongunitname")
    private String belongunitname;

    @Column(name = "auditteam")
    private String auditteam;

    @Column(name = "auditperson")
    private String auditperson;

    @Column(name = "unqualitynum", precision = 21, scale = 2)
    private BigDecimal unqualitynum;

    @Column(name = "creatorname")
    private String creatorname;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public UnQualityAudit id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnqualityname() {
        return this.unqualityname;
    }

    public UnQualityAudit unqualityname(String unqualityname) {
        this.setUnqualityname(unqualityname);
        return this;
    }

    public void setUnqualityname(String unqualityname) {
        this.unqualityname = unqualityname;
    }

    public Integer getUnqualitytype() {
        return this.unqualitytype;
    }

    public UnQualityAudit unqualitytype(Integer unqualitytype) {
        this.setUnqualitytype(unqualitytype);
        return this;
    }

    public void setUnqualitytype(Integer unqualitytype) {
        this.unqualitytype = unqualitytype;
    }

    public String getBelongunitid() {
        return this.belongunitid;
    }

    public UnQualityAudit belongunitid(String belongunitid) {
        this.setBelongunitid(belongunitid);
        return this;
    }

    public void setBelongunitid(String belongunitid) {
        this.belongunitid = belongunitid;
    }

    public String getBelongunitname() {
        return this.belongunitname;
    }

    public UnQualityAudit belongunitname(String belongunitname) {
        this.setBelongunitname(belongunitname);
        return this;
    }

    public void setBelongunitname(String belongunitname) {
        this.belongunitname = belongunitname;
    }

    public String getAuditteam() {
        return this.auditteam;
    }

    public UnQualityAudit auditteam(String auditteam) {
        this.setAuditteam(auditteam);
        return this;
    }

    public void setAuditteam(String auditteam) {
        this.auditteam = auditteam;
    }

    public String getAuditperson() {
        return this.auditperson;
    }

    public UnQualityAudit auditperson(String auditperson) {
        this.setAuditperson(auditperson);
        return this;
    }

    public void setAuditperson(String auditperson) {
        this.auditperson = auditperson;
    }

    public BigDecimal getUnqualitynum() {
        return this.unqualitynum;
    }

    public UnQualityAudit unqualitynum(BigDecimal unqualitynum) {
        this.setUnqualitynum(unqualitynum);
        return this;
    }

    public void setUnqualitynum(BigDecimal unqualitynum) {
        this.unqualitynum = unqualitynum;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public UnQualityAudit creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public UnQualityAudit auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnQualityAudit)) {
            return false;
        }
        return getId() != null && getId().equals(((UnQualityAudit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnQualityAudit{" +
            "id=" + getId() +
            ", unqualityname='" + getUnqualityname() + "'" +
            ", unqualitytype=" + getUnqualitytype() +
            ", belongunitid='" + getBelongunitid() + "'" +
            ", belongunitname='" + getBelongunitname() + "'" +
            ", auditteam='" + getAuditteam() + "'" +
            ", auditperson='" + getAuditperson() + "'" +
            ", unqualitynum=" + getUnqualitynum() +
            ", creatorname='" + getCreatorname() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
