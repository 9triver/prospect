package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A IntegratedmanagementWbs.
 */
@Entity
@Table(name = "integratedmanagement_wbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IntegratedmanagementWbs implements Serializable {

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
    @JsonIgnoreProperties(value = { "decument", "responsibleid", "auditorid" }, allowSetters = true)
    private Planstrategy planstrategy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    private Comprehensivecontrol comprehensivecontrol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "progressplanreturns", "auditedbudget" },
        allowSetters = true
    )
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    private Comprehensiveledger comprehensiveledger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid" },
        allowSetters = true
    )
    private Cycleplan cycleplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "document", "monthplan", "projectcharge", "creatorid", "auditorid", "cycleplan" }, allowSetters = true)
    private Annualplan annualplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "document", "planreturns", "projectcharge", "creatorid", "auditorid", "cycleplan", "annualplan" },
        allowSetters = true
    )
    private Monthplan monthplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "planexecute", "progressplan" }, allowSetters = true)
    private Planreturns planreturns;

    @ManyToOne(fetch = FetchType.LAZY)
    private Planmonitor planmonitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "planreturns", "responsibleid", "monthplan" }, allowSetters = true)
    private Planexecute planexecute;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "pbsbaseline", "wbsbaseline" },
        allowSetters = true
    )
    private Projectcharge projectcharge;

    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbs")
    private Integratedmanagement integratedmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public IntegratedmanagementWbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public IntegratedmanagementWbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public IntegratedmanagementWbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public IntegratedmanagementWbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public IntegratedmanagementWbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public IntegratedmanagementWbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public Planstrategy getPlanstrategy() {
        return this.planstrategy;
    }

    public void setPlanstrategy(Planstrategy planstrategy) {
        this.planstrategy = planstrategy;
    }

    public IntegratedmanagementWbs planstrategy(Planstrategy planstrategy) {
        this.setPlanstrategy(planstrategy);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public IntegratedmanagementWbs comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public IntegratedmanagementWbs document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Comprehensiveledger getComprehensiveledger() {
        return this.comprehensiveledger;
    }

    public void setComprehensiveledger(Comprehensiveledger comprehensiveledger) {
        this.comprehensiveledger = comprehensiveledger;
    }

    public IntegratedmanagementWbs comprehensiveledger(Comprehensiveledger comprehensiveledger) {
        this.setComprehensiveledger(comprehensiveledger);
        return this;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        this.cycleplan = cycleplan;
    }

    public IntegratedmanagementWbs cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Annualplan getAnnualplan() {
        return this.annualplan;
    }

    public void setAnnualplan(Annualplan annualplan) {
        this.annualplan = annualplan;
    }

    public IntegratedmanagementWbs annualplan(Annualplan annualplan) {
        this.setAnnualplan(annualplan);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        this.monthplan = monthplan;
    }

    public IntegratedmanagementWbs monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    public Planreturns getPlanreturns() {
        return this.planreturns;
    }

    public void setPlanreturns(Planreturns planreturns) {
        this.planreturns = planreturns;
    }

    public IntegratedmanagementWbs planreturns(Planreturns planreturns) {
        this.setPlanreturns(planreturns);
        return this;
    }

    public Planmonitor getPlanmonitor() {
        return this.planmonitor;
    }

    public void setPlanmonitor(Planmonitor planmonitor) {
        this.planmonitor = planmonitor;
    }

    public IntegratedmanagementWbs planmonitor(Planmonitor planmonitor) {
        this.setPlanmonitor(planmonitor);
        return this;
    }

    public Planexecute getPlanexecute() {
        return this.planexecute;
    }

    public void setPlanexecute(Planexecute planexecute) {
        this.planexecute = planexecute;
    }

    public IntegratedmanagementWbs planexecute(Planexecute planexecute) {
        this.setPlanexecute(planexecute);
        return this;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        this.projectcharge = projectcharge;
    }

    public IntegratedmanagementWbs projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    public Integratedmanagement getIntegratedmanagement() {
        return this.integratedmanagement;
    }

    public void setIntegratedmanagement(Integratedmanagement integratedmanagement) {
        if (this.integratedmanagement != null) {
            this.integratedmanagement.setWbs(null);
        }
        if (integratedmanagement != null) {
            integratedmanagement.setWbs(this);
        }
        this.integratedmanagement = integratedmanagement;
    }

    public IntegratedmanagementWbs integratedmanagement(Integratedmanagement integratedmanagement) {
        this.setIntegratedmanagement(integratedmanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IntegratedmanagementWbs)) {
            return false;
        }
        return getId() != null && getId().equals(((IntegratedmanagementWbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IntegratedmanagementWbs{" +
            "id=" + getId() +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
