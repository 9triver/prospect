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
 * A OutsourcingPurchasePlan.
 */
@Entity
@Table(name = "outsourcing_purchase_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingPurchasePlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

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
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(value = { "outsourcingplanid", "responsibleid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "outsourcingplanid")
    private OutsourcingPurchaseExecute outsourcingPurchaseExecute;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public OutsourcingPurchasePlan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatarialname() {
        return this.matarialname;
    }

    public OutsourcingPurchasePlan matarialname(String matarialname) {
        this.setMatarialname(matarialname);
        return this;
    }

    public void setMatarialname(String matarialname) {
        this.matarialname = matarialname;
    }

    public Integer getPurchasingmethod() {
        return this.purchasingmethod;
    }

    public OutsourcingPurchasePlan purchasingmethod(Integer purchasingmethod) {
        this.setPurchasingmethod(purchasingmethod);
        return this;
    }

    public void setPurchasingmethod(Integer purchasingmethod) {
        this.purchasingmethod = purchasingmethod;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public OutsourcingPurchasePlan budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public LocalDate getNeedtime() {
        return this.needtime;
    }

    public OutsourcingPurchasePlan needtime(LocalDate needtime) {
        this.setNeedtime(needtime);
        return this;
    }

    public void setNeedtime(LocalDate needtime) {
        this.needtime = needtime;
    }

    public LocalDate getPlanusetime() {
        return this.planusetime;
    }

    public OutsourcingPurchasePlan planusetime(LocalDate planusetime) {
        this.setPlanusetime(planusetime);
        return this;
    }

    public void setPlanusetime(LocalDate planusetime) {
        this.planusetime = planusetime;
    }

    public Long getSupplierid() {
        return this.supplierid;
    }

    public OutsourcingPurchasePlan supplierid(Long supplierid) {
        this.setSupplierid(supplierid);
        return this;
    }

    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public OutsourcingPurchasePlan price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public OutsourcingPurchasePlan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public OutsourcingPurchasePlan auditStatus(AuditStatus auditStatus) {
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

    public OutsourcingPurchasePlan project(Project project) {
        this.setProject(project);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public OutsourcingPurchasePlan responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public OutsourcingPurchasePlan auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public OutsourcingPurchaseExecute getOutsourcingPurchaseExecute() {
        return this.outsourcingPurchaseExecute;
    }

    public void setOutsourcingPurchaseExecute(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        if (this.outsourcingPurchaseExecute != null) {
            this.outsourcingPurchaseExecute.setOutsourcingplanid(null);
        }
        if (outsourcingPurchaseExecute != null) {
            outsourcingPurchaseExecute.setOutsourcingplanid(this);
        }
        this.outsourcingPurchaseExecute = outsourcingPurchaseExecute;
    }

    public OutsourcingPurchasePlan outsourcingPurchaseExecute(OutsourcingPurchaseExecute outsourcingPurchaseExecute) {
        this.setOutsourcingPurchaseExecute(outsourcingPurchaseExecute);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingPurchasePlan)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingPurchasePlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingPurchasePlan{" +
            "id=" + getId() +
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
