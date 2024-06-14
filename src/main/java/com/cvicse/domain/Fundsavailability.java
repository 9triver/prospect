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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fundsavailabilityid")
    private Long fundsavailabilityid;

    @Column(name = "fundsid")
    private Long fundsid;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "budgit", precision = 21, scale = 2)
    private BigDecimal budgit;

    @Column(name = "funding", precision = 21, scale = 2)
    private BigDecimal funding;

    @JsonIgnoreProperties(
        value = {
            "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "project", "comprehensivecontrol", "fundsavailability",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Fundsmanagement fundsmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fundsavailability id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFundsavailabilityid() {
        return this.fundsavailabilityid;
    }

    public Fundsavailability fundsavailabilityid(Long fundsavailabilityid) {
        this.setFundsavailabilityid(fundsavailabilityid);
        return this;
    }

    public void setFundsavailabilityid(Long fundsavailabilityid) {
        this.fundsavailabilityid = fundsavailabilityid;
    }

    public Long getFundsid() {
        return this.fundsid;
    }

    public Fundsavailability fundsid(Long fundsid) {
        this.setFundsid(fundsid);
        return this;
    }

    public void setFundsid(Long fundsid) {
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

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        this.fundsmanagement = fundsmanagement;
    }

    public Fundsavailability fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
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
            ", fundsavailabilityid=" + getFundsavailabilityid() +
            ", fundsid=" + getFundsid() +
            ", year=" + getYear() +
            ", budgit=" + getBudgit() +
            ", funding=" + getFunding() +
            "}";
    }
}
