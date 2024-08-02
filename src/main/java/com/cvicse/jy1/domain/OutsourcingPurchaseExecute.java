package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OutsourcingPurchaseExecute.
 */
@Entity
@Table(name = "outsourcing_purchase_execute")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingPurchaseExecute implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "projectwbs" }, allowSetters = true)
    private OutsourcingPurchasePlan outsourcingplanid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public OutsourcingPurchaseExecute id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatarialname() {
        return this.matarialname;
    }

    public OutsourcingPurchaseExecute matarialname(String matarialname) {
        this.setMatarialname(matarialname);
        return this;
    }

    public void setMatarialname(String matarialname) {
        this.matarialname = matarialname;
    }

    public Integer getPurchasingmethod() {
        return this.purchasingmethod;
    }

    public OutsourcingPurchaseExecute purchasingmethod(Integer purchasingmethod) {
        this.setPurchasingmethod(purchasingmethod);
        return this;
    }

    public void setPurchasingmethod(Integer purchasingmethod) {
        this.purchasingmethod = purchasingmethod;
    }

    public BigDecimal getBudgit() {
        return this.budgit;
    }

    public OutsourcingPurchaseExecute budgit(BigDecimal budgit) {
        this.setBudgit(budgit);
        return this;
    }

    public void setBudgit(BigDecimal budgit) {
        this.budgit = budgit;
    }

    public LocalDate getNeedtime() {
        return this.needtime;
    }

    public OutsourcingPurchaseExecute needtime(LocalDate needtime) {
        this.setNeedtime(needtime);
        return this;
    }

    public void setNeedtime(LocalDate needtime) {
        this.needtime = needtime;
    }

    public LocalDate getPlanusetime() {
        return this.planusetime;
    }

    public OutsourcingPurchaseExecute planusetime(LocalDate planusetime) {
        this.setPlanusetime(planusetime);
        return this;
    }

    public void setPlanusetime(LocalDate planusetime) {
        this.planusetime = planusetime;
    }

    public Long getSupplierid() {
        return this.supplierid;
    }

    public OutsourcingPurchaseExecute supplierid(Long supplierid) {
        this.setSupplierid(supplierid);
        return this;
    }

    public void setSupplierid(Long supplierid) {
        this.supplierid = supplierid;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public OutsourcingPurchaseExecute price(BigDecimal price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public OutsourcingPurchaseExecute secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Officers getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(Officers officers) {
        this.responsibleperson = officers;
    }

    public OutsourcingPurchaseExecute responsibleperson(Officers officers) {
        this.setResponsibleperson(officers);
        return this;
    }

    public OutsourcingPurchasePlan getOutsourcingplanid() {
        return this.outsourcingplanid;
    }

    public void setOutsourcingplanid(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        this.outsourcingplanid = outsourcingPurchasePlan;
    }

    public OutsourcingPurchaseExecute outsourcingplanid(OutsourcingPurchasePlan outsourcingPurchasePlan) {
        this.setOutsourcingplanid(outsourcingPurchasePlan);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingPurchaseExecute)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingPurchaseExecute) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingPurchaseExecute{" +
            "id=" + getId() +
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
