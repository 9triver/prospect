package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CommunicationPlan.
 */
@Entity
@Table(name = "communication_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommunicationPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "communicationtopic")
    private String communicationtopic;

    @Column(name = "communicationtime")
    private LocalDate communicationtime;

    @Column(name = "worktarget")
    private String worktarget;

    @Column(name = "workcontent")
    private String workcontent;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "projectpbs",
            "responsibleperson",
            "technicaldirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "projectdeliverables",
            "relevantdepartments",
            "workbags",
            "progressPlans",
            "projectBudgets",
            "projects",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "projectTotalwbs",
        },
        allowSetters = true
    )
    private Projectwbs projectwbs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public CommunicationPlan id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public CommunicationPlan wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getCommunicationtopic() {
        return this.communicationtopic;
    }

    public CommunicationPlan communicationtopic(String communicationtopic) {
        this.setCommunicationtopic(communicationtopic);
        return this;
    }

    public void setCommunicationtopic(String communicationtopic) {
        this.communicationtopic = communicationtopic;
    }

    public LocalDate getCommunicationtime() {
        return this.communicationtime;
    }

    public CommunicationPlan communicationtime(LocalDate communicationtime) {
        this.setCommunicationtime(communicationtime);
        return this;
    }

    public void setCommunicationtime(LocalDate communicationtime) {
        this.communicationtime = communicationtime;
    }

    public String getWorktarget() {
        return this.worktarget;
    }

    public CommunicationPlan worktarget(String worktarget) {
        this.setWorktarget(worktarget);
        return this;
    }

    public void setWorktarget(String worktarget) {
        this.worktarget = worktarget;
    }

    public String getWorkcontent() {
        return this.workcontent;
    }

    public CommunicationPlan workcontent(String workcontent) {
        this.setWorkcontent(workcontent);
        return this;
    }

    public void setWorkcontent(String workcontent) {
        this.workcontent = workcontent;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public CommunicationPlan remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public CommunicationPlan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public CommunicationPlan projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationPlan)) {
            return false;
        }
        return getId() != null && getId().equals(((CommunicationPlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationPlan{" +
            "id=" + getId() +
            ", wbsid='" + getWbsid() + "'" +
            ", communicationtopic='" + getCommunicationtopic() + "'" +
            ", communicationtime='" + getCommunicationtime() + "'" +
            ", worktarget='" + getWorktarget() + "'" +
            ", workcontent='" + getWorkcontent() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
