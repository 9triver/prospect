package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "unqualityid", unique = true)
    private Long unqualityid;

    @Column(name = "unqualityname")
    private String unqualityname;

    @Column(name = "unqualitytype")
    private Integer unqualitytype;

    @Column(name = "belongunitid")
    private Long belongunitid;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers inspector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UnQualityAudit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnqualityid() {
        return this.unqualityid;
    }

    public UnQualityAudit unqualityid(Long unqualityid) {
        this.setUnqualityid(unqualityid);
        return this;
    }

    public void setUnqualityid(Long unqualityid) {
        this.unqualityid = unqualityid;
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

    public Long getBelongunitid() {
        return this.belongunitid;
    }

    public UnQualityAudit belongunitid(Long belongunitid) {
        this.setBelongunitid(belongunitid);
        return this;
    }

    public void setBelongunitid(Long belongunitid) {
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

    public Officers getInspector() {
        return this.inspector;
    }

    public void setInspector(Officers officers) {
        this.inspector = officers;
    }

    public UnQualityAudit inspector(Officers officers) {
        this.setInspector(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public UnQualityAudit auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
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
            ", unqualityid=" + getUnqualityid() +
            ", unqualityname='" + getUnqualityname() + "'" +
            ", unqualitytype=" + getUnqualitytype() +
            ", belongunitid=" + getBelongunitid() +
            ", belongunitname='" + getBelongunitname() + "'" +
            ", auditteam='" + getAuditteam() + "'" +
            ", auditperson='" + getAuditperson() + "'" +
            ", unqualitynum=" + getUnqualitynum() +
            ", creatorname='" + getCreatorname() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
