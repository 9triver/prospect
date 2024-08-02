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
 * A CostControlSystem.
 */
@Entity
@Table(name = "cost_control_system")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CostControlSystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "jhi_type")
    private Integer type;

    @Enumerated(EnumType.STRING)
    @Column(name = "subject")
    private ContractSubject subject;

    @Column(name = "implementedamount", precision = 21, scale = 2)
    private BigDecimal implementedamount;

    @Column(name = "approvedamount", precision = 21, scale = 2)
    private BigDecimal approvedamount;

    @Column(name = "pendingimplementationamount", precision = 21, scale = 2)
    private BigDecimal pendingimplementationamount;

    @Column(name = "contractpaymentamount", precision = 21, scale = 2)
    private BigDecimal contractpaymentamount;

    @Column(name = "managementregistrationnumber")
    private Integer managementregistrationnumber;

    @Column(name = "financialregistrationnumber")
    private Integer financialregistrationnumber;

    @Column(name = "contractbudgetamount", precision = 21, scale = 2)
    private BigDecimal contractbudgetamount;

    @Column(name = "contractsigningamount", precision = 21, scale = 2)
    private BigDecimal contractsigningamount;

    @Column(name = "contractsettlementamount", precision = 21, scale = 2)
    private BigDecimal contractsettlementamount;

    @Column(name = "unforeseeableamount", precision = 21, scale = 2)
    private BigDecimal unforeseeableamount;

    @Column(name = "invoicepaymentamount", precision = 21, scale = 2)
    private BigDecimal invoicepaymentamount;

    @Column(name = "loanpaymentamount", precision = 21, scale = 2)
    private BigDecimal loanpaymentamount;

    @Column(name = "accountoutstandingamount", precision = 21, scale = 2)
    private BigDecimal accountoutstandingamount;

    @Column(name = "pendingpaymentamount", precision = 21, scale = 2)
    private BigDecimal pendingpaymentamount;

    @Column(name = "pendinginvoiceamount", precision = 21, scale = 2)
    private BigDecimal pendinginvoiceamount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_cost_control_system__projectwbs",
        joinColumns = @JoinColumn(name = "cost_control_system_id"),
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_cost_control_system__contract",
        joinColumns = @JoinColumn(name = "cost_control_system_id"),
        inverseJoinColumns = @JoinColumn(name = "contract_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "costControlSystems" }, allowSetters = true)
    private Set<Contract> contracts = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public CostControlSystem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return this.type;
    }

    public CostControlSystem type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public ContractSubject getSubject() {
        return this.subject;
    }

    public CostControlSystem subject(ContractSubject subject) {
        this.setSubject(subject);
        return this;
    }

    public void setSubject(ContractSubject subject) {
        this.subject = subject;
    }

    public BigDecimal getImplementedamount() {
        return this.implementedamount;
    }

    public CostControlSystem implementedamount(BigDecimal implementedamount) {
        this.setImplementedamount(implementedamount);
        return this;
    }

    public void setImplementedamount(BigDecimal implementedamount) {
        this.implementedamount = implementedamount;
    }

    public BigDecimal getApprovedamount() {
        return this.approvedamount;
    }

    public CostControlSystem approvedamount(BigDecimal approvedamount) {
        this.setApprovedamount(approvedamount);
        return this;
    }

    public void setApprovedamount(BigDecimal approvedamount) {
        this.approvedamount = approvedamount;
    }

    public BigDecimal getPendingimplementationamount() {
        return this.pendingimplementationamount;
    }

    public CostControlSystem pendingimplementationamount(BigDecimal pendingimplementationamount) {
        this.setPendingimplementationamount(pendingimplementationamount);
        return this;
    }

    public void setPendingimplementationamount(BigDecimal pendingimplementationamount) {
        this.pendingimplementationamount = pendingimplementationamount;
    }

    public BigDecimal getContractpaymentamount() {
        return this.contractpaymentamount;
    }

    public CostControlSystem contractpaymentamount(BigDecimal contractpaymentamount) {
        this.setContractpaymentamount(contractpaymentamount);
        return this;
    }

    public void setContractpaymentamount(BigDecimal contractpaymentamount) {
        this.contractpaymentamount = contractpaymentamount;
    }

    public Integer getManagementregistrationnumber() {
        return this.managementregistrationnumber;
    }

    public CostControlSystem managementregistrationnumber(Integer managementregistrationnumber) {
        this.setManagementregistrationnumber(managementregistrationnumber);
        return this;
    }

    public void setManagementregistrationnumber(Integer managementregistrationnumber) {
        this.managementregistrationnumber = managementregistrationnumber;
    }

    public Integer getFinancialregistrationnumber() {
        return this.financialregistrationnumber;
    }

    public CostControlSystem financialregistrationnumber(Integer financialregistrationnumber) {
        this.setFinancialregistrationnumber(financialregistrationnumber);
        return this;
    }

    public void setFinancialregistrationnumber(Integer financialregistrationnumber) {
        this.financialregistrationnumber = financialregistrationnumber;
    }

    public BigDecimal getContractbudgetamount() {
        return this.contractbudgetamount;
    }

    public CostControlSystem contractbudgetamount(BigDecimal contractbudgetamount) {
        this.setContractbudgetamount(contractbudgetamount);
        return this;
    }

    public void setContractbudgetamount(BigDecimal contractbudgetamount) {
        this.contractbudgetamount = contractbudgetamount;
    }

    public BigDecimal getContractsigningamount() {
        return this.contractsigningamount;
    }

    public CostControlSystem contractsigningamount(BigDecimal contractsigningamount) {
        this.setContractsigningamount(contractsigningamount);
        return this;
    }

    public void setContractsigningamount(BigDecimal contractsigningamount) {
        this.contractsigningamount = contractsigningamount;
    }

    public BigDecimal getContractsettlementamount() {
        return this.contractsettlementamount;
    }

    public CostControlSystem contractsettlementamount(BigDecimal contractsettlementamount) {
        this.setContractsettlementamount(contractsettlementamount);
        return this;
    }

    public void setContractsettlementamount(BigDecimal contractsettlementamount) {
        this.contractsettlementamount = contractsettlementamount;
    }

    public BigDecimal getUnforeseeableamount() {
        return this.unforeseeableamount;
    }

    public CostControlSystem unforeseeableamount(BigDecimal unforeseeableamount) {
        this.setUnforeseeableamount(unforeseeableamount);
        return this;
    }

    public void setUnforeseeableamount(BigDecimal unforeseeableamount) {
        this.unforeseeableamount = unforeseeableamount;
    }

    public BigDecimal getInvoicepaymentamount() {
        return this.invoicepaymentamount;
    }

    public CostControlSystem invoicepaymentamount(BigDecimal invoicepaymentamount) {
        this.setInvoicepaymentamount(invoicepaymentamount);
        return this;
    }

    public void setInvoicepaymentamount(BigDecimal invoicepaymentamount) {
        this.invoicepaymentamount = invoicepaymentamount;
    }

    public BigDecimal getLoanpaymentamount() {
        return this.loanpaymentamount;
    }

    public CostControlSystem loanpaymentamount(BigDecimal loanpaymentamount) {
        this.setLoanpaymentamount(loanpaymentamount);
        return this;
    }

    public void setLoanpaymentamount(BigDecimal loanpaymentamount) {
        this.loanpaymentamount = loanpaymentamount;
    }

    public BigDecimal getAccountoutstandingamount() {
        return this.accountoutstandingamount;
    }

    public CostControlSystem accountoutstandingamount(BigDecimal accountoutstandingamount) {
        this.setAccountoutstandingamount(accountoutstandingamount);
        return this;
    }

    public void setAccountoutstandingamount(BigDecimal accountoutstandingamount) {
        this.accountoutstandingamount = accountoutstandingamount;
    }

    public BigDecimal getPendingpaymentamount() {
        return this.pendingpaymentamount;
    }

    public CostControlSystem pendingpaymentamount(BigDecimal pendingpaymentamount) {
        this.setPendingpaymentamount(pendingpaymentamount);
        return this;
    }

    public void setPendingpaymentamount(BigDecimal pendingpaymentamount) {
        this.pendingpaymentamount = pendingpaymentamount;
    }

    public BigDecimal getPendinginvoiceamount() {
        return this.pendinginvoiceamount;
    }

    public CostControlSystem pendinginvoiceamount(BigDecimal pendinginvoiceamount) {
        this.setPendinginvoiceamount(pendinginvoiceamount);
        return this;
    }

    public void setPendinginvoiceamount(BigDecimal pendinginvoiceamount) {
        this.pendinginvoiceamount = pendinginvoiceamount;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public CostControlSystem responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public CostControlSystem auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public CostControlSystem projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public CostControlSystem addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public CostControlSystem removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    public Set<Contract> getContracts() {
        return this.contracts;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public CostControlSystem contracts(Set<Contract> contracts) {
        this.setContracts(contracts);
        return this;
    }

    public CostControlSystem addContract(Contract contract) {
        this.contracts.add(contract);
        return this;
    }

    public CostControlSystem removeContract(Contract contract) {
        this.contracts.remove(contract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostControlSystem)) {
            return false;
        }
        return getId() != null && getId().equals(((CostControlSystem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CostControlSystem{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", subject='" + getSubject() + "'" +
            ", implementedamount=" + getImplementedamount() +
            ", approvedamount=" + getApprovedamount() +
            ", pendingimplementationamount=" + getPendingimplementationamount() +
            ", contractpaymentamount=" + getContractpaymentamount() +
            ", managementregistrationnumber=" + getManagementregistrationnumber() +
            ", financialregistrationnumber=" + getFinancialregistrationnumber() +
            ", contractbudgetamount=" + getContractbudgetamount() +
            ", contractsigningamount=" + getContractsigningamount() +
            ", contractsettlementamount=" + getContractsettlementamount() +
            ", unforeseeableamount=" + getUnforeseeableamount() +
            ", invoicepaymentamount=" + getInvoicepaymentamount() +
            ", loanpaymentamount=" + getLoanpaymentamount() +
            ", accountoutstandingamount=" + getAccountoutstandingamount() +
            ", pendingpaymentamount=" + getPendingpaymentamount() +
            ", pendinginvoiceamount=" + getPendinginvoiceamount() +
            "}";
    }
}
