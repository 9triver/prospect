package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Secrecysystem.
 */
@Entity
@Table(name = "secrecysystem")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Secrecysystem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "publishedby")
    private String publishedby;

    @Column(name = "documentname")
    private String documentname;

    @Column(name = "documenttype")
    private Integer documenttype;

    @Column(name = "documentsize")
    private Long documentsize;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(value = { "secrecysystem", "creatorid", "auditorid", "projectid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "secrecysystem")
    private ProjectSecrecy projectSecrecy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Secrecysystem id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublishedby() {
        return this.publishedby;
    }

    public Secrecysystem publishedby(String publishedby) {
        this.setPublishedby(publishedby);
        return this;
    }

    public void setPublishedby(String publishedby) {
        this.publishedby = publishedby;
    }

    public String getDocumentname() {
        return this.documentname;
    }

    public Secrecysystem documentname(String documentname) {
        this.setDocumentname(documentname);
        return this;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public Integer getDocumenttype() {
        return this.documenttype;
    }

    public Secrecysystem documenttype(Integer documenttype) {
        this.setDocumenttype(documenttype);
        return this;
    }

    public void setDocumenttype(Integer documenttype) {
        this.documenttype = documenttype;
    }

    public Long getDocumentsize() {
        return this.documentsize;
    }

    public Secrecysystem documentsize(Long documentsize) {
        this.setDocumentsize(documentsize);
        return this;
    }

    public void setDocumentsize(Long documentsize) {
        this.documentsize = documentsize;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Secrecysystem secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Secrecysystem auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Secrecysystem creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Secrecysystem auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public ProjectSecrecy getProjectSecrecy() {
        return this.projectSecrecy;
    }

    public void setProjectSecrecy(ProjectSecrecy projectSecrecy) {
        if (this.projectSecrecy != null) {
            this.projectSecrecy.setSecrecysystem(null);
        }
        if (projectSecrecy != null) {
            projectSecrecy.setSecrecysystem(this);
        }
        this.projectSecrecy = projectSecrecy;
    }

    public Secrecysystem projectSecrecy(ProjectSecrecy projectSecrecy) {
        this.setProjectSecrecy(projectSecrecy);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Secrecysystem)) {
            return false;
        }
        return getId() != null && getId().equals(((Secrecysystem) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Secrecysystem{" +
            "id=" + getId() +
            ", publishedby='" + getPublishedby() + "'" +
            ", documentname='" + getDocumentname() + "'" +
            ", documenttype=" + getDocumenttype() +
            ", documentsize=" + getDocumentsize() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
