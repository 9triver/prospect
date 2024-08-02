package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectwbs.
 */
@Entity
@Table(name = "projectwbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectwbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "parentwbsid")
    private String parentwbsid;

    @Column(name = "pbsid")
    private String pbsid;

    @Column(name = "description")
    private String description;

    @Column(name = "belongfront")
    private String belongfront;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "priorty")
    private Integer priorty;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "deliverables")
    private String deliverables;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "workbag")
    private Integer workbag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers technicaldirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers administrativedirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department relevantdepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectpbs", "projectwbs" }, allowSetters = true)
    private Set<Project> projects = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "projectwbs",
            "projects",
        },
        allowSetters = true
    )
    private Set<Projectpbs> projectpbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "cooperatingperson",
            "auditorid",
            "responsibledepartment",
            "cooperatingdepartment",
            "planReturns",
            "projectwbs",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<ProgressPlan> progressPlans = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<FundsEstimation> fundsEstimations = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectwbs" }, allowSetters = true)
    private Set<ContractCostBudget> contractCostBudgets = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs", "contracts" }, allowSetters = true)
    private Set<CostControlSystem> costControlSystems = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs", "qualityReturns" }, allowSetters = true)
    private Set<QualityObjectives> qualityObjectives = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<OutsourcingContractual> outsourcingContractuals = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<OutsourcingPurchasePlan> outsourcingPurchasePlans = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<Technical> technicals = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<TechnicalCondition> technicalConditions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "riskReport", "creatorid", "responsibleperson", "auditorid", "projectwbs", "progressPlans" },
        allowSetters = true
    )
    private Set<ProjectRisk> projectRisks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Projectwbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public Projectwbs wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getParentwbsid() {
        return this.parentwbsid;
    }

    public Projectwbs parentwbsid(String parentwbsid) {
        this.setParentwbsid(parentwbsid);
        return this;
    }

    public void setParentwbsid(String parentwbsid) {
        this.parentwbsid = parentwbsid;
    }

    public String getPbsid() {
        return this.pbsid;
    }

    public Projectwbs pbsid(String pbsid) {
        this.setPbsid(pbsid);
        return this;
    }

    public void setPbsid(String pbsid) {
        this.pbsid = pbsid;
    }

    public String getDescription() {
        return this.description;
    }

    public Projectwbs description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBelongfront() {
        return this.belongfront;
    }

    public Projectwbs belongfront(String belongfront) {
        this.setBelongfront(belongfront);
        return this;
    }

    public void setBelongfront(String belongfront) {
        this.belongfront = belongfront;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Projectwbs starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Projectwbs endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public Projectwbs progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getType() {
        return this.type;
    }

    public Projectwbs type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriorty() {
        return this.priorty;
    }

    public Projectwbs priorty(Integer priorty) {
        this.setPriorty(priorty);
        return this;
    }

    public void setPriorty(Integer priorty) {
        this.priorty = priorty;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Projectwbs secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getDeliverables() {
        return this.deliverables;
    }

    public Projectwbs deliverables(String deliverables) {
        this.setDeliverables(deliverables);
        return this;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Projectwbs status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Projectwbs auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getWorkbag() {
        return this.workbag;
    }

    public Projectwbs workbag(Integer workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public void setWorkbag(Integer workbag) {
        this.workbag = workbag;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public Projectwbs responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getTechnicaldirector() {
        return this.technicaldirector;
    }

    public void setTechnicaldirector(Officers officers) {
        this.technicaldirector = officers;
    }

    public Projectwbs technicaldirector(Officers officers) {
        this.setTechnicaldirector(officers);
        return this;
    }

    public Officers getAdministrativedirector() {
        return this.administrativedirector;
    }

    public void setAdministrativedirector(Officers officers) {
        this.administrativedirector = officers;
    }

    public Projectwbs administrativedirector(Officers officers) {
        this.setAdministrativedirector(officers);
        return this;
    }

    public Officers getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(Officers officers) {
        this.knowingpeople = officers;
    }

    public Projectwbs knowingpeople(Officers officers) {
        this.setKnowingpeople(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Projectwbs auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Department getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public void setResponsibledepartment(Department department) {
        this.responsibledepartment = department;
    }

    public Projectwbs responsibledepartment(Department department) {
        this.setResponsibledepartment(department);
        return this;
    }

    public Department getRelevantdepartment() {
        return this.relevantdepartment;
    }

    public void setRelevantdepartment(Department department) {
        this.relevantdepartment = department;
    }

    public Projectwbs relevantdepartment(Department department) {
        this.setRelevantdepartment(department);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Projectwbs department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.removeProjectwbs(this));
        }
        if (projects != null) {
            projects.forEach(i -> i.addProjectwbs(this));
        }
        this.projects = projects;
    }

    public Projectwbs projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public Projectwbs addProject(Project project) {
        this.projects.add(project);
        project.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeProject(Project project) {
        this.projects.remove(project);
        project.getProjectwbs().remove(this);
        return this;
    }

    public Set<Projectpbs> getProjectpbs() {
        return this.projectpbs;
    }

    public void setProjectpbs(Set<Projectpbs> projectpbs) {
        if (this.projectpbs != null) {
            this.projectpbs.forEach(i -> i.removeProjectwbs(this));
        }
        if (projectpbs != null) {
            projectpbs.forEach(i -> i.addProjectwbs(this));
        }
        this.projectpbs = projectpbs;
    }

    public Projectwbs projectpbs(Set<Projectpbs> projectpbs) {
        this.setProjectpbs(projectpbs);
        return this;
    }

    public Projectwbs addProjectpbs(Projectpbs projectpbs) {
        this.projectpbs.add(projectpbs);
        projectpbs.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeProjectpbs(Projectpbs projectpbs) {
        this.projectpbs.remove(projectpbs);
        projectpbs.getProjectwbs().remove(this);
        return this;
    }

    public Set<ProgressPlan> getProgressPlans() {
        return this.progressPlans;
    }

    public void setProgressPlans(Set<ProgressPlan> progressPlans) {
        if (this.progressPlans != null) {
            this.progressPlans.forEach(i -> i.removeProjectwbs(this));
        }
        if (progressPlans != null) {
            progressPlans.forEach(i -> i.addProjectwbs(this));
        }
        this.progressPlans = progressPlans;
    }

    public Projectwbs progressPlans(Set<ProgressPlan> progressPlans) {
        this.setProgressPlans(progressPlans);
        return this;
    }

    public Projectwbs addProgressPlan(ProgressPlan progressPlan) {
        this.progressPlans.add(progressPlan);
        progressPlan.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeProgressPlan(ProgressPlan progressPlan) {
        this.progressPlans.remove(progressPlan);
        progressPlan.getProjectwbs().remove(this);
        return this;
    }

    public Set<FundsEstimation> getFundsEstimations() {
        return this.fundsEstimations;
    }

    public void setFundsEstimations(Set<FundsEstimation> fundsEstimations) {
        if (this.fundsEstimations != null) {
            this.fundsEstimations.forEach(i -> i.removeProjectwbs(this));
        }
        if (fundsEstimations != null) {
            fundsEstimations.forEach(i -> i.addProjectwbs(this));
        }
        this.fundsEstimations = fundsEstimations;
    }

    public Projectwbs fundsEstimations(Set<FundsEstimation> fundsEstimations) {
        this.setFundsEstimations(fundsEstimations);
        return this;
    }

    public Projectwbs addFundsEstimation(FundsEstimation fundsEstimation) {
        this.fundsEstimations.add(fundsEstimation);
        fundsEstimation.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeFundsEstimation(FundsEstimation fundsEstimation) {
        this.fundsEstimations.remove(fundsEstimation);
        fundsEstimation.getProjectwbs().remove(this);
        return this;
    }

    public Set<ContractCostBudget> getContractCostBudgets() {
        return this.contractCostBudgets;
    }

    public void setContractCostBudgets(Set<ContractCostBudget> contractCostBudgets) {
        if (this.contractCostBudgets != null) {
            this.contractCostBudgets.forEach(i -> i.removeProjectwbs(this));
        }
        if (contractCostBudgets != null) {
            contractCostBudgets.forEach(i -> i.addProjectwbs(this));
        }
        this.contractCostBudgets = contractCostBudgets;
    }

    public Projectwbs contractCostBudgets(Set<ContractCostBudget> contractCostBudgets) {
        this.setContractCostBudgets(contractCostBudgets);
        return this;
    }

    public Projectwbs addContractCostBudget(ContractCostBudget contractCostBudget) {
        this.contractCostBudgets.add(contractCostBudget);
        contractCostBudget.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeContractCostBudget(ContractCostBudget contractCostBudget) {
        this.contractCostBudgets.remove(contractCostBudget);
        contractCostBudget.getProjectwbs().remove(this);
        return this;
    }

    public Set<CostControlSystem> getCostControlSystems() {
        return this.costControlSystems;
    }

    public void setCostControlSystems(Set<CostControlSystem> costControlSystems) {
        if (this.costControlSystems != null) {
            this.costControlSystems.forEach(i -> i.removeProjectwbs(this));
        }
        if (costControlSystems != null) {
            costControlSystems.forEach(i -> i.addProjectwbs(this));
        }
        this.costControlSystems = costControlSystems;
    }

    public Projectwbs costControlSystems(Set<CostControlSystem> costControlSystems) {
        this.setCostControlSystems(costControlSystems);
        return this;
    }

    public Projectwbs addCostControlSystem(CostControlSystem costControlSystem) {
        this.costControlSystems.add(costControlSystem);
        costControlSystem.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeCostControlSystem(CostControlSystem costControlSystem) {
        this.costControlSystems.remove(costControlSystem);
        costControlSystem.getProjectwbs().remove(this);
        return this;
    }

    public Set<QualityObjectives> getQualityObjectives() {
        return this.qualityObjectives;
    }

    public void setQualityObjectives(Set<QualityObjectives> qualityObjectives) {
        if (this.qualityObjectives != null) {
            this.qualityObjectives.forEach(i -> i.removeProjectwbs(this));
        }
        if (qualityObjectives != null) {
            qualityObjectives.forEach(i -> i.addProjectwbs(this));
        }
        this.qualityObjectives = qualityObjectives;
    }

    public Projectwbs qualityObjectives(Set<QualityObjectives> qualityObjectives) {
        this.setQualityObjectives(qualityObjectives);
        return this;
    }

    public Projectwbs addQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.add(qualityObjectives);
        qualityObjectives.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.remove(qualityObjectives);
        qualityObjectives.getProjectwbs().remove(this);
        return this;
    }

    public Set<OutsourcingContractual> getOutsourcingContractuals() {
        return this.outsourcingContractuals;
    }

    public void setOutsourcingContractuals(Set<OutsourcingContractual> outsourcingContractuals) {
        if (this.outsourcingContractuals != null) {
            this.outsourcingContractuals.forEach(i -> i.removeProjectwbs(this));
        }
        if (outsourcingContractuals != null) {
            outsourcingContractuals.forEach(i -> i.addProjectwbs(this));
        }
        this.outsourcingContractuals = outsourcingContractuals;
    }

    public Projectwbs outsourcingContractuals(Set<OutsourcingContractual> outsourcingContractuals) {
        this.setOutsourcingContractuals(outsourcingContractuals);
        return this;
    }

    public Projectwbs addOutsourcingContractual(OutsourcingContractual outsourcingContractual) {
        this.outsourcingContractuals.add(outsourcingContractual);
        outsourcingContractual.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeOutsourcingContractual(OutsourcingContractual outsourcingContractual) {
        this.outsourcingContractuals.remove(outsourcingContractual);
        outsourcingContractual.getProjectwbs().remove(this);
        return this;
    }

    public Set<OutsourcingPurchasePlan> getOutsourcingPurchasePlans() {
        return this.outsourcingPurchasePlans;
    }

    public void setOutsourcingPurchasePlans(Set<OutsourcingPurchasePlan> outsourcingPurchasePlans) {
        if (this.outsourcingPurchasePlans != null) {
            this.outsourcingPurchasePlans.forEach(i -> i.removeProjectwbs(this));
        }
        if (outsourcingPurchasePlans != null) {
            outsourcingPurchasePlans.forEach(i -> i.addProjectwbs(this));
        }
        this.outsourcingPurchasePlans = outsourcingPurchasePlans;
    }

    public Projectwbs outsourcingPurchasePlans(Set<OutsourcingPurchasePlan> outsourcingPurchasePlans) {
        this.setOutsourcingPurchasePlans(outsourcingPurchasePlans);
        return this;
    }

    public Projectwbs addOutsourcingPurchasePlan(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        this.outsourcingPurchasePlans.add(outsourcingPurchasePlan);
        outsourcingPurchasePlan.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeOutsourcingPurchasePlan(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        this.outsourcingPurchasePlans.remove(outsourcingPurchasePlan);
        outsourcingPurchasePlan.getProjectwbs().remove(this);
        return this;
    }

    public Set<Technical> getTechnicals() {
        return this.technicals;
    }

    public void setTechnicals(Set<Technical> technicals) {
        if (this.technicals != null) {
            this.technicals.forEach(i -> i.removeProjectwbs(this));
        }
        if (technicals != null) {
            technicals.forEach(i -> i.addProjectwbs(this));
        }
        this.technicals = technicals;
    }

    public Projectwbs technicals(Set<Technical> technicals) {
        this.setTechnicals(technicals);
        return this;
    }

    public Projectwbs addTechnical(Technical technical) {
        this.technicals.add(technical);
        technical.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeTechnical(Technical technical) {
        this.technicals.remove(technical);
        technical.getProjectwbs().remove(this);
        return this;
    }

    public Set<TechnicalCondition> getTechnicalConditions() {
        return this.technicalConditions;
    }

    public void setTechnicalConditions(Set<TechnicalCondition> technicalConditions) {
        if (this.technicalConditions != null) {
            this.technicalConditions.forEach(i -> i.removeProjectwbs(this));
        }
        if (technicalConditions != null) {
            technicalConditions.forEach(i -> i.addProjectwbs(this));
        }
        this.technicalConditions = technicalConditions;
    }

    public Projectwbs technicalConditions(Set<TechnicalCondition> technicalConditions) {
        this.setTechnicalConditions(technicalConditions);
        return this;
    }

    public Projectwbs addTechnicalCondition(TechnicalCondition technicalCondition) {
        this.technicalConditions.add(technicalCondition);
        technicalCondition.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeTechnicalCondition(TechnicalCondition technicalCondition) {
        this.technicalConditions.remove(technicalCondition);
        technicalCondition.getProjectwbs().remove(this);
        return this;
    }

    public Set<ProjectRisk> getProjectRisks() {
        return this.projectRisks;
    }

    public void setProjectRisks(Set<ProjectRisk> projectRisks) {
        if (this.projectRisks != null) {
            this.projectRisks.forEach(i -> i.removeProjectwbs(this));
        }
        if (projectRisks != null) {
            projectRisks.forEach(i -> i.addProjectwbs(this));
        }
        this.projectRisks = projectRisks;
    }

    public Projectwbs projectRisks(Set<ProjectRisk> projectRisks) {
        this.setProjectRisks(projectRisks);
        return this;
    }

    public Projectwbs addProjectRisk(ProjectRisk projectRisk) {
        this.projectRisks.add(projectRisk);
        projectRisk.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeProjectRisk(ProjectRisk projectRisk) {
        this.projectRisks.remove(projectRisk);
        projectRisk.getProjectwbs().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectwbs)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectwbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectwbs{" +
            "id=" + getId() +
            ", wbsname='" + getWbsname() + "'" +
            ", parentwbsid='" + getParentwbsid() + "'" +
            ", pbsid='" + getPbsid() + "'" +
            ", description='" + getDescription() + "'" +
            ", belongfront='" + getBelongfront() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", progress=" + getProgress() +
            ", type=" + getType() +
            ", priorty=" + getPriorty() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", workbag=" + getWorkbag() +
            "}";
    }
}
