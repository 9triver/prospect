package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Permission.
 */
@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "permissionid")
    private Long permissionid;

    @Column(name = "permissionname")
    private String permissionname;

    @Column(name = "description")
    private String description;

    @JsonIgnoreProperties(value = { "permission", "officers" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "permission")
    private Role role;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Permission id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionid() {
        return this.permissionid;
    }

    public Permission permissionid(Long permissionid) {
        this.setPermissionid(permissionid);
        return this;
    }

    public void setPermissionid(Long permissionid) {
        this.permissionid = permissionid;
    }

    public String getPermissionname() {
        return this.permissionname;
    }

    public Permission permissionname(String permissionname) {
        this.setPermissionname(permissionname);
        return this;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public String getDescription() {
        return this.description;
    }

    public Permission description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        if (this.role != null) {
            this.role.setPermission(null);
        }
        if (role != null) {
            role.setPermission(this);
        }
        this.role = role;
    }

    public Permission role(Role role) {
        this.setRole(role);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Permission)) {
            return false;
        }
        return getId() != null && getId().equals(((Permission) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Permission{" +
            "id=" + getId() +
            ", permissionid=" + getPermissionid() +
            ", permissionname='" + getPermissionname() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
