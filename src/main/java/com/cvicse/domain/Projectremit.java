package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectremit.
 */
@Entity
@Table(name = "projectremit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectremit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "remit")
    private String remit;

    @Column(name = "outdeportment")
    private String outdeportment;

    @Column(name = "indeportment")
    private String indeportment;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "deportment")
    private String deportment;

    @Column(name = "projectleader")
    private String projectleader;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Projectremit id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemit() {
        return this.remit;
    }

    public Projectremit remit(String remit) {
        this.setRemit(remit);
        return this;
    }

    public void setRemit(String remit) {
        this.remit = remit;
    }

    public String getOutdeportment() {
        return this.outdeportment;
    }

    public Projectremit outdeportment(String outdeportment) {
        this.setOutdeportment(outdeportment);
        return this;
    }

    public void setOutdeportment(String outdeportment) {
        this.outdeportment = outdeportment;
    }

    public String getIndeportment() {
        return this.indeportment;
    }

    public Projectremit indeportment(String indeportment) {
        this.setIndeportment(indeportment);
        return this;
    }

    public void setIndeportment(String indeportment) {
        this.indeportment = indeportment;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Projectremit projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getDeportment() {
        return this.deportment;
    }

    public Projectremit deportment(String deportment) {
        this.setDeportment(deportment);
        return this;
    }

    public void setDeportment(String deportment) {
        this.deportment = deportment;
    }

    public String getProjectleader() {
        return this.projectleader;
    }

    public Projectremit projectleader(String projectleader) {
        this.setProjectleader(projectleader);
        return this;
    }

    public void setProjectleader(String projectleader) {
        this.projectleader = projectleader;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Projectremit secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Projectremit auditStatus(AuditStatus auditStatus) {
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

    public Projectremit project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Projectremit creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectremit)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectremit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectremit{" +
            "id=" + getId() +
            ", remit='" + getRemit() + "'" +
            ", outdeportment='" + getOutdeportment() + "'" +
            ", indeportment='" + getIndeportment() + "'" +
            ", projectname='" + getProjectname() + "'" +
            ", deportment='" + getDeportment() + "'" +
            ", projectleader='" + getProjectleader() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
