package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RiskReturn.
 */
@Entity
@Table(name = "risk_return")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RiskReturn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "belongriskid")
    private Integer belongriskid;

    @Column(name = "status")
    private String status;

    @Column(name = "closestatus")
    private String closestatus;

    @Column(name = "evidencefile")
    private String evidencefile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "riskReturn")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "planReturns",
            "responsibleperson",
            "cooperatingperson",
            "auditorid",
            "responsibledepartment",
            "cooperatingdepartment",
            "projectwbs",
            "projectRisks",
            "riskReturn",
        },
        allowSetters = true
    )
    private Set<ProgressPlan> progressPlans = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "wbsid", "workbag", "frontlineid", "systemLevel", "riskType", "riskLevel", "riskPossibility", "returns", "progressPlans",
        },
        allowSetters = true
    )
    private ProjectRisk riskid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement creatorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public RiskReturn id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBelongriskid() {
        return this.belongriskid;
    }

    public RiskReturn belongriskid(Integer belongriskid) {
        this.setBelongriskid(belongriskid);
        return this;
    }

    public void setBelongriskid(Integer belongriskid) {
        this.belongriskid = belongriskid;
    }

    public String getStatus() {
        return this.status;
    }

    public RiskReturn status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClosestatus() {
        return this.closestatus;
    }

    public RiskReturn closestatus(String closestatus) {
        this.setClosestatus(closestatus);
        return this;
    }

    public void setClosestatus(String closestatus) {
        this.closestatus = closestatus;
    }

    public String getEvidencefile() {
        return this.evidencefile;
    }

    public RiskReturn evidencefile(String evidencefile) {
        this.setEvidencefile(evidencefile);
        return this;
    }

    public void setEvidencefile(String evidencefile) {
        this.evidencefile = evidencefile;
    }

    public Set<ProgressPlan> getProgressPlans() {
        return this.progressPlans;
    }

    public void setProgressPlans(Set<ProgressPlan> progressPlans) {
        if (this.progressPlans != null) {
            this.progressPlans.forEach(i -> i.setRiskReturn(null));
        }
        if (progressPlans != null) {
            progressPlans.forEach(i -> i.setRiskReturn(this));
        }
        this.progressPlans = progressPlans;
    }

    public RiskReturn progressPlans(Set<ProgressPlan> progressPlans) {
        this.setProgressPlans(progressPlans);
        return this;
    }

    public RiskReturn addProgressPlans(ProgressPlan progressPlan) {
        this.progressPlans.add(progressPlan);
        progressPlan.setRiskReturn(this);
        return this;
    }

    public RiskReturn removeProgressPlans(ProgressPlan progressPlan) {
        this.progressPlans.remove(progressPlan);
        progressPlan.setRiskReturn(null);
        return this;
    }

    public ProjectRisk getRiskid() {
        return this.riskid;
    }

    public void setRiskid(ProjectRisk projectRisk) {
        this.riskid = projectRisk;
    }

    public RiskReturn riskid(ProjectRisk projectRisk) {
        this.setRiskid(projectRisk);
        return this;
    }

    public HrManagement getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(HrManagement hrManagement) {
        this.creatorid = hrManagement;
    }

    public RiskReturn creatorid(HrManagement hrManagement) {
        this.setCreatorid(hrManagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskReturn)) {
            return false;
        }
        return getId() != null && getId().equals(((RiskReturn) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskReturn{" +
            "id=" + getId() +
            ", belongriskid=" + getBelongriskid() +
            ", status='" + getStatus() + "'" +
            ", closestatus='" + getClosestatus() + "'" +
            ", evidencefile='" + getEvidencefile() + "'" +
            "}";
    }
}
