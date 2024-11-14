package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PaymentApplication.
 */
@Entity
@Table(name = "payment_application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "workbagname")
    private String workbagname;

    @Column(name = "outsourcingcontractid")
    private String outsourcingcontractid;

    @Column(name = "outsourcingcontractname")
    private String outsourcingcontractname;

    @Column(name = "planpaymentnode")
    private String planpaymentnode;

    @Column(name = "planpaymentname")
    private String planpaymentname;

    @Column(name = "planpaymentamount", precision = 21, scale = 2)
    private BigDecimal planpaymentamount;

    @Column(name = "contractpaymentid")
    private Integer contractpaymentid;

    @Column(name = "status")
    private String status;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public PaymentApplication id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkbagname() {
        return this.workbagname;
    }

    public PaymentApplication workbagname(String workbagname) {
        this.setWorkbagname(workbagname);
        return this;
    }

    public void setWorkbagname(String workbagname) {
        this.workbagname = workbagname;
    }

    public String getOutsourcingcontractid() {
        return this.outsourcingcontractid;
    }

    public PaymentApplication outsourcingcontractid(String outsourcingcontractid) {
        this.setOutsourcingcontractid(outsourcingcontractid);
        return this;
    }

    public void setOutsourcingcontractid(String outsourcingcontractid) {
        this.outsourcingcontractid = outsourcingcontractid;
    }

    public String getOutsourcingcontractname() {
        return this.outsourcingcontractname;
    }

    public PaymentApplication outsourcingcontractname(String outsourcingcontractname) {
        this.setOutsourcingcontractname(outsourcingcontractname);
        return this;
    }

    public void setOutsourcingcontractname(String outsourcingcontractname) {
        this.outsourcingcontractname = outsourcingcontractname;
    }

    public String getPlanpaymentnode() {
        return this.planpaymentnode;
    }

    public PaymentApplication planpaymentnode(String planpaymentnode) {
        this.setPlanpaymentnode(planpaymentnode);
        return this;
    }

    public void setPlanpaymentnode(String planpaymentnode) {
        this.planpaymentnode = planpaymentnode;
    }

    public String getPlanpaymentname() {
        return this.planpaymentname;
    }

    public PaymentApplication planpaymentname(String planpaymentname) {
        this.setPlanpaymentname(planpaymentname);
        return this;
    }

    public void setPlanpaymentname(String planpaymentname) {
        this.planpaymentname = planpaymentname;
    }

    public BigDecimal getPlanpaymentamount() {
        return this.planpaymentamount;
    }

    public PaymentApplication planpaymentamount(BigDecimal planpaymentamount) {
        this.setPlanpaymentamount(planpaymentamount);
        return this;
    }

    public void setPlanpaymentamount(BigDecimal planpaymentamount) {
        this.planpaymentamount = planpaymentamount;
    }

    public Integer getContractpaymentid() {
        return this.contractpaymentid;
    }

    public PaymentApplication contractpaymentid(Integer contractpaymentid) {
        this.setContractpaymentid(contractpaymentid);
        return this;
    }

    public void setContractpaymentid(Integer contractpaymentid) {
        this.contractpaymentid = contractpaymentid;
    }

    public String getStatus() {
        return this.status;
    }

    public PaymentApplication status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public PaymentApplication workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentApplication)) {
            return false;
        }
        return getId() != null && getId().equals(((PaymentApplication) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentApplication{" +
            "id=" + getId() +
            ", workbagname='" + getWorkbagname() + "'" +
            ", outsourcingcontractid='" + getOutsourcingcontractid() + "'" +
            ", outsourcingcontractname='" + getOutsourcingcontractname() + "'" +
            ", planpaymentnode='" + getPlanpaymentnode() + "'" +
            ", planpaymentname='" + getPlanpaymentname() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            ", contractpaymentid=" + getContractpaymentid() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
