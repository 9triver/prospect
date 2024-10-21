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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "jhi_year")
    private Integer year;

    @Column(name = "riskreportname")
    private String riskreportname;

    @Column(name = "reporttime")
    private LocalDate reporttime;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement creatorid;

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
    private Projectwbs wbsid;

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

    public RiskReport id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
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

    public Integer getYear() {
        return this.year;
    }

    public RiskReport year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public LocalDate getReporttime() {
        return this.reporttime;
    }

    public RiskReport reporttime(LocalDate reporttime) {
        this.setReporttime(reporttime);
        return this;
    }

    public void setReporttime(LocalDate reporttime) {
        this.reporttime = reporttime;
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

    public HrManagement getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(HrManagement hrManagement) {
        this.creatorid = hrManagement;
    }

    public RiskReport creatorid(HrManagement hrManagement) {
        this.setCreatorid(hrManagement);
        return this;
    }

    public Projectwbs getWbsid() {
        return this.wbsid;
    }

    public void setWbsid(Projectwbs projectwbs) {
        this.wbsid = projectwbs;
    }

    public RiskReport wbsid(Projectwbs projectwbs) {
        this.setWbsid(projectwbs);
        return this;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public RiskReport workbag(Workbag workbag) {
        this.setWorkbag(workbag);
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
            ", year=" + getYear() +
            ", riskreportname='" + getRiskreportname() + "'" +
            ", reporttime='" + getReporttime() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
