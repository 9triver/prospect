package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Comprehensivecontrol.
 */
@Entity
@Table(name = "comprehensivecontrol")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Comprehensivecontrol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "actualstarttime")
    private LocalDate actualstarttime;

    @Column(name = "actualendtime")
    private LocalDate actualendtime;

    @Column(name = "result")
    private String result;

    @Column(name = "acceptancetype")
    private String acceptancetype;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "responsiblename")
    private String responsiblename;

    @JsonIgnoreProperties(
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "comprehensivecontrol", "progressplanreturns" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Progressplan progress;

    @JsonIgnoreProperties(
        value = {
            "projectwbs",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingPurchasePlan",
            "projectHumanresourcesplan",
            "projectremit",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Project project;

    @JsonIgnoreProperties(value = { "wbs", "comprehensivecontrol" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Fundsmanagement funds;

    @JsonIgnoreProperties(value = { "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Totalbudget totalbudget;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Unitbudget unitbudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    private Department decument;

    /**
     * 协调部门
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    private Department coordinationdepartment;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Comprehensivecontrol id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public Comprehensivecontrol description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Comprehensivecontrol number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getType() {
        return this.type;
    }

    public Comprehensivecontrol type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Comprehensivecontrol starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Comprehensivecontrol endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public LocalDate getActualstarttime() {
        return this.actualstarttime;
    }

    public Comprehensivecontrol actualstarttime(LocalDate actualstarttime) {
        this.setActualstarttime(actualstarttime);
        return this;
    }

    public void setActualstarttime(LocalDate actualstarttime) {
        this.actualstarttime = actualstarttime;
    }

    public LocalDate getActualendtime() {
        return this.actualendtime;
    }

    public Comprehensivecontrol actualendtime(LocalDate actualendtime) {
        this.setActualendtime(actualendtime);
        return this;
    }

    public void setActualendtime(LocalDate actualendtime) {
        this.actualendtime = actualendtime;
    }

    public String getResult() {
        return this.result;
    }

    public Comprehensivecontrol result(String result) {
        this.setResult(result);
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAcceptancetype() {
        return this.acceptancetype;
    }

    public Comprehensivecontrol acceptancetype(String acceptancetype) {
        this.setAcceptancetype(acceptancetype);
        return this;
    }

    public void setAcceptancetype(String acceptancetype) {
        this.acceptancetype = acceptancetype;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Comprehensivecontrol status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Comprehensivecontrol auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Comprehensivecontrol responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public Progressplan getProgress() {
        return this.progress;
    }

    public void setProgress(Progressplan progressplan) {
        this.progress = progressplan;
    }

    public Comprehensivecontrol progress(Progressplan progressplan) {
        this.setProgress(progressplan);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Comprehensivecontrol project(Project project) {
        this.setProject(project);
        return this;
    }

    public Fundsmanagement getFunds() {
        return this.funds;
    }

    public void setFunds(Fundsmanagement fundsmanagement) {
        this.funds = fundsmanagement;
    }

    public Comprehensivecontrol funds(Fundsmanagement fundsmanagement) {
        this.setFunds(fundsmanagement);
        return this;
    }

    public Totalbudget getTotalbudget() {
        return this.totalbudget;
    }

    public void setTotalbudget(Totalbudget totalbudget) {
        this.totalbudget = totalbudget;
    }

    public Comprehensivecontrol totalbudget(Totalbudget totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public Unitbudget getUnitbudget() {
        return this.unitbudget;
    }

    public void setUnitbudget(Unitbudget unitbudget) {
        this.unitbudget = unitbudget;
    }

    public Comprehensivecontrol unitbudget(Unitbudget unitbudget) {
        this.setUnitbudget(unitbudget);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Comprehensivecontrol responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Comprehensivecontrol auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Department getDecument() {
        return this.decument;
    }

    public void setDecument(Department department) {
        this.decument = department;
    }

    public Comprehensivecontrol decument(Department department) {
        this.setDecument(department);
        return this;
    }

    public Department getCoordinationdepartment() {
        return this.coordinationdepartment;
    }

    public void setCoordinationdepartment(Department department) {
        this.coordinationdepartment = department;
    }

    public Comprehensivecontrol coordinationdepartment(Department department) {
        this.setCoordinationdepartment(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comprehensivecontrol)) {
            return false;
        }
        return getId() != null && getId().equals(((Comprehensivecontrol) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comprehensivecontrol{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", number=" + getNumber() +
            ", type=" + getType() +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", actualstarttime='" + getActualstarttime() + "'" +
            ", actualendtime='" + getActualendtime() + "'" +
            ", result='" + getResult() + "'" +
            ", acceptancetype='" + getAcceptancetype() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            "}";
    }
}
