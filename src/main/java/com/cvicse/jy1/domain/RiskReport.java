package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RiskReport.
 */
@Entity
@Table(name = "risk_report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RiskReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "riskreportname")
    private String riskreportname;

    @Column(name = "releasetime")
    private LocalDate releasetime;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public RiskReport id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public RiskReport type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRiskreportname() {
        return this.riskreportname;
    }

    public RiskReport riskreportname(String riskreportname) {
        this.setRiskreportname(riskreportname);
        return this;
    }

    public void setRiskreportname(String riskreportname) {
        this.riskreportname = riskreportname;
    }

    public LocalDate getReleasetime() {
        return this.releasetime;
    }

    public RiskReport releasetime(LocalDate releasetime) {
        this.setReleasetime(releasetime);
        return this;
    }

    public void setReleasetime(LocalDate releasetime) {
        this.releasetime = releasetime;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public RiskReport auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public RiskReport creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public RiskReport auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskReport)) {
            return false;
        }
        return getId() != null && getId().equals(((RiskReport) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskReport{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", riskreportname='" + getRiskreportname() + "'" +
            ", releasetime='" + getReleasetime() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
