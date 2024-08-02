package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.ContractSubject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ContractCostBudget.
 */
@Entity
@Table(name = "contract_cost_budget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractCostBudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private ContractSubject subject;

    @Column(name = "auxiliaryitem")
    private String auxiliaryitem;

    @Column(name = "unit")
    private String unit;

    @Column(name = "jhi_number")
    private String number;

    @Column(name = "unitprice", precision = 21, scale = 2)
    private BigDecimal unitprice;

    @Column(name = "totalprice", precision = 21, scale = 2)
    private BigDecimal totalprice;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_contract_cost_budget__projectwbs",
        joinColumns = @JoinColumn(name = "contract_cost_budget_id"),
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

    public ContractCostBudget id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ContractSubject getSubject() {
        return this.subject;
    }

    public ContractCostBudget subject(ContractSubject subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(ContractSubject subject) {
        this.subject = subject;
    }

    public String getAuxiliaryitem() {
        return this.auxiliaryitem;
    }

    public ContractCostBudget auxiliaryitem(String auxiliaryitem) {
        this.setAuxiliaryitem(auxiliaryitem);
        return this;
    }

    public void setAuxiliaryitem(String auxiliaryitem) {
        this.auxiliaryitem = auxiliaryitem;
    }

    public String getUnit() {
        return this.unit;
    }

    public ContractCostBudget unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return this.number;
    }

    public ContractCostBudget number(String number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public ContractCostBudget unitprice(BigDecimal unitprice) {
        this.setUnitprice(unitprice);
        return this;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getTotalprice() {
        return this.totalprice;
    }

    public ContractCostBudget totalprice(BigDecimal totalprice) {
        this.setTotalprice(totalprice);
        return this;
    }

    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public ContractCostBudget projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public ContractCostBudget addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public ContractCostBudget removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractCostBudget)) {
            return false;
        }
        return getId() != null && getId().equals(((ContractCostBudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractCostBudget{" +
            "id=" + getId() +
            ", subject='" + getSubject() + "'" +
            ", auxiliaryitem='" + getAuxiliaryitem() + "'" +
            ", unit='" + getUnit() + "'" +
            ", number='" + getNumber() + "'" +
            ", unitprice=" + getUnitprice() +
            ", totalprice=" + getTotalprice() +
            "}";
    }
}
