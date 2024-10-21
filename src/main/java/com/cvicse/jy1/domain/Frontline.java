package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Frontline.
 */
@Entity
@Table(name = "frontline")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Frontline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "frontlines")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "departments", "frontlines", "roles", "hrmanagements" }, allowSetters = true)
    private Set<Officers> officers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Frontline id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Frontline name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Frontline description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Officers> getOfficers() {
        return this.officers;
    }

    public void setOfficers(Set<Officers> officers) {
        if (this.officers != null) {
            this.officers.forEach(i -> i.removeFrontline(this));
        }
        if (officers != null) {
            officers.forEach(i -> i.addFrontline(this));
        }
        this.officers = officers;
    }

    public Frontline officers(Set<Officers> officers) {
        this.setOfficers(officers);
        return this;
    }

    public Frontline addOfficers(Officers officers) {
        this.officers.add(officers);
        officers.getFrontlines().add(this);
        return this;
    }

    public Frontline removeOfficers(Officers officers) {
        this.officers.remove(officers);
        officers.getFrontlines().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Frontline)) {
            return false;
        }
        return getId() != null && getId().equals(((Frontline) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Frontline{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
