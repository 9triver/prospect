package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Department.
 */
@Entity
@Table(name = "department")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_role")
    private String role;

    @Column(name = "officersnum")
    private Integer officersnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department superior;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "departments")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "departments", "frontlines", "roles", "hrmanagements" }, allowSetters = true)
    private Set<Officers> officers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "relevantdepartments")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartments",
            "projectwbs",
            "projects",
        },
        allowSetters = true
    )
    private Set<Projectpbs> pbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "relevantdepartments")
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
    private Set<Projectwbs> wbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "relevantdepartments")
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
    private Set<Workbag> workbags = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Department id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Department name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Department description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return this.role;
    }

    public Department role(String role) {
        this.setRole(role);
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getOfficersnum() {
        return this.officersnum;
    }

    public Department officersnum(Integer officersnum) {
        this.setOfficersnum(officersnum);
        return this;
    }

    public void setOfficersnum(Integer officersnum) {
        this.officersnum = officersnum;
    }

    public Department getSuperior() {
        return this.superior;
    }

    public void setSuperior(Department department) {
        this.superior = department;
    }

    public Department superior(Department department) {
        this.setSuperior(department);
        return this;
    }

    public Set<Officers> getOfficers() {
        return this.officers;
    }

    public void setOfficers(Set<Officers> officers) {
        if (this.officers != null) {
            this.officers.forEach(i -> i.removeDepartments(this));
        }
        if (officers != null) {
            officers.forEach(i -> i.addDepartments(this));
        }
        this.officers = officers;
    }

    public Department officers(Set<Officers> officers) {
        this.setOfficers(officers);
        return this;
    }

    public Department addOfficers(Officers officers) {
        this.officers.add(officers);
        officers.getDepartments().add(this);
        return this;
    }

    public Department removeOfficers(Officers officers) {
        this.officers.remove(officers);
        officers.getDepartments().remove(this);
        return this;
    }

    public Set<Projectpbs> getPbs() {
        return this.pbs;
    }

    public void setPbs(Set<Projectpbs> projectpbs) {
        if (this.pbs != null) {
            this.pbs.forEach(i -> i.removeRelevantdepartment(this));
        }
        if (projectpbs != null) {
            projectpbs.forEach(i -> i.addRelevantdepartment(this));
        }
        this.pbs = projectpbs;
    }

    public Department pbs(Set<Projectpbs> projectpbs) {
        this.setPbs(projectpbs);
        return this;
    }

    public Department addPbs(Projectpbs projectpbs) {
        this.pbs.add(projectpbs);
        projectpbs.getRelevantdepartments().add(this);
        return this;
    }

    public Department removePbs(Projectpbs projectpbs) {
        this.pbs.remove(projectpbs);
        projectpbs.getRelevantdepartments().remove(this);
        return this;
    }

    public Set<Projectwbs> getWbs() {
        return this.wbs;
    }

    public void setWbs(Set<Projectwbs> projectwbs) {
        if (this.wbs != null) {
            this.wbs.forEach(i -> i.removeRelevantdepartment(this));
        }
        if (projectwbs != null) {
            projectwbs.forEach(i -> i.addRelevantdepartment(this));
        }
        this.wbs = projectwbs;
    }

    public Department wbs(Set<Projectwbs> projectwbs) {
        this.setWbs(projectwbs);
        return this;
    }

    public Department addWbs(Projectwbs projectwbs) {
        this.wbs.add(projectwbs);
        projectwbs.getRelevantdepartments().add(this);
        return this;
    }

    public Department removeWbs(Projectwbs projectwbs) {
        this.wbs.remove(projectwbs);
        projectwbs.getRelevantdepartments().remove(this);
        return this;
    }

    public Set<Workbag> getWorkbags() {
        return this.workbags;
    }

    public void setWorkbags(Set<Workbag> workbags) {
        if (this.workbags != null) {
            this.workbags.forEach(i -> i.removeRelevantdepartment(this));
        }
        if (workbags != null) {
            workbags.forEach(i -> i.addRelevantdepartment(this));
        }
        this.workbags = workbags;
    }

    public Department workbags(Set<Workbag> workbags) {
        this.setWorkbags(workbags);
        return this;
    }

    public Department addWorkbag(Workbag workbag) {
        this.workbags.add(workbag);
        workbag.getRelevantdepartments().add(this);
        return this;
    }

    public Department removeWorkbag(Workbag workbag) {
        this.workbags.remove(workbag);
        workbag.getRelevantdepartments().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return getId() != null && getId().equals(((Department) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", role='" + getRole() + "'" +
            ", officersnum=" + getOfficersnum() +
            "}";
    }
}
