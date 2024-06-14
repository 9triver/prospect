package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Cycleplanstatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cycleplan.
 */
@Entity
@Table(name = "cycleplan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cycleplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "cycleplanid", unique = true)
    private Long cycleplanid;

    @Column(name = "cycleplanname")
    private String cycleplanname;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "actualstarttime")
    private LocalDate actualstarttime;

    @Column(name = "actualendtime")
    private LocalDate actualendtime;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Cycleplanstatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = { "creatorid", "project", "cycleplan", "annualplan", "monthplan", "fundsmanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

    @JsonIgnoreProperties(value = { "document", "monthplan", "projectcharge", "creatorid", "auditorid", "cycleplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Annualplan annualplan;

    @JsonIgnoreProperties(
        value = { "document", "planreturns", "projectcharge", "creatorid", "auditorid", "cycleplan", "annualplan" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Monthplan monthplan;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "pbsbaseline", "wbsbaseline" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectcharge projectcharge;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cycleplan")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cycleplan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCycleplanid() {
        return this.cycleplanid;
    }

    public Cycleplan cycleplanid(Long cycleplanid) {
        this.setCycleplanid(cycleplanid);
        return this;
    }

    public void setCycleplanid(Long cycleplanid) {
        this.cycleplanid = cycleplanid;
    }

    public String getCycleplanname() {
        return this.cycleplanname;
    }

    public Cycleplan cycleplanname(String cycleplanname) {
        this.setCycleplanname(cycleplanname);
        return this;
    }

    public void setCycleplanname(String cycleplanname) {
        this.cycleplanname = cycleplanname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Cycleplan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Cycleplan starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Cycleplan endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public LocalDate getActualstarttime() {
        return this.actualstarttime;
    }

    public Cycleplan actualstarttime(LocalDate actualstarttime) {
        this.setActualstarttime(actualstarttime);
        return this;
    }

    public void setActualstarttime(LocalDate actualstarttime) {
        this.actualstarttime = actualstarttime;
    }

    public LocalDate getActualendtime() {
        return this.actualendtime;
    }

    public Cycleplan actualendtime(LocalDate actualendtime) {
        this.setActualendtime(actualendtime);
        return this;
    }

    public void setActualendtime(LocalDate actualendtime) {
        this.actualendtime = actualendtime;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Cycleplan responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public Cycleplanstatus getStatus() {
        return this.status;
    }

    public Cycleplan status(Cycleplanstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Cycleplanstatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Cycleplan auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Cycleplan document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Annualplan getAnnualplan() {
        return this.annualplan;
    }

    public void setAnnualplan(Annualplan annualplan) {
        this.annualplan = annualplan;
    }

    public Cycleplan annualplan(Annualplan annualplan) {
        this.setAnnualplan(annualplan);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        this.monthplan = monthplan;
    }

    public Cycleplan monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        this.projectcharge = projectcharge;
    }

    public Cycleplan projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Cycleplan responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Cycleplan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setCycleplan(null);
        }
        if (project != null) {
            project.setCycleplan(this);
        }
        this.project = project;
    }

    public Cycleplan project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cycleplan)) {
            return false;
        }
        return getId() != null && getId().equals(((Cycleplan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cycleplan{" +
            "id=" + getId() +
            ", cycleplanid=" + getCycleplanid() +
            ", cycleplanname='" + getCycleplanname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", actualstarttime='" + getActualstarttime() + "'" +
            ", actualendtime='" + getActualendtime() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
