package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EvaluationCriteria.
 */
@Entity
@Table(name = "evaluation_criteria")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EvaluationCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "standardtype")
    private Integer standardtype;

    @Column(name = "standardname")
    private String standardname;

    @Column(name = "mark", precision = 21, scale = 2)
    private BigDecimal mark;

    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(value = { "evaluationCriteria", "project", "creatorid", "responsibleid", "ratingperson" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "evaluationCriteria")
    private ManagementCapacityEvaluation managementCapacityEvaluation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public EvaluationCriteria id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStandardtype() {
        return this.standardtype;
    }

    public EvaluationCriteria standardtype(Integer standardtype) {
        this.setStandardtype(standardtype);
        return this;
    }

    public void setStandardtype(Integer standardtype) {
        this.standardtype = standardtype;
    }

    public String getStandardname() {
        return this.standardname;
    }

    public EvaluationCriteria standardname(String standardname) {
        this.setStandardname(standardname);
        return this;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname;
    }

    public BigDecimal getMark() {
        return this.mark;
    }

    public EvaluationCriteria mark(BigDecimal mark) {
        this.setMark(mark);
        return this;
    }

    public void setMark(BigDecimal mark) {
        this.mark = mark;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EvaluationCriteria department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public ManagementCapacityEvaluation getManagementCapacityEvaluation() {
        return this.managementCapacityEvaluation;
    }

    public void setManagementCapacityEvaluation(ManagementCapacityEvaluation managementCapacityEvaluation) {
        if (this.managementCapacityEvaluation != null) {
            this.managementCapacityEvaluation.setEvaluationCriteria(null);
        }
        if (managementCapacityEvaluation != null) {
            managementCapacityEvaluation.setEvaluationCriteria(this);
        }
        this.managementCapacityEvaluation = managementCapacityEvaluation;
    }

    public EvaluationCriteria managementCapacityEvaluation(ManagementCapacityEvaluation managementCapacityEvaluation) {
        this.setManagementCapacityEvaluation(managementCapacityEvaluation);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EvaluationCriteria)) {
            return false;
        }
        return getId() != null && getId().equals(((EvaluationCriteria) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EvaluationCriteria{" +
            "id=" + getId() +
            ", standardtype=" + getStandardtype() +
            ", standardname='" + getStandardname() + "'" +
            ", mark=" + getMark() +
            "}";
    }
}
