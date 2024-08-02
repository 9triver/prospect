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
 * A QualityReturns.
 */
@Entity
@Table(name = "quality_returns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityReturns implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers creatorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_quality_returns__quality_objectives",
        joinColumns = @JoinColumn(name = "quality_returns_id"),
        inverseJoinColumns = @JoinColumn(name = "quality_objectives_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs", "qualityReturns" }, allowSetters = true)
    private Set<QualityObjectives> qualityObjectives = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public QualityReturns id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public QualityReturns name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectives() {
        return this.objectives;
    }

    public QualityReturns objectives(String objectives) {
        this.setObjectives(objectives);
        return this;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public QualityType getQualitytype() {
        return this.qualitytype;
    }

    public QualityReturns qualitytype(QualityType qualitytype) {
        this.setQualitytype(qualitytype);
        return this;
    }

    public void setQualitytype(QualityType qualitytype) {
        this.qualitytype = qualitytype;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public QualityReturns secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Integer getTarget() {
        return this.target;
    }

    public QualityReturns target(Integer target) {
        this.setTarget(target);
        return this;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getStatisticalmethod() {
        return this.statisticalmethod;
    }

    public QualityReturns statisticalmethod(String statisticalmethod) {
        this.setStatisticalmethod(statisticalmethod);
        return this;
    }

    public void setStatisticalmethod(String statisticalmethod) {
        this.statisticalmethod = statisticalmethod;
    }

    public String getStatisticalfrequency() {
        return this.statisticalfrequency;
    }

    public QualityReturns statisticalfrequency(String statisticalfrequency) {
        this.setStatisticalfrequency(statisticalfrequency);
        return this;
    }

    public void setStatisticalfrequency(String statisticalfrequency) {
        this.statisticalfrequency = statisticalfrequency;
    }

    public Integer getIstarget() {
        return this.istarget;
    }

    public QualityReturns istarget(Integer istarget) {
        this.setIstarget(istarget);
        return this;
    }

    public void setIstarget(Integer istarget) {
        this.istarget = istarget;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public QualityReturns progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return this.description;
    }

    public QualityReturns description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProblems() {
        return this.problems;
    }

    public QualityReturns problems(String problems) {
        this.setProblems(problems);
        return this;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getImprovementmeasures() {
        return this.improvementmeasures;
    }

    public QualityReturns improvementmeasures(String improvementmeasures) {
        this.setImprovementmeasures(improvementmeasures);
        return this;
    }

    public void setImprovementmeasures(String improvementmeasures) {
        this.improvementmeasures = improvementmeasures;
    }

    public LocalDate getReturntime() {
        return this.returntime;
    }

    public QualityReturns returntime(LocalDate returntime) {
        this.setReturntime(returntime);
        return this;
    }

    public void setReturntime(LocalDate returntime) {
        this.returntime = returntime;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public QualityReturns createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public QualityReturns auditStatus(AuditStatus auditStatus) {
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

    public QualityReturns responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public QualityReturns auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public QualityReturns creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Set<QualityObjectives> getQualityObjectives() {
        return this.qualityObjectives;
    }

    public void setQualityObjectives(Set<QualityObjectives> qualityObjectives) {
        this.qualityObjectives = qualityObjectives;
    }

    public QualityReturns qualityObjectives(Set<QualityObjectives> qualityObjectives) {
        this.setQualityObjectives(qualityObjectives);
        return this;
    }

    public QualityReturns addQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.add(qualityObjectives);
        return this;
    }

    public QualityReturns removeQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.remove(qualityObjectives);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityReturns)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityReturns) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityReturns{" +
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
