package com.cvicse.domain;

import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pbsmanage.
 */
@Entity
@Table(name = "pbsmanage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Pbsmanage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "pbsid")
    private String pbsid;

    @Column(name = "pbsname")
    private String pbsname;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "administratorid")
    private String administratorid;

    @Column(name = "administratorname")
    private String administratorname;

    @Column(name = "responsiblename")
    private String responsiblename;

    @Column(name = "department")
    private String department;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_status")
    private AuditStatus auditStatus;

    @Column(name = "audit_userid")
    private String auditUserid;

    @JsonIgnoreProperties(value = { "responsibleid", "auditorid", "pbsmanage", "wbsmanage" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Pbssubmanage pbssubmanage;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Pbsmanage id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPbsid() {
        return this.pbsid;
    }

    public Pbsmanage pbsid(String pbsid) {
        this.setPbsid(pbsid);
        return this;
    }

    public void setPbsid(String pbsid) {
        this.pbsid = pbsid;
    }

    public String getPbsname() {
        return this.pbsname;
    }

    public Pbsmanage pbsname(String pbsname) {
        this.setPbsname(pbsname);
        return this;
    }

    public void setPbsname(String pbsname) {
        this.pbsname = pbsname;
    }

    public Integer getNumber() {
        return this.number;
    }

    public Pbsmanage number(Integer number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public Pbsmanage type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdministratorid() {
        return this.administratorid;
    }

    public Pbsmanage administratorid(String administratorid) {
        this.setAdministratorid(administratorid);
        return this;
    }

    public void setAdministratorid(String administratorid) {
        this.administratorid = administratorid;
    }

    public String getAdministratorname() {
        return this.administratorname;
    }

    public Pbsmanage administratorname(String administratorname) {
        this.setAdministratorname(administratorname);
        return this;
    }

    public void setAdministratorname(String administratorname) {
        this.administratorname = administratorname;
    }

    public String getResponsiblename() {
        return this.responsiblename;
    }

    public Pbsmanage responsiblename(String responsiblename) {
        this.setResponsiblename(responsiblename);
        return this;
    }

    public void setResponsiblename(String responsiblename) {
        this.responsiblename = responsiblename;
    }

    public String getDepartment() {
        return this.department;
    }

    public Pbsmanage department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Pbsmanage secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public AuditStatus getAuditStatus() {
        return this.auditStatus;
    }

    public Pbsmanage auditStatus(AuditStatus auditStatus) {
        this.setAuditStatus(auditStatus);
        return this;
    }

    public void setAuditStatus(AuditStatus auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditUserid() {
        return this.auditUserid;
    }

    public Pbsmanage auditUserid(String auditUserid) {
        this.setAuditUserid(auditUserid);
        return this;
    }

    public void setAuditUserid(String auditUserid) {
        this.auditUserid = auditUserid;
    }

    public Pbssubmanage getPbssubmanage() {
        return this.pbssubmanage;
    }

    public void setPbssubmanage(Pbssubmanage pbssubmanage) {
        this.pbssubmanage = pbssubmanage;
    }

    public Pbsmanage pbssubmanage(Pbssubmanage pbssubmanage) {
        this.setPbssubmanage(pbssubmanage);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Pbsmanage responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Pbsmanage auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pbsmanage)) {
            return false;
        }
        return getId() != null && getId().equals(((Pbsmanage) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pbsmanage{" +
            "id=" + getId() +
            ", pbsid='" + getPbsid() + "'" +
            ", pbsname='" + getPbsname() + "'" +
            ", number=" + getNumber() +
            ", type='" + getType() + "'" +
            ", administratorid='" + getAdministratorid() + "'" +
            ", administratorname='" + getAdministratorname() + "'" +
            ", responsiblename='" + getResponsiblename() + "'" +
            ", department='" + getDepartment() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", auditStatus='" + getAuditStatus() + "'" +
            ", auditUserid='" + getAuditUserid() + "'" +
            "}";
    }
}
