package com.cvicse.domain;

import com.cvicse.domain.enumeration.ReturnsStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "planreturnsid", unique = true)
    private Long planreturnsid;

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
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "project", "comprehensivecontrol" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "planreturns")
    private Progressmanagement progressmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Planreturns id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlanreturnsid() {
        return this.planreturnsid;
    }

    public Planreturns planreturnsid(Long planreturnsid) {
        this.setPlanreturnsid(planreturnsid);
        return this;
    }

    public void setPlanreturnsid(Long planreturnsid) {
        this.planreturnsid = planreturnsid;
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

    public Progressmanagement getProgressmanagement() {
        return this.progressmanagement;
    }

    public void setProgressmanagement(Progressmanagement progressmanagement) {
        if (this.progressmanagement != null) {
            this.progressmanagement.setPlanreturns(null);
        }
        if (progressmanagement != null) {
            progressmanagement.setPlanreturns(this);
        }
        this.progressmanagement = progressmanagement;
    }

    public Planreturns progressmanagement(Progressmanagement progressmanagement) {
        this.setProgressmanagement(progressmanagement);
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
            ", planreturnsid=" + getPlanreturnsid() +
            ", planreturnsname='" + getPlanreturnsname() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", plantype=" + getPlantype() +
            ", returnstime='" + getReturnstime() + "'" +
            ", returnsstatus='" + getReturnsstatus() + "'" +
            "}";
    }
}
