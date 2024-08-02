package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_outsourcing_purchase_plan__projectwbs",
        joinColumns = @JoinColumn(name = "outsourcing_purchase_plan_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

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

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public OutsourcingPurchasePlan responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
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

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public OutsourcingPurchasePlan projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public OutsourcingPurchasePlan addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public OutsourcingPurchasePlan removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
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
