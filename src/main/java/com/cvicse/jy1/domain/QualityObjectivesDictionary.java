package com.cvicse.jy1.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QualityObjectivesDictionary.
 */
@Entity
@Table(name = "quality_objectives_dictionary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityObjectivesDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "objectiveslevel")
    private String objectiveslevel;

    @Column(name = "objectivestype")
    private String objectivestype;

    @Column(name = "objectivesname")
    private String objectivesname;

    @Column(name = "objectivescontent")
    private String objectivescontent;

    @Column(name = "calculationmethod")
    private String calculationmethod;

    @Column(name = "frequency")
    private String frequency;

    @Column(name = "evaluationcriteria")
    private String evaluationcriteria;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public QualityObjectivesDictionary id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectiveslevel() {
        return this.objectiveslevel;
    }

    public QualityObjectivesDictionary objectiveslevel(String objectiveslevel) {
        this.setObjectiveslevel(objectiveslevel);
        return this;
    }

    public void setObjectiveslevel(String objectiveslevel) {
        this.objectiveslevel = objectiveslevel;
    }

    public String getObjectivestype() {
        return this.objectivestype;
    }

    public QualityObjectivesDictionary objectivestype(String objectivestype) {
        this.setObjectivestype(objectivestype);
        return this;
    }

    public void setObjectivestype(String objectivestype) {
        this.objectivestype = objectivestype;
    }

    public String getObjectivesname() {
        return this.objectivesname;
    }

    public QualityObjectivesDictionary objectivesname(String objectivesname) {
        this.setObjectivesname(objectivesname);
        return this;
    }

    public void setObjectivesname(String objectivesname) {
        this.objectivesname = objectivesname;
    }

    public String getObjectivescontent() {
        return this.objectivescontent;
    }

    public QualityObjectivesDictionary objectivescontent(String objectivescontent) {
        this.setObjectivescontent(objectivescontent);
        return this;
    }

    public void setObjectivescontent(String objectivescontent) {
        this.objectivescontent = objectivescontent;
    }

    public String getCalculationmethod() {
        return this.calculationmethod;
    }

    public QualityObjectivesDictionary calculationmethod(String calculationmethod) {
        this.setCalculationmethod(calculationmethod);
        return this;
    }

    public void setCalculationmethod(String calculationmethod) {
        this.calculationmethod = calculationmethod;
    }

    public String getFrequency() {
        return this.frequency;
    }

    public QualityObjectivesDictionary frequency(String frequency) {
        this.setFrequency(frequency);
        return this;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getEvaluationcriteria() {
        return this.evaluationcriteria;
    }

    public QualityObjectivesDictionary evaluationcriteria(String evaluationcriteria) {
        this.setEvaluationcriteria(evaluationcriteria);
        return this;
    }

    public void setEvaluationcriteria(String evaluationcriteria) {
        this.evaluationcriteria = evaluationcriteria;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityObjectivesDictionary)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityObjectivesDictionary) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityObjectivesDictionary{" +
            "id=" + getId() +
            ", objectiveslevel='" + getObjectiveslevel() + "'" +
            ", objectivestype='" + getObjectivestype() + "'" +
            ", objectivesname='" + getObjectivesname() + "'" +
            ", objectivescontent='" + getObjectivescontent() + "'" +
            ", calculationmethod='" + getCalculationmethod() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", evaluationcriteria='" + getEvaluationcriteria() + "'" +
            "}";
    }
}
