package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.QualityType;
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
 * A QualityObjectives.
 */
@Entity
@Table(name = "quality_objectives")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityObjectives implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "objectives")
    private String objectives;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualitytype")
    private QualityType qualitytype;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "target")
    private Integer target;

    @Column(name = "statisticalmethod")
    private String statisticalmethod;

    @Column(name = "statisticalfrequency")
    private String statisticalfrequency;

    @Column(name = "istarget")
    private Integer istarget;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "description")
    private String description;

    @Column(name = "problems")
    private String problems;

    @Column(name = "improvementmeasures")
    private String improvementmeasures;

    @Column(name = "returntime")
    private LocalDate returntime;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_quality_objectives__projectwbs",
        joinColumns = @JoinColumn(name = "quality_objectives_id"),
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "qualityObjectives")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "creatorid", "qualityObjectives" }, allowSetters = true)
    private Set<QualityReturns> qualityReturns = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public QualityObjectives id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public QualityObjectives name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectives() {
        return this.objectives;
    }

    public QualityObjectives objectives(String objectives) {
        this.setObjectives(objectives);
        return this;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public QualityType getQualitytype() {
        return this.qualitytype;
    }

    public QualityObjectives qualitytype(QualityType qualitytype) {
        this.setQualitytype(qualitytype);
        return this;
    }

    public void setQualitytype(QualityType qualitytype) {
        this.qualitytype = qualitytype;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public QualityObjectives secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Integer getTarget() {
        return this.target;
    }

    public QualityObjectives target(Integer target) {
        this.setTarget(target);
        return this;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getStatisticalmethod() {
        return this.statisticalmethod;
    }

    public QualityObjectives statisticalmethod(String statisticalmethod) {
        this.setStatisticalmethod(statisticalmethod);
        return this;
    }

    public void setStatisticalmethod(String statisticalmethod) {
        this.statisticalmethod = statisticalmethod;
    }

    public String getStatisticalfrequency() {
        return this.statisticalfrequency;
    }

    public QualityObjectives statisticalfrequency(String statisticalfrequency) {
        this.setStatisticalfrequency(statisticalfrequency);
        return this;
    }

    public void setStatisticalfrequency(String statisticalfrequency) {
        this.statisticalfrequency = statisticalfrequency;
    }

    public Integer getIstarget() {
        return this.istarget;
    }

    public QualityObjectives istarget(Integer istarget) {
        this.setIstarget(istarget);
        return this;
    }

    public void setIstarget(Integer istarget) {
        this.istarget = istarget;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public QualityObjectives progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return this.description;
    }

    public QualityObjectives description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProblems() {
        return this.problems;
    }

    public QualityObjectives problems(String problems) {
        this.setProblems(problems);
        return this;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getImprovementmeasures() {
        return this.improvementmeasures;
    }

    public QualityObjectives improvementmeasures(String improvementmeasures) {
        this.setImprovementmeasures(improvementmeasures);
        return this;
    }

    public void setImprovementmeasures(String improvementmeasures) {
        this.improvementmeasures = improvementmeasures;
    }

    public LocalDate getReturntime() {
        return this.returntime;
    }

    public QualityObjectives returntime(LocalDate returntime) {
        this.setReturntime(returntime);
        return this;
    }

    public void setReturntime(LocalDate returntime) {
        this.returntime = returntime;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public QualityObjectives createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public QualityObjectives auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public QualityObjectives responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public QualityObjectives auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public QualityObjectives projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public QualityObjectives addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public QualityObjectives removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    public Set<QualityReturns> getQualityReturns() {
        return this.qualityReturns;
    }

    public void setQualityReturns(Set<QualityReturns> qualityReturns) {
        if (this.qualityReturns != null) {
            this.qualityReturns.forEach(i -> i.removeQualityObjectives(this));
        }
        if (qualityReturns != null) {
            qualityReturns.forEach(i -> i.addQualityObjectives(this));
        }
        this.qualityReturns = qualityReturns;
    }

    public QualityObjectives qualityReturns(Set<QualityReturns> qualityReturns) {
        this.setQualityReturns(qualityReturns);
        return this;
    }

    public QualityObjectives addQualityReturns(QualityReturns qualityReturns) {
        this.qualityReturns.add(qualityReturns);
        qualityReturns.getQualityObjectives().add(this);
        return this;
    }

    public QualityObjectives removeQualityReturns(QualityReturns qualityReturns) {
        this.qualityReturns.remove(qualityReturns);
        qualityReturns.getQualityObjectives().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityObjectives)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityObjectives) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityObjectives{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", objectives='" + getObjectives() + "'" +
            ", qualitytype='" + getQualitytype() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", target=" + getTarget() +
            ", statisticalmethod='" + getStatisticalmethod() + "'" +
            ", statisticalfrequency='" + getStatisticalfrequency() + "'" +
            ", istarget=" + getIstarget() +
            ", progress=" + getProgress() +
            ", description='" + getDescription() + "'" +
            ", problems='" + getProblems() + "'" +
            ", improvementmeasures='" + getImprovementmeasures() + "'" +
            ", returntime='" + getReturntime() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
