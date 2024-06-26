package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fundsavailability.
 */
@Entity
@Table(name = "fundsavailability")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fundsavailability implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "fundsid")
    private String fundsid;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "budgit", precision = 21, scale = 2)
    private BigDecimal budgit;

    @Column(name = "funding", precision = 21, scale = 2)
    private BigDecimal funding;

    @JsonIgnoreProperties(
        value = { "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "fundsavailability" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Auditedbudget auditedbudget;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Fundsavailability id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFundsid() {
        return this.fundsid;
    }

    public Fundsavailability fundsid(String fundsid) {
        this.setFundsid(fundsid);
        return this;
    }

    public void setFundsid(String fundsid) {
        this.fundsid = fundsid;
    }

    public Long getYear() {
        return this.year;
    }

    public Fundsavailability year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public Fundsavailability budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public BigDecimal getFunding() {
        return this.funding;
    }

    public Fundsavailability funding(BigDecimal funding) {
        this.setFunding(funding);
        return this;
    }

    public void setFunding(BigDecimal funding) {
        this.funding = funding;
    }

    public Auditedbudget getAuditedbudget() {
        return this.auditedbudget;
    }

    public void setAuditedbudget(Auditedbudget auditedbudget) {
        this.auditedbudget = auditedbudget;
    }

    public Fundsavailability auditedbudget(Auditedbudget auditedbudget) {
        this.setAuditedbudget(auditedbudget);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fundsavailability)) {
            return false;
        }
        return getId() != null && getId().equals(((Fundsavailability) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fundsavailability{" +
            "id=" + getId() +
            ", fundsid='" + getFundsid() + "'" +
            ", year=" + getYear() +
            ", budgit=" + getBudgit() +
            ", funding=" + getFunding() +
            "}";
    }
}
