package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.ProjectStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "projecttype")
    private Integer projecttype;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Column(name = "duedate")
    private LocalDate duedate;

    @Column(name = "priorty")
    private Integer priorty;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = {
            "cycleplan",
            "progressmanagement",
            "qualitymanagement",
            "fundsmanagement",
            "technicalmanagement",
            "contractualfunds",
            "outsourcingmanagement",
            "resourcemanagement",
            "riskmanagement",
            "securitymanagement",
            "document",
            "safetycheck",
            "department",
            "evaluationCriteria",
            "project",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectwbs projectwbs;

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
    @JsonIgnoreProperties(value = { "secrecysystem", "creatorid", "auditorid", "projectid" }, allowSetters = true)
    private ProjectSecrecy projectSecrecy;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private Comprehensivecontrol comprehensivecontrol;

    @JsonIgnoreProperties(
        value = { "wbssubmanage", "pbssubmanage", "project", "administratorid", "auditorid", "responsibleid" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private Wbsmanage wbsmanage;

    @JsonIgnoreProperties(value = { "project", "responsibleid", "auditorid", "outsourcingPurchaseExecute" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private OutsourcingPurchasePlan outsourcingPurchasePlan;

    @JsonIgnoreProperties(value = { "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private ProjectHumanresourcesplan projectHumanresourcesplan;

    @JsonIgnoreProperties(value = { "project", "creatorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private Projectremit projectremit;

    @JsonIgnoreProperties(value = { "project", "creatorid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private Humanresources humanresources;

    @JsonIgnoreProperties(value = { "project", "creatorid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private AnnualSecurityPlan annualSecurityPlan;

    @JsonIgnoreProperties(value = { "evaluationCriteria", "project", "creatorid", "responsibleid", "ratingperson" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private ManagementCapacityEvaluation managementCapacityEvaluation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Project id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Project projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDescription() {
        return this.description;
    }

    public Project description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Project number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getProjecttype() {
        return this.projecttype;
    }

    public Project projecttype(Integer projecttype) {
        this.setProjecttype(projecttype);
        return this;
    }

    public void setProjecttype(Integer projecttype) {
        this.projecttype = projecttype;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Project responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public LocalDate getDuedate() {
        return this.duedate;
    }

    public Project duedate(LocalDate duedate) {
        this.setDuedate(duedate);
        return this;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }

    public Integer getPriorty() {
        return this.priorty;
    }

    public Project priorty(Integer priorty) {
        this.setPriorty(priorty);
        return this;
    }

    public void setPriorty(Integer priorty) {
        this.priorty = priorty;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Project status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Project auditStatus(AuditStatus auditStatus) {
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

    public Project projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Project responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Project auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public ProjectSecrecy getProjectSecrecy() {
        return this.projectSecrecy;
    }

    public void setProjectSecrecy(ProjectSecrecy projectSecrecy) {
        this.projectSecrecy = projectSecrecy;
    }

    public Project projectSecrecy(ProjectSecrecy projectSecrecy) {
        this.setProjectSecrecy(projectSecrecy);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setProject(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setProject(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Project comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Wbsmanage getWbsmanage() {
        return this.wbsmanage;
    }

    public void setWbsmanage(Wbsmanage wbsmanage) {
        if (this.wbsmanage != null) {
            this.wbsmanage.setProject(null);
        }
        if (wbsmanage != null) {
            wbsmanage.setProject(this);
        }
        this.wbsmanage = wbsmanage;
    }

    public Project wbsmanage(Wbsmanage wbsmanage) {
        this.setWbsmanage(wbsmanage);
        return this;
    }

    public OutsourcingPurchasePlan getOutsourcingPurchasePlan() {
        return this.outsourcingPurchasePlan;
    }

    public void setOutsourcingPurchasePlan(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        if (this.outsourcingPurchasePlan != null) {
            this.outsourcingPurchasePlan.setProject(null);
        }
        if (outsourcingPurchasePlan != null) {
            outsourcingPurchasePlan.setProject(this);
        }
        this.outsourcingPurchasePlan = outsourcingPurchasePlan;
    }

    public Project outsourcingPurchasePlan(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        this.setOutsourcingPurchasePlan(outsourcingPurchasePlan);
        return this;
    }

    public ProjectHumanresourcesplan getProjectHumanresourcesplan() {
        return this.projectHumanresourcesplan;
    }

    public void setProjectHumanresourcesplan(ProjectHumanresourcesplan projectHumanresourcesplan) {
        if (this.projectHumanresourcesplan != null) {
            this.projectHumanresourcesplan.setProject(null);
        }
        if (projectHumanresourcesplan != null) {
            projectHumanresourcesplan.setProject(this);
        }
        this.projectHumanresourcesplan = projectHumanresourcesplan;
    }

    public Project projectHumanresourcesplan(ProjectHumanresourcesplan projectHumanresourcesplan) {
        this.setProjectHumanresourcesplan(projectHumanresourcesplan);
        return this;
    }

    public Projectremit getProjectremit() {
        return this.projectremit;
    }

    public void setProjectremit(Projectremit projectremit) {
        if (this.projectremit != null) {
            this.projectremit.setProject(null);
        }
        if (projectremit != null) {
            projectremit.setProject(this);
        }
        this.projectremit = projectremit;
    }

    public Project projectremit(Projectremit projectremit) {
        this.setProjectremit(projectremit);
        return this;
    }

    public Humanresources getHumanresources() {
        return this.humanresources;
    }

    public void setHumanresources(Humanresources humanresources) {
        if (this.humanresources != null) {
            this.humanresources.setProject(null);
        }
        if (humanresources != null) {
            humanresources.setProject(this);
        }
        this.humanresources = humanresources;
    }

    public Project humanresources(Humanresources humanresources) {
        this.setHumanresources(humanresources);
        return this;
    }

    public AnnualSecurityPlan getAnnualSecurityPlan() {
        return this.annualSecurityPlan;
    }

    public void setAnnualSecurityPlan(AnnualSecurityPlan annualSecurityPlan) {
        if (this.annualSecurityPlan != null) {
            this.annualSecurityPlan.setProject(null);
        }
        if (annualSecurityPlan != null) {
            annualSecurityPlan.setProject(this);
        }
        this.annualSecurityPlan = annualSecurityPlan;
    }

    public Project annualSecurityPlan(AnnualSecurityPlan annualSecurityPlan) {
        this.setAnnualSecurityPlan(annualSecurityPlan);
        return this;
    }

    public ManagementCapacityEvaluation getManagementCapacityEvaluation() {
        return this.managementCapacityEvaluation;
    }

    public void setManagementCapacityEvaluation(ManagementCapacityEvaluation managementCapacityEvaluation) {
        if (this.managementCapacityEvaluation != null) {
            this.managementCapacityEvaluation.setProject(null);
        }
        if (managementCapacityEvaluation != null) {
            managementCapacityEvaluation.setProject(this);
        }
        this.managementCapacityEvaluation = managementCapacityEvaluation;
    }

    public Project managementCapacityEvaluation(ManagementCapacityEvaluation managementCapacityEvaluation) {
        this.setManagementCapacityEvaluation(managementCapacityEvaluation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return getId() != null && getId().equals(((Project) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectname='" + getProjectname() + "'" +
            ", description='" + getDescription() + "'" +
            ", number=" + getNumber() +
            ", projecttype=" + getProjecttype() +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", duedate='" + getDuedate() + "'" +
            ", priorty=" + getPriorty() +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
