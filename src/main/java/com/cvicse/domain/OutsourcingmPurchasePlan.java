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
 * A OutsourcingmPurchasePlan.
 */
@Entity
@Table(name = "outsourcingm_purchase_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingmPurchasePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "outsourcingplanid", unique = true)
    private Long outsourcingplanid;

    @Column(name = "matarialname")
    private String matarialname;

    @Column(name = "purchasingmethod")
    private Integer purchasingmethod;

    @Column(name = "budgit", precision = 21, scale = 2)
    private BigDecimal budgit;

    @Column(name = "needtime")
    private LocalDate needtime;

    @Column(name = "planusetime")
    private LocalDate planusetime;

    @Column(name = "supplierid")
    private Long supplierid;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Project project;

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

    @JsonIgnoreProperties(value = { "outsourcingplanid", "responsibleid", "project" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "outsourcingplanid")
    private OutsourcingmPurchaseExecute outsourcingmPurchaseExecute;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OutsourcingmPurchasePlan id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutsourcingplanid() {
        return this.outsourcingplanid;
    }

    public OutsourcingmPurchasePlan outsourcingplanid(Long outsourcingplanid) {
        this.setOutsourcingplanid(outsourcingplanid);
        return this;
    }

    public void setOutsourcingplanid(Long outsourcingplanid) {
        this.outsourcingplanid = outsourcingplanid;
    }

    public String getMatarialname() {
        return this.matarialname;
    }

    public OutsourcingmPurchasePlan matarialname(String matarialname) {
        this.setMatarialname(matarialname);
        return this;
    }

    public void setMatarialname(String matarialname) {
        this.matarialname = matarialname;
    }

    public Integer getPurchasingmethod() {
        return this.purchasingmethod;
    }

    public OutsourcingmPurchasePlan purchasingmethod(Integer purchasingmethod) {
        this.setPurchasingmethod(purchasingmethod);
        return this;
    }

    public void setPurchasingmethod(Integer purchasingmethod) {
        this.purchasingmethod = purchasingmethod;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public OutsourcingmPurchasePlan budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public LocalDate getNeedtime() {
        return this.needtime;
    }

    public OutsourcingmPurchasePlan needtime(LocalDate needtime) {
        this.setNeedtime(needtime);
        return this;
    }

    public void setNeedtime(LocalDate needtime) {
        this.needtime = needtime;
    }

    public LocalDate getPlanusetime() {
        return this.planusetime;
    }

    public OutsourcingmPurchasePlan planusetime(LocalDate planusetime) {
        this.setPlanusetime(planusetime);
        return this;
    }

    public void setPlanusetime(LocalDate planusetime) {
        this.planusetime = planusetime;
    }

    public Long getSupplierid() {
        return this.supplierid;
    }

    public OutsourcingmPurchasePlan supplierid(Long supplierid) {
        this.setSupplierid(supplierid);
        return this;
    }

    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public OutsourcingmPurchasePlan price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public OutsourcingmPurchasePlan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public OutsourcingmPurchasePlan auditStatus(AuditStatus auditStatus) {
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

    public OutsourcingmPurchasePlan project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public OutsourcingmPurchasePlan responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public OutsourcingmPurchasePlan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public OutsourcingmPurchaseExecute getOutsourcingmPurchaseExecute() {
        return this.outsourcingmPurchaseExecute;
    }

    public void setOutsourcingmPurchaseExecute(OutsourcingmPurchaseExecute outsourcingmPurchaseExecute) {
        if (this.outsourcingmPurchaseExecute != null) {
            this.outsourcingmPurchaseExecute.setOutsourcingplanid(null);
        }
        if (outsourcingmPurchaseExecute != null) {
            outsourcingmPurchaseExecute.setOutsourcingplanid(this);
        }
        this.outsourcingmPurchaseExecute = outsourcingmPurchaseExecute;
    }

    public OutsourcingmPurchasePlan outsourcingmPurchaseExecute(OutsourcingmPurchaseExecute outsourcingmPurchaseExecute) {
        this.setOutsourcingmPurchaseExecute(outsourcingmPurchaseExecute);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingmPurchasePlan)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingmPurchasePlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingmPurchasePlan{" +
            "id=" + getId() +
            ", outsourcingplanid=" + getOutsourcingplanid() +
            ", matarialname='" + getMatarialname() + "'" +
            ", purchasingmethod=" + getPurchasingmethod() +
            ", budgit=" + getBudgit() +
            ", needtime='" + getNeedtime() + "'" +
            ", planusetime='" + getPlanusetime() + "'" +
            ", supplierid=" + getSupplierid() +
            ", price=" + getPrice() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
