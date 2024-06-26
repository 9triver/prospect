package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Planstrategy.
 */
@Entity
@Table(name = "planstrategy")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Planstrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "strategyname")
    private String strategyname;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "jhi_type")
    private String type;

    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department decument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Planstrategy id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrategyname() {
        return this.strategyname;
    }

    public Planstrategy strategyname(String strategyname) {
        this.setStrategyname(strategyname);
        return this;
    }

    public void setStrategyname(String strategyname) {
        this.strategyname = strategyname;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Planstrategy number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public Planstrategy type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Department getDecument() {
        return this.decument;
    }

    public void setDecument(Department department) {
        this.decument = department;
    }

    public Planstrategy decument(Department department) {
        this.setDecument(department);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Planstrategy responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Planstrategy auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planstrategy)) {
            return false;
        }
        return getId() != null && getId().equals(((Planstrategy) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Planstrategy{" +
            "id=" + getId() +
            ", strategyname='" + getStrategyname() + "'" +
            ", number=" + getNumber() +
            ", type='" + getType() + "'" +
            "}";
    }
}
