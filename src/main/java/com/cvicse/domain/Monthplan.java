package com.cvicse.domain;

import com.cvicse.domain.enumeration.Annualplanstatus;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Monthplan.
 */
@Entity
@Table(name = "monthplan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Monthplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "monthplanname")
    private String monthplanname;

    @Column(name = "month")
    private LocalDate month;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "creatorname")
    private String creatorname;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Annualplanstatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "progressplanreturns", "auditedbudget" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

    @JsonIgnoreProperties(value = { "planreturns", "responsibleid", "monthplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Planexecute planreturns;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "pbsbaseline", "wbsbaseline" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectcharge projectcharge;

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

    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "monthplan")
    private Cycleplan cycleplan;

    @JsonIgnoreProperties(value = { "document", "monthplan", "projectcharge", "creatorid", "auditorid", "cycleplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "monthplan")
    private Annualplan annualplan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Monthplan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonthplanname() {
        return this.monthplanname;
    }

    public Monthplan monthplanname(String monthplanname) {
        this.setMonthplanname(monthplanname);
        return this;
    }

    public void setMonthplanname(String monthplanname) {
        this.monthplanname = monthplanname;
    }

    public LocalDate getMonth() {
        return this.month;
    }

    public Monthplan month(LocalDate month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Monthplan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Monthplan creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Annualplanstatus getStatus() {
        return this.status;
    }

    public Monthplan status(Annualplanstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Annualplanstatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Monthplan auditStatus(AuditStatus auditStatus) {
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

    public Monthplan document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Planexecute getPlanreturns() {
        return this.planreturns;
    }

    public void setPlanreturns(Planexecute planexecute) {
        this.planreturns = planexecute;
    }

    public Monthplan planreturns(Planexecute planexecute) {
        this.setPlanreturns(planexecute);
        return this;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        this.projectcharge = projectcharge;
    }

    public Monthplan projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Monthplan creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Monthplan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        if (this.cycleplan != null) {
            this.cycleplan.setMonthplan(null);
        }
        if (cycleplan != null) {
            cycleplan.setMonthplan(this);
        }
        this.cycleplan = cycleplan;
    }

    public Monthplan cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Annualplan getAnnualplan() {
        return this.annualplan;
    }

    public void setAnnualplan(Annualplan annualplan) {
        if (this.annualplan != null) {
            this.annualplan.setMonthplan(null);
        }
        if (annualplan != null) {
            annualplan.setMonthplan(this);
        }
        this.annualplan = annualplan;
    }

    public Monthplan annualplan(Annualplan annualplan) {
        this.setAnnualplan(annualplan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Monthplan)) {
            return false;
        }
        return getId() != null && getId().equals(((Monthplan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Monthplan{" +
            "id=" + getId() +
            ", monthplanname='" + getMonthplanname() + "'" +
            ", month='" + getMonth() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
