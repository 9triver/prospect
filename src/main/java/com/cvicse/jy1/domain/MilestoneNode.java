package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A MilestoneNode.
 */
@Entity
@Table(name = "milestone_node")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MilestoneNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "planpaymenttime")
    private LocalDate planpaymenttime;

    @Column(name = "planpaymentamount", precision = 21, scale = 2)
    private BigDecimal planpaymentamount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "workbag", "deliveryContents", "milestoneNodes", "paymentApplications" }, allowSetters = true)
    private OutsourcingContract outsourcingContract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public MilestoneNode id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public MilestoneNode name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getPlanpaymenttime() {
        return this.planpaymenttime;
    }

    public MilestoneNode planpaymenttime(LocalDate planpaymenttime) {
        this.setPlanpaymenttime(planpaymenttime);
        return this;
    }

    public void setPlanpaymenttime(LocalDate planpaymenttime) {
        this.planpaymenttime = planpaymenttime;
    }

    public BigDecimal getPlanpaymentamount() {
        return this.planpaymentamount;
    }

    public MilestoneNode planpaymentamount(BigDecimal planpaymentamount) {
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

    public MilestoneNode outsourcingContract(OutsourcingContract outsourcingContract) {
        this.setOutsourcingContract(outsourcingContract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MilestoneNode)) {
            return false;
        }
        return getId() != null && getId().equals(((MilestoneNode) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MilestoneNode{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", planpaymenttime='" + getPlanpaymenttime() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            "}";
    }
}
