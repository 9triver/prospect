package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * A Workbag.
 */
@Entity
@Table(name = "workbag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Workbag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.cvicse.jy1.service.CustomStringIdGenerator")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "pbsid")
    private String pbsid;

    @Column(name = "workbagtype")
    private Integer workbagtype;

    @Column(name = "supplier")
    private String supplier;

    @Column(name = "iskeyimportant")
    private Integer iskeyimportant;

    @Column(name = "keypbsname")
    private String keypbsname;

    @Column(name = "importantpbsname")
    private String importantpbsname;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "description")
    private String description;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "estimatedpurchasingtime")
    private LocalDate estimatedpurchasingtime;

    @Column(name = "progress")
    private Integer progress;

    @Column(name = "issafetywork")
    private Integer issafetywork;

    @Column(name = "remark")
    private String remark;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement projectmanager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement knowingpeople;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement auditorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department responsibledepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department department;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_workbag__projectdeliverables",
        joinColumns = @JoinColumn(name = "workbag_id"),
        inverseJoinColumns = @JoinColumn(name = "projectdeliverables_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "code", "belongwbsids", "belongworkbagids" }, allowSetters = true)
    private Set<Projectdeliverables> projectdeliverables = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_workbag__relevantdepartment",
        joinColumns = @JoinColumn(name = "workbag_id"),
        inverseJoinColumns = @JoinColumn(name = "relevantdepartment_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Set<Department> relevantdepartments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_workbag__wbsid",
        joinColumns = @JoinColumn(name = "workbag_id"),
        inverseJoinColumns = @JoinColumn(name = "wbsid_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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
    private Set<Projectwbs> wbsids = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_workbag__work",
        joinColumns = @JoinColumn(name = "workbag_id"),
        inverseJoinColumns = @JoinColumn(name = "work_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "workbags" }, allowSetters = true)
    private Set<Work> works = new HashSet<>();

    @JsonIgnoreProperties(value = { "workbag", "deliveryContents", "milestoneNodes", "paymentApplications" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "workbag")
    private OutsourcingContract outsourcingContract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Workbag id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Workbag name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPbsid() {
        return this.pbsid;
    }

    public Workbag pbsid(String pbsid) {
        this.setPbsid(pbsid);
        return this;
    }

    public void setPbsid(String pbsid) {
        this.pbsid = pbsid;
    }

    public Integer getWorkbagtype() {
        return this.workbagtype;
    }

    public Workbag workbagtype(Integer workbagtype) {
        this.setWorkbagtype(workbagtype);
        return this;
    }

    public void setWorkbagtype(Integer workbagtype) {
        this.workbagtype = workbagtype;
    }

    public String getSupplier() {
        return this.supplier;
    }

    public Workbag supplier(String supplier) {
        this.setSupplier(supplier);
        return this;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getIskeyimportant() {
        return this.iskeyimportant;
    }

    public Workbag iskeyimportant(Integer iskeyimportant) {
        this.setIskeyimportant(iskeyimportant);
        return this;
    }

    public void setIskeyimportant(Integer iskeyimportant) {
        this.iskeyimportant = iskeyimportant;
    }

    public String getKeypbsname() {
        return this.keypbsname;
    }

    public Workbag keypbsname(String keypbsname) {
        this.setKeypbsname(keypbsname);
        return this;
    }

    public void setKeypbsname(String keypbsname) {
        this.keypbsname = keypbsname;
    }

    public String getImportantpbsname() {
        return this.importantpbsname;
    }

    public Workbag importantpbsname(String importantpbsname) {
        this.setImportantpbsname(importantpbsname);
        return this;
    }

    public void setImportantpbsname(String importantpbsname) {
        this.importantpbsname = importantpbsname;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Workbag secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getDescription() {
        return this.description;
    }

    public Workbag description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Workbag starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Workbag endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public LocalDate getEstimatedpurchasingtime() {
        return this.estimatedpurchasingtime;
    }

    public Workbag estimatedpurchasingtime(LocalDate estimatedpurchasingtime) {
        this.setEstimatedpurchasingtime(estimatedpurchasingtime);
        return this;
    }

    public void setEstimatedpurchasingtime(LocalDate estimatedpurchasingtime) {
        this.estimatedpurchasingtime = estimatedpurchasingtime;
    }

    public Integer getProgress() {
        return this.progress;
    }

    public Workbag progress(Integer progress) {
        this.setProgress(progress);
        return this;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public Integer getIssafetywork() {
        return this.issafetywork;
    }

    public Workbag issafetywork(Integer issafetywork) {
        this.setIssafetywork(issafetywork);
        return this;
    }

    public void setIssafetywork(Integer issafetywork) {
        this.issafetywork = issafetywork;
    }

    public String getRemark() {
        return this.remark;
    }

    public Workbag remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Workbag auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public HrManagement getResponsibleperson() {
        return this.responsibleperson;
    }

    public void setResponsibleperson(HrManagement hrManagement) {
        this.responsibleperson = hrManagement;
    }

    public Workbag responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getProjectmanager() {
        return this.projectmanager;
    }

    public void setProjectmanager(HrManagement hrManagement) {
        this.projectmanager = hrManagement;
    }

    public Workbag projectmanager(HrManagement hrManagement) {
        this.setProjectmanager(hrManagement);
        return this;
    }

    public HrManagement getKnowingpeople() {
        return this.knowingpeople;
    }

    public void setKnowingpeople(HrManagement hrManagement) {
        this.knowingpeople = hrManagement;
    }

    public Workbag knowingpeople(HrManagement hrManagement) {
        this.setKnowingpeople(hrManagement);
        return this;
    }

    public HrManagement getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(HrManagement hrManagement) {
        this.auditorid = hrManagement;
    }

    public Workbag auditorid(HrManagement hrManagement) {
        this.setAuditorid(hrManagement);
        return this;
    }

    public Department getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public void setResponsibledepartment(Department department) {
        this.responsibledepartment = department;
    }

    public Workbag responsibledepartment(Department department) {
        this.setResponsibledepartment(department);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Workbag department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Set<Projectdeliverables> getProjectdeliverables() {
        return this.projectdeliverables;
    }

    public void setProjectdeliverables(Set<Projectdeliverables> projectdeliverables) {
        this.projectdeliverables = projectdeliverables;
    }

    public Workbag projectdeliverables(Set<Projectdeliverables> projectdeliverables) {
        this.setProjectdeliverables(projectdeliverables);
        return this;
    }

    public Workbag addProjectdeliverables(Projectdeliverables projectdeliverables) {
        this.projectdeliverables.add(projectdeliverables);
        return this;
    }

    public Workbag removeProjectdeliverables(Projectdeliverables projectdeliverables) {
        this.projectdeliverables.remove(projectdeliverables);
        return this;
    }

    public Set<Department> getRelevantdepartments() {
        return this.relevantdepartments;
    }

    public void setRelevantdepartments(Set<Department> departments) {
        this.relevantdepartments = departments;
    }

    public Workbag relevantdepartments(Set<Department> departments) {
        this.setRelevantdepartments(departments);
        return this;
    }

    public Workbag addRelevantdepartment(Department department) {
        this.relevantdepartments.add(department);
        return this;
    }

    public Workbag removeRelevantdepartment(Department department) {
        this.relevantdepartments.remove(department);
        return this;
    }

    public Set<Projectwbs> getWbsids() {
        return this.wbsids;
    }

    public void setWbsids(Set<Projectwbs> projectwbs) {
        this.wbsids = projectwbs;
    }

    public Workbag wbsids(Set<Projectwbs> projectwbs) {
        this.setWbsids(projectwbs);
        return this;
    }

    public Workbag addWbsid(Projectwbs projectwbs) {
        this.wbsids.add(projectwbs);
        return this;
    }

    public Workbag removeWbsid(Projectwbs projectwbs) {
        this.wbsids.remove(projectwbs);
        return this;
    }

    public Set<Work> getWorks() {
        return this.works;
    }

    public void setWorks(Set<Work> works) {
        this.works = works;
    }

    public Workbag works(Set<Work> works) {
        this.setWorks(works);
        return this;
    }

    public Workbag addWork(Work work) {
        this.works.add(work);
        return this;
    }

    public Workbag removeWork(Work work) {
        this.works.remove(work);
        return this;
    }

    public OutsourcingContract getOutsourcingContract() {
        return this.outsourcingContract;
    }

    public void setOutsourcingContract(OutsourcingContract outsourcingContract) {
        if (this.outsourcingContract != null) {
            this.outsourcingContract.setWorkbag(null);
        }
        if (outsourcingContract != null) {
            outsourcingContract.setWorkbag(this);
        }
        this.outsourcingContract = outsourcingContract;
    }

    public Workbag outsourcingContract(OutsourcingContract outsourcingContract) {
        this.setOutsourcingContract(outsourcingContract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Workbag)) {
            return false;
        }
        return getId() != null && getId().equals(((Workbag) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Workbag{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", pbsid='" + getPbsid() + "'" +
            ", workbagtype=" + getWorkbagtype() +
            ", supplier='" + getSupplier() + "'" +
            ", iskeyimportant=" + getIskeyimportant() +
            ", keypbsname='" + getKeypbsname() + "'" +
            ", importantpbsname='" + getImportantpbsname() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", description='" + getDescription() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", estimatedpurchasingtime='" + getEstimatedpurchasingtime() + "'" +
            ", progress=" + getProgress() +
            ", issafetywork=" + getIssafetywork() +
            ", remark='" + getRemark() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
