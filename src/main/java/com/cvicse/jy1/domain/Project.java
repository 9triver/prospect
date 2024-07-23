package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Project.
 */
@Entity
@Table(name = "project")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "parentid")
    private String parentid;

    @Column(name = "pbsid")
    private String pbsid;

    @Column(name = "description")
    private String description;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "projecttype")
    private Integer projecttype;

    @Column(name = "priorty")
    private Integer priorty;

    @Column(name = "createdate")
    private LocalDate createdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "progress")
    private Integer progress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_project__projectpbs",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "projectpbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleid", "auditorid", "department", "projects" }, allowSetters = true)
    private Set<Projectpbs> projectpbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_project__projectwbs",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleid", "auditorid", "department", "projects" }, allowSetters = true)
    private Set<Projectwbs> projectwbs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Project id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Project projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getParentid() {
        return this.parentid;
    }

    public Project parentid(String parentid) {
        this.setParentid(parentid);
        return this;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getPbsid() {
        return this.pbsid;
    }

    public Project pbsid(String pbsid) {
        this.setPbsid(pbsid);
        return this;
    }

    public void setPbsid(String pbsid) {
        this.pbsid = pbsid;
    }

    public String getDescription() {
        return this.description;
    }

    public Project description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Project number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getProjecttype() {
        return this.projecttype;
    }

    public Project projecttype(Integer projecttype) {
        this.setProjecttype(projecttype);
        return this;
    }

    public void setProjecttype(Integer projecttype) {
        this.projecttype = projecttype;
    }

    public Integer getPriorty() {
        return this.priorty;
    }

    public Project priorty(Integer priorty) {
        this.setPriorty(priorty);
        return this;
    }

    public void setPriorty(Integer priorty) {
        this.priorty = priorty;
    }

    public LocalDate getCreatedate() {
        return this.createdate;
    }

    public Project createdate(LocalDate createdate) {
        this.setCreatedate(createdate);
        return this;
    }

    public void setCreatedate(LocalDate createdate) {
        this.createdate = createdate;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Project secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Project status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Project auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public Project progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Set<Projectpbs> getProjectpbs() {
        return this.projectpbs;
    }

    public void setProjectpbs(Set<Projectpbs> projectpbs) {
        this.projectpbs = projectpbs;
    }

    public Project projectpbs(Set<Projectpbs> projectpbs) {
        this.setProjectpbs(projectpbs);
        return this;
    }

    public Project addProjectpbs(Projectpbs projectpbs) {
        this.projectpbs.add(projectpbs);
        return this;
    }

    public Project removeProjectpbs(Projectpbs projectpbs) {
        this.projectpbs.remove(projectpbs);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public Project projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Project addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public Project removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Project)) {
            return false;
        }
        return getId() != null && getId().equals(((Project) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Project{" +
            "id=" + getId() +
            ", projectname='" + getProjectname() + "'" +
            ", parentid='" + getParentid() + "'" +
            ", pbsid='" + getPbsid() + "'" +
            ", description='" + getDescription() + "'" +
            ", number=" + getNumber() +
            ", projecttype=" + getProjecttype() +
            ", priorty=" + getPriorty() +
            ", createdate='" + getCreatedate() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", progress=" + getProgress() +
            "}";
    }
}
