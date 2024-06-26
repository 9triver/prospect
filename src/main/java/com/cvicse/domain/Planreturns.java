package com.cvicse.domain;

import com.cvicse.domain.enumeration.ReturnsStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Planreturns.
 */
@Entity
@Table(name = "planreturns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Planreturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "planreturnsname")
    private String planreturnsname;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "plantype")
    private Integer plantype;

    @Column(name = "returnstime")
    private LocalDate returnstime;

    @Enumerated(EnumType.STRING)
    @Column(name = "returnsstatus")
    private ReturnsStatus returnsstatus;

    @JsonIgnoreProperties(value = { "planreturns", "responsibleid", "monthplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "planreturns")
    private Planexecute planexecute;

    @JsonIgnoreProperties(
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "comprehensivecontrol", "progressplanreturns" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "planreturns")
    private Progressplan progressplan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Planreturns id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanreturnsname() {
        return this.planreturnsname;
    }

    public Planreturns planreturnsname(String planreturnsname) {
        this.setPlanreturnsname(planreturnsname);
        return this;
    }

    public void setPlanreturnsname(String planreturnsname) {
        this.planreturnsname = planreturnsname;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Planreturns starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Planreturns endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getPlantype() {
        return this.plantype;
    }

    public Planreturns plantype(Integer plantype) {
        this.setPlantype(plantype);
        return this;
    }

    public void setPlantype(Integer plantype) {
        this.plantype = plantype;
    }

    public LocalDate getReturnstime() {
        return this.returnstime;
    }

    public Planreturns returnstime(LocalDate returnstime) {
        this.setReturnstime(returnstime);
        return this;
    }

    public void setReturnstime(LocalDate returnstime) {
        this.returnstime = returnstime;
    }

    public ReturnsStatus getReturnsstatus() {
        return this.returnsstatus;
    }

    public Planreturns returnsstatus(ReturnsStatus returnsstatus) {
        this.setReturnsstatus(returnsstatus);
        return this;
    }

    public void setReturnsstatus(ReturnsStatus returnsstatus) {
        this.returnsstatus = returnsstatus;
    }

    public Planexecute getPlanexecute() {
        return this.planexecute;
    }

    public void setPlanexecute(Planexecute planexecute) {
        if (this.planexecute != null) {
            this.planexecute.setPlanreturns(null);
        }
        if (planexecute != null) {
            planexecute.setPlanreturns(this);
        }
        this.planexecute = planexecute;
    }

    public Planreturns planexecute(Planexecute planexecute) {
        this.setPlanexecute(planexecute);
        return this;
    }

    public Progressplan getProgressplan() {
        return this.progressplan;
    }

    public void setProgressplan(Progressplan progressplan) {
        if (this.progressplan != null) {
            this.progressplan.setPlanreturns(null);
        }
        if (progressplan != null) {
            progressplan.setPlanreturns(this);
        }
        this.progressplan = progressplan;
    }

    public Planreturns progressplan(Progressplan progressplan) {
        this.setProgressplan(progressplan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planreturns)) {
            return false;
        }
        return getId() != null && getId().equals(((Planreturns) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Planreturns{" +
            "id=" + getId() +
            ", planreturnsname='" + getPlanreturnsname() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", plantype=" + getPlantype() +
            ", returnstime='" + getReturnstime() + "'" +
            ", returnsstatus='" + getReturnsstatus() + "'" +
            "}";
    }
}
