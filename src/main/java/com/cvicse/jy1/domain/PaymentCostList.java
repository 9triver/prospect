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
 * A PaymentCostList.
 */
@Entity
@Table(name = "payment_cost_list")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentCostList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "parentwbsid")
    private String parentwbsid;

    @Column(name = "unit")
    private String unit;

    @Column(name = "unitprice", precision = 21, scale = 2)
    private BigDecimal unitprice;

    @Column(name = "jhi_number", precision = 21, scale = 2)
    private BigDecimal number;

    @Column(name = "subjectid")
    private Integer subjectid;

    @Column(name = "subjectname")
    private String subjectname;

    @Column(name = "invoicepaymentamount", precision = 21, scale = 2)
    private BigDecimal invoicepaymentamount;

    @Column(name = "borrowingpaymentamount", precision = 21, scale = 2)
    private BigDecimal borrowingpaymentamount;

    @Column(name = "accountingamount", precision = 21, scale = 2)
    private BigDecimal accountingamount;

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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "paymentCostLists")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workbag", "paymentApplication", "paymentCostLists", "fundSourceLists" }, allowSetters = true)
    private Set<ContractPayment> contractPayments = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public PaymentCostList id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public PaymentCostList wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public PaymentCostList wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getParentwbsid() {
        return this.parentwbsid;
    }

    public PaymentCostList parentwbsid(String parentwbsid) {
        this.setParentwbsid(parentwbsid);
        return this;
    }

    public void setParentwbsid(String parentwbsid) {
        this.parentwbsid = parentwbsid;
    }

    public String getUnit() {
        return this.unit;
    }

    public PaymentCostList unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public PaymentCostList unitprice(BigDecimal unitprice) {
        this.setUnitprice(unitprice);
        return this;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getNumber() {
        return this.number;
    }

    public PaymentCostList number(BigDecimal number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public Integer getSubjectid() {
        return this.subjectid;
    }

    public PaymentCostList subjectid(Integer subjectid) {
        this.setSubjectid(subjectid);
        return this;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return this.subjectname;
    }

    public PaymentCostList subjectname(String subjectname) {
        this.setSubjectname(subjectname);
        return this;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public BigDecimal getInvoicepaymentamount() {
        return this.invoicepaymentamount;
    }

    public PaymentCostList invoicepaymentamount(BigDecimal invoicepaymentamount) {
        this.setInvoicepaymentamount(invoicepaymentamount);
        return this;
    }

    public void setInvoicepaymentamount(BigDecimal invoicepaymentamount) {
        this.invoicepaymentamount = invoicepaymentamount;
    }

    public BigDecimal getBorrowingpaymentamount() {
        return this.borrowingpaymentamount;
    }

    public PaymentCostList borrowingpaymentamount(BigDecimal borrowingpaymentamount) {
        this.setBorrowingpaymentamount(borrowingpaymentamount);
        return this;
    }

    public void setBorrowingpaymentamount(BigDecimal borrowingpaymentamount) {
        this.borrowingpaymentamount = borrowingpaymentamount;
    }

    public BigDecimal getAccountingamount() {
        return this.accountingamount;
    }

    public PaymentCostList accountingamount(BigDecimal accountingamount) {
        this.setAccountingamount(accountingamount);
        return this;
    }

    public void setAccountingamount(BigDecimal accountingamount) {
        this.accountingamount = accountingamount;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public PaymentCostList workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public Set<ContractPayment> getContractPayments() {
        return this.contractPayments;
    }

    public void setContractPayments(Set<ContractPayment> contractPayments) {
        if (this.contractPayments != null) {
            this.contractPayments.forEach(i -> i.removePaymentCostList(this));
        }
        if (contractPayments != null) {
            contractPayments.forEach(i -> i.addPaymentCostList(this));
        }
        this.contractPayments = contractPayments;
    }

    public PaymentCostList contractPayments(Set<ContractPayment> contractPayments) {
        this.setContractPayments(contractPayments);
        return this;
    }

    public PaymentCostList addContractPayment(ContractPayment contractPayment) {
        this.contractPayments.add(contractPayment);
        contractPayment.getPaymentCostLists().add(this);
        return this;
    }

    public PaymentCostList removeContractPayment(ContractPayment contractPayment) {
        this.contractPayments.remove(contractPayment);
        contractPayment.getPaymentCostLists().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentCostList)) {
            return false;
        }
        return getId() != null && getId().equals(((PaymentCostList) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentCostList{" +
            "id=" + getId() +
            ", wbsid='" + getWbsid() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            ", parentwbsid='" + getParentwbsid() + "'" +
            ", unit='" + getUnit() + "'" +
            ", unitprice=" + getUnitprice() +
            ", number=" + getNumber() +
            ", subjectid=" + getSubjectid() +
            ", subjectname='" + getSubjectname() + "'" +
            ", invoicepaymentamount=" + getInvoicepaymentamount() +
            ", borrowingpaymentamount=" + getBorrowingpaymentamount() +
            ", accountingamount=" + getAccountingamount() +
            "}";
    }
}
