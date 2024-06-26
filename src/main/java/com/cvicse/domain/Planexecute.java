package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Planexecute.
 */
@Entity
@Table(name = "planexecute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Planexecute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "planname")
    private String planname;

    @Column(name = "planstarttime")
    private LocalDate planstarttime;

    @Column(name = "planendtime")
    private LocalDate planendtime;

    @JsonIgnoreProperties(value = { "planexecute", "progressplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Planreturns planreturns;

    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Officers responsibleid;

    @JsonIgnoreProperties(
        value = { "document", "planreturns", "projectcharge", "creatorid", "auditorid", "cycleplan", "annualplan" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "planreturns")
    private Monthplan monthplan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Planexecute id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanname() {
        return this.planname;
    }

    public Planexecute planname(String planname) {
        this.setPlanname(planname);
        return this;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public LocalDate getPlanstarttime() {
        return this.planstarttime;
    }

    public Planexecute planstarttime(LocalDate planstarttime) {
        this.setPlanstarttime(planstarttime);
        return this;
    }

    public void setPlanstarttime(LocalDate planstarttime) {
        this.planstarttime = planstarttime;
    }

    public LocalDate getPlanendtime() {
        return this.planendtime;
    }

    public Planexecute planendtime(LocalDate planendtime) {
        this.setPlanendtime(planendtime);
        return this;
    }

    public void setPlanendtime(LocalDate planendtime) {
        this.planendtime = planendtime;
    }

    public Planreturns getPlanreturns() {
        return this.planreturns;
    }

    public void setPlanreturns(Planreturns planreturns) {
        this.planreturns = planreturns;
    }

    public Planexecute planreturns(Planreturns planreturns) {
        this.setPlanreturns(planreturns);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Planexecute responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        if (this.monthplan != null) {
            this.monthplan.setPlanreturns(null);
        }
        if (monthplan != null) {
            monthplan.setPlanreturns(this);
        }
        this.monthplan = monthplan;
    }

    public Planexecute monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planexecute)) {
            return false;
        }
        return getId() != null && getId().equals(((Planexecute) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Planexecute{" +
            "id=" + getId() +
            ", planname='" + getPlanname() + "'" +
            ", planstarttime='" + getPlanstarttime() + "'" +
            ", planendtime='" + getPlanendtime() + "'" +
            "}";
    }
}
