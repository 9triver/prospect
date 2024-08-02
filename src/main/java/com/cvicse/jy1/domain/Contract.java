package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.ContractStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Contract.
 */
@Entity
@Table(name = "contract")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "jhi_year", precision = 21, scale = 2)
    private BigDecimal year;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "contractbudgetcost", precision = 21, scale = 2)
    private BigDecimal contractbudgetcost;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatus status;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "contracts")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs", "contracts" }, allowSetters = true)
    private Set<CostControlSystem> costControlSystems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Contract id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractname() {
        return this.contractname;
    }

    public Contract contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public BigDecimal getYear() {
        return this.year;
    }

    public Contract year(BigDecimal year) {
        this.setYear(year);
        return this;
    }

    public void setYear(BigDecimal year) {
        this.year = year;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Contract starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Contract endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public BigDecimal getContractbudgetcost() {
        return this.contractbudgetcost;
    }

    public Contract contractbudgetcost(BigDecimal contractbudgetcost) {
        this.setContractbudgetcost(contractbudgetcost);
        return this;
    }

    public void setContractbudgetcost(BigDecimal contractbudgetcost) {
        this.contractbudgetcost = contractbudgetcost;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Contract secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public ContractStatus getStatus() {
        return this.status;
    }

    public Contract status(ContractStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ContractStatus status) {
        this.status = status;
    }

    public Set<CostControlSystem> getCostControlSystems() {
        return this.costControlSystems;
    }

    public void setCostControlSystems(Set<CostControlSystem> costControlSystems) {
        if (this.costControlSystems != null) {
            this.costControlSystems.forEach(i -> i.removeContract(this));
        }
        if (costControlSystems != null) {
            costControlSystems.forEach(i -> i.addContract(this));
        }
        this.costControlSystems = costControlSystems;
    }

    public Contract costControlSystems(Set<CostControlSystem> costControlSystems) {
        this.setCostControlSystems(costControlSystems);
        return this;
    }

    public Contract addCostControlSystem(CostControlSystem costControlSystem) {
        this.costControlSystems.add(costControlSystem);
        costControlSystem.getContracts().add(this);
        return this;
    }

    public Contract removeCostControlSystem(CostControlSystem costControlSystem) {
        this.costControlSystems.remove(costControlSystem);
        costControlSystem.getContracts().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return getId() != null && getId().equals(((Contract) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", contractname='" + getContractname() + "'" +
            ", year=" + getYear() +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", contractbudgetcost=" + getContractbudgetcost() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
