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
 * A SporadicPurchasePayment.
 */
@Entity
@Table(name = "sporadic_purchase_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SporadicPurchasePayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sporadicPurchasePayment")
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

    public SporadicPurchasePayment id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlanpaymentnode() {
        return this.planpaymentnode;
    }

    public SporadicPurchasePayment planpaymentnode(String planpaymentnode) {
        this.setPlanpaymentnode(planpaymentnode);
        return this;
    }

    public void setPlanpaymentnode(String planpaymentnode) {
        this.planpaymentnode = planpaymentnode;
    }

    public BigDecimal getPlanpaymentamount() {
        return this.planpaymentamount;
    }

    public SporadicPurchasePayment planpaymentamount(BigDecimal planpaymentamount) {
        this.setPlanpaymentamount(planpaymentamount);
        return this;
    }

    public void setPlanpaymentamount(BigDecimal planpaymentamount) {
        this.planpaymentamount = planpaymentamount;
    }

    public BigDecimal getActualpaymentamount() {
        return this.actualpaymentamount;
    }

    public SporadicPurchasePayment actualpaymentamount(BigDecimal actualpaymentamount) {
        this.setActualpaymentamount(actualpaymentamount);
        return this;
    }

    public void setActualpaymentamount(BigDecimal actualpaymentamount) {
        this.actualpaymentamount = actualpaymentamount;
    }

    public PaymentType getPaymenttype() {
        return this.paymenttype;
    }

    public SporadicPurchasePayment paymenttype(PaymentType paymenttype) {
        this.setPaymenttype(paymenttype);
        return this;
    }

    public void setPaymenttype(PaymentType paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getFinancialvoucherid() {
        return this.financialvoucherid;
    }

    public SporadicPurchasePayment financialvoucherid(String financialvoucherid) {
        this.setFinancialvoucherid(financialvoucherid);
        return this;
    }

    public void setFinancialvoucherid(String financialvoucherid) {
        this.financialvoucherid = financialvoucherid;
    }

    public Set<FundSourceList> getFundSourceLists() {
        return this.fundSourceLists;
    }

    public void setFundSourceLists(Set<FundSourceList> fundSourceLists) {
        if (this.fundSourceLists != null) {
            this.fundSourceLists.forEach(i -> i.setSporadicPurchasePayment(null));
        }
        if (fundSourceLists != null) {
            fundSourceLists.forEach(i -> i.setSporadicPurchasePayment(this));
        }
        this.fundSourceLists = fundSourceLists;
    }

    public SporadicPurchasePayment fundSourceLists(Set<FundSourceList> fundSourceLists) {
        this.setFundSourceLists(fundSourceLists);
        return this;
    }

    public SporadicPurchasePayment addFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.add(fundSourceList);
        fundSourceList.setSporadicPurchasePayment(this);
        return this;
    }

    public SporadicPurchasePayment removeFundSourceList(FundSourceList fundSourceList) {
        this.fundSourceLists.remove(fundSourceList);
        fundSourceList.setSporadicPurchasePayment(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SporadicPurchasePayment)) {
            return false;
        }
        return getId() != null && getId().equals(((SporadicPurchasePayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SporadicPurchasePayment{" +
            "id=" + getId() +
            ", planpaymentnode='" + getPlanpaymentnode() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            ", actualpaymentamount=" + getActualpaymentamount() +
            ", paymenttype='" + getPaymenttype() + "'" +
            ", financialvoucherid='" + getFinancialvoucherid() + "'" +
            "}";
    }
}
