package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Auditedbudget.
 */
@Entity
@Table(name = "auditedbudget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Auditedbudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "budgit", precision = 21, scale = 2)
    private BigDecimal budgit;

    @Column(name = "dapartmentid")
    private String dapartmentid;

    @Column(name = "draftapproval")
    private Long draftapproval;

    @Column(name = "totalbudgetid", precision = 21, scale = 2)
    private BigDecimal totalbudgetid;

    @Column(name = "unitbudgetid", precision = 21, scale = 2)
    private BigDecimal unitbudgetid;

    @Column(name = "documentid")
    private String documentid;

    @Column(name = "maintainerid")
    private String maintainerid;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Totalbudget totalbudget;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Unitbudget unitbudget;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "progressplanreturns", "auditedbudget" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

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

    @JsonIgnoreProperties(value = { "auditedbudget" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "auditedbudget")
    private Fundsavailability fundsavailability;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Auditedbudget id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Auditedbudget createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Auditedbudget creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Auditedbudget secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Long getYear() {
        return this.year;
    }

    public Auditedbudget year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public Auditedbudget budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public String getDapartmentid() {
        return this.dapartmentid;
    }

    public Auditedbudget dapartmentid(String dapartmentid) {
        this.setDapartmentid(dapartmentid);
        return this;
    }

    public void setDapartmentid(String dapartmentid) {
        this.dapartmentid = dapartmentid;
    }

    public Long getDraftapproval() {
        return this.draftapproval;
    }

    public Auditedbudget draftapproval(Long draftapproval) {
        this.setDraftapproval(draftapproval);
        return this;
    }

    public void setDraftapproval(Long draftapproval) {
        this.draftapproval = draftapproval;
    }

    public BigDecimal getTotalbudgetid() {
        return this.totalbudgetid;
    }

    public Auditedbudget totalbudgetid(BigDecimal totalbudgetid) {
        this.setTotalbudgetid(totalbudgetid);
        return this;
    }

    public void setTotalbudgetid(BigDecimal totalbudgetid) {
        this.totalbudgetid = totalbudgetid;
    }

    public BigDecimal getUnitbudgetid() {
        return this.unitbudgetid;
    }

    public Auditedbudget unitbudgetid(BigDecimal unitbudgetid) {
        this.setUnitbudgetid(unitbudgetid);
        return this;
    }

    public void setUnitbudgetid(BigDecimal unitbudgetid) {
        this.unitbudgetid = unitbudgetid;
    }

    public String getDocumentid() {
        return this.documentid;
    }

    public Auditedbudget documentid(String documentid) {
        this.setDocumentid(documentid);
        return this;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public String getMaintainerid() {
        return this.maintainerid;
    }

    public Auditedbudget maintainerid(String maintainerid) {
        this.setMaintainerid(maintainerid);
        return this;
    }

    public void setMaintainerid(String maintainerid) {
        this.maintainerid = maintainerid;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Auditedbudget auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Totalbudget getTotalbudget() {
        return this.totalbudget;
    }

    public void setTotalbudget(Totalbudget totalbudget) {
        this.totalbudget = totalbudget;
    }

    public Auditedbudget totalbudget(Totalbudget totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public Unitbudget getUnitbudget() {
        return this.unitbudget;
    }

    public void setUnitbudget(Unitbudget unitbudget) {
        this.unitbudget = unitbudget;
    }

    public Auditedbudget unitbudget(Unitbudget unitbudget) {
        this.setUnitbudget(unitbudget);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Auditedbudget document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Auditedbudget creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Auditedbudget auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Fundsavailability getFundsavailability() {
        return this.fundsavailability;
    }

    public void setFundsavailability(Fundsavailability fundsavailability) {
        if (this.fundsavailability != null) {
            this.fundsavailability.setAuditedbudget(null);
        }
        if (fundsavailability != null) {
            fundsavailability.setAuditedbudget(this);
        }
        this.fundsavailability = fundsavailability;
    }

    public Auditedbudget fundsavailability(Fundsavailability fundsavailability) {
        this.setFundsavailability(fundsavailability);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Auditedbudget)) {
            return false;
        }
        return getId() != null && getId().equals(((Auditedbudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Auditedbudget{" +
            "id=" + getId() +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", year=" + getYear() +
            ", budgit=" + getBudgit() +
            ", dapartmentid='" + getDapartmentid() + "'" +
            ", draftapproval=" + getDraftapproval() +
            ", totalbudgetid=" + getTotalbudgetid() +
            ", unitbudgetid=" + getUnitbudgetid() +
            ", documentid='" + getDocumentid() + "'" +
            ", maintainerid='" + getMaintainerid() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
