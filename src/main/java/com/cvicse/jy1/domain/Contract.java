package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.ContractStatus;
import com.cvicse.jy1.domain.enumeration.ContractType;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "projectwbsname")
    private String projectwbsname;

    @Enumerated(EnumType.STRING)
    @Column(name = "contracttype")
    private ContractType contracttype;

    @Column(name = "jhi_year")
    private Integer year;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "fileurl")
    private String fileurl;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatus status;

    @Column(name = "remark")
    private String remark;

    @Column(name = "budgetamount", precision = 21, scale = 2)
    private BigDecimal budgetamount;

    @Column(name = "estimatedamount", precision = 21, scale = 2)
    private BigDecimal estimatedamount;

    @Column(name = "implementedamount", precision = 21, scale = 2)
    private BigDecimal implementedamount;

    @Column(name = "difference", precision = 21, scale = 2)
    private BigDecimal difference;

    @ManyToOne(fetch = FetchType.LAZY)
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
            "projectBudgets",
            "workbags",
            "progressPlans",
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
    private Projectwbs projectwbs;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "contracts")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs", "contracts" }, allowSetters = true)
    private Set<CostControlSystem> costControlSystems = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Contract id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public Contract contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
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

    public String getProjectwbsname() {
        return this.projectwbsname;
    }

    public Contract projectwbsname(String projectwbsname) {
        this.setProjectwbsname(projectwbsname);
        return this;
    }

    public void setProjectwbsname(String projectwbsname) {
        this.projectwbsname = projectwbsname;
    }

    public ContractType getContracttype() {
        return this.contracttype;
    }

    public Contract contracttype(ContractType contracttype) {
        this.setContracttype(contracttype);
        return this;
    }

    public void setContracttype(ContractType contracttype) {
        this.contracttype = contracttype;
    }

    public Integer getYear() {
        return this.year;
    }

    public Contract year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Contract amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public String getFileurl() {
        return this.fileurl;
    }

    public Contract fileurl(String fileurl) {
        this.setFileurl(fileurl);
        return this;
    }

    public void setFileurl(String fileurl) {
        this.fileurl = fileurl;
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

    public String getRemark() {
        return this.remark;
    }

    public Contract remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getBudgetamount() {
        return this.budgetamount;
    }

    public Contract budgetamount(BigDecimal budgetamount) {
        this.setBudgetamount(budgetamount);
        return this;
    }

    public void setBudgetamount(BigDecimal budgetamount) {
        this.budgetamount = budgetamount;
    }

    public BigDecimal getEstimatedamount() {
        return this.estimatedamount;
    }

    public Contract estimatedamount(BigDecimal estimatedamount) {
        this.setEstimatedamount(estimatedamount);
        return this;
    }

    public void setEstimatedamount(BigDecimal estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public BigDecimal getImplementedamount() {
        return this.implementedamount;
    }

    public Contract implementedamount(BigDecimal implementedamount) {
        this.setImplementedamount(implementedamount);
        return this;
    }

    public void setImplementedamount(BigDecimal implementedamount) {
        this.implementedamount = implementedamount;
    }

    public BigDecimal getDifference() {
        return this.difference;
    }

    public Contract difference(BigDecimal difference) {
        this.setDifference(difference);
        return this;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public Contract projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
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
            ", contractcode='" + getContractcode() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", projectwbsname='" + getProjectwbsname() + "'" +
            ", contracttype='" + getContracttype() + "'" +
            ", year=" + getYear() +
            ", amount=" + getAmount() +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", fileurl='" + getFileurl() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", status='" + getStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", budgetamount=" + getBudgetamount() +
            ", estimatedamount=" + getEstimatedamount() +
            ", implementedamount=" + getImplementedamount() +
            ", difference=" + getDifference() +
            "}";
    }
}
