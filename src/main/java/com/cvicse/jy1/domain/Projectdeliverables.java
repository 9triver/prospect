package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectdeliverables.
 */
@Entity
@Table(name = "projectdeliverables")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectdeliverables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "parentcode")
    private String parentcode;

    @Column(name = "is_submit")
    private Boolean isSubmit;

    @Column(name = "status")
    private String status;

    @JsonIgnoreProperties(value = { "projectdeliverables" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Deliverables code;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectdeliverables")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "projectpbs",
            "responsibleperson",
            "technicaldirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "projectdeliverables",
            "relevantdepartments",
            "workbags",
            "progressPlans",
            "projectBudgets",
            "projects",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "projectTotalwbs",
        },
        allowSetters = true
    )
    private Set<Projectwbs> belongwbsids = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectdeliverables")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "projectmanager",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "department",
            "projectdeliverables",
            "relevantdepartments",
            "wbsids",
            "works",
            "outsourcingContract",
        },
        allowSetters = true
    )
    private Set<Workbag> belongworkbagids = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Projectdeliverables id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParentcode() {
        return this.parentcode;
    }

    public Projectdeliverables parentcode(String parentcode) {
        this.setParentcode(parentcode);
        return this;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public Boolean getIsSubmit() {
        return this.isSubmit;
    }

    public Projectdeliverables isSubmit(Boolean isSubmit) {
        this.setIsSubmit(isSubmit);
        return this;
    }

    public void setIsSubmit(Boolean isSubmit) {
        this.isSubmit = isSubmit;
    }

    public String getStatus() {
        return this.status;
    }

    public Projectdeliverables status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Deliverables getCode() {
        return this.code;
    }

    public void setCode(Deliverables deliverables) {
        this.code = deliverables;
    }

    public Projectdeliverables code(Deliverables deliverables) {
        this.setCode(deliverables);
        return this;
    }

    public Set<Projectwbs> getBelongwbsids() {
        return this.belongwbsids;
    }

    public void setBelongwbsids(Set<Projectwbs> projectwbs) {
        if (this.belongwbsids != null) {
            this.belongwbsids.forEach(i -> i.removeProjectdeliverables(this));
        }
        if (projectwbs != null) {
            projectwbs.forEach(i -> i.addProjectdeliverables(this));
        }
        this.belongwbsids = projectwbs;
    }

    public Projectdeliverables belongwbsids(Set<Projectwbs> projectwbs) {
        this.setBelongwbsids(projectwbs);
        return this;
    }

    public Projectdeliverables addBelongwbsid(Projectwbs projectwbs) {
        this.belongwbsids.add(projectwbs);
        projectwbs.getProjectdeliverables().add(this);
        return this;
    }

    public Projectdeliverables removeBelongwbsid(Projectwbs projectwbs) {
        this.belongwbsids.remove(projectwbs);
        projectwbs.getProjectdeliverables().remove(this);
        return this;
    }

    public Set<Workbag> getBelongworkbagids() {
        return this.belongworkbagids;
    }

    public void setBelongworkbagids(Set<Workbag> workbags) {
        if (this.belongworkbagids != null) {
            this.belongworkbagids.forEach(i -> i.removeProjectdeliverables(this));
        }
        if (workbags != null) {
            workbags.forEach(i -> i.addProjectdeliverables(this));
        }
        this.belongworkbagids = workbags;
    }

    public Projectdeliverables belongworkbagids(Set<Workbag> workbags) {
        this.setBelongworkbagids(workbags);
        return this;
    }

    public Projectdeliverables addBelongworkbagid(Workbag workbag) {
        this.belongworkbagids.add(workbag);
        workbag.getProjectdeliverables().add(this);
        return this;
    }

    public Projectdeliverables removeBelongworkbagid(Workbag workbag) {
        this.belongworkbagids.remove(workbag);
        workbag.getProjectdeliverables().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectdeliverables)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectdeliverables) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectdeliverables{" +
            "id=" + getId() +
            ", parentcode='" + getParentcode() + "'" +
            ", isSubmit='" + getIsSubmit() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
