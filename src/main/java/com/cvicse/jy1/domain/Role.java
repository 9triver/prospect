package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Role.
 */
@Entity
@Table(name = "jhi_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "rolename")
    private String rolename;

    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_jhi_role__permission",
        joinColumns = @JoinColumn(name = "jhi_role_id"),
        inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "roles" }, allowSetters = true)
    private Set<Permission> permissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Set<Officers> officers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Role id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRolename() {
        return this.rolename;
    }

    public Role rolename(String rolename) {
        this.setRolename(rolename);
        return this;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getDescription() {
        return this.description;
    }

    public Role description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Role permissions(Set<Permission> permissions) {
        this.setPermissions(permissions);
        return this;
    }

    public Role addPermission(Permission permission) {
        this.permissions.add(permission);
        return this;
    }

    public Role removePermission(Permission permission) {
        this.permissions.remove(permission);
        return this;
    }

    public Set<Officers> getOfficers() {
        return this.officers;
    }

    public void setOfficers(Set<Officers> officers) {
        if (this.officers != null) {
            this.officers.forEach(i -> i.removeRole(this));
        }
        if (officers != null) {
            officers.forEach(i -> i.addRole(this));
        }
        this.officers = officers;
    }

    public Role officers(Set<Officers> officers) {
        this.setOfficers(officers);
        return this;
    }

    public Role addOfficers(Officers officers) {
        this.officers.add(officers);
        officers.getRoles().add(this);
        return this;
    }

    public Role removeOfficers(Officers officers) {
        this.officers.remove(officers);
        officers.getRoles().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Role)) {
            return false;
        }
        return getId() != null && getId().equals(((Role) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Role{" +
            "id=" + getId() +
            ", rolename='" + getRolename() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
