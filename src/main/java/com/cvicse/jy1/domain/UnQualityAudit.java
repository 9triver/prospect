package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UnQualityAudit.
 */
@Entity
@Table(name = "un_quality_audit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UnQualityAudit implements Serializable {

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

    @Column(name = "unqualityid")
    private String unqualityid;

    @Column(name = "unqualityname")
    private String unqualityname;

    @Column(name = "unqualityunit")
    private String unqualityunit;

    @Column(name = "unqualitytrialgroup")
    private String unqualitytrialgroup;

    @Column(name = "inspector")
    private String inspector;

    @Column(name = "unqualitystage")
    private String unqualitystage;

    @Column(name = "unqualitynumber")
    private Integer unqualitynumber;

    @Column(name = "unqualityintroduction")
    private String unqualityintroduction;

    @Column(name = "unqualitycategory")
    private String unqualitycategory;

    @Column(name = "handlingopinion")
    private String handlingopinion;

    @Column(name = "applicant")
    private String applicant;

    @Column(name = "applicationdate")
    private String applicationdate;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "attachment")
    private String attachment;

    @Column(name = "disposalmethod")
    private String disposalmethod;

    @Column(name = "causeanalysis")
    private String causeanalysis;

    @Column(name = "correctivemeasures")
    private String correctivemeasures;

    @Column(name = "remarks")
    private String remarks;

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

    public UnQualityAudit id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public UnQualityAudit workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getBelongwbsid() {
        return this.belongwbsid;
    }

    public UnQualityAudit belongwbsid(String belongwbsid) {
        this.setBelongwbsid(belongwbsid);
        return this;
    }

    public void setBelongwbsid(String belongwbsid) {
        this.belongwbsid = belongwbsid;
    }

    public String getOutsourcingcontractid() {
        return this.outsourcingcontractid;
    }

    public UnQualityAudit outsourcingcontractid(String outsourcingcontractid) {
        this.setOutsourcingcontractid(outsourcingcontractid);
        return this;
    }

    public void setOutsourcingcontractid(String outsourcingcontractid) {
        this.outsourcingcontractid = outsourcingcontractid;
    }

    public String getUnqualityid() {
        return this.unqualityid;
    }

    public UnQualityAudit unqualityid(String unqualityid) {
        this.setUnqualityid(unqualityid);
        return this;
    }

    public void setUnqualityid(String unqualityid) {
        this.unqualityid = unqualityid;
    }

    public String getUnqualityname() {
        return this.unqualityname;
    }

    public UnQualityAudit unqualityname(String unqualityname) {
        this.setUnqualityname(unqualityname);
        return this;
    }

    public void setUnqualityname(String unqualityname) {
        this.unqualityname = unqualityname;
    }

    public String getUnqualityunit() {
        return this.unqualityunit;
    }

    public UnQualityAudit unqualityunit(String unqualityunit) {
        this.setUnqualityunit(unqualityunit);
        return this;
    }

    public void setUnqualityunit(String unqualityunit) {
        this.unqualityunit = unqualityunit;
    }

    public String getUnqualitytrialgroup() {
        return this.unqualitytrialgroup;
    }

    public UnQualityAudit unqualitytrialgroup(String unqualitytrialgroup) {
        this.setUnqualitytrialgroup(unqualitytrialgroup);
        return this;
    }

    public void setUnqualitytrialgroup(String unqualitytrialgroup) {
        this.unqualitytrialgroup = unqualitytrialgroup;
    }

    public String getInspector() {
        return this.inspector;
    }

    public UnQualityAudit inspector(String inspector) {
        this.setInspector(inspector);
        return this;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getUnqualitystage() {
        return this.unqualitystage;
    }

    public UnQualityAudit unqualitystage(String unqualitystage) {
        this.setUnqualitystage(unqualitystage);
        return this;
    }

    public void setUnqualitystage(String unqualitystage) {
        this.unqualitystage = unqualitystage;
    }

    public Integer getUnqualitynumber() {
        return this.unqualitynumber;
    }

    public UnQualityAudit unqualitynumber(Integer unqualitynumber) {
        this.setUnqualitynumber(unqualitynumber);
        return this;
    }

    public void setUnqualitynumber(Integer unqualitynumber) {
        this.unqualitynumber = unqualitynumber;
    }

    public String getUnqualityintroduction() {
        return this.unqualityintroduction;
    }

    public UnQualityAudit unqualityintroduction(String unqualityintroduction) {
        this.setUnqualityintroduction(unqualityintroduction);
        return this;
    }

    public void setUnqualityintroduction(String unqualityintroduction) {
        this.unqualityintroduction = unqualityintroduction;
    }

    public String getUnqualitycategory() {
        return this.unqualitycategory;
    }

    public UnQualityAudit unqualitycategory(String unqualitycategory) {
        this.setUnqualitycategory(unqualitycategory);
        return this;
    }

    public void setUnqualitycategory(String unqualitycategory) {
        this.unqualitycategory = unqualitycategory;
    }

    public String getHandlingopinion() {
        return this.handlingopinion;
    }

    public UnQualityAudit handlingopinion(String handlingopinion) {
        this.setHandlingopinion(handlingopinion);
        return this;
    }

    public void setHandlingopinion(String handlingopinion) {
        this.handlingopinion = handlingopinion;
    }

    public String getApplicant() {
        return this.applicant;
    }

    public UnQualityAudit applicant(String applicant) {
        this.setApplicant(applicant);
        return this;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApplicationdate() {
        return this.applicationdate;
    }

    public UnQualityAudit applicationdate(String applicationdate) {
        this.setApplicationdate(applicationdate);
        return this;
    }

    public void setApplicationdate(String applicationdate) {
        this.applicationdate = applicationdate;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public UnQualityAudit auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAttachment() {
        return this.attachment;
    }

    public UnQualityAudit attachment(String attachment) {
        this.setAttachment(attachment);
        return this;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getDisposalmethod() {
        return this.disposalmethod;
    }

    public UnQualityAudit disposalmethod(String disposalmethod) {
        this.setDisposalmethod(disposalmethod);
        return this;
    }

    public void setDisposalmethod(String disposalmethod) {
        this.disposalmethod = disposalmethod;
    }

    public String getCauseanalysis() {
        return this.causeanalysis;
    }

    public UnQualityAudit causeanalysis(String causeanalysis) {
        this.setCauseanalysis(causeanalysis);
        return this;
    }

    public void setCauseanalysis(String causeanalysis) {
        this.causeanalysis = causeanalysis;
    }

    public String getCorrectivemeasures() {
        return this.correctivemeasures;
    }

    public UnQualityAudit correctivemeasures(String correctivemeasures) {
        this.setCorrectivemeasures(correctivemeasures);
        return this;
    }

    public void setCorrectivemeasures(String correctivemeasures) {
        this.correctivemeasures = correctivemeasures;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public UnQualityAudit remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public UnQualityAudit workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnQualityAudit)) {
            return false;
        }
        return getId() != null && getId().equals(((UnQualityAudit) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnQualityAudit{" +
            "id=" + getId() +
            ", workbagid='" + getWorkbagid() + "'" +
            ", belongwbsid='" + getBelongwbsid() + "'" +
            ", outsourcingcontractid='" + getOutsourcingcontractid() + "'" +
            ", unqualityid='" + getUnqualityid() + "'" +
            ", unqualityname='" + getUnqualityname() + "'" +
            ", unqualityunit='" + getUnqualityunit() + "'" +
            ", unqualitytrialgroup='" + getUnqualitytrialgroup() + "'" +
            ", inspector='" + getInspector() + "'" +
            ", unqualitystage='" + getUnqualitystage() + "'" +
            ", unqualitynumber=" + getUnqualitynumber() +
            ", unqualityintroduction='" + getUnqualityintroduction() + "'" +
            ", unqualitycategory='" + getUnqualitycategory() + "'" +
            ", handlingopinion='" + getHandlingopinion() + "'" +
            ", applicant='" + getApplicant() + "'" +
            ", applicationdate='" + getApplicationdate() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", attachment='" + getAttachment() + "'" +
            ", disposalmethod='" + getDisposalmethod() + "'" +
            ", causeanalysis='" + getCauseanalysis() + "'" +
            ", correctivemeasures='" + getCorrectivemeasures() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
