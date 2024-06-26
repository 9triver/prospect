package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Riskreport.
 */
@Entity
@Table(name = "riskreport")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Riskreport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "riskid")
    private Long riskid;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "riskreportname")
    private String riskreportname;

    @Column(name = "releasetime")
    private LocalDate releasetime;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "creatorid", "responsibleid", "auditorid", "project", "riskreport" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Riskmanagement riskmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

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

    public Riskreport id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRiskid() {
        return this.riskid;
    }

    public Riskreport riskid(Long riskid) {
        this.setRiskid(riskid);
        return this;
    }

    public void setRiskid(Long riskid) {
        this.riskid = riskid;
    }

    public String getType() {
        return this.type;
    }

    public Riskreport type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRiskreportname() {
        return this.riskreportname;
    }

    public Riskreport riskreportname(String riskreportname) {
        this.setRiskreportname(riskreportname);
        return this;
    }

    public void setRiskreportname(String riskreportname) {
        this.riskreportname = riskreportname;
    }

    public LocalDate getReleasetime() {
        return this.releasetime;
    }

    public Riskreport releasetime(LocalDate releasetime) {
        this.setReleasetime(releasetime);
        return this;
    }

    public void setReleasetime(LocalDate releasetime) {
        this.releasetime = releasetime;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Riskreport auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Riskmanagement getRiskmanagement() {
        return this.riskmanagement;
    }

    public void setRiskmanagement(Riskmanagement riskmanagement) {
        this.riskmanagement = riskmanagement;
    }

    public Riskreport riskmanagement(Riskmanagement riskmanagement) {
        this.setRiskmanagement(riskmanagement);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Riskreport creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Riskreport auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Riskreport)) {
            return false;
        }
        return getId() != null && getId().equals(((Riskreport) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Riskreport{" +
            "id=" + getId() +
            ", riskid=" + getRiskid() +
            ", type='" + getType() + "'" +
            ", riskreportname='" + getRiskreportname() + "'" +
            ", releasetime='" + getReleasetime() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
