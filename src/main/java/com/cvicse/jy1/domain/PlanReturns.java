package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.ReturnsStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PlanReturns.
 */
@Entity
@Table(name = "plan_returns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PlanReturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "planreturnsname")
    private String planreturnsname;

    @Column(name = "plantype")
    private Integer plantype;

    @Enumerated(EnumType.STRING)
    @Column(name = "planlevel")
    private PlanLevel planlevel;

    @Column(name = "description")
    private String description;

    @Column(name = "actualstarttime")
    private LocalDate actualstarttime;

    @Column(name = "actualendtime")
    private LocalDate actualendtime;

    @Column(name = "deliverables")
    private String deliverables;

    @Column(name = "progress")
    private Integer progress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Planstatus status;

    @Column(name = "impactanalysis")
    private String impactanalysis;

    @Column(name = "returnstime")
    private LocalDate returnstime;

    @Column(name = "rejectionreason")
    private String rejectionreason;

    @Enumerated(EnumType.STRING)
    @Column(name = "returnsstatus")
    private ReturnsStatus returnsstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "planReturns",
            "responsibleperson",
            "cooperatingperson",
            "auditorid",
            "responsibledepartment",
            "cooperatingdepartment",
            "projectwbs",
            "projectRisks",
            "riskReturn",
        },
        allowSetters = true
    )
    private ProgressPlan progressPlan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public PlanReturns id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanreturnsname() {
        return this.planreturnsname;
    }

    public PlanReturns planreturnsname(String planreturnsname) {
        this.setPlanreturnsname(planreturnsname);
        return this;
    }

    public void setPlanreturnsname(String planreturnsname) {
        this.planreturnsname = planreturnsname;
    }

    public Integer getPlantype() {
        return this.plantype;
    }

    public PlanReturns plantype(Integer plantype) {
        this.setPlantype(plantype);
        return this;
    }

    public void setPlantype(Integer plantype) {
        this.plantype = plantype;
    }

    public PlanLevel getPlanlevel() {
        return this.planlevel;
    }

    public PlanReturns planlevel(PlanLevel planlevel) {
        this.setPlanlevel(planlevel);
        return this;
    }

    public void setPlanlevel(PlanLevel planlevel) {
        this.planlevel = planlevel;
    }

    public String getDescription() {
        return this.description;
    }

    public PlanReturns description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getActualstarttime() {
        return this.actualstarttime;
    }

    public PlanReturns actualstarttime(LocalDate actualstarttime) {
        this.setActualstarttime(actualstarttime);
        return this;
    }

    public void setActualstarttime(LocalDate actualstarttime) {
        this.actualstarttime = actualstarttime;
    }

    public LocalDate getActualendtime() {
        return this.actualendtime;
    }

    public PlanReturns actualendtime(LocalDate actualendtime) {
        this.setActualendtime(actualendtime);
        return this;
    }

    public void setActualendtime(LocalDate actualendtime) {
        this.actualendtime = actualendtime;
    }

    public String getDeliverables() {
        return this.deliverables;
    }

    public PlanReturns deliverables(String deliverables) {
        this.setDeliverables(deliverables);
        return this;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public PlanReturns progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Planstatus getStatus() {
        return this.status;
    }

    public PlanReturns status(Planstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Planstatus status) {
        this.status = status;
    }

    public String getImpactanalysis() {
        return this.impactanalysis;
    }

    public PlanReturns impactanalysis(String impactanalysis) {
        this.setImpactanalysis(impactanalysis);
        return this;
    }

    public void setImpactanalysis(String impactanalysis) {
        this.impactanalysis = impactanalysis;
    }

    public LocalDate getReturnstime() {
        return this.returnstime;
    }

    public PlanReturns returnstime(LocalDate returnstime) {
        this.setReturnstime(returnstime);
        return this;
    }

    public void setReturnstime(LocalDate returnstime) {
        this.returnstime = returnstime;
    }

    public String getRejectionreason() {
        return this.rejectionreason;
    }

    public PlanReturns rejectionreason(String rejectionreason) {
        this.setRejectionreason(rejectionreason);
        return this;
    }

    public void setRejectionreason(String rejectionreason) {
        this.rejectionreason = rejectionreason;
    }

    public ReturnsStatus getReturnsstatus() {
        return this.returnsstatus;
    }

    public PlanReturns returnsstatus(ReturnsStatus returnsstatus) {
        this.setReturnsstatus(returnsstatus);
        return this;
    }

    public void setReturnsstatus(ReturnsStatus returnsstatus) {
        this.returnsstatus = returnsstatus;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public PlanReturns responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public PlanReturns auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
        return this;
    }

    public ProgressPlan getProgressPlan() {
        return this.progressPlan;
    }

    public void setProgressPlan(ProgressPlan progressPlan) {
        this.progressPlan = progressPlan;
    }

    public PlanReturns progressPlan(ProgressPlan progressPlan) {
        this.setProgressPlan(progressPlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlanReturns)) {
            return false;
        }
        return getId() != null && getId().equals(((PlanReturns) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PlanReturns{" +
            "id=" + getId() +
            ", planreturnsname='" + getPlanreturnsname() + "'" +
            ", plantype=" + getPlantype() +
            ", planlevel='" + getPlanlevel() + "'" +
            ", description='" + getDescription() + "'" +
            ", actualstarttime='" + getActualstarttime() + "'" +
            ", actualendtime='" + getActualendtime() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            ", progress=" + getProgress() +
            ", status='" + getStatus() + "'" +
            ", impactanalysis='" + getImpactanalysis() + "'" +
            ", returnstime='" + getReturnstime() + "'" +
            ", rejectionreason='" + getRejectionreason() + "'" +
            ", returnsstatus='" + getReturnsstatus() + "'" +
            "}";
    }
}
