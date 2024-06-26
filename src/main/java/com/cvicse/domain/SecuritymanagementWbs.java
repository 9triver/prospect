package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SecuritymanagementWbs.
 */
@Entity
@Table(name = "securitymanagement_wbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecuritymanagementWbs implements Serializable {

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
    @JsonIgnoreProperties(value = { "project", "creatorid", "auditorid" }, allowSetters = true)
    private AnnualSecurityPlan annualSecurityPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "auditorid", "responsibleid" }, allowSetters = true)
    private Safetycheck safetycheck;

    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbs")
    private Securitymanagement securitymanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SecuritymanagementWbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public SecuritymanagementWbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public SecuritymanagementWbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public SecuritymanagementWbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public SecuritymanagementWbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public SecuritymanagementWbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public AnnualSecurityPlan getAnnualSecurityPlan() {
        return this.annualSecurityPlan;
    }

    public void setAnnualSecurityPlan(AnnualSecurityPlan annualSecurityPlan) {
        this.annualSecurityPlan = annualSecurityPlan;
    }

    public SecuritymanagementWbs annualSecurityPlan(AnnualSecurityPlan annualSecurityPlan) {
        this.setAnnualSecurityPlan(annualSecurityPlan);
        return this;
    }

    public Safetycheck getSafetycheck() {
        return this.safetycheck;
    }

    public void setSafetycheck(Safetycheck safetycheck) {
        this.safetycheck = safetycheck;
    }

    public SecuritymanagementWbs safetycheck(Safetycheck safetycheck) {
        this.setSafetycheck(safetycheck);
        return this;
    }

    public Securitymanagement getSecuritymanagement() {
        return this.securitymanagement;
    }

    public void setSecuritymanagement(Securitymanagement securitymanagement) {
        if (this.securitymanagement != null) {
            this.securitymanagement.setWbs(null);
        }
        if (securitymanagement != null) {
            securitymanagement.setWbs(this);
        }
        this.securitymanagement = securitymanagement;
    }

    public SecuritymanagementWbs securitymanagement(Securitymanagement securitymanagement) {
        this.setSecuritymanagement(securitymanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecuritymanagementWbs)) {
            return false;
        }
        return getId() != null && getId().equals(((SecuritymanagementWbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecuritymanagementWbs{" +
            "id=" + getId() +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
