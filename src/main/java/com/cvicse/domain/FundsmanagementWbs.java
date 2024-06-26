package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A FundsmanagementWbs.
 */
@Entity
@Table(name = "fundsmanagement_wbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class FundsmanagementWbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "wbsspare_1")
    private String wbsspare1;

    @Column(name = "wbsspare_2")
    private String wbsspare2;

    @Column(name = "wbsspare_3")
    private String wbsspare3;

    @Column(name = "wbsspare_4")
    private String wbsspare4;

    @Column(name = "wbsspare_5")
    private String wbsspare5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "fundsavailability" },
        allowSetters = true
    )
    private Auditedbudget auditedbudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    private Totalbudget totalbudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "comprehensivecontrol", "auditedbudget" }, allowSetters = true)
    private Unitbudget unitbudget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "auditedbudget" }, allowSetters = true)
    private Fundsavailability fundsavailability;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid" }, allowSetters = true)
    private Contractualfunds contractualfunds;

    @JsonIgnoreProperties(value = { "wbs", "comprehensivecontrol" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbs")
    private Fundsmanagement fundsmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public FundsmanagementWbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public FundsmanagementWbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public FundsmanagementWbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public FundsmanagementWbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public FundsmanagementWbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public FundsmanagementWbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public Auditedbudget getAuditedbudget() {
        return this.auditedbudget;
    }

    public void setAuditedbudget(Auditedbudget auditedbudget) {
        this.auditedbudget = auditedbudget;
    }

    public FundsmanagementWbs auditedbudget(Auditedbudget auditedbudget) {
        this.setAuditedbudget(auditedbudget);
        return this;
    }

    public Totalbudget getTotalbudget() {
        return this.totalbudget;
    }

    public void setTotalbudget(Totalbudget totalbudget) {
        this.totalbudget = totalbudget;
    }

    public FundsmanagementWbs totalbudget(Totalbudget totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public Unitbudget getUnitbudget() {
        return this.unitbudget;
    }

    public void setUnitbudget(Unitbudget unitbudget) {
        this.unitbudget = unitbudget;
    }

    public FundsmanagementWbs unitbudget(Unitbudget unitbudget) {
        this.setUnitbudget(unitbudget);
        return this;
    }

    public Fundsavailability getFundsavailability() {
        return this.fundsavailability;
    }

    public void setFundsavailability(Fundsavailability fundsavailability) {
        this.fundsavailability = fundsavailability;
    }

    public FundsmanagementWbs fundsavailability(Fundsavailability fundsavailability) {
        this.setFundsavailability(fundsavailability);
        return this;
    }

    public Contractualfunds getContractualfunds() {
        return this.contractualfunds;
    }

    public void setContractualfunds(Contractualfunds contractualfunds) {
        this.contractualfunds = contractualfunds;
    }

    public FundsmanagementWbs contractualfunds(Contractualfunds contractualfunds) {
        this.setContractualfunds(contractualfunds);
        return this;
    }

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        if (this.fundsmanagement != null) {
            this.fundsmanagement.setWbs(null);
        }
        if (fundsmanagement != null) {
            fundsmanagement.setWbs(this);
        }
        this.fundsmanagement = fundsmanagement;
    }

    public FundsmanagementWbs fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FundsmanagementWbs)) {
            return false;
        }
        return getId() != null && getId().equals(((FundsmanagementWbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FundsmanagementWbs{" +
            "id=" + getId() +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
