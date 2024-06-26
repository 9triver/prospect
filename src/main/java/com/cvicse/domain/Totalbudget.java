package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Totalbudget.
 */
@Entity
@Table(name = "totalbudget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Totalbudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "valuationsubjects")
    private String valuationsubjects;

    @Column(name = "budget", precision = 21, scale = 2)
    private BigDecimal budget;

    @Column(name = "percentage", precision = 21, scale = 2)
    private BigDecimal percentage;

    @Column(name = "remarks")
    private String remarks;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "totalbudget")
    private Comprehensivecontrol comprehensivecontrol;

    @JsonIgnoreProperties(
        value = { "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "fundsavailability" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "totalbudget")
    private Auditedbudget auditedbudget;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Totalbudget id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValuationsubjects() {
        return this.valuationsubjects;
    }

    public Totalbudget valuationsubjects(String valuationsubjects) {
        this.setValuationsubjects(valuationsubjects);
        return this;
    }

    public void setValuationsubjects(String valuationsubjects) {
        this.valuationsubjects = valuationsubjects;
    }

    public BigDecimal getBudget() {
        return this.budget;
    }

    public Totalbudget budget(BigDecimal budget) {
        this.setBudget(budget);
        return this;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getPercentage() {
        return this.percentage;
    }

    public Totalbudget percentage(BigDecimal percentage) {
        this.setPercentage(percentage);
        return this;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Totalbudget remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setTotalbudget(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setTotalbudget(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Totalbudget comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Auditedbudget getAuditedbudget() {
        return this.auditedbudget;
    }

    public void setAuditedbudget(Auditedbudget auditedbudget) {
        if (this.auditedbudget != null) {
            this.auditedbudget.setTotalbudget(null);
        }
        if (auditedbudget != null) {
            auditedbudget.setTotalbudget(this);
        }
        this.auditedbudget = auditedbudget;
    }

    public Totalbudget auditedbudget(Auditedbudget auditedbudget) {
        this.setAuditedbudget(auditedbudget);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Totalbudget)) {
            return false;
        }
        return getId() != null && getId().equals(((Totalbudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Totalbudget{" +
            "id=" + getId() +
            ", valuationsubjects='" + getValuationsubjects() + "'" +
            ", budget=" + getBudget() +
            ", percentage=" + getPercentage() +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
