package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Wbsmanage.
 */
@Entity
@Table(name = "wbsmanage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Wbsmanage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "description")
    private String description;

    @Column(name = "result")
    private String result;

    @Column(name = "administratorname")
    private String administratorname;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Column(name = "department")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "responsibleid", "auditorid", "wbsmanage" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Wbssubmanage wbssubmanage;

    @JsonIgnoreProperties(value = { "responsibleid", "auditorid", "pbsmanage", "wbsmanage" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Pbssubmanage pbssubmanage;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers administratorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Wbsmanage id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public Wbsmanage wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getDescription() {
        return this.description;
    }

    public Wbsmanage description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getResult() {
        return this.result;
    }

    public Wbsmanage result(String result) {
        this.setResult(result);
        return this;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAdministratorname() {
        return this.administratorname;
    }

    public Wbsmanage administratorname(String administratorname) {
        this.setAdministratorname(administratorname);
        return this;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Wbsmanage responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public String getDepartment() {
        return this.department;
    }

    public Wbsmanage department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Wbsmanage secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Wbsmanage auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Wbssubmanage getWbssubmanage() {
        return this.wbssubmanage;
    }

    public void setWbssubmanage(Wbssubmanage wbssubmanage) {
        this.wbssubmanage = wbssubmanage;
    }

    public Wbsmanage wbssubmanage(Wbssubmanage wbssubmanage) {
        this.setWbssubmanage(wbssubmanage);
        return this;
    }

    public Pbssubmanage getPbssubmanage() {
        return this.pbssubmanage;
    }

    public void setPbssubmanage(Pbssubmanage pbssubmanage) {
        this.pbssubmanage = pbssubmanage;
    }

    public Wbsmanage pbssubmanage(Pbssubmanage pbssubmanage) {
        this.setPbssubmanage(pbssubmanage);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Wbsmanage project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getAdministratorid() {
        return this.administratorid;
    }

    public void setAdministratorid(Officers officers) {
        this.administratorid = officers;
    }

    public Wbsmanage administratorid(Officers officers) {
        this.setAdministratorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Wbsmanage auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Wbsmanage responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wbsmanage)) {
            return false;
        }
        return getId() != null && getId().equals(((Wbsmanage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wbsmanage{" +
            "id=" + getId() +
            ", wbsname='" + getWbsname() + "'" +
            ", description='" + getDescription() + "'" +
            ", result='" + getResult() + "'" +
            ", administratorname='" + getAdministratorname() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", department='" + getDepartment() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
