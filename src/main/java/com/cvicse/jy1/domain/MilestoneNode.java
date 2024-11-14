package com.cvicse.jy1.domain;

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
 * A MilestoneNode.
 */
@Entity
@Table(name = "milestone_node")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class MilestoneNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "outsourcingcontractid")
    private String outsourcingcontractid;

    @Column(name = "outsourcingcontractname")
    private String outsourcingcontractname;

    @Column(name = "name")
    private String name;

    @Column(name = "planpaymenttime")
    private LocalDate planpaymenttime;

    @Column(name = "planpaymentamount", precision = 21, scale = 2)
    private BigDecimal planpaymentamount;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "milestoneNodes")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workbag", "milestoneNodes" }, allowSetters = true)
    private Set<OutsourcingContract> outsourcingContracts = new HashSet<>();

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

    public String getOutsourcingcontractid() {
        return this.outsourcingcontractid;
    }

    public MilestoneNode outsourcingcontractid(String outsourcingcontractid) {
        this.setOutsourcingcontractid(outsourcingcontractid);
        return this;
    }

    public void setOutsourcingcontractid(String outsourcingcontractid) {
        this.outsourcingcontractid = outsourcingcontractid;
    }

    public String getOutsourcingcontractname() {
        return this.outsourcingcontractname;
    }

    public MilestoneNode outsourcingcontractname(String outsourcingcontractname) {
        this.setOutsourcingcontractname(outsourcingcontractname);
        return this;
    }

    public void setOutsourcingcontractname(String outsourcingcontractname) {
        this.outsourcingcontractname = outsourcingcontractname;
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

    public Set<OutsourcingContract> getOutsourcingContracts() {
        return this.outsourcingContracts;
    }

    public void setOutsourcingContracts(Set<OutsourcingContract> outsourcingContracts) {
        if (this.outsourcingContracts != null) {
            this.outsourcingContracts.forEach(i -> i.removeMilestoneNode(this));
        }
        if (outsourcingContracts != null) {
            outsourcingContracts.forEach(i -> i.addMilestoneNode(this));
        }
        this.outsourcingContracts = outsourcingContracts;
    }

    public MilestoneNode outsourcingContracts(Set<OutsourcingContract> outsourcingContracts) {
        this.setOutsourcingContracts(outsourcingContracts);
        return this;
    }

    public MilestoneNode addOutsourcingContract(OutsourcingContract outsourcingContract) {
        this.outsourcingContracts.add(outsourcingContract);
        outsourcingContract.getMilestoneNodes().add(this);
        return this;
    }

    public MilestoneNode removeOutsourcingContract(OutsourcingContract outsourcingContract) {
        this.outsourcingContracts.remove(outsourcingContract);
        outsourcingContract.getMilestoneNodes().remove(this);
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
            ", outsourcingcontractid='" + getOutsourcingcontractid() + "'" +
            ", outsourcingcontractname='" + getOutsourcingcontractname() + "'" +
            ", name='" + getName() + "'" +
            ", planpaymenttime='" + getPlanpaymenttime() + "'" +
            ", planpaymentamount=" + getPlanpaymentamount() +
            "}";
    }
}
