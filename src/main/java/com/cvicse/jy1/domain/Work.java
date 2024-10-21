package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Work.
 */
@Entity
@Table(name = "jhi_work")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Work implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "description")
    private String description;

    @Column(name = "workbagid")
    private String workbagid;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "works")
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

    public Work id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Work name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Work secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getDescription() {
        return this.description;
    }

    public Work description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public Work workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Work auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Set<Workbag> getWorkbags() {
        return this.workbags;
    }

    public void setWorkbags(Set<Workbag> workbags) {
        if (this.workbags != null) {
            this.workbags.forEach(i -> i.removeWork(this));
        }
        if (workbags != null) {
            workbags.forEach(i -> i.addWork(this));
        }
        this.workbags = workbags;
    }

    public Work workbags(Set<Workbag> workbags) {
        this.setWorkbags(workbags);
        return this;
    }

    public Work addWorkbag(Workbag workbag) {
        this.workbags.add(workbag);
        workbag.getWorks().add(this);
        return this;
    }

    public Work removeWorkbag(Workbag workbag) {
        this.workbags.remove(workbag);
        workbag.getWorks().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Work)) {
            return false;
        }
        return getId() != null && getId().equals(((Work) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Work{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", description='" + getDescription() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
