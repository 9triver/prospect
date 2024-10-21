package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectRisk.
 */
@Entity
@Table(name = "project_risk")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectRisk implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "name")
    private String name;

    @Column(name = "riskcontent")
    private String riskcontent;

    @Column(name = "identificationtime")
    private LocalDate identificationtime;

    @Column(name = "riskreason")
    private String riskreason;

    @Column(name = "importantrange")
    private String importantrange;

    @Column(name = "measuresandtimelimit")
    private String measuresandtimelimit;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "closedloopindicator")
    private String closedloopindicator;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private Frontline frontlineid;

    @ManyToOne(fetch = FetchType.LAZY)
    private SystemLevel systemLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private RiskType riskType;

    @ManyToOne(fetch = FetchType.LAZY)
    private RiskLevel riskLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    private RiskPossibility riskPossibility;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "riskid")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "progressPlans", "riskid", "creatorid" }, allowSetters = true)
    private Set<RiskReturn> returns = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectRisks")
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public ProjectRisk id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getYear() {
        return this.year;
    }

    public ProjectRisk year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getName() {
        return this.name;
    }

    public ProjectRisk name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiskcontent() {
        return this.riskcontent;
    }

    public ProjectRisk riskcontent(String riskcontent) {
        this.setRiskcontent(riskcontent);
        return this;
    }

    public void setRiskcontent(String riskcontent) {
        this.riskcontent = riskcontent;
    }

    public LocalDate getIdentificationtime() {
        return this.identificationtime;
    }

    public ProjectRisk identificationtime(LocalDate identificationtime) {
        this.setIdentificationtime(identificationtime);
        return this;
    }

    public void setIdentificationtime(LocalDate identificationtime) {
        this.identificationtime = identificationtime;
    }

    public String getRiskreason() {
        return this.riskreason;
    }

    public ProjectRisk riskreason(String riskreason) {
        this.setRiskreason(riskreason);
        return this;
    }

    public void setRiskreason(String riskreason) {
        this.riskreason = riskreason;
    }

    public String getImportantrange() {
        return this.importantrange;
    }

    public ProjectRisk importantrange(String importantrange) {
        this.setImportantrange(importantrange);
        return this;
    }

    public void setImportantrange(String importantrange) {
        this.importantrange = importantrange;
    }

    public String getMeasuresandtimelimit() {
        return this.measuresandtimelimit;
    }

    public ProjectRisk measuresandtimelimit(String measuresandtimelimit) {
        this.setMeasuresandtimelimit(measuresandtimelimit);
        return this;
    }

    public void setMeasuresandtimelimit(String measuresandtimelimit) {
        this.measuresandtimelimit = measuresandtimelimit;
    }

    public String getConditions() {
        return this.conditions;
    }

    public ProjectRisk conditions(String conditions) {
        this.setConditions(conditions);
        return this;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getClosedloopindicator() {
        return this.closedloopindicator;
    }

    public ProjectRisk closedloopindicator(String closedloopindicator) {
        this.setClosedloopindicator(closedloopindicator);
        return this;
    }

    public void setClosedloopindicator(String closedloopindicator) {
        this.closedloopindicator = closedloopindicator;
    }

    public Projectwbs getWbsid() {
        return this.wbsid;
    }

    public void setWbsid(Projectwbs projectwbs) {
        this.wbsid = projectwbs;
    }

    public ProjectRisk wbsid(Projectwbs projectwbs) {
        this.setWbsid(projectwbs);
        return this;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public ProjectRisk workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public Frontline getFrontlineid() {
        return this.frontlineid;
    }

    public void setFrontlineid(Frontline frontline) {
        this.frontlineid = frontline;
    }

    public ProjectRisk frontlineid(Frontline frontline) {
        this.setFrontlineid(frontline);
        return this;
    }

    public SystemLevel getSystemLevel() {
        return this.systemLevel;
    }

    public void setSystemLevel(SystemLevel systemLevel) {
        this.systemLevel = systemLevel;
    }

    public ProjectRisk systemLevel(SystemLevel systemLevel) {
        this.setSystemLevel(systemLevel);
        return this;
    }

    public RiskType getRiskType() {
        return this.riskType;
    }

    public void setRiskType(RiskType riskType) {
        this.riskType = riskType;
    }

    public ProjectRisk riskType(RiskType riskType) {
        this.setRiskType(riskType);
        return this;
    }

    public RiskLevel getRiskLevel() {
        return this.riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public ProjectRisk riskLevel(RiskLevel riskLevel) {
        this.setRiskLevel(riskLevel);
        return this;
    }

    public RiskPossibility getRiskPossibility() {
        return this.riskPossibility;
    }

    public void setRiskPossibility(RiskPossibility riskPossibility) {
        this.riskPossibility = riskPossibility;
    }

    public ProjectRisk riskPossibility(RiskPossibility riskPossibility) {
        this.setRiskPossibility(riskPossibility);
        return this;
    }

    public Set<RiskReturn> getReturns() {
        return this.returns;
    }

    public void setReturns(Set<RiskReturn> riskReturns) {
        if (this.returns != null) {
            this.returns.forEach(i -> i.setRiskid(null));
        }
        if (riskReturns != null) {
            riskReturns.forEach(i -> i.setRiskid(this));
        }
        this.returns = riskReturns;
    }

    public ProjectRisk returns(Set<RiskReturn> riskReturns) {
        this.setReturns(riskReturns);
        return this;
    }

    public ProjectRisk addReturns(RiskReturn riskReturn) {
        this.returns.add(riskReturn);
        riskReturn.setRiskid(this);
        return this;
    }

    public ProjectRisk removeReturns(RiskReturn riskReturn) {
        this.returns.remove(riskReturn);
        riskReturn.setRiskid(null);
        return this;
    }

    public Set<ProgressPlan> getProgressPlans() {
        return this.progressPlans;
    }

    public void setProgressPlans(Set<ProgressPlan> progressPlans) {
        if (this.progressPlans != null) {
            this.progressPlans.forEach(i -> i.removeProjectRisk(this));
        }
        if (progressPlans != null) {
            progressPlans.forEach(i -> i.addProjectRisk(this));
        }
        this.progressPlans = progressPlans;
    }

    public ProjectRisk progressPlans(Set<ProgressPlan> progressPlans) {
        this.setProgressPlans(progressPlans);
        return this;
    }

    public ProjectRisk addProgressPlan(ProgressPlan progressPlan) {
        this.progressPlans.add(progressPlan);
        progressPlan.getProjectRisks().add(this);
        return this;
    }

    public ProjectRisk removeProgressPlan(ProgressPlan progressPlan) {
        this.progressPlans.remove(progressPlan);
        progressPlan.getProjectRisks().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectRisk)) {
            return false;
        }
        return getId() != null && getId().equals(((ProjectRisk) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectRisk{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", name='" + getName() + "'" +
            ", riskcontent='" + getRiskcontent() + "'" +
            ", identificationtime='" + getIdentificationtime() + "'" +
            ", riskreason='" + getRiskreason() + "'" +
            ", importantrange='" + getImportantrange() + "'" +
            ", measuresandtimelimit='" + getMeasuresandtimelimit() + "'" +
            ", conditions='" + getConditions() + "'" +
            ", closedloopindicator='" + getClosedloopindicator() + "'" +
            "}";
    }
}
