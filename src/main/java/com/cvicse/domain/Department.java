package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "departmentid")
    private Long departmentid;

    @Column(name = "departmentname")
    private String departmentname;

    @Column(name = "officersnum")
    private Integer officersnum;

    @Column(name = "officersid")
    private String officersid;

    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private Officers officers;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private Project project;

    @JsonIgnoreProperties(value = { "decument", "responsibleid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "decument")
    private Planstrategy planstrategy;

    @JsonIgnoreProperties(
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "project", "comprehensivecontrol" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private Progressmanagement progressmanagement;

    @JsonIgnoreProperties(value = { "department", "project", "managementCapacityEvaluation" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "department")
    private EvaluationCriteria evaluationCriteria;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Department id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepartmentid() {
        return this.departmentid;
    }

    public Department departmentid(Long departmentid) {
        this.setDepartmentid(departmentid);
        return this;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return this.departmentname;
    }

    public Department departmentname(String departmentname) {
        this.setDepartmentname(departmentname);
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public Integer getOfficersnum() {
        return this.officersnum;
    }

    public Department officersnum(Integer officersnum) {
        this.setOfficersnum(officersnum);
        return this;
    }

    public void setOfficersnum(Integer officersnum) {
        this.officersnum = officersnum;
    }

    public String getOfficersid() {
        return this.officersid;
    }

    public Department officersid(String officersid) {
        this.setOfficersid(officersid);
        return this;
    }

    public void setOfficersid(String officersid) {
        this.officersid = officersid;
    }

    public Officers getOfficers() {
        return this.officers;
    }

    public void setOfficers(Officers officers) {
        if (this.officers != null) {
            this.officers.setDepartment(null);
        }
        if (officers != null) {
            officers.setDepartment(this);
        }
        this.officers = officers;
    }

    public Department officers(Officers officers) {
        this.setOfficers(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setDepartment(null);
        }
        if (project != null) {
            project.setDepartment(this);
        }
        this.project = project;
    }

    public Department project(Project project) {
        this.setProject(project);
        return this;
    }

    public Planstrategy getPlanstrategy() {
        return this.planstrategy;
    }

    public void setPlanstrategy(Planstrategy planstrategy) {
        if (this.planstrategy != null) {
            this.planstrategy.setDecument(null);
        }
        if (planstrategy != null) {
            planstrategy.setDecument(this);
        }
        this.planstrategy = planstrategy;
    }

    public Department planstrategy(Planstrategy planstrategy) {
        this.setPlanstrategy(planstrategy);
        return this;
    }

    public Progressmanagement getProgressmanagement() {
        return this.progressmanagement;
    }

    public void setProgressmanagement(Progressmanagement progressmanagement) {
        if (this.progressmanagement != null) {
            this.progressmanagement.setDepartment(null);
        }
        if (progressmanagement != null) {
            progressmanagement.setDepartment(this);
        }
        this.progressmanagement = progressmanagement;
    }

    public Department progressmanagement(Progressmanagement progressmanagement) {
        this.setProgressmanagement(progressmanagement);
        return this;
    }

    public EvaluationCriteria getEvaluationCriteria() {
        return this.evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        if (this.evaluationCriteria != null) {
            this.evaluationCriteria.setDepartment(null);
        }
        if (evaluationCriteria != null) {
            evaluationCriteria.setDepartment(this);
        }
        this.evaluationCriteria = evaluationCriteria;
    }

    public Department evaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.setEvaluationCriteria(evaluationCriteria);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return getId() != null && getId().equals(((Department) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", departmentid=" + getDepartmentid() +
            ", departmentname='" + getDepartmentname() + "'" +
            ", officersnum=" + getOfficersnum() +
            ", officersid='" + getOfficersid() + "'" +
            "}";
    }
}
