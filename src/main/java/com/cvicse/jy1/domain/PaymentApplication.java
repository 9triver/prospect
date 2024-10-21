package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "planpaymentnode")
    private String planpaymentnode;

    @Column(name = "planpaymentamount", precision = 21, scale = 2)
    private BigDecimal planpaymentamount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "workbag", "deliveryContents", "milestoneNodes", "paymentApplications" }, allowSetters = true)
    private OutsourcingContract outsourcingContract;

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

    public String getWorkbagid() {
        return this.workbagid;
    }

    public PaymentApplication workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public PaymentApplication contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
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

    public OutsourcingContract getOutsourcingContract() {
        return this.outsourcingContract;
    }

    public void setOutsourcingContract(OutsourcingContract outsourcingContract) {
        this.outsourcingContract = outsourcingContract;
    }

    public PaymentApplication outsourcingContract(OutsourcingContract outsourcingContract) {
        this.setOutsourcingContract(outsourcingContract);
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
            ", workbagid='" + getWorkbagid() + "'" +
            ", contractcode='" + getContractcode() + "'" +
            ", planpaymentnode='" + getPlanpaymentnode() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            "}";
    }
}
