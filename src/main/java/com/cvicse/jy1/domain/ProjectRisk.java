package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Risklevel;
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
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "nodename")
    private String nodename;

    @Column(name = "risktype")
    private Integer risktype;

    @Column(name = "decumentid")
    private Long decumentid;

    @Column(name = "version")
    private Integer version;

    @Column(name = "usetime")
    private LocalDate usetime;

    @Column(name = "systemlevel")
    private Integer systemlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "risklevel")
    private Risklevel risklevel;

    @Column(name = "limitationtime")
    private String limitationtime;

    @Column(name = "closetype")
    private Integer closetype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid" }, allowSetters = true)
    private RiskReport riskReport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_project_risk__projectwbs",
        joinColumns = @JoinColumn(name = "project_risk_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectRisks")
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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ProjectRisk id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
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

    public String getNodename() {
        return this.nodename;
    }

    public ProjectRisk nodename(String nodename) {
        this.setNodename(nodename);
        return this;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getRisktype() {
        return this.risktype;
    }

    public ProjectRisk risktype(Integer risktype) {
        this.setRisktype(risktype);
        return this;
    }

    public void setRisktype(Integer risktype) {
        this.risktype = risktype;
    }

    public Long getDecumentid() {
        return this.decumentid;
    }

    public ProjectRisk decumentid(Long decumentid) {
        this.setDecumentid(decumentid);
        return this;
    }

    public void setDecumentid(Long decumentid) {
        this.decumentid = decumentid;
    }

    public Integer getVersion() {
        return this.version;
    }

    public ProjectRisk version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getUsetime() {
        return this.usetime;
    }

    public ProjectRisk usetime(LocalDate usetime) {
        this.setUsetime(usetime);
        return this;
    }

    public void setUsetime(LocalDate usetime) {
        this.usetime = usetime;
    }

    public Integer getSystemlevel() {
        return this.systemlevel;
    }

    public ProjectRisk systemlevel(Integer systemlevel) {
        this.setSystemlevel(systemlevel);
        return this;
    }

    public void setSystemlevel(Integer systemlevel) {
        this.systemlevel = systemlevel;
    }

    public Risklevel getRisklevel() {
        return this.risklevel;
    }

    public ProjectRisk risklevel(Risklevel risklevel) {
        this.setRisklevel(risklevel);
        return this;
    }

    public void setRisklevel(Risklevel risklevel) {
        this.risklevel = risklevel;
    }

    public String getLimitationtime() {
        return this.limitationtime;
    }

    public ProjectRisk limitationtime(String limitationtime) {
        this.setLimitationtime(limitationtime);
        return this;
    }

    public void setLimitationtime(String limitationtime) {
        this.limitationtime = limitationtime;
    }

    public Integer getClosetype() {
        return this.closetype;
    }

    public ProjectRisk closetype(Integer closetype) {
        this.setClosetype(closetype);
        return this;
    }

    public void setClosetype(Integer closetype) {
        this.closetype = closetype;
    }

    public RiskReport getRiskReport() {
        return this.riskReport;
    }

    public void setRiskReport(RiskReport riskReport) {
        this.riskReport = riskReport;
    }

    public ProjectRisk riskReport(RiskReport riskReport) {
        this.setRiskReport(riskReport);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public ProjectRisk creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public ProjectRisk responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public ProjectRisk auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public ProjectRisk projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public ProjectRisk addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public ProjectRisk removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
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
            ", nodename='" + getNodename() + "'" +
            ", risktype=" + getRisktype() +
            ", decumentid=" + getDecumentid() +
            ", version=" + getVersion() +
            ", usetime='" + getUsetime() + "'" +
            ", systemlevel=" + getSystemlevel() +
            ", risklevel='" + getRisklevel() + "'" +
            ", limitationtime='" + getLimitationtime() + "'" +
            ", closetype=" + getClosetype() +
            "}";
    }
}
