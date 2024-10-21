package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectBudget.
 */
@Entity
@Table(name = "project_budget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "wbsid", nullable = false)
    private String wbsid;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "parentwbsid")
    private String parentwbsid;

    @Column(name = "subjectid")
    private Integer subjectid;

    @Column(name = "subjectname")
    private String subjectname;

    @NotNull
    @Column(name = "contractid", nullable = false)
    private String contractid;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "jhi_year")
    private Integer year;

    @Column(name = "auxiliaryitem")
    private String auxiliaryitem;

    @Column(name = "unit")
    private String unit;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "unitprice", precision = 21, scale = 2)
    private BigDecimal unitprice;

    @Column(name = "budgetamount", precision = 21, scale = 2)
    private BigDecimal budgetamount;

    @Column(name = "estimatedamount", precision = 21, scale = 2)
    private BigDecimal estimatedamount;

    @Column(name = "implementedamount", precision = 21, scale = 2)
    private BigDecimal implementedamount;

    @Column(name = "difference", precision = 21, scale = 2)
    private BigDecimal difference;

    @Column(name = "remark")
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_project_budget__projectwbs",
        joinColumns = @JoinColumn(name = "project_budget_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "projectpbs",
            "responsibleperson",
            "technicaldirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "projectdeliverables",
            "relevantdepartments",
            "workbags",
            "progressPlans",
            "projectBudgets",
            "projects",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "projectTotalwbs",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ProjectBudget id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public ProjectBudget wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public ProjectBudget wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getParentwbsid() {
        return this.parentwbsid;
    }

    public ProjectBudget parentwbsid(String parentwbsid) {
        this.setParentwbsid(parentwbsid);
        return this;
    }

    public void setParentwbsid(String parentwbsid) {
        this.parentwbsid = parentwbsid;
    }

    public Integer getSubjectid() {
        return this.subjectid;
    }

    public ProjectBudget subjectid(Integer subjectid) {
        this.setSubjectid(subjectid);
        return this;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return this.subjectname;
    }

    public ProjectBudget subjectname(String subjectname) {
        this.setSubjectname(subjectname);
        return this;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public String getContractid() {
        return this.contractid;
    }

    public ProjectBudget contractid(String contractid) {
        this.setContractid(contractid);
        return this;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getContractname() {
        return this.contractname;
    }

    public ProjectBudget contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public Integer getYear() {
        return this.year;
    }

    public ProjectBudget year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuxiliaryitem() {
        return this.auxiliaryitem;
    }

    public ProjectBudget auxiliaryitem(String auxiliaryitem) {
        this.setAuxiliaryitem(auxiliaryitem);
        return this;
    }

    public void setAuxiliaryitem(String auxiliaryitem) {
        this.auxiliaryitem = auxiliaryitem;
    }

    public String getUnit() {
        return this.unit;
    }

    public ProjectBudget unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return this.number;
    }

    public ProjectBudget number(String number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public ProjectBudget unitprice(BigDecimal unitprice) {
        this.setUnitprice(unitprice);
        return this;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getBudgetamount() {
        return this.budgetamount;
    }

    public ProjectBudget budgetamount(BigDecimal budgetamount) {
        this.setBudgetamount(budgetamount);
        return this;
    }

    public void setBudgetamount(BigDecimal budgetamount) {
        this.budgetamount = budgetamount;
    }

    public BigDecimal getEstimatedamount() {
        return this.estimatedamount;
    }

    public ProjectBudget estimatedamount(BigDecimal estimatedamount) {
        this.setEstimatedamount(estimatedamount);
        return this;
    }

    public void setEstimatedamount(BigDecimal estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public BigDecimal getImplementedamount() {
        return this.implementedamount;
    }

    public ProjectBudget implementedamount(BigDecimal implementedamount) {
        this.setImplementedamount(implementedamount);
        return this;
    }

    public void setImplementedamount(BigDecimal implementedamount) {
        this.implementedamount = implementedamount;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public ProjectBudget difference(BigDecimal difference) {
        this.setDifference(difference);
        return this;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public String getRemark() {
        return this.remark;
    }

    public ProjectBudget remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public ProjectBudget responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public ProjectBudget auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public ProjectBudget projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public ProjectBudget addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public ProjectBudget removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectBudget)) {
            return false;
        }
        return getId() != null && getId().equals(((ProjectBudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectBudget{" +
            "id=" + getId() +
            ", wbsid='" + getWbsid() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            ", parentwbsid='" + getParentwbsid() + "'" +
            ", subjectid=" + getSubjectid() +
            ", subjectname='" + getSubjectname() + "'" +
            ", contractid='" + getContractid() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", year=" + getYear() +
            ", auxiliaryitem='" + getAuxiliaryitem() + "'" +
            ", unit='" + getUnit() + "'" +
            ", number='" + getNumber() + "'" +
            ", unitprice=" + getUnitprice() +
            ", budgetamount=" + getBudgetamount() +
            ", estimatedamount=" + getEstimatedamount() +
            ", implementedamount=" + getImplementedamount() +
            ", difference=" + getDifference() +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
