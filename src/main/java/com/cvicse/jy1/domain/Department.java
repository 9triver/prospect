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

    @Column(name = "departmentname")
    private String departmentname;

    @Column(name = "officersnum")
    private Integer officersnum;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_department__officers",
        joinColumns = @JoinColumn(name = "department_id"),
        inverseJoinColumns = @JoinColumn(name = "officers_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "departments" }, allowSetters = true)
    private Set<Officers> officers = new HashSet<>();

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

    public String getDepartmentname() {
        return this.departmentname;
    }

    public Department departmentname(String departmentname) {
        this.setDepartmentname(departmentname);
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
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

    public Set<Officers> getOfficers() {
        return this.officers;
    }

    public void setOfficers(Set<Officers> officers) {
        this.officers = officers;
    }

    public Department officers(Set<Officers> officers) {
        this.setOfficers(officers);
        return this;
    }

    public Department addOfficers(Officers officers) {
        this.officers.add(officers);
        return this;
    }

    public Department removeOfficers(Officers officers) {
        this.officers.remove(officers);
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
            ", departmentname='" + getDepartmentname() + "'" +
            ", officersnum=" + getOfficersnum() +
            "}";
    }
}
