package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RegularInspection.
 */
@Entity
@Table(name = "regular_inspection")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RegularInspection implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "workbagname")
    private String workbagname;

    @Column(name = "jhi_type")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "standard")
    private String standard;

    @Column(name = "measurementmethod")
    private String measurementmethod;

    @Column(name = "checkresult")
    private String checkresult;

    @Column(name = "checktarget")
    private String checktarget;

    @Column(name = "checktime")
    private LocalDate checktime;

    @Column(name = "checkcompletion")
    private String checkcompletion;

    @Column(name = "checkstatus")
    private String checkstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleperson;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement designer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement checkperson;

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

    public RegularInspection id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public RegularInspection name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public RegularInspection workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getWorkbagname() {
        return this.workbagname;
    }

    public RegularInspection workbagname(String workbagname) {
        this.setWorkbagname(workbagname);
        return this;
    }

    public void setWorkbagname(String workbagname) {
        this.workbagname = workbagname;
    }

    public String getType() {
        return this.type;
    }

    public RegularInspection type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public RegularInspection secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getStandard() {
        return this.standard;
    }

    public RegularInspection standard(String standard) {
        this.setStandard(standard);
        return this;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getMeasurementmethod() {
        return this.measurementmethod;
    }

    public RegularInspection measurementmethod(String measurementmethod) {
        this.setMeasurementmethod(measurementmethod);
        return this;
    }

    public void setMeasurementmethod(String measurementmethod) {
        this.measurementmethod = measurementmethod;
    }

    public String getCheckresult() {
        return this.checkresult;
    }

    public RegularInspection checkresult(String checkresult) {
        this.setCheckresult(checkresult);
        return this;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public String getChecktarget() {
        return this.checktarget;
    }

    public RegularInspection checktarget(String checktarget) {
        this.setChecktarget(checktarget);
        return this;
    }

    public void setChecktarget(String checktarget) {
        this.checktarget = checktarget;
    }

    public LocalDate getChecktime() {
        return this.checktime;
    }

    public RegularInspection checktime(LocalDate checktime) {
        this.setChecktime(checktime);
        return this;
    }

    public void setChecktime(LocalDate checktime) {
        this.checktime = checktime;
    }

    public String getCheckcompletion() {
        return this.checkcompletion;
    }

    public RegularInspection checkcompletion(String checkcompletion) {
        this.setCheckcompletion(checkcompletion);
        return this;
    }

    public void setCheckcompletion(String checkcompletion) {
        this.checkcompletion = checkcompletion;
    }

    public String getCheckstatus() {
        return this.checkstatus;
    }

    public RegularInspection checkstatus(String checkstatus) {
        this.setCheckstatus(checkstatus);
        return this;
    }

    public void setCheckstatus(String checkstatus) {
        this.checkstatus = checkstatus;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public RegularInspection auditStatus(AuditStatus auditStatus) {
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

    public RegularInspection responsibleperson(HrManagement hrManagement) {
        this.setResponsibleperson(hrManagement);
        return this;
    }

    public HrManagement getDesigner() {
        return this.designer;
    }

    public void setDesigner(HrManagement hrManagement) {
        this.designer = hrManagement;
    }

    public RegularInspection designer(HrManagement hrManagement) {
        this.setDesigner(hrManagement);
        return this;
    }

    public HrManagement getCheckperson() {
        return this.checkperson;
    }

    public void setCheckperson(HrManagement hrManagement) {
        this.checkperson = hrManagement;
    }

    public RegularInspection checkperson(HrManagement hrManagement) {
        this.setCheckperson(hrManagement);
        return this;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public RegularInspection workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RegularInspection)) {
            return false;
        }
        return getId() != null && getId().equals(((RegularInspection) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RegularInspection{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", workbagname='" + getWorkbagname() + "'" +
            ", type='" + getType() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", standard='" + getStandard() + "'" +
            ", measurementmethod='" + getMeasurementmethod() + "'" +
            ", checkresult='" + getCheckresult() + "'" +
            ", checktarget='" + getChecktarget() + "'" +
            ", checktime='" + getChecktime() + "'" +
            ", checkcompletion='" + getCheckcompletion() + "'" +
            ", checkstatus='" + getCheckstatus() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
