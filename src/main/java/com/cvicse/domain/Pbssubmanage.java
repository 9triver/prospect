package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pbssubmanage.
 */
@Entity
@Table(name = "pbssubmanage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Pbssubmanage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "pbssubname")
    private String pbssubname;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Column(name = "responsibledepartment")
    private String responsibledepartment;

    @Column(name = "relevantdepartment")
    private String relevantdepartment;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(value = { "pbssubmanage", "responsibleid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pbssubmanage")
    private Pbsmanage pbsmanage;

    @JsonIgnoreProperties(
        value = { "wbssubmanage", "pbssubmanage", "project", "administratorid", "auditorid", "responsibleid" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pbssubmanage")
    private Wbsmanage wbsmanage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Pbssubmanage id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPbssubname() {
        return this.pbssubname;
    }

    public Pbssubmanage pbssubname(String pbssubname) {
        this.setPbssubname(pbssubname);
        return this;
    }

    public void setPbssubname(String pbssubname) {
        this.pbssubname = pbssubname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Pbssubmanage responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public String getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public Pbssubmanage responsibledepartment(String responsibledepartment) {
        this.setResponsibledepartment(responsibledepartment);
        return this;
    }

    public void setResponsibledepartment(String responsibledepartment) {
        this.responsibledepartment = responsibledepartment;
    }

    public String getRelevantdepartment() {
        return this.relevantdepartment;
    }

    public Pbssubmanage relevantdepartment(String relevantdepartment) {
        this.setRelevantdepartment(relevantdepartment);
        return this;
    }

    public void setRelevantdepartment(String relevantdepartment) {
        this.relevantdepartment = relevantdepartment;
    }

    public String getType() {
        return this.type;
    }

    public Pbssubmanage type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Pbssubmanage starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Pbssubmanage endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Pbssubmanage secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Pbssubmanage auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Pbssubmanage responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Pbssubmanage auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Pbsmanage getPbsmanage() {
        return this.pbsmanage;
    }

    public void setPbsmanage(Pbsmanage pbsmanage) {
        if (this.pbsmanage != null) {
            this.pbsmanage.setPbssubmanage(null);
        }
        if (pbsmanage != null) {
            pbsmanage.setPbssubmanage(this);
        }
        this.pbsmanage = pbsmanage;
    }

    public Pbssubmanage pbsmanage(Pbsmanage pbsmanage) {
        this.setPbsmanage(pbsmanage);
        return this;
    }

    public Wbsmanage getWbsmanage() {
        return this.wbsmanage;
    }

    public void setWbsmanage(Wbsmanage wbsmanage) {
        if (this.wbsmanage != null) {
            this.wbsmanage.setPbssubmanage(null);
        }
        if (wbsmanage != null) {
            wbsmanage.setPbssubmanage(this);
        }
        this.wbsmanage = wbsmanage;
    }

    public Pbssubmanage wbsmanage(Wbsmanage wbsmanage) {
        this.setWbsmanage(wbsmanage);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pbssubmanage)) {
            return false;
        }
        return getId() != null && getId().equals(((Pbssubmanage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pbssubmanage{" +
            "id=" + getId() +
            ", pbssubname='" + getPbssubname() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", responsibledepartment='" + getResponsibledepartment() + "'" +
            ", relevantdepartment='" + getRelevantdepartment() + "'" +
            ", type='" + getType() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            "}";
    }
}
