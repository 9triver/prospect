package com.cvicse.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Comprehensiveledger.
 */
@Entity
@Table(name = "comprehensiveledger")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Comprehensiveledger implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fundsname")
    private String fundsname;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "unitname")
    private String unitname;

    @Column(name = "budgetsection")
    private String budgetsection;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "unit")
    private String unit;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "unitprice", precision = 21, scale = 2)
    private BigDecimal unitprice;

    @Column(name = "foreignexchange", precision = 21, scale = 2)
    private BigDecimal foreignexchange;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Comprehensiveledger id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundsname() {
        return this.fundsname;
    }

    public Comprehensiveledger fundsname(String fundsname) {
        this.setFundsname(fundsname);
        return this;
    }

    public void setFundsname(String fundsname) {
        this.fundsname = fundsname;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public Comprehensiveledger wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getUnitname() {
        return this.unitname;
    }

    public Comprehensiveledger unitname(String unitname) {
        this.setUnitname(unitname);
        return this;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getBudgetsection() {
        return this.budgetsection;
    }

    public Comprehensiveledger budgetsection(String budgetsection) {
        this.setBudgetsection(budgetsection);
        return this;
    }

    public void setBudgetsection(String budgetsection) {
        this.budgetsection = budgetsection;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public Comprehensiveledger purpose(String purpose) {
        this.setPurpose(purpose);
        return this;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUnit() {
        return this.unit;
    }

    public Comprehensiveledger unit(String unit) {
        this.setUnit(unit);
        return this;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Comprehensiveledger number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getUnitprice() {
        return this.unitprice;
    }

    public Comprehensiveledger unitprice(BigDecimal unitprice) {
        this.setUnitprice(unitprice);
        return this;
    }

    public void setUnitprice(BigDecimal unitprice) {
        this.unitprice = unitprice;
    }

    public BigDecimal getForeignexchange() {
        return this.foreignexchange;
    }

    public Comprehensiveledger foreignexchange(BigDecimal foreignexchange) {
        this.setForeignexchange(foreignexchange);
        return this;
    }

    public void setForeignexchange(BigDecimal foreignexchange) {
        this.foreignexchange = foreignexchange;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comprehensiveledger)) {
            return false;
        }
        return getId() != null && getId().equals(((Comprehensiveledger) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comprehensiveledger{" +
            "id=" + getId() +
            ", fundsname='" + getFundsname() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            ", unitname='" + getUnitname() + "'" +
            ", budgetsection='" + getBudgetsection() + "'" +
            ", purpose='" + getPurpose() + "'" +
            ", unit='" + getUnit() + "'" +
            ", number=" + getNumber() +
            ", unitprice=" + getUnitprice() +
            ", foreignexchange=" + getForeignexchange() +
            "}";
    }
}
