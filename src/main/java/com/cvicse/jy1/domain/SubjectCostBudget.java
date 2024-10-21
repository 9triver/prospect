package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SubjectCostBudget.
 */
@Entity
@Table(name = "subject_cost_budget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SubjectCostBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contractid", nullable = false)
    private String contractid;

    @NotNull
    @Column(name = "subjectid", nullable = false)
    private Integer subjectid;

    @Column(name = "subjectname")
    private String subjectname;

    @Column(name = "budgetamount", precision = 21, scale = 2)
    private BigDecimal budgetamount;

    @Column(name = "estimatedamount", precision = 21, scale = 2)
    private BigDecimal estimatedamount;

    @Column(name = "implementedamount", precision = 21, scale = 2)
    private BigDecimal implementedamount;

    @Column(name = "difference", precision = 21, scale = 2)
    private BigDecimal difference;

    @Column(name = "percentage", precision = 21, scale = 2)
    private BigDecimal percentage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs" }, allowSetters = true)
    private ProjectBudget projectBudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public SubjectCostBudget id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractid() {
        return this.contractid;
    }

    public SubjectCostBudget contractid(String contractid) {
        this.setContractid(contractid);
        return this;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public Integer getSubjectid() {
        return this.subjectid;
    }

    public SubjectCostBudget subjectid(Integer subjectid) {
        this.setSubjectid(subjectid);
        return this;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return this.subjectname;
    }

    public SubjectCostBudget subjectname(String subjectname) {
        this.setSubjectname(subjectname);
        return this;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public BigDecimal getBudgetamount() {
        return this.budgetamount;
    }

    public SubjectCostBudget budgetamount(BigDecimal budgetamount) {
        this.setBudgetamount(budgetamount);
        return this;
    }

    public void setBudgetamount(BigDecimal budgetamount) {
        this.budgetamount = budgetamount;
    }

    public BigDecimal getEstimatedamount() {
        return this.estimatedamount;
    }

    public SubjectCostBudget estimatedamount(BigDecimal estimatedamount) {
        this.setEstimatedamount(estimatedamount);
        return this;
    }

    public void setEstimatedamount(BigDecimal estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public BigDecimal getImplementedamount() {
        return this.implementedamount;
    }

    public SubjectCostBudget implementedamount(BigDecimal implementedamount) {
        this.setImplementedamount(implementedamount);
        return this;
    }

    public void setImplementedamount(BigDecimal implementedamount) {
        this.implementedamount = implementedamount;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public SubjectCostBudget difference(BigDecimal difference) {
        this.setDifference(difference);
        return this;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public SubjectCostBudget percentage(BigDecimal percentage) {
        this.setPercentage(percentage);
        return this;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public ProjectBudget getProjectBudget() {
        return this.projectBudget;
    }

    public void setProjectBudget(ProjectBudget projectBudget) {
        this.projectBudget = projectBudget;
    }

    public SubjectCostBudget projectBudget(ProjectBudget projectBudget) {
        this.setProjectBudget(projectBudget);
        return this;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public SubjectCostBudget responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public SubjectCostBudget auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubjectCostBudget)) {
            return false;
        }
        return getId() != null && getId().equals(((SubjectCostBudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubjectCostBudget{" +
            "id=" + getId() +
            ", contractid='" + getContractid() + "'" +
            ", subjectid=" + getSubjectid() +
            ", subjectname='" + getSubjectname() + "'" +
            ", budgetamount=" + getBudgetamount() +
            ", estimatedamount=" + getEstimatedamount() +
            ", implementedamount=" + getImplementedamount() +
            ", difference=" + getDifference() +
            ", percentage=" + getPercentage() +
            "}";
    }
}
