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
 * A Wbssubmanage.
 */
@Entity
@Table(name = "wbssubmanage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Wbssubmanage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "pbssubid")
    private String pbssubid;

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
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(
        value = { "wbssubmanage", "pbssubmanage", "project", "administratorid", "auditorid", "responsibleid" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbssubmanage")
    private Wbsmanage wbsmanage;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Wbssubmanage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPbssubid() {
        return this.pbssubid;
    }

    public Wbssubmanage pbssubid(String pbssubid) {
        this.setPbssubid(pbssubid);
        return this;
    }

    public void setPbssubid(String pbssubid) {
        this.pbssubid = pbssubid;
    }

    public String getPbssubname() {
        return this.pbssubname;
    }

    public Wbssubmanage pbssubname(String pbssubname) {
        this.setPbssubname(pbssubname);
        return this;
    }

    public void setPbssubname(String pbssubname) {
        this.pbssubname = pbssubname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Wbssubmanage responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public String getResponsibledepartment() {
        return this.responsibledepartment;
    }

    public Wbssubmanage responsibledepartment(String responsibledepartment) {
        this.setResponsibledepartment(responsibledepartment);
        return this;
    }

    public void setResponsibledepartment(String responsibledepartment) {
        this.responsibledepartment = responsibledepartment;
    }

    public String getRelevantdepartment() {
        return this.relevantdepartment;
    }

    public Wbssubmanage relevantdepartment(String relevantdepartment) {
        this.setRelevantdepartment(relevantdepartment);
        return this;
    }

    public void setRelevantdepartment(String relevantdepartment) {
        this.relevantdepartment = relevantdepartment;
    }

    public String getType() {
        return this.type;
    }

    public Wbssubmanage type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Wbssubmanage starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Wbssubmanage endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Wbssubmanage secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Wbssubmanage auditStatus(AuditStatus auditStatus) {
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

    public Wbssubmanage responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Wbssubmanage auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Wbsmanage getWbsmanage() {
        return this.wbsmanage;
    }

    public void setWbsmanage(Wbsmanage wbsmanage) {
        if (this.wbsmanage != null) {
            this.wbsmanage.setWbssubmanage(null);
        }
        if (wbsmanage != null) {
            wbsmanage.setWbssubmanage(this);
        }
        this.wbsmanage = wbsmanage;
    }

    public Wbssubmanage wbsmanage(Wbsmanage wbsmanage) {
        this.setWbsmanage(wbsmanage);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Wbssubmanage)) {
            return false;
        }
        return getId() != null && getId().equals(((Wbssubmanage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Wbssubmanage{" +
            "id=" + getId() +
            ", pbssubid='" + getPbssubid() + "'" +
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
