package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.PaymentType;
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
 * A ContractPayment.
 */
@Entity
@Table(name = "contract_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ContractPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractPayment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "contractPayment" }, allowSetters = true)
    private Set<PaymentCostList> paymentCostLists = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contractPayment")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "transactionPayment", "sporadicPurchasePayment", "sharePayment", "contractPayment" },
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

    public Set<PaymentCostList> getPaymentCostLists() {
        return this.paymentCostLists;
    }

    public void setPaymentCostLists(Set<PaymentCostList> paymentCostLists) {
        if (this.paymentCostLists != null) {
            this.paymentCostLists.forEach(i -> i.setContractPayment(null));
        }
        if (paymentCostLists != null) {
            paymentCostLists.forEach(i -> i.setContractPayment(this));
        }
        this.paymentCostLists = paymentCostLists;
    }

    public ContractPayment paymentCostLists(Set<PaymentCostList> paymentCostLists) {
        this.setPaymentCostLists(paymentCostLists);
        return this;
    }

    public ContractPayment addPaymentCostList(PaymentCostList paymentCostList) {
        this.paymentCostLists.add(paymentCostList);
        paymentCostList.setContractPayment(this);
        return this;
    }

    public ContractPayment removePaymentCostList(PaymentCostList paymentCostList) {
        this.paymentCostLists.remove(paymentCostList);
        paymentCostList.setContractPayment(null);
        return this;
    }

    public Set<FundSourceList> getFundSourceLists() {
        return this.fundSourceLists;
    }

    public void setFundSourceLists(Set<FundSourceList> fundSourceLists) {
        if (this.fundSourceLists != null) {
            this.fundSourceLists.forEach(i -> i.setContractPayment(null));
        }
        if (fundSourceLists != null) {
            fundSourceLists.forEach(i -> i.setContractPayment(this));
        }
        this.fundSourceLists = fundSourceLists;
    }

    public ContractPayment fundSourceLists(Set<FundSourceList> fundSourceLists) {
        this.setFundSourceLists(fundSourceLists);
        return this;
    }

    public ContractPayment addFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.add(fundSourceList);
        fundSourceList.setContractPayment(this);
        return this;
    }

    public ContractPayment removeFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.remove(fundSourceList);
        fundSourceList.setContractPayment(null);
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
