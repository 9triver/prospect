package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DeviationPermitApplication.
 */
@Entity
@Table(name = "deviation_permit_application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeviationPermitApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "technicalfileid")
    private String technicalfileid;

    @Column(name = "applicationunit")
    private String applicationunit;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "applicationdate")
    private LocalDate applicationdate;

    @Column(name = "permitcontent")
    private String permitcontent;

    @Column(name = "permitreason")
    private String permitreason;

    @Column(name = "projectinfluence")
    private String projectinfluence;

    @Column(name = "contractinfluence")
    private String contractinfluence;

    @Column(name = "permitrange")
    private String permitrange;

    @Column(name = "implementationdate")
    private LocalDate implementationdate;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "projectpbs",
            "responsibleperson",
            "technicaldirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "projectdeliverables",
            "relevantdepartments",
            "workbags",
            "progressPlans",
            "projectBudgets",
            "projects",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "projectTotalwbs",
        },
        allowSetters = true
    )
    private Projectwbs projectwbs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public DeviationPermitApplication id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public DeviationPermitApplication wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getTechnicalfileid() {
        return this.technicalfileid;
    }

    public DeviationPermitApplication technicalfileid(String technicalfileid) {
        this.setTechnicalfileid(technicalfileid);
        return this;
    }

    public void setTechnicalfileid(String technicalfileid) {
        this.technicalfileid = technicalfileid;
    }

    public String getApplicationunit() {
        return this.applicationunit;
    }

    public DeviationPermitApplication applicationunit(String applicationunit) {
        this.setApplicationunit(applicationunit);
        return this;
    }

    public void setApplicationunit(String applicationunit) {
        this.applicationunit = applicationunit;
    }

    public String getApplicant() {
        return this.applicant;
    }

    public DeviationPermitApplication applicant(String applicant) {
        this.setApplicant(applicant);
        return this;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public LocalDate getApplicationdate() {
        return this.applicationdate;
    }

    public DeviationPermitApplication applicationdate(LocalDate applicationdate) {
        this.setApplicationdate(applicationdate);
        return this;
    }

    public void setApplicationdate(LocalDate applicationdate) {
        this.applicationdate = applicationdate;
    }

    public String getPermitcontent() {
        return this.permitcontent;
    }

    public DeviationPermitApplication permitcontent(String permitcontent) {
        this.setPermitcontent(permitcontent);
        return this;
    }

    public void setPermitcontent(String permitcontent) {
        this.permitcontent = permitcontent;
    }

    public String getPermitreason() {
        return this.permitreason;
    }

    public DeviationPermitApplication permitreason(String permitreason) {
        this.setPermitreason(permitreason);
        return this;
    }

    public void setPermitreason(String permitreason) {
        this.permitreason = permitreason;
    }

    public String getProjectinfluence() {
        return this.projectinfluence;
    }

    public DeviationPermitApplication projectinfluence(String projectinfluence) {
        this.setProjectinfluence(projectinfluence);
        return this;
    }

    public void setProjectinfluence(String projectinfluence) {
        this.projectinfluence = projectinfluence;
    }

    public String getContractinfluence() {
        return this.contractinfluence;
    }

    public DeviationPermitApplication contractinfluence(String contractinfluence) {
        this.setContractinfluence(contractinfluence);
        return this;
    }

    public void setContractinfluence(String contractinfluence) {
        this.contractinfluence = contractinfluence;
    }

    public String getPermitrange() {
        return this.permitrange;
    }

    public DeviationPermitApplication permitrange(String permitrange) {
        this.setPermitrange(permitrange);
        return this;
    }

    public void setPermitrange(String permitrange) {
        this.permitrange = permitrange;
    }

    public LocalDate getImplementationdate() {
        return this.implementationdate;
    }

    public DeviationPermitApplication implementationdate(LocalDate implementationdate) {
        this.setImplementationdate(implementationdate);
        return this;
    }

    public void setImplementationdate(LocalDate implementationdate) {
        this.implementationdate = implementationdate;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public DeviationPermitApplication remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public DeviationPermitApplication auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public DeviationPermitApplication projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviationPermitApplication)) {
            return false;
        }
        return getId() != null && getId().equals(((DeviationPermitApplication) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeviationPermitApplication{" +
            "id=" + getId() +
            ", wbsid='" + getWbsid() + "'" +
            ", technicalfileid='" + getTechnicalfileid() + "'" +
            ", applicationunit='" + getApplicationunit() + "'" +
            ", applicant='" + getApplicant() + "'" +
            ", applicationdate='" + getApplicationdate() + "'" +
            ", permitcontent='" + getPermitcontent() + "'" +
            ", permitreason='" + getPermitreason() + "'" +
            ", projectinfluence='" + getProjectinfluence() + "'" +
            ", contractinfluence='" + getContractinfluence() + "'" +
            ", permitrange='" + getPermitrange() + "'" +
            ", implementationdate='" + getImplementationdate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
