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
 * A FundSourceList.
 */
@Entity
@Table(name = "fund_source_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundSourceList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "paymentid")
    private String paymentid;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "amount", precision = 21, scale = 2)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "projectwbs", "costControlSystems" }, allowSetters = true)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "fundSourceLists" }, allowSetters = true)
    private TransactionPayment transactionPayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "fundSourceLists" }, allowSetters = true)
    private SporadicPurchasePayment sporadicPurchasePayment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "fundSourceLists" }, allowSetters = true)
    private SharePayment sharePayment;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "fundSourceLists")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workbag", "paymentApplication", "paymentCostLists", "fundSourceLists" }, allowSetters = true)
    private Set<ContractPayment> contractPayments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public FundSourceList id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentid() {
        return this.paymentid;
    }

    public FundSourceList paymentid(String paymentid) {
        this.setPaymentid(paymentid);
        return this;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public FundSourceList contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return this.contractname;
    }

    public FundSourceList contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public FundSourceList wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public FundSourceList wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public FundSourceList amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public FundSourceList contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public TransactionPayment getTransactionPayment() {
        return this.transactionPayment;
    }

    public void setTransactionPayment(TransactionPayment transactionPayment) {
        this.transactionPayment = transactionPayment;
    }

    public FundSourceList transactionPayment(TransactionPayment transactionPayment) {
        this.setTransactionPayment(transactionPayment);
        return this;
    }

    public SporadicPurchasePayment getSporadicPurchasePayment() {
        return this.sporadicPurchasePayment;
    }

    public void setSporadicPurchasePayment(SporadicPurchasePayment sporadicPurchasePayment) {
        this.sporadicPurchasePayment = sporadicPurchasePayment;
    }

    public FundSourceList sporadicPurchasePayment(SporadicPurchasePayment sporadicPurchasePayment) {
        this.setSporadicPurchasePayment(sporadicPurchasePayment);
        return this;
    }

    public SharePayment getSharePayment() {
        return this.sharePayment;
    }

    public void setSharePayment(SharePayment sharePayment) {
        this.sharePayment = sharePayment;
    }

    public FundSourceList sharePayment(SharePayment sharePayment) {
        this.setSharePayment(sharePayment);
        return this;
    }

    public Set<ContractPayment> getContractPayments() {
        return this.contractPayments;
    }

    public void setContractPayments(Set<ContractPayment> contractPayments) {
        if (this.contractPayments != null) {
            this.contractPayments.forEach(i -> i.removeFundSourceList(this));
        }
        if (contractPayments != null) {
            contractPayments.forEach(i -> i.addFundSourceList(this));
        }
        this.contractPayments = contractPayments;
    }

    public FundSourceList contractPayments(Set<ContractPayment> contractPayments) {
        this.setContractPayments(contractPayments);
        return this;
    }

    public FundSourceList addContractPayment(ContractPayment contractPayment) {
        this.contractPayments.add(contractPayment);
        contractPayment.getFundSourceLists().add(this);
        return this;
    }

    public FundSourceList removeContractPayment(ContractPayment contractPayment) {
        this.contractPayments.remove(contractPayment);
        contractPayment.getFundSourceLists().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundSourceList)) {
            return false;
        }
        return getId() != null && getId().equals(((FundSourceList) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundSourceList{" +
            "id=" + getId() +
            ", paymentid='" + getPaymentid() + "'" +
            ", contractcode='" + getContractcode() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", wbsid='" + getWbsid() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            ", amount=" + getAmount() +
            "}";
    }
}
