package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fundsmanagement.
 */
@Entity
@Table(name = "fundsmanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fundsmanagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "fundsid", unique = true)
    private Long fundsid;

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
    private Long documentid;

    @Column(name = "maintainerid")
    private Long maintainerid;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @JsonIgnoreProperties(value = { "comprehensivecontrol", "fundsmanagement" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Totalbudget totalbudget;

    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "comprehensivecontrol", "fundsmanagement" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Unitbudget unitbudget;

    @JsonIgnoreProperties(
        value = { "creatorid", "project", "cycleplan", "annualplan", "monthplan", "fundsmanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fundsmanagement")
    private Project project;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "funds")
    private Comprehensivecontrol comprehensivecontrol;

    @JsonIgnoreProperties(value = { "fundsmanagement" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "fundsmanagement")
    private Fundsavailability fundsavailability;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fundsmanagement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFundsid() {
        return this.fundsid;
    }

    public Fundsmanagement fundsid(Long fundsid) {
        this.setFundsid(fundsid);
        return this;
    }

    public void setFundsid(Long fundsid) {
        this.fundsid = fundsid;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Fundsmanagement createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Fundsmanagement creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Fundsmanagement secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Long getYear() {
        return this.year;
    }

    public Fundsmanagement year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public Fundsmanagement budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public String getDapartmentid() {
        return this.dapartmentid;
    }

    public Fundsmanagement dapartmentid(String dapartmentid) {
        this.setDapartmentid(dapartmentid);
        return this;
    }

    public void setDapartmentid(String dapartmentid) {
        this.dapartmentid = dapartmentid;
    }

    public Long getDraftapproval() {
        return this.draftapproval;
    }

    public Fundsmanagement draftapproval(Long draftapproval) {
        this.setDraftapproval(draftapproval);
        return this;
    }

    public void setDraftapproval(Long draftapproval) {
        this.draftapproval = draftapproval;
    }

    public BigDecimal getTotalbudgetid() {
        return this.totalbudgetid;
    }

    public Fundsmanagement totalbudgetid(BigDecimal totalbudgetid) {
        this.setTotalbudgetid(totalbudgetid);
        return this;
    }

    public void setTotalbudgetid(BigDecimal totalbudgetid) {
        this.totalbudgetid = totalbudgetid;
    }

    public BigDecimal getUnitbudgetid() {
        return this.unitbudgetid;
    }

    public Fundsmanagement unitbudgetid(BigDecimal unitbudgetid) {
        this.setUnitbudgetid(unitbudgetid);
        return this;
    }

    public void setUnitbudgetid(BigDecimal unitbudgetid) {
        this.unitbudgetid = unitbudgetid;
    }

    public Long getDocumentid() {
        return this.documentid;
    }

    public Fundsmanagement documentid(Long documentid) {
        this.setDocumentid(documentid);
        return this;
    }

    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }

    public Long getMaintainerid() {
        return this.maintainerid;
    }

    public Fundsmanagement maintainerid(Long maintainerid) {
        this.setMaintainerid(maintainerid);
        return this;
    }

    public void setMaintainerid(Long maintainerid) {
        this.maintainerid = maintainerid;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Fundsmanagement auditStatus(AuditStatus auditStatus) {
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

    public Fundsmanagement totalbudget(Totalbudget totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public Unitbudget getUnitbudget() {
        return this.unitbudget;
    }

    public void setUnitbudget(Unitbudget unitbudget) {
        this.unitbudget = unitbudget;
    }

    public Fundsmanagement unitbudget(Unitbudget unitbudget) {
        this.setUnitbudget(unitbudget);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Fundsmanagement document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Fundsmanagement creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Fundsmanagement auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setFundsmanagement(null);
        }
        if (project != null) {
            project.setFundsmanagement(this);
        }
        this.project = project;
    }

    public Fundsmanagement project(Project project) {
        this.setProject(project);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setFunds(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setFunds(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Fundsmanagement comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Fundsavailability getFundsavailability() {
        return this.fundsavailability;
    }

    public void setFundsavailability(Fundsavailability fundsavailability) {
        if (this.fundsavailability != null) {
            this.fundsavailability.setFundsmanagement(null);
        }
        if (fundsavailability != null) {
            fundsavailability.setFundsmanagement(this);
        }
        this.fundsavailability = fundsavailability;
    }

    public Fundsmanagement fundsavailability(Fundsavailability fundsavailability) {
        this.setFundsavailability(fundsavailability);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fundsmanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Fundsmanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fundsmanagement{" +
            "id=" + getId() +
            ", fundsid=" + getFundsid() +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", year=" + getYear() +
            ", budgit=" + getBudgit() +
            ", dapartmentid='" + getDapartmentid() + "'" +
            ", draftapproval=" + getDraftapproval() +
            ", totalbudgetid=" + getTotalbudgetid() +
            ", unitbudgetid=" + getUnitbudgetid() +
            ", documentid=" + getDocumentid() +
            ", maintainerid=" + getMaintainerid() +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
