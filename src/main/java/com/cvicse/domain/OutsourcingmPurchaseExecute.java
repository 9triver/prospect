package com.cvicse.domain;

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
 * A OutsourcingmPurchaseExecute.
 */
@Entity
@Table(name = "outsourcingm_purchase_execute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingmPurchaseExecute implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "outsourcingexecuteid", unique = true)
    private Long outsourcingexecuteid;

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

    @JsonIgnoreProperties(value = { "project", "responsibleid", "auditorid", "outsourcingmPurchaseExecute" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private OutsourcingmPurchasePlan outsourcingplanid;

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "outsourcingmPurchaseExecute")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public OutsourcingmPurchaseExecute id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOutsourcingexecuteid() {
        return this.outsourcingexecuteid;
    }

    public OutsourcingmPurchaseExecute outsourcingexecuteid(Long outsourcingexecuteid) {
        this.setOutsourcingexecuteid(outsourcingexecuteid);
        return this;
    }

    public void setOutsourcingexecuteid(Long outsourcingexecuteid) {
        this.outsourcingexecuteid = outsourcingexecuteid;
    }

    public String getMatarialname() {
        return this.matarialname;
    }

    public OutsourcingmPurchaseExecute matarialname(String matarialname) {
        this.setMatarialname(matarialname);
        return this;
    }

    public void setMatarialname(String matarialname) {
        this.matarialname = matarialname;
    }

    public Integer getPurchasingmethod() {
        return this.purchasingmethod;
    }

    public OutsourcingmPurchaseExecute purchasingmethod(Integer purchasingmethod) {
        this.setPurchasingmethod(purchasingmethod);
        return this;
    }

    public void setPurchasingmethod(Integer purchasingmethod) {
        this.purchasingmethod = purchasingmethod;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public OutsourcingmPurchaseExecute budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public LocalDate getNeedtime() {
        return this.needtime;
    }

    public OutsourcingmPurchaseExecute needtime(LocalDate needtime) {
        this.setNeedtime(needtime);
        return this;
    }

    public void setNeedtime(LocalDate needtime) {
        this.needtime = needtime;
    }

    public LocalDate getPlanusetime() {
        return this.planusetime;
    }

    public OutsourcingmPurchaseExecute planusetime(LocalDate planusetime) {
        this.setPlanusetime(planusetime);
        return this;
    }

    public void setPlanusetime(LocalDate planusetime) {
        this.planusetime = planusetime;
    }

    public Long getSupplierid() {
        return this.supplierid;
    }

    public OutsourcingmPurchaseExecute supplierid(Long supplierid) {
        this.setSupplierid(supplierid);
        return this;
    }

    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public OutsourcingmPurchaseExecute price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public OutsourcingmPurchaseExecute secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public OutsourcingmPurchasePlan getOutsourcingplanid() {
        return this.outsourcingplanid;
    }

    public void setOutsourcingplanid(OutsourcingmPurchasePlan outsourcingmPurchasePlan) {
        this.outsourcingplanid = outsourcingmPurchasePlan;
    }

    public OutsourcingmPurchaseExecute outsourcingplanid(OutsourcingmPurchasePlan outsourcingmPurchasePlan) {
        this.setOutsourcingplanid(outsourcingmPurchasePlan);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public OutsourcingmPurchaseExecute responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setOutsourcingmPurchaseExecute(null);
        }
        if (project != null) {
            project.setOutsourcingmPurchaseExecute(this);
        }
        this.project = project;
    }

    public OutsourcingmPurchaseExecute project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingmPurchaseExecute)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingmPurchaseExecute) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingmPurchaseExecute{" +
            "id=" + getId() +
            ", outsourcingexecuteid=" + getOutsourcingexecuteid() +
            ", matarialname='" + getMatarialname() + "'" +
            ", purchasingmethod=" + getPurchasingmethod() +
            ", budgit=" + getBudgit() +
            ", needtime='" + getNeedtime() + "'" +
            ", planusetime='" + getPlanusetime() + "'" +
            ", supplierid=" + getSupplierid() +
            ", price=" + getPrice() +
            ", secretlevel='" + getSecretlevel() + "'" +
            "}";
    }
}
