package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.QualityType;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A QualityPlan.
 */
@Entity
@Table(name = "quality_plan")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class QualityPlan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "qualitytype")
    private QualityType qualitytype;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "fileversion")
    private String fileversion;

    @Column(name = "author")
    private String author;

    @Column(name = "attachment")
    private String attachment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "qualityPlan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "qualityReturns", "qualityPlan" }, allowSetters = true)
    private Set<QualityObjectives> qualityObjectives = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "qualityPlan")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "responsibleperson", "auditorid", "qualityObjectives", "qualityPlan" }, allowSetters = true)
    private Set<QualityReturns> qualityReturns = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
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
    private Projectwbs projectwbs;

    @ManyToOne(fetch = FetchType.LAZY)
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
    private Workbag workbag;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public QualityPlan id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public QualityPlan name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QualityType getQualitytype() {
        return this.qualitytype;
    }

    public QualityPlan qualitytype(QualityType qualitytype) {
        this.setQualitytype(qualitytype);
        return this;
    }

    public void setQualitytype(QualityType qualitytype) {
        this.qualitytype = qualitytype;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public QualityPlan secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public QualityPlan wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public QualityPlan workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getFileversion() {
        return this.fileversion;
    }

    public QualityPlan fileversion(String fileversion) {
        this.setFileversion(fileversion);
        return this;
    }

    public void setFileversion(String fileversion) {
        this.fileversion = fileversion;
    }

    public String getAuthor() {
        return this.author;
    }

    public QualityPlan author(String author) {
        this.setAuthor(author);
        return this;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public QualityPlan attachment(String attachment) {
        this.setAttachment(attachment);
        return this;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Set<QualityObjectives> getQualityObjectives() {
        return this.qualityObjectives;
    }

    public void setQualityObjectives(Set<QualityObjectives> qualityObjectives) {
        if (this.qualityObjectives != null) {
            this.qualityObjectives.forEach(i -> i.setQualityPlan(null));
        }
        if (qualityObjectives != null) {
            qualityObjectives.forEach(i -> i.setQualityPlan(this));
        }
        this.qualityObjectives = qualityObjectives;
    }

    public QualityPlan qualityObjectives(Set<QualityObjectives> qualityObjectives) {
        this.setQualityObjectives(qualityObjectives);
        return this;
    }

    public QualityPlan addQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.add(qualityObjectives);
        qualityObjectives.setQualityPlan(this);
        return this;
    }

    public QualityPlan removeQualityObjectives(QualityObjectives qualityObjectives) {
        this.qualityObjectives.remove(qualityObjectives);
        qualityObjectives.setQualityPlan(null);
        return this;
    }

    public Set<QualityReturns> getQualityReturns() {
        return this.qualityReturns;
    }

    public void setQualityReturns(Set<QualityReturns> qualityReturns) {
        if (this.qualityReturns != null) {
            this.qualityReturns.forEach(i -> i.setQualityPlan(null));
        }
        if (qualityReturns != null) {
            qualityReturns.forEach(i -> i.setQualityPlan(this));
        }
        this.qualityReturns = qualityReturns;
    }

    public QualityPlan qualityReturns(Set<QualityReturns> qualityReturns) {
        this.setQualityReturns(qualityReturns);
        return this;
    }

    public QualityPlan addQualityReturns(QualityReturns qualityReturns) {
        this.qualityReturns.add(qualityReturns);
        qualityReturns.setQualityPlan(this);
        return this;
    }

    public QualityPlan removeQualityReturns(QualityReturns qualityReturns) {
        this.qualityReturns.remove(qualityReturns);
        qualityReturns.setQualityPlan(null);
        return this;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public QualityPlan projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public QualityPlan workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QualityPlan)) {
            return false;
        }
        return getId() != null && getId().equals(((QualityPlan) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "QualityPlan{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", qualitytype='" + getQualitytype() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", wbsid='" + getWbsid() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", fileversion='" + getFileversion() + "'" +
            ", author='" + getAuthor() + "'" +
            ", attachment='" + getAttachment() + "'" +
            "}";
    }
}
