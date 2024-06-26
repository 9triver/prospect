package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TechnicalCondition.
 */
@Entity
@Table(name = "technical_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TechnicalCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "decumentid")
    private Long decumentid;

    @Column(name = "claimant")
    private String claimant;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "applicanttime")
    private LocalDate applicanttime;

    @Column(name = "validrange")
    private String validrange;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

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

    public TechnicalCondition id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaption() {
        return this.caption;
    }

    public TechnicalCondition caption(String caption) {
        this.setCaption(caption);
        return this;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public TechnicalCondition projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Long getDecumentid() {
        return this.decumentid;
    }

    public TechnicalCondition decumentid(Long decumentid) {
        this.setDecumentid(decumentid);
        return this;
    }

    public void setDecumentid(Long decumentid) {
        this.decumentid = decumentid;
    }

    public String getClaimant() {
        return this.claimant;
    }

    public TechnicalCondition claimant(String claimant) {
        this.setClaimant(claimant);
        return this;
    }

    public void setClaimant(String claimant) {
        this.claimant = claimant;
    }

    public String getApplicant() {
        return this.applicant;
    }

    public TechnicalCondition applicant(String applicant) {
        this.setApplicant(applicant);
        return this;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public LocalDate getApplicanttime() {
        return this.applicanttime;
    }

    public TechnicalCondition applicanttime(LocalDate applicanttime) {
        this.setApplicanttime(applicanttime);
        return this;
    }

    public void setApplicanttime(LocalDate applicanttime) {
        this.applicanttime = applicanttime;
    }

    public String getValidrange() {
        return this.validrange;
    }

    public TechnicalCondition validrange(String validrange) {
        this.setValidrange(validrange);
        return this;
    }

    public void setValidrange(String validrange) {
        this.validrange = validrange;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public TechnicalCondition createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public TechnicalCondition auditStatus(AuditStatus auditStatus) {
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

    public TechnicalCondition creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public TechnicalCondition auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TechnicalCondition)) {
            return false;
        }
        return getId() != null && getId().equals(((TechnicalCondition) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TechnicalCondition{" +
            "id=" + getId() +
            ", caption='" + getCaption() + "'" +
            ", projectname='" + getProjectname() + "'" +
            ", decumentid=" + getDecumentid() +
            ", claimant='" + getClaimant() + "'" +
            ", applicant='" + getApplicant() + "'" +
            ", applicanttime='" + getApplicanttime() + "'" +
            ", validrange='" + getValidrange() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
