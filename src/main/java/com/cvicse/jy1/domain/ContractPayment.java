package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ContractPayment.
 */
@Entity
@Table(name = "contract_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "workbagname")
    private String workbagname;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "planpaymentnode")
    private String planpaymentnode;

    @Column(name = "planpaymentamount", precision = 21, scale = 2)
    private BigDecimal planpaymentamount;

    @Column(name = "actualpaymentamount", precision = 21, scale = 2)
    private BigDecimal actualpaymentamount;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymenttype")
    private PaymentType paymenttype;

    @Column(name = "financialvoucherid")
    private String financialvoucherid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "projectmanager",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "department",
            "projectdeliverables",
            "relevantdepartments",
            "wbsids",
            "works",
            "outsourcingContract",
            "paymentApplications",
        },
        allowSetters = true
    )
    private Workbag workbag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "workbag" }, allowSetters = true)
    private PaymentApplication paymentApplication;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_contract_payment__payment_cost_list",
        joinColumns = @JoinColumn(name = "contract_payment_id"),
        inverseJoinColumns = @JoinColumn(name = "payment_cost_list_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workbag", "contractPayments" }, allowSetters = true)
    private Set<PaymentCostList> paymentCostLists = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_contract_payment__fund_source_list",
        joinColumns = @JoinColumn(name = "contract_payment_id"),
        inverseJoinColumns = @JoinColumn(name = "fund_source_list_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "contract", "transactionPayment", "sporadicPurchasePayment", "sharePayment", "contractPayments" },
        allowSetters = true
    )
    private Set<FundSourceList> fundSourceLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public ContractPayment id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public ContractPayment workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getWorkbagname() {
        return this.workbagname;
    }

    public ContractPayment workbagname(String workbagname) {
        this.setWorkbagname(workbagname);
        return this;
    }

    public void setWorkbagname(String workbagname) {
        this.workbagname = workbagname;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public ContractPayment contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return this.contractname;
    }

    public ContractPayment contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getPlanpaymentnode() {
        return this.planpaymentnode;
    }

    public ContractPayment planpaymentnode(String planpaymentnode) {
        this.setPlanpaymentnode(planpaymentnode);
        return this;
    }

    public void setPlanpaymentnode(String planpaymentnode) {
        this.planpaymentnode = planpaymentnode;
    }

    public BigDecimal getPlanpaymentamount() {
        return this.planpaymentamount;
    }

    public ContractPayment planpaymentamount(BigDecimal planpaymentamount) {
        this.setPlanpaymentamount(planpaymentamount);
        return this;
    }

    public void setPlanpaymentamount(BigDecimal planpaymentamount) {
        this.planpaymentamount = planpaymentamount;
    }

    public BigDecimal getActualpaymentamount() {
        return this.actualpaymentamount;
    }

    public ContractPayment actualpaymentamount(BigDecimal actualpaymentamount) {
        this.setActualpaymentamount(actualpaymentamount);
        return this;
    }

    public void setActualpaymentamount(BigDecimal actualpaymentamount) {
        this.actualpaymentamount = actualpaymentamount;
    }

    public PaymentType getPaymenttype() {
        return this.paymenttype;
    }

    public ContractPayment paymenttype(PaymentType paymenttype) {
        this.setPaymenttype(paymenttype);
        return this;
    }

    public void setPaymenttype(PaymentType paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getFinancialvoucherid() {
        return this.financialvoucherid;
    }

    public ContractPayment financialvoucherid(String financialvoucherid) {
        this.setFinancialvoucherid(financialvoucherid);
        return this;
    }

    public void setFinancialvoucherid(String financialvoucherid) {
        this.financialvoucherid = financialvoucherid;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public ContractPayment workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public PaymentApplication getPaymentApplication() {
        return this.paymentApplication;
    }

    public void setPaymentApplication(PaymentApplication paymentApplication) {
        this.paymentApplication = paymentApplication;
    }

    public ContractPayment paymentApplication(PaymentApplication paymentApplication) {
        this.setPaymentApplication(paymentApplication);
        return this;
    }

    public Set<PaymentCostList> getPaymentCostLists() {
        return this.paymentCostLists;
    }

    public void setPaymentCostLists(Set<PaymentCostList> paymentCostLists) {
        this.paymentCostLists = paymentCostLists;
    }

    public ContractPayment paymentCostLists(Set<PaymentCostList> paymentCostLists) {
        this.setPaymentCostLists(paymentCostLists);
        return this;
    }

    public ContractPayment addPaymentCostList(PaymentCostList paymentCostList) {
        this.paymentCostLists.add(paymentCostList);
        return this;
    }

    public ContractPayment removePaymentCostList(PaymentCostList paymentCostList) {
        this.paymentCostLists.remove(paymentCostList);
        return this;
    }

    public Set<FundSourceList> getFundSourceLists() {
        return this.fundSourceLists;
    }

    public void setFundSourceLists(Set<FundSourceList> fundSourceLists) {
        this.fundSourceLists = fundSourceLists;
    }

    public ContractPayment fundSourceLists(Set<FundSourceList> fundSourceLists) {
        this.setFundSourceLists(fundSourceLists);
        return this;
    }

    public ContractPayment addFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.add(fundSourceList);
        return this;
    }

    public ContractPayment removeFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.remove(fundSourceList);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractPayment)) {
            return false;
        }
        return getId() != null && getId().equals(((ContractPayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractPayment{" +
            "id=" + getId() +
            ", workbagid='" + getWorkbagid() + "'" +
            ", workbagname='" + getWorkbagname() + "'" +
            ", contractcode='" + getContractcode() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", planpaymentnode='" + getPlanpaymentnode() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            ", actualpaymentamount=" + getActualpaymentamount() +
            ", paymenttype='" + getPaymenttype() + "'" +
            ", financialvoucherid='" + getFinancialvoucherid() + "'" +
            "}";
    }
}
