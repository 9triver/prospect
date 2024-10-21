package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CommunicationRecord.
 */
@Entity
@Table(name = "communication_record")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommunicationRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "wbsname")
    private String wbsname;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "associationmeetingname")
    private String associationmeetingname;

    @Column(name = "communicationtime")
    private LocalDate communicationtime;

    @Column(name = "communicationlocation")
    private String communicationlocation;

    @Column(name = "communicationcontent")
    private String communicationcontent;

    @Column(name = "auditorid")
    private String auditorid;

    @Column(name = "auditorname")
    private String auditorname;

    @Column(name = "remarks")
    private String remarks;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunicationDictionary communication;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommunicationFormDictionary workCommunicationForm;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public CommunicationRecord id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public CommunicationRecord wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public CommunicationRecord wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public CommunicationRecord workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getAssociationmeetingname() {
        return this.associationmeetingname;
    }

    public CommunicationRecord associationmeetingname(String associationmeetingname) {
        this.setAssociationmeetingname(associationmeetingname);
        return this;
    }

    public void setAssociationmeetingname(String associationmeetingname) {
        this.associationmeetingname = associationmeetingname;
    }

    public LocalDate getCommunicationtime() {
        return this.communicationtime;
    }

    public CommunicationRecord communicationtime(LocalDate communicationtime) {
        this.setCommunicationtime(communicationtime);
        return this;
    }

    public void setCommunicationtime(LocalDate communicationtime) {
        this.communicationtime = communicationtime;
    }

    public String getCommunicationlocation() {
        return this.communicationlocation;
    }

    public CommunicationRecord communicationlocation(String communicationlocation) {
        this.setCommunicationlocation(communicationlocation);
        return this;
    }

    public void setCommunicationlocation(String communicationlocation) {
        this.communicationlocation = communicationlocation;
    }

    public String getCommunicationcontent() {
        return this.communicationcontent;
    }

    public CommunicationRecord communicationcontent(String communicationcontent) {
        this.setCommunicationcontent(communicationcontent);
        return this;
    }

    public void setCommunicationcontent(String communicationcontent) {
        this.communicationcontent = communicationcontent;
    }

    public String getAuditorid() {
        return this.auditorid;
    }

    public CommunicationRecord auditorid(String auditorid) {
        this.setAuditorid(auditorid);
        return this;
    }

    public void setAuditorid(String auditorid) {
        this.auditorid = auditorid;
    }

    public String getAuditorname() {
        return this.auditorname;
    }

    public CommunicationRecord auditorname(String auditorname) {
        this.setAuditorname(auditorname);
        return this;
    }

    public void setAuditorname(String auditorname) {
        this.auditorname = auditorname;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public CommunicationRecord remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public CommunicationRecord projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public CommunicationRecord workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public CommunicationDictionary getCommunication() {
        return this.communication;
    }

    public void setCommunication(CommunicationDictionary communicationDictionary) {
        this.communication = communicationDictionary;
    }

    public CommunicationRecord communication(CommunicationDictionary communicationDictionary) {
        this.setCommunication(communicationDictionary);
        return this;
    }

    public CommunicationFormDictionary getWorkCommunicationForm() {
        return this.workCommunicationForm;
    }

    public void setWorkCommunicationForm(CommunicationFormDictionary communicationFormDictionary) {
        this.workCommunicationForm = communicationFormDictionary;
    }

    public CommunicationRecord workCommunicationForm(CommunicationFormDictionary communicationFormDictionary) {
        this.setWorkCommunicationForm(communicationFormDictionary);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationRecord)) {
            return false;
        }
        return getId() != null && getId().equals(((CommunicationRecord) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationRecord{" +
            "id=" + getId() +
            ", wbsid='" + getWbsid() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", associationmeetingname='" + getAssociationmeetingname() + "'" +
            ", communicationtime='" + getCommunicationtime() + "'" +
            ", communicationlocation='" + getCommunicationlocation() + "'" +
            ", communicationcontent='" + getCommunicationcontent() + "'" +
            ", auditorid='" + getAuditorid() + "'" +
            ", auditorname='" + getAuditorname() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
