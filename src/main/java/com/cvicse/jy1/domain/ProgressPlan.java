package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.Progressstatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProgressPlan.
 */
@Entity
@Table(name = "progress_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProgressPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "planname")
    private String planname;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "plantype")
    private Integer plantype;

    @Enumerated(EnumType.STRING)
    @Column(name = "planlevel")
    private PlanLevel planlevel;

    @Column(name = "belongplanid")
    private String belongplanid;

    @Column(name = "planstage")
    private String planstage;

    @Column(name = "readytime")
    private LocalDate readytime;

    @Column(name = "description")
    private String description;

    @Column(name = "deliverables")
    private String deliverables;

    @Column(name = "planobjectives")
    private String planobjectives;

    @Column(name = "preplan")
    private String preplan;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "actualstarttime")
    private LocalDate actualstarttime;

    @Column(name = "actualendtime")
    private LocalDate actualendtime;

    @Column(name = "progress")
    private Integer progress;

    @Enumerated(EnumType.STRING)
    @Column(name = "progresstype")
    private Progressstatus progresstype;

    @Column(name = "iskey")
    private Integer iskey;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Planstatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "remark")
    private String remark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers cooperatingperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department cooperatingdepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid" }, allowSetters = true)
    private PlanReturns planReturns;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_progress_plan__projectwbs",
        joinColumns = @JoinColumn(name = "progress_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_progress_plan__project_risk",
        joinColumns = @JoinColumn(name = "progress_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "project_risk_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = { "riskReport", "creatorid", "responsibleperson", "auditorid", "projectwbs", "progressPlans" },
        allowSetters = true
    )
    private Set<ProjectRisk> projectRisks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ProgressPlan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanname() {
        return this.planname;
    }

    public ProgressPlan planname(String planname) {
        this.setPlanname(planname);
        return this;
    }

    public void setPlanname(String planname) {
        this.planname = planname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public ProgressPlan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Integer getPlantype() {
        return this.plantype;
    }

    public ProgressPlan plantype(Integer plantype) {
        this.setPlantype(plantype);
        return this;
    }

    public void setPlantype(Integer plantype) {
        this.plantype = plantype;
    }

    public PlanLevel getPlanlevel() {
        return this.planlevel;
    }

    public ProgressPlan planlevel(PlanLevel planlevel) {
        this.setPlanlevel(planlevel);
        return this;
    }

    public void setPlanlevel(PlanLevel planlevel) {
        this.planlevel = planlevel;
    }

    public String getBelongplanid() {
        return this.belongplanid;
    }

    public ProgressPlan belongplanid(String belongplanid) {
        this.setBelongplanid(belongplanid);
        return this;
    }

    public void setBelongplanid(String belongplanid) {
        this.belongplanid = belongplanid;
    }

    public String getPlanstage() {
        return this.planstage;
    }

    public ProgressPlan planstage(String planstage) {
        this.setPlanstage(planstage);
        return this;
    }

    public void setPlanstage(String planstage) {
        this.planstage = planstage;
    }

    public LocalDate getReadytime() {
        return this.readytime;
    }

    public ProgressPlan readytime(LocalDate readytime) {
        this.setReadytime(readytime);
        return this;
    }

    public void setReadytime(LocalDate readytime) {
        this.readytime = readytime;
    }

    public String getDescription() {
        return this.description;
    }

    public ProgressPlan description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeliverables() {
        return this.deliverables;
    }

    public ProgressPlan deliverables(String deliverables) {
        this.setDeliverables(deliverables);
        return this;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public String getPlanobjectives() {
        return this.planobjectives;
    }

    public ProgressPlan planobjectives(String planobjectives) {
        this.setPlanobjectives(planobjectives);
        return this;
    }

    public void setPlanobjectives(String planobjectives) {
        this.planobjectives = planobjectives;
    }

    public String getPreplan() {
        return this.preplan;
    }

    public ProgressPlan preplan(String preplan) {
        this.setPreplan(preplan);
        return this;
    }

    public void setPreplan(String preplan) {
        this.preplan = preplan;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public ProgressPlan starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public ProgressPlan endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public LocalDate getActualstarttime() {
        return this.actualstarttime;
    }

    public ProgressPlan actualstarttime(LocalDate actualstarttime) {
        this.setActualstarttime(actualstarttime);
        return this;
    }

    public void setActualstarttime(LocalDate actualstarttime) {
        this.actualstarttime = actualstarttime;
    }

    public LocalDate getActualendtime() {
        return this.actualendtime;
    }

    public ProgressPlan actualendtime(LocalDate actualendtime) {
        this.setActualendtime(actualendtime);
        return this;
    }

    public void setActualendtime(LocalDate actualendtime) {
        this.actualendtime = actualendtime;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public ProgressPlan progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Progressstatus getProgresstype() {
        return this.progresstype;
    }

    public ProgressPlan progresstype(Progressstatus progresstype) {
        this.setProgresstype(progresstype);
        return this;
    }

    public void setProgresstype(Progressstatus progresstype) {
        this.progresstype = progresstype;
    }

    public Integer getIskey() {
        return this.iskey;
    }

    public ProgressPlan iskey(Integer iskey) {
        this.setIskey(iskey);
        return this;
    }

    public void setIskey(Integer iskey) {
        this.iskey = iskey;
    }

    public Planstatus getStatus() {
        return this.status;
    }

    public ProgressPlan status(Planstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Planstatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public ProgressPlan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRemark() {
        return this.remark;
    }

    public ProgressPlan remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public ProgressPlan responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getCooperatingperson() {
        return this.cooperatingperson;
    }

    public void setCooperatingperson(Officers officers) {
        this.cooperatingperson = officers;
    }

    public ProgressPlan cooperatingperson(Officers officers) {
        this.setCooperatingperson(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public ProgressPlan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Department getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public void setResponsibledepartment(Department department) {
        this.responsibledepartment = department;
    }

    public ProgressPlan responsibledepartment(Department department) {
        this.setResponsibledepartment(department);
        return this;
    }

    public Department getCooperatingdepartment() {
        return this.cooperatingdepartment;
    }

    public void setCooperatingdepartment(Department department) {
        this.cooperatingdepartment = department;
    }

    public ProgressPlan cooperatingdepartment(Department department) {
        this.setCooperatingdepartment(department);
        return this;
    }

    public PlanReturns getPlanReturns() {
        return this.planReturns;
    }

    public void setPlanReturns(PlanReturns planReturns) {
        this.planReturns = planReturns;
    }

    public ProgressPlan planReturns(PlanReturns planReturns) {
        this.setPlanReturns(planReturns);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public ProgressPlan projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public ProgressPlan addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public ProgressPlan removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    public Set<ProjectRisk> getProjectRisks() {
        return this.projectRisks;
    }

    public void setProjectRisks(Set<ProjectRisk> projectRisks) {
        this.projectRisks = projectRisks;
    }

    public ProgressPlan projectRisks(Set<ProjectRisk> projectRisks) {
        this.setProjectRisks(projectRisks);
        return this;
    }

    public ProgressPlan addProjectRisk(ProjectRisk projectRisk) {
        this.projectRisks.add(projectRisk);
        return this;
    }

    public ProgressPlan removeProjectRisk(ProjectRisk projectRisk) {
        this.projectRisks.remove(projectRisk);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProgressPlan)) {
            return false;
        }
        return getId() != null && getId().equals(((ProgressPlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProgressPlan{" +
            "id=" + getId() +
            ", planname='" + getPlanname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", plantype=" + getPlantype() +
            ", belongplanid='" + getBelongplanid() + "'" +
            ", planlevel='" + getPlanlevel() + "'" +
            ", planstage='" + getPlanstage() + "'" +
            ", readytime='" + getReadytime() + "'" +
            ", description='" + getDescription() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            ", planobjectives='" + getPlanobjectives() + "'" +
            ", preplan='" + getPreplan() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", actualstarttime='" + getActualstarttime() + "'" +
            ", actualendtime='" + getActualendtime() + "'" +
            ", progress=" + getProgress() +
            ", progresstype='" + getProgresstype() + "'" +
            ", iskey=" + getIskey() +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
