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
    @GeneratedValue
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
    private String productlevel;

    @Column(name = "iskey")
    private Integer iskey;

    @Column(name = "isimportant")
    private Integer isimportant;

    @Column(name = "description")
    private String description;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "jhi_type")
    private Integer type;

    @Column(name = "priorty")
    private Integer priorty;

    @Column(name = "wbsid")
    private String wbsid;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement technicaldirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement administrativedirector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_projectpbs__relevantdepartment",
        joinColumns = @JoinColumn(name = "projectpbs_id"),
        inverseJoinColumns = @JoinColumn(name = "relevantdepartment_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Set<Department> relevantdepartments = new HashSet<>();

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
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectpbs")
    private Projectwbs projectwbs;

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

    public String getProductlevel() {
        return this.productlevel;
    }

    public Projectpbs productlevel(String productlevel) {
        this.setProductlevel(productlevel);
        return this;
    }

    public void setProductlevel(String productlevel) {
        this.productlevel = productlevel;
    }

    public Integer getIskey() {
        return this.iskey;
    }

    public Projectpbs iskey(Integer iskey) {
        this.setIskey(iskey);
        return this;
    }

    public void setIskey(Integer iskey) {
        this.iskey = iskey;
    }

    public Integer getIsimportant() {
        return this.isimportant;
    }

    public Projectpbs isimportant(Integer isimportant) {
        this.setIsimportant(isimportant);
        return this;
    }

    public void setIsimportant(Integer isimportant) {
        this.isimportant = isimportant;
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

    public String getWbsid() {
        return this.wbsid;
    }

    public Projectpbs wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
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

    public HrManagement getTechnicaldirector() {
        return this.technicaldirector;
    }

    public void setTechnicaldirector(HrManagement hrManagement) {
        this.technicaldirector = hrManagement;
    }

    public Projectpbs technicaldirector(HrManagement hrManagement) {
        this.setTechnicaldirector(hrManagement);
        return this;
    }

    public HrManagement getAdministrativedirector() {
        return this.administrativedirector;
    }

    public void setAdministrativedirector(HrManagement hrManagement) {
        this.administrativedirector = hrManagement;
    }

    public Projectpbs administrativedirector(HrManagement hrManagement) {
        this.setAdministrativedirector(hrManagement);
        return this;
    }

    public HrManagement getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(HrManagement hrManagement) {
        this.knowingpeople = hrManagement;
    }

    public Projectpbs knowingpeople(HrManagement hrManagement) {
        this.setKnowingpeople(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public Projectpbs auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
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

    public Set<Department> getRelevantdepartments() {
        return this.relevantdepartments;
    }

    public void setRelevantdepartments(Set<Department> departments) {
        this.relevantdepartments = departments;
    }

    public Projectpbs relevantdepartments(Set<Department> departments) {
        this.setRelevantdepartments(departments);
        return this;
    }

    public Projectpbs addRelevantdepartment(Department department) {
        this.relevantdepartments.add(department);
        return this;
    }

    public Projectpbs removeRelevantdepartment(Department department) {
        this.relevantdepartments.remove(department);
        return this;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        if (this.projectwbs != null) {
            this.projectwbs.setProjectpbs(null);
        }
        if (projectwbs != null) {
            projectwbs.setProjectpbs(this);
        }
        this.projectwbs = projectwbs;
    }

    public Projectpbs projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
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
            ", productlevel='" + getProductlevel() + "'" +
            ", iskey=" + getIskey() +
            ", isimportant=" + getIsimportant() +
            ", description='" + getDescription() + "'" +
            ", progress=" + getProgress() +
            ", type=" + getType() +
            ", priorty=" + getPriorty() +
            ", wbsid='" + getWbsid() + "'" +
            ", status='" + getStatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
