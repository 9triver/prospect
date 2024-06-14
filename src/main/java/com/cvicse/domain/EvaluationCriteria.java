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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "standardtype")
    private Integer standardtype;

    @Column(name = "standardname")
    private String standardname;

    @Column(name = "mark", precision = 21, scale = 2)
    private BigDecimal mark;

    @JsonIgnoreProperties(
        value = { "officers", "project", "planstrategy", "progressmanagement", "evaluationCriteria" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(
        value = {
            "cycleplan",
            "progressmanagement",
            "qualitymanagement",
            "fundsmanagement",
            "technicalCondition",
            "contractualfunds",
            "outsourcingmPurchaseExecute",
            "resourcemanagement",
            "riskmanagement",
            "document",
            "safetycheck",
            "department",
            "evaluationCriteria",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingmPurchasePlan",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "evaluationCriteria")
    private Project project;

    @JsonIgnoreProperties(value = { "evaluationCriteria", "project", "creatorid", "responsibleid", "ratingperson" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "evaluationCriteria")
    private ManagementCapacityEvaluation managementCapacityEvaluation;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EvaluationCriteria id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
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

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setEvaluationCriteria(null);
        }
        if (project != null) {
            project.setEvaluationCriteria(this);
        }
        this.project = project;
    }

    public EvaluationCriteria project(Project project) {
        this.setProject(project);
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
