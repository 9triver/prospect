package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ProjectHumanresourcesplan.
 */
@Entity
@Table(name = "project_humanresourcesplan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProjectHumanresourcesplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "clientname")
    private String clientname;

    @Column(name = "plandate")
    private LocalDate plandate;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = {
            "projectwbs",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingPurchasePlan",
            "projectHumanresourcesplan",
            "projectremit",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ProjectHumanresourcesplan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public ProjectHumanresourcesplan projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getClientname() {
        return this.clientname;
    }

    public ProjectHumanresourcesplan clientname(String clientname) {
        this.setClientname(clientname);
        return this;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public LocalDate getPlandate() {
        return this.plandate;
    }

    public ProjectHumanresourcesplan plandate(LocalDate plandate) {
        this.setPlandate(plandate);
        return this;
    }

    public void setPlandate(LocalDate plandate) {
        this.plandate = plandate;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public ProjectHumanresourcesplan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public ProjectHumanresourcesplan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectHumanresourcesplan project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProjectHumanresourcesplan)) {
            return false;
        }
        return getId() != null && getId().equals(((ProjectHumanresourcesplan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProjectHumanresourcesplan{" +
            "id=" + getId() +
            ", projectname='" + getProjectname() + "'" +
            ", clientname='" + getClientname() + "'" +
            ", plandate='" + getPlandate() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
