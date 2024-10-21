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

    @Column(name = "description")
    private String description;

    @Column(name = "belongfrontline")
    private String belongfrontline;

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

    @Column(name = "workbagid")
    private String workbagid;

    @JsonIgnoreProperties(
        value = {
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartments",
            "projectwbs",
            "projects",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectpbs projectpbs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement technicaldirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_projectwbs__projectdeliverables",
        joinColumns = @JoinColumn(name = "projectwbs_id"),
        inverseJoinColumns = @JoinColumn(name = "projectdeliverables_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "code", "belongwbsids", "belongworkbagids" }, allowSetters = true)
    private Set<Projectdeliverables> projectdeliverables = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_projectwbs__relevantdepartment",
        joinColumns = @JoinColumn(name = "projectwbs_id"),
        inverseJoinColumns = @JoinColumn(name = "relevantdepartment_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Set<Department> relevantdepartments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "wbsids")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<Workbag> workbags = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "planReturns",
            "responsibleperson",
            "cooperatingperson",
            "auditorid",
            "responsibledepartment",
            "cooperatingdepartment",
            "projectwbs",
            "projectRisks",
            "riskReturn",
        },
        allowSetters = true
    )
    private Set<ProgressPlan> progressPlans = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs" }, allowSetters = true)
    private Set<ProjectBudget> projectBudgets = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectpbs", "projectwbs" }, allowSetters = true)
    private Set<Project> projects = new HashSet<>();

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

    @JsonIgnoreProperties(
        value = {
            "projectwbs",
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    private ProjectTotalwbs projectTotalwbs;

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

    public String getBelongfrontline() {
        return this.belongfrontline;
    }

    public Projectwbs belongfrontline(String belongfrontline) {
        this.setBelongfrontline(belongfrontline);
        return this;
    }

    public void setBelongfrontline(String belongfrontline) {
        this.belongfrontline = belongfrontline;
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

    public String getWorkbagid() {
        return this.workbagid;
    }

    public Projectwbs workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public Projectpbs getProjectpbs() {
        return this.projectpbs;
    }

    public void setProjectpbs(Projectpbs projectpbs) {
        this.projectpbs = projectpbs;
    }

    public Projectwbs projectpbs(Projectpbs projectpbs) {
        this.setProjectpbs(projectpbs);
        return this;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public Projectwbs responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getTechnicaldirector() {
        return this.technicaldirector;
    }

    public void setTechnicaldirector(HrManagement hrManagement) {
        this.technicaldirector = hrManagement;
    }

    public Projectwbs technicaldirector(HrManagement hrManagement) {
        this.setTechnicaldirector(hrManagement);
        return this;
    }

    public HrManagement getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(HrManagement hrManagement) {
        this.knowingpeople = hrManagement;
    }

    public Projectwbs knowingpeople(HrManagement hrManagement) {
        this.setKnowingpeople(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public Projectwbs auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
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

    public Set<Projectdeliverables> getProjectdeliverables() {
        return this.projectdeliverables;
    }

    public void setProjectdeliverables(Set<Projectdeliverables> projectdeliverables) {
        this.projectdeliverables = projectdeliverables;
    }

    public Projectwbs projectdeliverables(Set<Projectdeliverables> projectdeliverables) {
        this.setProjectdeliverables(projectdeliverables);
        return this;
    }

    public Projectwbs addProjectdeliverables(Projectdeliverables projectdeliverables) {
        this.projectdeliverables.add(projectdeliverables);
        return this;
    }

    public Projectwbs removeProjectdeliverables(Projectdeliverables projectdeliverables) {
        this.projectdeliverables.remove(projectdeliverables);
        return this;
    }

    public Set<Department> getRelevantdepartments() {
        return this.relevantdepartments;
    }

    public void setRelevantdepartments(Set<Department> departments) {
        this.relevantdepartments = departments;
    }

    public Projectwbs relevantdepartments(Set<Department> departments) {
        this.setRelevantdepartments(departments);
        return this;
    }

    public Projectwbs addRelevantdepartment(Department department) {
        this.relevantdepartments.add(department);
        return this;
    }

    public Projectwbs removeRelevantdepartment(Department department) {
        this.relevantdepartments.remove(department);
        return this;
    }

    public Set<Workbag> getWorkbags() {
        return this.workbags;
    }

    public void setWorkbags(Set<Workbag> workbags) {
        if (this.workbags != null) {
            this.workbags.forEach(i -> i.removeWbsid(this));
        }
        if (workbags != null) {
            workbags.forEach(i -> i.addWbsid(this));
        }
        this.workbags = workbags;
    }

    public Projectwbs workbags(Set<Workbag> workbags) {
        this.setWorkbags(workbags);
        return this;
    }

    public Projectwbs addWorkbag(Workbag workbag) {
        this.workbags.add(workbag);
        workbag.getWbsids().add(this);
        return this;
    }

    public Projectwbs removeWorkbag(Workbag workbag) {
        this.workbags.remove(workbag);
        workbag.getWbsids().remove(this);
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

    public Set<ProjectBudget> getProjectBudgets() {
        return this.projectBudgets;
    }

    public void setProjectBudgets(Set<ProjectBudget> projectBudgets) {
        if (this.projectBudgets != null) {
            this.projectBudgets.forEach(i -> i.removeProjectwbs(this));
        }
        if (projectBudgets != null) {
            projectBudgets.forEach(i -> i.addProjectwbs(this));
        }
        this.projectBudgets = projectBudgets;
    }

    public Projectwbs projectBudgets(Set<ProjectBudget> projectBudgets) {
        this.setProjectBudgets(projectBudgets);
        return this;
    }

    public Projectwbs addProjectBudget(ProjectBudget projectBudget) {
        this.projectBudgets.add(projectBudget);
        projectBudget.getProjectwbs().add(this);
        return this;
    }

    public Projectwbs removeProjectBudget(ProjectBudget projectBudget) {
        this.projectBudgets.remove(projectBudget);
        projectBudget.getProjectwbs().remove(this);
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

    public ProjectTotalwbs getProjectTotalwbs() {
        return this.projectTotalwbs;
    }

    public void setProjectTotalwbs(ProjectTotalwbs projectTotalwbs) {
        if (this.projectTotalwbs != null) {
            this.projectTotalwbs.setProjectwbs(null);
        }
        if (projectTotalwbs != null) {
            projectTotalwbs.setProjectwbs(this);
        }
        this.projectTotalwbs = projectTotalwbs;
    }

    public Projectwbs projectTotalwbs(ProjectTotalwbs projectTotalwbs) {
        this.setProjectTotalwbs(projectTotalwbs);
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
            ", description='" + getDescription() + "'" +
            ", belongfrontline='" + getBelongfrontline() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", progress=" + getProgress() +
            ", type=" + getType() +
            ", priorty=" + getPriorty() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            "}";
    }
}
