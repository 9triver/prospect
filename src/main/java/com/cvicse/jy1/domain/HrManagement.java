package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A HrManagement.
 */
@Entity
@Table(name = "hr_management")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class HrManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "officersid")
    private String officersid;

    @Column(name = "officersname")
    private String officersname;

    @Column(name = "projectid")
    private Integer projectid;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "projectrole")
    private String projectrole;

    @Column(name = "jobgrade")
    private String jobgrade;

    @Column(name = "departmentid")
    private String departmentid;

    @Column(name = "departmentname")
    private String departmentname;

    @Column(name = "frontlineid")
    private String frontlineid;

    @Column(name = "frontlinename")
    private String frontlinename;

    @Column(name = "jobduty")
    private String jobduty;

    @Column(name = "annualworktime")
    private Integer annualworktime;

    @Column(name = "annualtasktarget")
    private String annualtasktarget;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "frontlines", "roles", "hrmanagements" }, allowSetters = true)
    private Officers officers;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public HrManagement id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOfficersid() {
        return this.officersid;
    }

    public HrManagement officersid(String officersid) {
        this.setOfficersid(officersid);
        return this;
    }

    public void setOfficersid(String officersid) {
        this.officersid = officersid;
    }

    public String getOfficersname() {
        return this.officersname;
    }

    public HrManagement officersname(String officersname) {
        this.setOfficersname(officersname);
        return this;
    }

    public void setOfficersname(String officersname) {
        this.officersname = officersname;
    }

    public Integer getProjectid() {
        return this.projectid;
    }

    public HrManagement projectid(Integer projectid) {
        this.setProjectid(projectid);
        return this;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public HrManagement projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProjectrole() {
        return this.projectrole;
    }

    public HrManagement projectrole(String projectrole) {
        this.setProjectrole(projectrole);
        return this;
    }

    public void setProjectrole(String projectrole) {
        this.projectrole = projectrole;
    }

    public String getJobgrade() {
        return this.jobgrade;
    }

    public HrManagement jobgrade(String jobgrade) {
        this.setJobgrade(jobgrade);
        return this;
    }

    public void setJobgrade(String jobgrade) {
        this.jobgrade = jobgrade;
    }

    public String getDepartmentid() {
        return this.departmentid;
    }

    public HrManagement departmentid(String departmentid) {
        this.setDepartmentid(departmentid);
        return this;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return this.departmentname;
    }

    public HrManagement departmentname(String departmentname) {
        this.setDepartmentname(departmentname);
        return this;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getFrontlineid() {
        return this.frontlineid;
    }

    public HrManagement frontlineid(String frontlineid) {
        this.setFrontlineid(frontlineid);
        return this;
    }

    public void setFrontlineid(String frontlineid) {
        this.frontlineid = frontlineid;
    }

    public String getFrontlinename() {
        return this.frontlinename;
    }

    public HrManagement frontlinename(String frontlinename) {
        this.setFrontlinename(frontlinename);
        return this;
    }

    public void setFrontlinename(String frontlinename) {
        this.frontlinename = frontlinename;
    }

    public String getJobduty() {
        return this.jobduty;
    }

    public HrManagement jobduty(String jobduty) {
        this.setJobduty(jobduty);
        return this;
    }

    public void setJobduty(String jobduty) {
        this.jobduty = jobduty;
    }

    public Integer getAnnualworktime() {
        return this.annualworktime;
    }

    public HrManagement annualworktime(Integer annualworktime) {
        this.setAnnualworktime(annualworktime);
        return this;
    }

    public void setAnnualworktime(Integer annualworktime) {
        this.annualworktime = annualworktime;
    }

    public String getAnnualtasktarget() {
        return this.annualtasktarget;
    }

    public HrManagement annualtasktarget(String annualtasktarget) {
        this.setAnnualtasktarget(annualtasktarget);
        return this;
    }

    public void setAnnualtasktarget(String annualtasktarget) {
        this.annualtasktarget = annualtasktarget;
    }

    public Officers getOfficers() {
        return this.officers;
    }

    public void setOfficers(Officers officers) {
        this.officers = officers;
    }

    public HrManagement officers(Officers officers) {
        this.setOfficers(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HrManagement)) {
            return false;
        }
        return getId() != null && getId().equals(((HrManagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HrManagement{" +
            "id=" + getId() +
            ", officersid='" + getOfficersid() + "'" +
            ", officersname='" + getOfficersname() + "'" +
            ", projectid=" + getProjectid() +
            ", projectname='" + getProjectname() + "'" +
            ", projectrole='" + getProjectrole() + "'" +
            ", jobgrade='" + getJobgrade() + "'" +
            ", departmentid='" + getDepartmentid() + "'" +
            ", departmentname='" + getDepartmentname() + "'" +
            ", frontlineid='" + getFrontlineid() + "'" +
            ", frontlinename='" + getFrontlinename() + "'" +
            ", jobduty='" + getJobduty() + "'" +
            ", annualworktime=" + getAnnualworktime() +
            ", annualtasktarget='" + getAnnualtasktarget() + "'" +
            "}";
    }
}
