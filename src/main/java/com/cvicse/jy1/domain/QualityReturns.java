package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
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

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "qualityplanid")
    private String qualityplanid;

    @Column(name = "qualityobjectivesid")
    private String qualityobjectivesid;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "responsibleid")
    private String responsibleid;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "objectiveslevel")
    private String objectiveslevel;

    @Column(name = "objectives")
    private String objectives;

    @Column(name = "objectivesvalue")
    private String objectivesvalue;

    @Column(name = "calculationmethod")
    private String calculationmethod;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "isobjectivesvalue")
    private Boolean isobjectivesvalue;

    @Column(name = "percentage", precision = 21, scale = 2)
    private BigDecimal percentage;

    @Column(name = "objectivescompletion")
    private String objectivescompletion;

    @Column(name = "problem")
    private String problem;

    @Column(name = "takeaction")
    private String takeaction;

    @Column(name = "continuousimprovement")
    private String continuousimprovement;

    @Column(name = "workevidence")
    private String workevidence;

    @Column(name = "returntime")
    private LocalDate returntime;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_quality_returns__quality_objectives",
        joinColumns = @JoinColumn(name = "quality_returns_id"),
        inverseJoinColumns = @JoinColumn(name = "quality_objectives_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "qualityReturns", "qualityPlan" }, allowSetters = true)
    private Set<QualityObjectives> qualityObjectives = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "qualityObjectives", "qualityReturns", "projectwbs", "workbag" }, allowSetters = true)
    private QualityPlan qualityPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public QualityReturns id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQualityplanid() {
        return this.qualityplanid;
    }

    public QualityReturns qualityplanid(String qualityplanid) {
        this.setQualityplanid(qualityplanid);
        return this;
    }

    public void setQualityplanid(String qualityplanid) {
        this.qualityplanid = qualityplanid;
    }

    public String getQualityobjectivesid() {
        return this.qualityobjectivesid;
    }

    public QualityReturns qualityobjectivesid(String qualityobjectivesid) {
        this.setQualityobjectivesid(qualityobjectivesid);
        return this;
    }

    public void setQualityobjectivesid(String qualityobjectivesid) {
        this.qualityobjectivesid = qualityobjectivesid;
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

    public String getDepartment() {
        return this.department;
    }

    public QualityReturns department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getResponsibleid() {
        return this.responsibleid;
    }

    public QualityReturns responsibleid(String responsibleid) {
        this.setResponsibleid(responsibleid);
        return this;
    }

    public void setResponsibleid(String responsibleid) {
        this.responsibleid = responsibleid;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public QualityReturns wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public QualityReturns workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getObjectiveslevel() {
        return this.objectiveslevel;
    }

    public QualityReturns objectiveslevel(String objectiveslevel) {
        this.setObjectiveslevel(objectiveslevel);
        return this;
    }

    public void setObjectiveslevel(String objectiveslevel) {
        this.objectiveslevel = objectiveslevel;
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

    public String getObjectivesvalue() {
        return this.objectivesvalue;
    }

    public QualityReturns objectivesvalue(String objectivesvalue) {
        this.setObjectivesvalue(objectivesvalue);
        return this;
    }

    public void setObjectivesvalue(String objectivesvalue) {
        this.objectivesvalue = objectivesvalue;
    }

    public String getCalculationmethod() {
        return this.calculationmethod;
    }

    public QualityReturns calculationmethod(String calculationmethod) {
        this.setCalculationmethod(calculationmethod);
        return this;
    }

    public void setCalculationmethod(String calculationmethod) {
        this.calculationmethod = calculationmethod;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public QualityReturns frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Boolean getIsobjectivesvalue() {
        return this.isobjectivesvalue;
    }

    public QualityReturns isobjectivesvalue(Boolean isobjectivesvalue) {
        this.setIsobjectivesvalue(isobjectivesvalue);
        return this;
    }

    public void setIsobjectivesvalue(Boolean isobjectivesvalue) {
        this.isobjectivesvalue = isobjectivesvalue;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public QualityReturns percentage(BigDecimal percentage) {
        this.setPercentage(percentage);
        return this;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getObjectivescompletion() {
        return this.objectivescompletion;
    }

    public QualityReturns objectivescompletion(String objectivescompletion) {
        this.setObjectivescompletion(objectivescompletion);
        return this;
    }

    public void setObjectivescompletion(String objectivescompletion) {
        this.objectivescompletion = objectivescompletion;
    }

    public String getProblem() {
        return this.problem;
    }

    public QualityReturns problem(String problem) {
        this.setProblem(problem);
        return this;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getTakeaction() {
        return this.takeaction;
    }

    public QualityReturns takeaction(String takeaction) {
        this.setTakeaction(takeaction);
        return this;
    }

    public void setTakeaction(String takeaction) {
        this.takeaction = takeaction;
    }

    public String getContinuousimprovement() {
        return this.continuousimprovement;
    }

    public QualityReturns continuousimprovement(String continuousimprovement) {
        this.setContinuousimprovement(continuousimprovement);
        return this;
    }

    public void setContinuousimprovement(String continuousimprovement) {
        this.continuousimprovement = continuousimprovement;
    }

    public String getWorkevidence() {
        return this.workevidence;
    }

    public QualityReturns workevidence(String workevidence) {
        this.setWorkevidence(workevidence);
        return this;
    }

    public void setWorkevidence(String workevidence) {
        this.workevidence = workevidence;
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

    public String getStatus() {
        return this.status;
    }

    public QualityReturns status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public QualityReturns responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public QualityReturns auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
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

    public QualityPlan getQualityPlan() {
        return this.qualityPlan;
    }

    public void setQualityPlan(QualityPlan qualityPlan) {
        this.qualityPlan = qualityPlan;
    }

    public QualityReturns qualityPlan(QualityPlan qualityPlan) {
        this.setQualityPlan(qualityPlan);
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
            ", qualityplanid='" + getQualityplanid() + "'" +
            ", qualityobjectivesid='" + getQualityobjectivesid() + "'" +
            ", name='" + getName() + "'" +
            ", department='" + getDepartment() + "'" +
            ", responsibleid='" + getResponsibleid() + "'" +
            ", wbsid='" + getWbsid() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", objectiveslevel='" + getObjectiveslevel() + "'" +
            ", objectives='" + getObjectives() + "'" +
            ", objectivesvalue='" + getObjectivesvalue() + "'" +
            ", calculationmethod='" + getCalculationmethod() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", isobjectivesvalue='" + getIsobjectivesvalue() + "'" +
            ", percentage=" + getPercentage() +
            ", objectivescompletion='" + getObjectivescompletion() + "'" +
            ", problem='" + getProblem() + "'" +
            ", takeaction='" + getTakeaction() + "'" +
            ", continuousimprovement='" + getContinuousimprovement() + "'" +
            ", workevidence='" + getWorkevidence() + "'" +
            ", returntime='" + getReturntime() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
