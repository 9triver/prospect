package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FundsEstimation.
 */
@Entity
@Table(name = "funds_estimation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundsEstimation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "parentwbsid")
    private String parentwbsid;

    @Column(name = "source")
    private String source;

    @Column(name = "unit")
    private String unit;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "unitprice", precision = 21, scale = 2)
    private BigDecimal unitprice;

    @Column(name = "materialfee", precision = 21, scale = 2)
    private BigDecimal materialfee;

    @Column(name = "specialfee", precision = 21, scale = 2)
    private BigDecimal specialfee;

    @Column(name = "outsourcingfee", precision = 21, scale = 2)
    private BigDecimal outsourcingfee;

    @Column(name = "totalbudgetprice", precision = 21, scale = 2)
    private BigDecimal totalbudgetprice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_funds_estimation__projectwbs",
        joinColumns = @JoinColumn(name = "funds_estimation_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FundsEstimation id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public FundsEstimation name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWbsid() {
        return this.parentwbsid;
    }

    public FundsEstimation wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getParentwbsid() {
        return this.parentwbsid;
    }

    public FundsEstimation parentwbsid(String parentwbsid) {
        this.setParentwbsid(parentwbsid);
        return this;
    }

    public void setParentwbsid(String parentwbsid) {
        this.parentwbsid = parentwbsid;
    }

    public String getSource() {
        return this.source;
    }

    public FundsEstimation source(String source) {
        this.setSource(source);
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUnit() {
        return this.unit;
    }

    public FundsEstimation unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return this.number;
    }

    public FundsEstimation number(String number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public FundsEstimation unitprice(BigDecimal unitprice) {
        this.setUnitprice(unitprice);
        return this;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getMaterialfee() {
        return this.materialfee;
    }

    public FundsEstimation materialfee(BigDecimal materialfee) {
        this.setMaterialfee(materialfee);
        return this;
    }

    public void setMaterialfee(BigDecimal materialfee) {
        this.materialfee = materialfee;
    }

    public BigDecimal getSpecialfee() {
        return this.specialfee;
    }

    public FundsEstimation specialfee(BigDecimal specialfee) {
        this.setSpecialfee(specialfee);
        return this;
    }

    public void setSpecialfee(BigDecimal specialfee) {
        this.specialfee = specialfee;
    }

    public BigDecimal getOutsourcingfee() {
        return this.outsourcingfee;
    }

    public FundsEstimation outsourcingfee(BigDecimal outsourcingfee) {
        this.setOutsourcingfee(outsourcingfee);
        return this;
    }

    public void setOutsourcingfee(BigDecimal outsourcingfee) {
        this.outsourcingfee = outsourcingfee;
    }

    public BigDecimal getTotalbudgetprice() {
        return this.totalbudgetprice;
    }

    public FundsEstimation totalbudgetprice(BigDecimal totalbudgetprice) {
        this.setTotalbudgetprice(totalbudgetprice);
        return this;
    }

    public void setTotalbudgetprice(BigDecimal totalbudgetprice) {
        this.totalbudgetprice = totalbudgetprice;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public FundsEstimation responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public FundsEstimation auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public FundsEstimation projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public FundsEstimation addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public FundsEstimation removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundsEstimation)) {
            return false;
        }
        return getId() != null && getId().equals(((FundsEstimation) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundsEstimation{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", wbsid='" + getWbsid() + "'" +
            ", parentwbsid='" + getParentwbsid() + "'" +
            ", source='" + getSource() + "'" +
            ", unit='" + getUnit() + "'" +
            ", number='" + getNumber() + "'" +
            ", unitprice=" + getUnitprice() +
            ", materialfee=" + getMaterialfee() +
            ", specialfee=" + getSpecialfee() +
            ", outsourcingfee=" + getOutsourcingfee() +
            ", totalbudgetprice=" + getTotalbudgetprice() +
            "}";
    }
}
