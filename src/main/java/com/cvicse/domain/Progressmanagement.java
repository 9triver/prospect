package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Progressstatus;
import com.cvicse.domain.enumeration.Progresstype;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Progressmanagement.
 */
@Entity
@Table(name = "progressmanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Progressmanagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "progressid", unique = true)
    private Long progressid;

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

    @Column(name = "baselineid")
    private Long baselineid;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = { "officers", "project", "planstrategy", "progressmanagement", "evaluationCriteria" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(value = { "planexecute", "progressmanagement" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Planreturns planreturns;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(
        value = {
            "cycleplan",
            "progressmanagement",
            "qualitymanagement",
            "fundsmanagement",
            "technicalCondition",
            "contractualfunds",
            "outsourcingmPurchaseExecute",
            "resourcemanagement",
            "riskmanagement",
            "document",
            "safetycheck",
            "department",
            "evaluationCriteria",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingmPurchasePlan",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progressmanagement")
    private Project project;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "progress")
    private Comprehensivecontrol comprehensivecontrol;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Progressmanagement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgressid() {
        return this.progressid;
    }

    public Progressmanagement progressid(Long progressid) {
        this.setProgressid(progressid);
        return this;
    }

    public void setProgressid(Long progressid) {
        this.progressid = progressid;
    }

    public String getProgressname() {
        return this.progressname;
    }

    public Progressmanagement progressname(String progressname) {
        this.setProgressname(progressname);
        return this;
    }

    public void setProgressname(String progressname) {
        this.progressname = progressname;
    }

    public Progresstype getProgresstype() {
        return this.progresstype;
    }

    public Progressmanagement progresstype(Progresstype progresstype) {
        this.setProgresstype(progresstype);
        return this;
    }

    public void setProgresstype(Progresstype progresstype) {
        this.progresstype = progresstype;
    }

    public String getWorkfocus() {
        return this.workfocus;
    }

    public Progressmanagement workfocus(String workfocus) {
        this.setWorkfocus(workfocus);
        return this;
    }

    public void setWorkfocus(String workfocus) {
        this.workfocus = workfocus;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Progressmanagement createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Progressmanagement creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Progressmanagement responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public Progressstatus getStatus() {
        return this.status;
    }

    public Progressmanagement status(Progressstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Progressstatus status) {
        this.status = status;
    }

    public Long getBaselineid() {
        return this.baselineid;
    }

    public Progressmanagement baselineid(Long baselineid) {
        this.setBaselineid(baselineid);
        return this;
    }

    public void setBaselineid(Long baselineid) {
        this.baselineid = baselineid;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Progressmanagement auditStatus(AuditStatus auditStatus) {
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

    public Progressmanagement department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Planreturns getPlanreturns() {
        return this.planreturns;
    }

    public void setPlanreturns(Planreturns planreturns) {
        this.planreturns = planreturns;
    }

    public Progressmanagement planreturns(Planreturns planreturns) {
        this.setPlanreturns(planreturns);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Progressmanagement responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Progressmanagement creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Progressmanagement auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setProgressmanagement(null);
        }
        if (project != null) {
            project.setProgressmanagement(this);
        }
        this.project = project;
    }

    public Progressmanagement project(Project project) {
        this.setProject(project);
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

    public Progressmanagement comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Progressmanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Progressmanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Progressmanagement{" +
            "id=" + getId() +
            ", progressid=" + getProgressid() +
            ", progressname='" + getProgressname() + "'" +
            ", progresstype='" + getProgresstype() + "'" +
            ", workfocus='" + getWorkfocus() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", status='" + getStatus() + "'" +
            ", baselineid=" + getBaselineid() +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
