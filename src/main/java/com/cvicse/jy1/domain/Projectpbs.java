package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectpbs.
 */
@Entity
@Table(name = "projectpbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectpbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    // @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "pbsname")
    private String pbsname;

    @Column(name = "parentpbsid")
    private String parentpbsid;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "productlevel")
    private Integer productlevel;

    @Column(name = "ifkey")
    private Integer ifkey;

    @Column(name = "ifimporttant")
    private Integer ifimporttant;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "priorty")
    private Integer priorty;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    /*技术总监/技术负责人 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers technicaldirector;

    /*行政总监 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers administrativedirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Department relevantdepartment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_projectpbs__projectwbs",
        joinColumns = @JoinColumn(name = "projectpbs_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projectpbs")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "projectpbs", "projectwbs" }, allowSetters = true)
    private Set<Project> projects = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Projectpbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPbsname() {
        return this.pbsname;
    }

    public Projectpbs pbsname(String pbsname) {
        this.setPbsname(pbsname);
        return this;
    }

    public void setPbsname(String pbsname) {
        this.pbsname = pbsname;
    }

    public String getParentpbsid() {
        return this.parentpbsid;
    }

    public Projectpbs parentpbsid(String parentpbsid) {
        this.setParentpbsid(parentpbsid);
        return this;
    }

    public void setParentpbsid(String parentpbsid) {
        this.parentpbsid = parentpbsid;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Projectpbs secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Projectpbs starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Projectpbs endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getProductlevel() {
        return this.productlevel;
    }

    public Projectpbs productlevel(Integer productlevel) {
        this.setProductlevel(productlevel);
        return this;
    }

    public void setProductlevel(Integer productlevel) {
        this.productlevel = productlevel;
    }

    public Integer getIfkey() {
        return this.ifkey;
    }

    public Projectpbs ifkey(Integer ifkey) {
        this.setIfkey(ifkey);
        return this;
    }

    public void setIfkey(Integer ifkey) {
        this.ifkey = ifkey;
    }

    public Integer getIfimporttant() {
        return this.ifimporttant;
    }

    public Projectpbs ifimporttant(Integer ifimporttant) {
        this.setIfimporttant(ifimporttant);
        return this;
    }

    public void setIfimporttant(Integer ifimporttant) {
        this.ifimporttant = ifimporttant;
    }

    public String getDescription() {
        return this.description;
    }

    public Projectpbs description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public Projectpbs progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getType() {
        return this.type;
    }

    public Projectpbs type(Integer type) {
        this.setType(type);
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriorty() {
        return this.priorty;
    }

    public Projectpbs priorty(Integer priorty) {
        this.setPriorty(priorty);
        return this;
    }

    public void setPriorty(Integer priorty) {
        this.priorty = priorty;
    }

    public ProjectStatus getStatus() {
        return this.status;
    }

    public Projectpbs status(ProjectStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Projectpbs auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getTechnicaldirector() {
        return this.technicaldirector;
    }

    public void setTechnicaldirector(Officers officers) {
        this.technicaldirector = officers;
    }

    public Projectpbs technicaldirector(Officers officers) {
        this.setTechnicaldirector(officers);
        return this;
    }

    public Officers getAdministrativedirector() {
        return this.administrativedirector;
    }

    public void setAdministrativedirector(Officers officers) {
        this.administrativedirector = officers;
    }

    public Projectpbs administrativedirector(Officers officers) {
        this.setAdministrativedirector(officers);
        return this;
    }

    public Officers getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(Officers officers) {
        this.knowingpeople = officers;
    }

    public Projectpbs knowingpeople(Officers officers) {
        this.setKnowingpeople(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Projectpbs auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Department getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public void setResponsibledepartment(Department department) {
        this.responsibledepartment = department;
    }

    public Projectpbs responsibledepartment(Department department) {
        this.setResponsibledepartment(department);
        return this;
    }

    public Department getRelevantdepartment() {
        return this.relevantdepartment;
    }

    public void setRelevantdepartment(Department department) {
        this.relevantdepartment = department;
    }

    public Projectpbs relevantdepartment(Department department) {
        this.setRelevantdepartment(department);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public Projectpbs projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Projectpbs addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public Projectpbs removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    public Set<Project> getProjects() {
        return this.projects;
    }

    public void setProjects(Set<Project> projects) {
        if (this.projects != null) {
            this.projects.forEach(i -> i.removeProjectpbs(this));
        }
        if (projects != null) {
            projects.forEach(i -> i.addProjectpbs(this));
        }
        this.projects = projects;
    }

    public Projectpbs projects(Set<Project> projects) {
        this.setProjects(projects);
        return this;
    }

    public Projectpbs addProject(Project project) {
        this.projects.add(project);
        project.getProjectpbs().add(this);
        return this;
    }

    public Projectpbs removeProject(Project project) {
        this.projects.remove(project);
        project.getProjectpbs().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectpbs)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectpbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectpbs{" +
            "id=" + getId() +
            ", pbsname='" + getPbsname() + "'" +
            ", parentpbsid='" + getParentpbsid() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", productlevel=" + getProductlevel() +
            ", ifkey=" + getIfkey() +
            ", ifimporttant=" + getIfimporttant() +
            ", description='" + getDescription() + "'" +
            ", progress=" + getProgress() +
            ", type=" + getType() +
            ", priorty=" + getPriorty() +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
