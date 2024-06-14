package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ManagementCapacityEvaluation.
 */
@Entity
@Table(name = "management_capacity_evaluation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ManagementCapacityEvaluation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "deprotment")
    private String deprotment;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "status")
    private Integer status;

    @Column(name = "totalmark", precision = 21, scale = 2)
    private BigDecimal totalmark;

    @Column(name = "mark", precision = 21, scale = 2)
    private BigDecimal mark;

    @Column(name = "ratingpersonname")
    private String ratingpersonname;

    @Column(name = "ratingdepartment")
    private String ratingdepartment;

    @Column(name = "ratingtimg")
    private LocalDate ratingtimg;

    @JsonIgnoreProperties(value = { "department", "project", "managementCapacityEvaluation" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private EvaluationCriteria evaluationCriteria;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers ratingperson;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ManagementCapacityEvaluation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return this.year;
    }

    public ManagementCapacityEvaluation year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getDeprotment() {
        return this.deprotment;
    }

    public ManagementCapacityEvaluation deprotment(String deprotment) {
        this.setDeprotment(deprotment);
        return this;
    }

    public void setDeprotment(String deprotment) {
        this.deprotment = deprotment;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public ManagementCapacityEvaluation createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public ManagementCapacityEvaluation status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getTotalmark() {
        return this.totalmark;
    }

    public ManagementCapacityEvaluation totalmark(BigDecimal totalmark) {
        this.setTotalmark(totalmark);
        return this;
    }

    public void setTotalmark(BigDecimal totalmark) {
        this.totalmark = totalmark;
    }

    public BigDecimal getMark() {
        return this.mark;
    }

    public ManagementCapacityEvaluation mark(BigDecimal mark) {
        this.setMark(mark);
        return this;
    }

    public void setMark(BigDecimal mark) {
        this.mark = mark;
    }

    public String getRatingpersonname() {
        return this.ratingpersonname;
    }

    public ManagementCapacityEvaluation ratingpersonname(String ratingpersonname) {
        this.setRatingpersonname(ratingpersonname);
        return this;
    }

    public void setRatingpersonname(String ratingpersonname) {
        this.ratingpersonname = ratingpersonname;
    }

    public String getRatingdepartment() {
        return this.ratingdepartment;
    }

    public ManagementCapacityEvaluation ratingdepartment(String ratingdepartment) {
        this.setRatingdepartment(ratingdepartment);
        return this;
    }

    public void setRatingdepartment(String ratingdepartment) {
        this.ratingdepartment = ratingdepartment;
    }

    public LocalDate getRatingtimg() {
        return this.ratingtimg;
    }

    public ManagementCapacityEvaluation ratingtimg(LocalDate ratingtimg) {
        this.setRatingtimg(ratingtimg);
        return this;
    }

    public void setRatingtimg(LocalDate ratingtimg) {
        this.ratingtimg = ratingtimg;
    }

    public EvaluationCriteria getEvaluationCriteria() {
        return this.evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public ManagementCapacityEvaluation evaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.setEvaluationCriteria(evaluationCriteria);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ManagementCapacityEvaluation project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public ManagementCapacityEvaluation creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public ManagementCapacityEvaluation responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getRatingperson() {
        return this.ratingperson;
    }

    public void setRatingperson(Officers officers) {
        this.ratingperson = officers;
    }

    public ManagementCapacityEvaluation ratingperson(Officers officers) {
        this.setRatingperson(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ManagementCapacityEvaluation)) {
            return false;
        }
        return getId() != null && getId().equals(((ManagementCapacityEvaluation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagementCapacityEvaluation{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", deprotment='" + getDeprotment() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", status=" + getStatus() +
            ", totalmark=" + getTotalmark() +
            ", mark=" + getMark() +
            ", ratingpersonname='" + getRatingpersonname() + "'" +
            ", ratingdepartment='" + getRatingdepartment() + "'" +
            ", ratingtimg='" + getRatingtimg() + "'" +
            "}";
    }
}
