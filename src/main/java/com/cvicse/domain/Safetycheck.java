package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Risklevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Safetycheck.
 */
@Entity
@Table(name = "safetycheck")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Safetycheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "safetycheckid")
    private Long safetycheckid;

    @Column(name = "safetycheckname")
    private String safetycheckname;

    @Column(name = "checksource")
    private String checksource;

    @Column(name = "checktime")
    private LocalDate checktime;

    @Column(name = "effectivetime")
    private LocalDate effectivetime;

    @Column(name = "operatinglocation")
    private String operatinglocation;

    @Column(name = "deprotment")
    private String deprotment;

    @Column(name = "phonenumber")
    private Long phonenumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "risklevel")
    private Risklevel risklevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "safetycheck")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Safetycheck id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSafetycheckid() {
        return this.safetycheckid;
    }

    public Safetycheck safetycheckid(Long safetycheckid) {
        this.setSafetycheckid(safetycheckid);
        return this;
    }

    public void setSafetycheckid(Long safetycheckid) {
        this.safetycheckid = safetycheckid;
    }

    public String getSafetycheckname() {
        return this.safetycheckname;
    }

    public Safetycheck safetycheckname(String safetycheckname) {
        this.setSafetycheckname(safetycheckname);
        return this;
    }

    public void setSafetycheckname(String safetycheckname) {
        this.safetycheckname = safetycheckname;
    }

    public String getChecksource() {
        return this.checksource;
    }

    public Safetycheck checksource(String checksource) {
        this.setChecksource(checksource);
        return this;
    }

    public void setChecksource(String checksource) {
        this.checksource = checksource;
    }

    public LocalDate getChecktime() {
        return this.checktime;
    }

    public Safetycheck checktime(LocalDate checktime) {
        this.setChecktime(checktime);
        return this;
    }

    public void setChecktime(LocalDate checktime) {
        this.checktime = checktime;
    }

    public LocalDate getEffectivetime() {
        return this.effectivetime;
    }

    public Safetycheck effectivetime(LocalDate effectivetime) {
        this.setEffectivetime(effectivetime);
        return this;
    }

    public void setEffectivetime(LocalDate effectivetime) {
        this.effectivetime = effectivetime;
    }

    public String getOperatinglocation() {
        return this.operatinglocation;
    }

    public Safetycheck operatinglocation(String operatinglocation) {
        this.setOperatinglocation(operatinglocation);
        return this;
    }

    public void setOperatinglocation(String operatinglocation) {
        this.operatinglocation = operatinglocation;
    }

    public String getDeprotment() {
        return this.deprotment;
    }

    public Safetycheck deprotment(String deprotment) {
        this.setDeprotment(deprotment);
        return this;
    }

    public void setDeprotment(String deprotment) {
        this.deprotment = deprotment;
    }

    public Long getPhonenumber() {
        return this.phonenumber;
    }

    public Safetycheck phonenumber(Long phonenumber) {
        this.setPhonenumber(phonenumber);
        return this;
    }

    public void setPhonenumber(Long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Risklevel getRisklevel() {
        return this.risklevel;
    }

    public Safetycheck risklevel(Risklevel risklevel) {
        this.setRisklevel(risklevel);
        return this;
    }

    public void setRisklevel(Risklevel risklevel) {
        this.risklevel = risklevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Safetycheck auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Safetycheck auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Safetycheck responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setSafetycheck(null);
        }
        if (project != null) {
            project.setSafetycheck(this);
        }
        this.project = project;
    }

    public Safetycheck project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Safetycheck)) {
            return false;
        }
        return getId() != null && getId().equals(((Safetycheck) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Safetycheck{" +
            "id=" + getId() +
            ", safetycheckid=" + getSafetycheckid() +
            ", safetycheckname='" + getSafetycheckname() + "'" +
            ", checksource='" + getChecksource() + "'" +
            ", checktime='" + getChecktime() + "'" +
            ", effectivetime='" + getEffectivetime() + "'" +
            ", operatinglocation='" + getOperatinglocation() + "'" +
            ", deprotment='" + getDeprotment() + "'" +
            ", phonenumber=" + getPhonenumber() +
            ", risklevel='" + getRisklevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
