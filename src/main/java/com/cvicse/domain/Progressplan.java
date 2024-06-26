package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Progressstatus;
import com.cvicse.domain.enumeration.Progresstype;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Progressplan.
 */
@Entity
@Table(name = "progressplan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Progressplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "progressname")
    private String progressname;

    @Enumerated(EnumType.STRING)
    @Column(name = "progresstype")
    private Progresstype progresstype;

    @Column(name = "workfocus")
    private String workfocus;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Progressstatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(value = { "planexecute", "progressplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Planreturns planreturns;

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
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    private Comprehensivecontrol comprehensivecontrol;

    @JsonIgnoreProperties(value = { "progressplan", "document" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progressplan")
    private Progressplanreturns progressplanreturns;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Progressplan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProgressname() {
        return this.progressname;
    }

    public Progressplan progressname(String progressname) {
        this.setProgressname(progressname);
        return this;
    }

    public void setProgressname(String progressname) {
        this.progressname = progressname;
    }

    public Progresstype getProgresstype() {
        return this.progresstype;
    }

    public Progressplan progresstype(Progresstype progresstype) {
        this.setProgresstype(progresstype);
        return this;
    }

    public void setProgresstype(Progresstype progresstype) {
        this.progresstype = progresstype;
    }

    public String getWorkfocus() {
        return this.workfocus;
    }

    public Progressplan workfocus(String workfocus) {
        this.setWorkfocus(workfocus);
        return this;
    }

    public void setWorkfocus(String workfocus) {
        this.workfocus = workfocus;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Progressplan createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Progressplan creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Progressplan responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public Progressstatus getStatus() {
        return this.status;
    }

    public Progressplan status(Progressstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Progressstatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Progressplan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Progressplan department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Planreturns getPlanreturns() {
        return this.planreturns;
    }

    public void setPlanreturns(Planreturns planreturns) {
        this.planreturns = planreturns;
    }

    public Progressplan planreturns(Planreturns planreturns) {
        this.setPlanreturns(planreturns);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Progressplan responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Progressplan creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Progressplan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setProgress(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setProgress(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Progressplan comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Progressplanreturns getProgressplanreturns() {
        return this.progressplanreturns;
    }

    public void setProgressplanreturns(Progressplanreturns progressplanreturns) {
        if (this.progressplanreturns != null) {
            this.progressplanreturns.setProgressplan(null);
        }
        if (progressplanreturns != null) {
            progressplanreturns.setProgressplan(this);
        }
        this.progressplanreturns = progressplanreturns;
    }

    public Progressplan progressplanreturns(Progressplanreturns progressplanreturns) {
        this.setProgressplanreturns(progressplanreturns);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Progressplan)) {
            return false;
        }
        return getId() != null && getId().equals(((Progressplan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Progressplan{" +
            "id=" + getId() +
            ", progressname='" + getProgressname() + "'" +
            ", progresstype='" + getProgresstype() + "'" +
            ", workfocus='" + getWorkfocus() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
