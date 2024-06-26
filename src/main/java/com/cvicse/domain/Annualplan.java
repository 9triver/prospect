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
 * A Annualplan.
 */
@Entity
@Table(name = "annualplan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Annualplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "annualplanname")
    private String annualplanname;

    @Column(name = "jhi_year")
    private LocalDate year;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "annualplan")
    private Cycleplan cycleplan;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Annualplan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnnualplanname() {
        return this.annualplanname;
    }

    public Annualplan annualplanname(String annualplanname) {
        this.setAnnualplanname(annualplanname);
        return this;
    }

    public void setAnnualplanname(String annualplanname) {
        this.annualplanname = annualplanname;
    }

    public LocalDate getYear() {
        return this.year;
    }

    public Annualplan year(LocalDate year) {
        this.setYear(year);
        return this;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Annualplan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Annualplan creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Annualplanstatus getStatus() {
        return this.status;
    }

    public Annualplan status(Annualplanstatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Annualplanstatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Annualplan auditStatus(AuditStatus auditStatus) {
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

    public Annualplan document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        this.monthplan = monthplan;
    }

    public Annualplan monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        this.projectcharge = projectcharge;
    }

    public Annualplan projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Annualplan creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Annualplan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        if (this.cycleplan != null) {
            this.cycleplan.setAnnualplan(null);
        }
        if (cycleplan != null) {
            cycleplan.setAnnualplan(this);
        }
        this.cycleplan = cycleplan;
    }

    public Annualplan cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Annualplan)) {
            return false;
        }
        return getId() != null && getId().equals(((Annualplan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Annualplan{" +
            "id=" + getId() +
            ", annualplanname='" + getAnnualplanname() + "'" +
            ", year='" + getYear() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
