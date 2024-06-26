package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AnnualSecurityPlan.
 */
@Entity
@Table(name = "annual_security_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AnnualSecurityPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "securityplanname")
    private String securityplanname;

    @Column(name = "releasetime")
    private LocalDate releasetime;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "version")
    private Integer version;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public AnnualSecurityPlan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSecurityplanname() {
        return this.securityplanname;
    }

    public AnnualSecurityPlan securityplanname(String securityplanname) {
        this.setSecurityplanname(securityplanname);
        return this;
    }

    public void setSecurityplanname(String securityplanname) {
        this.securityplanname = securityplanname;
    }

    public LocalDate getReleasetime() {
        return this.releasetime;
    }

    public AnnualSecurityPlan releasetime(LocalDate releasetime) {
        this.setReleasetime(releasetime);
        return this;
    }

    public void setReleasetime(LocalDate releasetime) {
        this.releasetime = releasetime;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public AnnualSecurityPlan createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public AnnualSecurityPlan creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public AnnualSecurityPlan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getVersion() {
        return this.version;
    }

    public AnnualSecurityPlan version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AnnualSecurityPlan project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public AnnualSecurityPlan creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public AnnualSecurityPlan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AnnualSecurityPlan)) {
            return false;
        }
        return getId() != null && getId().equals(((AnnualSecurityPlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AnnualSecurityPlan{" +
            "id=" + getId() +
            ", securityplanname='" + getSecurityplanname() + "'" +
            ", releasetime='" + getReleasetime() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", version=" + getVersion() +
            "}";
    }
}
