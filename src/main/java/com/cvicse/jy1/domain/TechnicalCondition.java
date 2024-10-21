package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "belongwbsid")
    private String belongwbsid;

    @Column(name = "outsourcingcontractid")
    private String outsourcingcontractid;

    @Column(name = "technicalid")
    private String technicalid;

    @Column(name = "technicalname")
    private String technicalname;

    @Column(name = "changedfilename")
    private String changedfilename;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "applicationdate")
    private LocalDate applicationdate;

    @Column(name = "changedreason")
    private String changedreason;

    @Column(name = "changedbefore")
    private String changedbefore;

    @Column(name = "changedafter")
    private String changedafter;

    @Column(name = "distributionrange")
    private String distributionrange;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "projectmanager",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "department",
            "projectdeliverables",
            "relevantdepartments",
            "wbsids",
            "works",
            "outsourcingContract",
        },
        allowSetters = true
    )
    private Workbag workbag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public TechnicalCondition id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public TechnicalCondition workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getBelongwbsid() {
        return this.belongwbsid;
    }

    public TechnicalCondition belongwbsid(String belongwbsid) {
        this.setBelongwbsid(belongwbsid);
        return this;
    }

    public void setBelongwbsid(String belongwbsid) {
        this.belongwbsid = belongwbsid;
    }

    public String getOutsourcingcontractid() {
        return this.outsourcingcontractid;
    }

    public TechnicalCondition outsourcingcontractid(String outsourcingcontractid) {
        this.setOutsourcingcontractid(outsourcingcontractid);
        return this;
    }

    public void setOutsourcingcontractid(String outsourcingcontractid) {
        this.outsourcingcontractid = outsourcingcontractid;
    }

    public String getTechnicalid() {
        return this.technicalid;
    }

    public TechnicalCondition technicalid(String technicalid) {
        this.setTechnicalid(technicalid);
        return this;
    }

    public void setTechnicalid(String technicalid) {
        this.technicalid = technicalid;
    }

    public String getTechnicalname() {
        return this.technicalname;
    }

    public TechnicalCondition technicalname(String technicalname) {
        this.setTechnicalname(technicalname);
        return this;
    }

    public void setTechnicalname(String technicalname) {
        this.technicalname = technicalname;
    }

    public String getChangedfilename() {
        return this.changedfilename;
    }

    public TechnicalCondition changedfilename(String changedfilename) {
        this.setChangedfilename(changedfilename);
        return this;
    }

    public void setChangedfilename(String changedfilename) {
        this.changedfilename = changedfilename;
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

    public LocalDate getApplicationdate() {
        return this.applicationdate;
    }

    public TechnicalCondition applicationdate(LocalDate applicationdate) {
        this.setApplicationdate(applicationdate);
        return this;
    }

    public void setApplicationdate(LocalDate applicationdate) {
        this.applicationdate = applicationdate;
    }

    public String getChangedreason() {
        return this.changedreason;
    }

    public TechnicalCondition changedreason(String changedreason) {
        this.setChangedreason(changedreason);
        return this;
    }

    public void setChangedreason(String changedreason) {
        this.changedreason = changedreason;
    }

    public String getChangedbefore() {
        return this.changedbefore;
    }

    public TechnicalCondition changedbefore(String changedbefore) {
        this.setChangedbefore(changedbefore);
        return this;
    }

    public void setChangedbefore(String changedbefore) {
        this.changedbefore = changedbefore;
    }

    public String getChangedafter() {
        return this.changedafter;
    }

    public TechnicalCondition changedafter(String changedafter) {
        this.setChangedafter(changedafter);
        return this;
    }

    public void setChangedafter(String changedafter) {
        this.changedafter = changedafter;
    }

    public String getDistributionrange() {
        return this.distributionrange;
    }

    public TechnicalCondition distributionrange(String distributionrange) {
        this.setDistributionrange(distributionrange);
        return this;
    }

    public void setDistributionrange(String distributionrange) {
        this.distributionrange = distributionrange;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public TechnicalCondition remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public TechnicalCondition workbag(Workbag workbag) {
        this.setWorkbag(workbag);
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
            ", workbagid='" + getWorkbagid() + "'" +
            ", belongwbsid='" + getBelongwbsid() + "'" +
            ", outsourcingcontractid='" + getOutsourcingcontractid() + "'" +
            ", technicalid='" + getTechnicalid() + "'" +
            ", technicalname='" + getTechnicalname() + "'" +
            ", changedfilename='" + getChangedfilename() + "'" +
            ", applicant='" + getApplicant() + "'" +
            ", applicationdate='" + getApplicationdate() + "'" +
            ", changedreason='" + getChangedreason() + "'" +
            ", changedbefore='" + getChangedbefore() + "'" +
            ", changedafter='" + getChangedafter() + "'" +
            ", distributionrange='" + getDistributionrange() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
