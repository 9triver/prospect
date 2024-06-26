package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Contractualfunds.
 */
@Entity
@Table(name = "contractualfunds")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Contractualfunds implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "department")
    private String department;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Column(name = "status")
    private Integer status;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "foreigncurrency", precision = 21, scale = 2)
    private BigDecimal foreigncurrency;

    @Column(name = "totalbudget", precision = 21, scale = 2)
    private BigDecimal totalbudget;

    @Column(name = "fundsinplace", precision = 21, scale = 2)
    private BigDecimal fundsinplace;

    @Column(name = "responsibleunitname")
    private String responsibleunitname;

    @Column(name = "audittime")
    private LocalDate audittime;

    @Column(name = "accountbank")
    private String accountbank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Contractualfunds id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return this.department;
    }

    public Contractualfunds department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getYear() {
        return this.year;
    }

    public Contractualfunds year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Contractualfunds starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Contractualfunds endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Contractualfunds status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Contractualfunds secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public BigDecimal getForeigncurrency() {
        return this.foreigncurrency;
    }

    public Contractualfunds foreigncurrency(BigDecimal foreigncurrency) {
        this.setForeigncurrency(foreigncurrency);
        return this;
    }

    public void setForeigncurrency(BigDecimal foreigncurrency) {
        this.foreigncurrency = foreigncurrency;
    }

    public BigDecimal getTotalbudget() {
        return this.totalbudget;
    }

    public Contractualfunds totalbudget(BigDecimal totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public void setTotalbudget(BigDecimal totalbudget) {
        this.totalbudget = totalbudget;
    }

    public BigDecimal getFundsinplace() {
        return this.fundsinplace;
    }

    public Contractualfunds fundsinplace(BigDecimal fundsinplace) {
        this.setFundsinplace(fundsinplace);
        return this;
    }

    public void setFundsinplace(BigDecimal fundsinplace) {
        this.fundsinplace = fundsinplace;
    }

    public String getResponsibleunitname() {
        return this.responsibleunitname;
    }

    public Contractualfunds responsibleunitname(String responsibleunitname) {
        this.setResponsibleunitname(responsibleunitname);
        return this;
    }

    public void setResponsibleunitname(String responsibleunitname) {
        this.responsibleunitname = responsibleunitname;
    }

    public LocalDate getAudittime() {
        return this.audittime;
    }

    public Contractualfunds audittime(LocalDate audittime) {
        this.setAudittime(audittime);
        return this;
    }

    public void setAudittime(LocalDate audittime) {
        this.audittime = audittime;
    }

    public String getAccountbank() {
        return this.accountbank;
    }

    public Contractualfunds accountbank(String accountbank) {
        this.setAccountbank(accountbank);
        return this;
    }

    public void setAccountbank(String accountbank) {
        this.accountbank = accountbank;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Contractualfunds creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Contractualfunds auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contractualfunds)) {
            return false;
        }
        return getId() != null && getId().equals(((Contractualfunds) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contractualfunds{" +
            "id=" + getId() +
            ", department='" + getDepartment() + "'" +
            ", year=" + getYear() +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", status=" + getStatus() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", foreigncurrency=" + getForeigncurrency() +
            ", totalbudget=" + getTotalbudget() +
            ", fundsinplace=" + getFundsinplace() +
            ", responsibleunitname='" + getResponsibleunitname() + "'" +
            ", audittime='" + getAudittime() + "'" +
            ", accountbank='" + getAccountbank() + "'" +
            "}";
    }
}
