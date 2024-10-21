package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Qualitytozero.
 */
@Entity
@Table(name = "qualitytozero")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Qualitytozero implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "belongwbsid")
    private String belongwbsid;

    @Column(name = "outsourcingcontractid")
    private String outsourcingcontractid;

    @Column(name = "qualityproblemid")
    private String qualityproblemid;

    @Column(name = "qualityproblemname")
    private String qualityproblemname;

    @Column(name = "problemhappentime")
    private LocalDate problemhappentime;

    @Column(name = "problemresponsibleperson")
    private String problemresponsibleperson;

    @Column(name = "problemresponsibleunit")
    private String problemresponsibleunit;

    @Column(name = "producttype")
    private String producttype;

    @Column(name = "productname")
    private String productname;

    @Column(name = "problemphenomenon")
    private String problemphenomenon;

    @Column(name = "problemtype")
    private String problemtype;

    @Column(name = "qualitylevel")
    private String qualitylevel;

    @Column(name = "zerotype")
    private String zerotype;

    @Column(name = "problemreasonanalysis")
    private String problemreasonanalysis;

    @Column(name = "problemreasoncategory")
    private String problemreasoncategory;

    @Column(name = "takemeasures")
    private String takemeasures;

    @Column(name = "onebyonecategory")
    private String onebyonecategory;

    @Column(name = "verificationeffect")
    private String verificationeffect;

    @Column(name = "qualityprojectreport")
    private String qualityprojectreport;

    @Column(name = "qualitytozeroreport")
    private String qualitytozeroreport;

    @Column(name = "reviewopinion")
    private String reviewopinion;

    @Column(name = "implementationverificationtable")
    private String implementationverificationtable;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

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

    public Integer getId() {
        return this.id;
    }

    public Qualitytozero id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public Qualitytozero workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getBelongwbsid() {
        return this.belongwbsid;
    }

    public Qualitytozero belongwbsid(String belongwbsid) {
        this.setBelongwbsid(belongwbsid);
        return this;
    }

    public void setBelongwbsid(String belongwbsid) {
        this.belongwbsid = belongwbsid;
    }

    public String getOutsourcingcontractid() {
        return this.outsourcingcontractid;
    }

    public Qualitytozero outsourcingcontractid(String outsourcingcontractid) {
        this.setOutsourcingcontractid(outsourcingcontractid);
        return this;
    }

    public void setOutsourcingcontractid(String outsourcingcontractid) {
        this.outsourcingcontractid = outsourcingcontractid;
    }

    public String getQualityproblemid() {
        return this.qualityproblemid;
    }

    public Qualitytozero qualityproblemid(String qualityproblemid) {
        this.setQualityproblemid(qualityproblemid);
        return this;
    }

    public void setQualityproblemid(String qualityproblemid) {
        this.qualityproblemid = qualityproblemid;
    }

    public String getQualityproblemname() {
        return this.qualityproblemname;
    }

    public Qualitytozero qualityproblemname(String qualityproblemname) {
        this.setQualityproblemname(qualityproblemname);
        return this;
    }

    public void setQualityproblemname(String qualityproblemname) {
        this.qualityproblemname = qualityproblemname;
    }

    public LocalDate getProblemhappentime() {
        return this.problemhappentime;
    }

    public Qualitytozero problemhappentime(LocalDate problemhappentime) {
        this.setProblemhappentime(problemhappentime);
        return this;
    }

    public void setProblemhappentime(LocalDate problemhappentime) {
        this.problemhappentime = problemhappentime;
    }

    public String getProblemresponsibleperson() {
        return this.problemresponsibleperson;
    }

    public Qualitytozero problemresponsibleperson(String problemresponsibleperson) {
        this.setProblemresponsibleperson(problemresponsibleperson);
        return this;
    }

    public void setProblemresponsibleperson(String problemresponsibleperson) {
        this.problemresponsibleperson = problemresponsibleperson;
    }

    public String getProblemresponsibleunit() {
        return this.problemresponsibleunit;
    }

    public Qualitytozero problemresponsibleunit(String problemresponsibleunit) {
        this.setProblemresponsibleunit(problemresponsibleunit);
        return this;
    }

    public void setProblemresponsibleunit(String problemresponsibleunit) {
        this.problemresponsibleunit = problemresponsibleunit;
    }

    public String getProducttype() {
        return this.producttype;
    }

    public Qualitytozero producttype(String producttype) {
        this.setProducttype(producttype);
        return this;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getProductname() {
        return this.productname;
    }

    public Qualitytozero productname(String productname) {
        this.setProductname(productname);
        return this;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProblemphenomenon() {
        return this.problemphenomenon;
    }

    public Qualitytozero problemphenomenon(String problemphenomenon) {
        this.setProblemphenomenon(problemphenomenon);
        return this;
    }

    public void setProblemphenomenon(String problemphenomenon) {
        this.problemphenomenon = problemphenomenon;
    }

    public String getProblemtype() {
        return this.problemtype;
    }

    public Qualitytozero problemtype(String problemtype) {
        this.setProblemtype(problemtype);
        return this;
    }

    public void setProblemtype(String problemtype) {
        this.problemtype = problemtype;
    }

    public String getQualitylevel() {
        return this.qualitylevel;
    }

    public Qualitytozero qualitylevel(String qualitylevel) {
        this.setQualitylevel(qualitylevel);
        return this;
    }

    public void setQualitylevel(String qualitylevel) {
        this.qualitylevel = qualitylevel;
    }

    public String getZerotype() {
        return this.zerotype;
    }

    public Qualitytozero zerotype(String zerotype) {
        this.setZerotype(zerotype);
        return this;
    }

    public void setZerotype(String zerotype) {
        this.zerotype = zerotype;
    }

    public String getProblemreasonanalysis() {
        return this.problemreasonanalysis;
    }

    public Qualitytozero problemreasonanalysis(String problemreasonanalysis) {
        this.setProblemreasonanalysis(problemreasonanalysis);
        return this;
    }

    public void setProblemreasonanalysis(String problemreasonanalysis) {
        this.problemreasonanalysis = problemreasonanalysis;
    }

    public String getProblemreasoncategory() {
        return this.problemreasoncategory;
    }

    public Qualitytozero problemreasoncategory(String problemreasoncategory) {
        this.setProblemreasoncategory(problemreasoncategory);
        return this;
    }

    public void setProblemreasoncategory(String problemreasoncategory) {
        this.problemreasoncategory = problemreasoncategory;
    }

    public String getTakemeasures() {
        return this.takemeasures;
    }

    public Qualitytozero takemeasures(String takemeasures) {
        this.setTakemeasures(takemeasures);
        return this;
    }

    public void setTakemeasures(String takemeasures) {
        this.takemeasures = takemeasures;
    }

    public String getOnebyonecategory() {
        return this.onebyonecategory;
    }

    public Qualitytozero onebyonecategory(String onebyonecategory) {
        this.setOnebyonecategory(onebyonecategory);
        return this;
    }

    public void setOnebyonecategory(String onebyonecategory) {
        this.onebyonecategory = onebyonecategory;
    }

    public String getVerificationeffect() {
        return this.verificationeffect;
    }

    public Qualitytozero verificationeffect(String verificationeffect) {
        this.setVerificationeffect(verificationeffect);
        return this;
    }

    public void setVerificationeffect(String verificationeffect) {
        this.verificationeffect = verificationeffect;
    }

    public String getQualityprojectreport() {
        return this.qualityprojectreport;
    }

    public Qualitytozero qualityprojectreport(String qualityprojectreport) {
        this.setQualityprojectreport(qualityprojectreport);
        return this;
    }

    public void setQualityprojectreport(String qualityprojectreport) {
        this.qualityprojectreport = qualityprojectreport;
    }

    public String getQualitytozeroreport() {
        return this.qualitytozeroreport;
    }

    public Qualitytozero qualitytozeroreport(String qualitytozeroreport) {
        this.setQualitytozeroreport(qualitytozeroreport);
        return this;
    }

    public void setQualitytozeroreport(String qualitytozeroreport) {
        this.qualitytozeroreport = qualitytozeroreport;
    }

    public String getReviewopinion() {
        return this.reviewopinion;
    }

    public Qualitytozero reviewopinion(String reviewopinion) {
        this.setReviewopinion(reviewopinion);
        return this;
    }

    public void setReviewopinion(String reviewopinion) {
        this.reviewopinion = reviewopinion;
    }

    public String getImplementationverificationtable() {
        return this.implementationverificationtable;
    }

    public Qualitytozero implementationverificationtable(String implementationverificationtable) {
        this.setImplementationverificationtable(implementationverificationtable);
        return this;
    }

    public void setImplementationverificationtable(String implementationverificationtable) {
        this.implementationverificationtable = implementationverificationtable;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Qualitytozero auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public Qualitytozero workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Qualitytozero)) {
            return false;
        }
        return getId() != null && getId().equals(((Qualitytozero) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Qualitytozero{" +
            "id=" + getId() +
            ", workbagid='" + getWorkbagid() + "'" +
            ", belongwbsid='" + getBelongwbsid() + "'" +
            ", outsourcingcontractid='" + getOutsourcingcontractid() + "'" +
            ", qualityproblemid='" + getQualityproblemid() + "'" +
            ", qualityproblemname='" + getQualityproblemname() + "'" +
            ", problemhappentime='" + getProblemhappentime() + "'" +
            ", problemresponsibleperson='" + getProblemresponsibleperson() + "'" +
            ", problemresponsibleunit='" + getProblemresponsibleunit() + "'" +
            ", producttype='" + getProducttype() + "'" +
            ", productname='" + getProductname() + "'" +
            ", problemphenomenon='" + getProblemphenomenon() + "'" +
            ", problemtype='" + getProblemtype() + "'" +
            ", qualitylevel='" + getQualitylevel() + "'" +
            ", zerotype='" + getZerotype() + "'" +
            ", problemreasonanalysis='" + getProblemreasonanalysis() + "'" +
            ", problemreasoncategory='" + getProblemreasoncategory() + "'" +
            ", takemeasures='" + getTakemeasures() + "'" +
            ", onebyonecategory='" + getOnebyonecategory() + "'" +
            ", verificationeffect='" + getVerificationeffect() + "'" +
            ", qualityprojectreport='" + getQualityprojectreport() + "'" +
            ", qualitytozeroreport='" + getQualitytozeroreport() + "'" +
            ", reviewopinion='" + getReviewopinion() + "'" +
            ", implementationverificationtable='" + getImplementationverificationtable() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
