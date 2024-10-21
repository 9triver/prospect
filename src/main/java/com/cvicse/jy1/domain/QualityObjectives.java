package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
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

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

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

    @Column(name = "takeaction")
    private String takeaction;

    @Column(name = "needresource")
    private String needresource;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "qualityObjectives")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "qualityObjectives", "qualityPlan" }, allowSetters = true)
    private Set<QualityReturns> qualityReturns = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "qualityObjectives", "qualityReturns", "projectwbs", "workbag" }, allowSetters = true)
    private QualityPlan qualityPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public QualityObjectives id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
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

    public String getObjectiveslevel() {
        return this.objectiveslevel;
    }

    public QualityObjectives objectiveslevel(String objectiveslevel) {
        this.setObjectiveslevel(objectiveslevel);
        return this;
    }

    public void setObjectiveslevel(String objectiveslevel) {
        this.objectiveslevel = objectiveslevel;
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

    public String getObjectivesvalue() {
        return this.objectivesvalue;
    }

    public QualityObjectives objectivesvalue(String objectivesvalue) {
        this.setObjectivesvalue(objectivesvalue);
        return this;
    }

    public void setObjectivesvalue(String objectivesvalue) {
        this.objectivesvalue = objectivesvalue;
    }

    public String getCalculationmethod() {
        return this.calculationmethod;
    }

    public QualityObjectives calculationmethod(String calculationmethod) {
        this.setCalculationmethod(calculationmethod);
        return this;
    }

    public void setCalculationmethod(String calculationmethod) {
        this.calculationmethod = calculationmethod;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public QualityObjectives frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTakeaction() {
        return this.takeaction;
    }

    public QualityObjectives takeaction(String takeaction) {
        this.setTakeaction(takeaction);
        return this;
    }

    public void setTakeaction(String takeaction) {
        this.takeaction = takeaction;
    }

    public String getNeedresource() {
        return this.needresource;
    }

    public QualityObjectives needresource(String needresource) {
        this.setNeedresource(needresource);
        return this;
    }

    public void setNeedresource(String needresource) {
        this.needresource = needresource;
    }

    public String getStatus() {
        return this.status;
    }

    public QualityObjectives status(String status) {
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

    public QualityObjectives responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
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

    public QualityPlan getQualityPlan() {
        return this.qualityPlan;
    }

    public void setQualityPlan(QualityPlan qualityPlan) {
        this.qualityPlan = qualityPlan;
    }

    public QualityObjectives qualityPlan(QualityPlan qualityPlan) {
        this.setQualityPlan(qualityPlan);
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
            ", objectiveslevel='" + getObjectiveslevel() + "'" +
            ", objectives='" + getObjectives() + "'" +
            ", objectivesvalue='" + getObjectivesvalue() + "'" +
            ", calculationmethod='" + getCalculationmethod() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", takeaction='" + getTakeaction() + "'" +
            ", needresource='" + getNeedresource() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
