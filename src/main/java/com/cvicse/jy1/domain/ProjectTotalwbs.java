package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectTotalwbs.
 */
@Entity
@Table(name = "project_totalwbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectTotalwbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "parentwbsid")
    private String parentwbsid;

    @Column(name = "pbsid")
    private String pbsid;

    @Column(name = "description")
    private String description;

    @Column(name = "belongfront")
    private String belongfront;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "priorty")
    private Integer priorty;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "deliverables")
    private String deliverables;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "workbag")
    private Integer workbag;

    @JsonIgnoreProperties(
        value = {
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
            "projectTotalwbs",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectwbs projectwbs;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officers technicaldirector;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officers administrativedirector;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officers knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department responsibledepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department relevantdepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ProjectTotalwbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public ProjectTotalwbs wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getParentwbsid() {
        return this.parentwbsid;
    }

    public ProjectTotalwbs parentwbsid(String parentwbsid) {
        this.setParentwbsid(parentwbsid);
        return this;
    }

    public void setParentwbsid(String parentwbsid) {
        this.parentwbsid = parentwbsid;
    }

    public String getPbsid() {
        return this.pbsid;
    }

    public ProjectTotalwbs pbsid(String pbsid) {
        this.setPbsid(pbsid);
        return this;
    }

    public void setPbsid(String pbsid) {
        this.pbsid = pbsid;
    }

    public String getDescription() {
        return this.description;
    }

    public ProjectTotalwbs description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBelongfront() {
        return this.belongfront;
    }

    public ProjectTotalwbs belongfront(String belongfront) {
        this.setBelongfront(belongfront);
        return this;
    }

    public void setBelongfront(String belongfront) {
        this.belongfront = belongfront;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public ProjectTotalwbs starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public ProjectTotalwbs endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public ProjectTotalwbs progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getType() {
        return this.type;
    }

    public ProjectTotalwbs type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriorty() {
        return this.priorty;
    }

    public ProjectTotalwbs priorty(Integer priorty) {
        this.setPriorty(priorty);
        return this;
    }

    public void setPriorty(Integer priorty) {
        this.priorty = priorty;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public ProjectTotalwbs secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getDeliverables() {
        return this.deliverables;
    }

    public ProjectTotalwbs deliverables(String deliverables) {
        this.setDeliverables(deliverables);
        return this;
    }

    public void setDeliverables(String deliverables) {
        this.deliverables = deliverables;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public ProjectTotalwbs status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public ProjectTotalwbs auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getWorkbag() {
        return this.workbag;
    }

    public ProjectTotalwbs workbag(Integer workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public void setWorkbag(Integer workbag) {
        this.workbag = workbag;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public ProjectTotalwbs projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public ProjectTotalwbs responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public Officers getTechnicaldirector() {
        return this.technicaldirector;
    }

    public void setTechnicaldirector(Officers officers) {
        this.technicaldirector = officers;
    }

    public ProjectTotalwbs technicaldirector(Officers officers) {
        this.setTechnicaldirector(officers);
        return this;
    }

    public Officers getAdministrativedirector() {
        return this.administrativedirector;
    }

    public void setAdministrativedirector(Officers officers) {
        this.administrativedirector = officers;
    }

    public ProjectTotalwbs administrativedirector(Officers officers) {
        this.setAdministrativedirector(officers);
        return this;
    }

    public Officers getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(Officers officers) {
        this.knowingpeople = officers;
    }

    public ProjectTotalwbs knowingpeople(Officers officers) {
        this.setKnowingpeople(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public ProjectTotalwbs auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Department getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public void setResponsibledepartment(Department department) {
        this.responsibledepartment = department;
    }

    public ProjectTotalwbs responsibledepartment(Department department) {
        this.setResponsibledepartment(department);
        return this;
    }

    public Department getRelevantdepartment() {
        return this.relevantdepartment;
    }

    public void setRelevantdepartment(Department department) {
        this.relevantdepartment = department;
    }

    public ProjectTotalwbs relevantdepartment(Department department) {
        this.setRelevantdepartment(department);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ProjectTotalwbs department(Department department) {
        this.setDepartment(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectTotalwbs)) {
            return false;
        }
        return getId() != null && getId().equals(((ProjectTotalwbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectTotalwbs{" +
            "id=" + getId() +
            ", wbsname='" + getWbsname() + "'" +
            ", parentwbsid='" + getParentwbsid() + "'" +
            ", pbsid='" + getPbsid() + "'" +
            ", description='" + getDescription() + "'" +
            ", belongfront='" + getBelongfront() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", progress=" + getProgress() +
            ", type=" + getType() +
            ", priorty=" + getPriorty() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", deliverables='" + getDeliverables() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", workbag=" + getWorkbag() +
            "}";
    }
}
