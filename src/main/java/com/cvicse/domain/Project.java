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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "projectid", unique = true)
    private Long projectid;

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

    @Column(name = "progressid")
    private Long progressid;

    @Column(name = "returnsid")
    private Long returnsid;

    @Column(name = "qualityid")
    private Long qualityid;

    @Column(name = "fundsid")
    private Long fundsid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid", "project" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Cycleplan cycleplan;

    @JsonIgnoreProperties(
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "project", "comprehensivecontrol" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Progressmanagement progressmanagement;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Qualitymanagement qualitymanagement;

    @JsonIgnoreProperties(
        value = {
            "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "project", "comprehensivecontrol", "fundsavailability",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Fundsmanagement fundsmanagement;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private TechnicalCondition technicalCondition;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Contractualfunds contractualfunds;

    @JsonIgnoreProperties(value = { "outsourcingplanid", "responsibleid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private OutsourcingmPurchaseExecute outsourcingmPurchaseExecute;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Resourcemanagement resourcemanagement;

    @JsonIgnoreProperties(value = { "creatorid", "responsibleid", "auditorid", "project", "riskreport" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Riskmanagement riskmanagement;

    @JsonIgnoreProperties(
        value = { "creatorid", "project", "cycleplan", "annualplan", "monthplan", "fundsmanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

    @JsonIgnoreProperties(value = { "auditorid", "responsibleid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Safetycheck safetycheck;

    @JsonIgnoreProperties(
        value = { "officers", "project", "planstrategy", "progressmanagement", "evaluationCriteria" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(value = { "department", "project", "managementCapacityEvaluation" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EvaluationCriteria evaluationCriteria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "secrecymanagement", "creatorid", "auditorid", "projectid" }, allowSetters = true)
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

    @JsonIgnoreProperties(value = { "project", "responsibleid", "auditorid", "outsourcingmPurchaseExecute" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "project")
    private OutsourcingmPurchasePlan outsourcingmPurchasePlan;

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

    public Long getId() {
        return this.id;
    }

    public Project id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectid() {
        return this.projectid;
    }

    public Project projectid(Long projectid) {
        this.setProjectid(projectid);
        return this;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
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

    public Long getProgressid() {
        return this.progressid;
    }

    public Project progressid(Long progressid) {
        this.setProgressid(progressid);
        return this;
    }

    public void setProgressid(Long progressid) {
        this.progressid = progressid;
    }

    public Long getReturnsid() {
        return this.returnsid;
    }

    public Project returnsid(Long returnsid) {
        this.setReturnsid(returnsid);
        return this;
    }

    public void setReturnsid(Long returnsid) {
        this.returnsid = returnsid;
    }

    public Long getQualityid() {
        return this.qualityid;
    }

    public Project qualityid(Long qualityid) {
        this.setQualityid(qualityid);
        return this;
    }

    public void setQualityid(Long qualityid) {
        this.qualityid = qualityid;
    }

    public Long getFundsid() {
        return this.fundsid;
    }

    public Project fundsid(Long fundsid) {
        this.setFundsid(fundsid);
        return this;
    }

    public void setFundsid(Long fundsid) {
        this.fundsid = fundsid;
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

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        this.cycleplan = cycleplan;
    }

    public Project cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Progressmanagement getProgressmanagement() {
        return this.progressmanagement;
    }

    public void setProgressmanagement(Progressmanagement progressmanagement) {
        this.progressmanagement = progressmanagement;
    }

    public Project progressmanagement(Progressmanagement progressmanagement) {
        this.setProgressmanagement(progressmanagement);
        return this;
    }

    public Qualitymanagement getQualitymanagement() {
        return this.qualitymanagement;
    }

    public void setQualitymanagement(Qualitymanagement qualitymanagement) {
        this.qualitymanagement = qualitymanagement;
    }

    public Project qualitymanagement(Qualitymanagement qualitymanagement) {
        this.setQualitymanagement(qualitymanagement);
        return this;
    }

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        this.fundsmanagement = fundsmanagement;
    }

    public Project fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
        return this;
    }

    public TechnicalCondition getTechnicalCondition() {
        return this.technicalCondition;
    }

    public void setTechnicalCondition(TechnicalCondition technicalCondition) {
        this.technicalCondition = technicalCondition;
    }

    public Project technicalCondition(TechnicalCondition technicalCondition) {
        this.setTechnicalCondition(technicalCondition);
        return this;
    }

    public Contractualfunds getContractualfunds() {
        return this.contractualfunds;
    }

    public void setContractualfunds(Contractualfunds contractualfunds) {
        this.contractualfunds = contractualfunds;
    }

    public Project contractualfunds(Contractualfunds contractualfunds) {
        this.setContractualfunds(contractualfunds);
        return this;
    }

    public OutsourcingmPurchaseExecute getOutsourcingmPurchaseExecute() {
        return this.outsourcingmPurchaseExecute;
    }

    public void setOutsourcingmPurchaseExecute(OutsourcingmPurchaseExecute outsourcingmPurchaseExecute) {
        this.outsourcingmPurchaseExecute = outsourcingmPurchaseExecute;
    }

    public Project outsourcingmPurchaseExecute(OutsourcingmPurchaseExecute outsourcingmPurchaseExecute) {
        this.setOutsourcingmPurchaseExecute(outsourcingmPurchaseExecute);
        return this;
    }

    public Resourcemanagement getResourcemanagement() {
        return this.resourcemanagement;
    }

    public void setResourcemanagement(Resourcemanagement resourcemanagement) {
        this.resourcemanagement = resourcemanagement;
    }

    public Project resourcemanagement(Resourcemanagement resourcemanagement) {
        this.setResourcemanagement(resourcemanagement);
        return this;
    }

    public Riskmanagement getRiskmanagement() {
        return this.riskmanagement;
    }

    public void setRiskmanagement(Riskmanagement riskmanagement) {
        this.riskmanagement = riskmanagement;
    }

    public Project riskmanagement(Riskmanagement riskmanagement) {
        this.setRiskmanagement(riskmanagement);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Project document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Safetycheck getSafetycheck() {
        return this.safetycheck;
    }

    public void setSafetycheck(Safetycheck safetycheck) {
        this.safetycheck = safetycheck;
    }

    public Project safetycheck(Safetycheck safetycheck) {
        this.setSafetycheck(safetycheck);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Project department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public EvaluationCriteria getEvaluationCriteria() {
        return this.evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public Project evaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.setEvaluationCriteria(evaluationCriteria);
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

    public OutsourcingmPurchasePlan getOutsourcingmPurchasePlan() {
        return this.outsourcingmPurchasePlan;
    }

    public void setOutsourcingmPurchasePlan(OutsourcingmPurchasePlan outsourcingmPurchasePlan) {
        if (this.outsourcingmPurchasePlan != null) {
            this.outsourcingmPurchasePlan.setProject(null);
        }
        if (outsourcingmPurchasePlan != null) {
            outsourcingmPurchasePlan.setProject(this);
        }
        this.outsourcingmPurchasePlan = outsourcingmPurchasePlan;
    }

    public Project outsourcingmPurchasePlan(OutsourcingmPurchasePlan outsourcingmPurchasePlan) {
        this.setOutsourcingmPurchasePlan(outsourcingmPurchasePlan);
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
            ", projectid=" + getProjectid() +
            ", projectname='" + getProjectname() + "'" +
            ", description='" + getDescription() + "'" +
            ", number=" + getNumber() +
            ", projecttype=" + getProjecttype() +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", duedate='" + getDuedate() + "'" +
            ", priorty=" + getPriorty() +
            ", progressid=" + getProgressid() +
            ", returnsid=" + getReturnsid() +
            ", qualityid=" + getQualityid() +
            ", fundsid=" + getFundsid() +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
