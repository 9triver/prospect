package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
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

    @JsonIgnoreProperties(value = { "role" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Permission permission;

    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "role")
    private Officers officers;

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

    public Permission getPermission() {
        return this.permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Role permission(Permission permission) {
        this.setPermission(permission);
        return this;
    }

    public Officers getOfficers() {
        return this.officers;
    }

    public void setOfficers(Officers officers) {
        if (this.officers != null) {
            this.officers.setRole(null);
        }
        if (officers != null) {
            officers.setRole(this);
        }
        this.officers = officers;
    }

    public Role officers(Officers officers) {
        this.setOfficers(officers);
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
