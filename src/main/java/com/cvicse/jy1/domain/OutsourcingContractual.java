package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OutsourcingContractual.
 */
@Entity
@Table(name = "outsourcing_contractual")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingContractual implements Serializable {

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
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "departments", "roles" }, allowSetters = true)
    private Officers auditorid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_outsourcing_contractual__projectwbs",
        joinColumns = @JoinColumn(name = "outsourcing_contractual_id"),
        inverseJoinColumns = @JoinColumn(name = "projectwbs_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "technicaldirector",
            "administrativedirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "relevantdepartment",
            "department",
            "projects",
            "projectpbs",
            "progressPlans",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "qualityObjectives",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "technicalConditions",
            "projectRisks",
        },
        allowSetters = true
    )
    private Set<Projectwbs> projectwbs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public OutsourcingContractual id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartment() {
        return this.department;
    }

    public OutsourcingContractual department(String department) {
        this.setDepartment(department);
        return this;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getYear() {
        return this.year;
    }

    public OutsourcingContractual year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public OutsourcingContractual starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public OutsourcingContractual endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public OutsourcingContractual status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public OutsourcingContractual secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public BigDecimal getForeigncurrency() {
        return this.foreigncurrency;
    }

    public OutsourcingContractual foreigncurrency(BigDecimal foreigncurrency) {
        this.setForeigncurrency(foreigncurrency);
        return this;
    }

    public void setForeigncurrency(BigDecimal foreigncurrency) {
        this.foreigncurrency = foreigncurrency;
    }

    public BigDecimal getTotalbudget() {
        return this.totalbudget;
    }

    public OutsourcingContractual totalbudget(BigDecimal totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public void setTotalbudget(BigDecimal totalbudget) {
        this.totalbudget = totalbudget;
    }

    public BigDecimal getFundsinplace() {
        return this.fundsinplace;
    }

    public OutsourcingContractual fundsinplace(BigDecimal fundsinplace) {
        this.setFundsinplace(fundsinplace);
        return this;
    }

    public void setFundsinplace(BigDecimal fundsinplace) {
        this.fundsinplace = fundsinplace;
    }

    public String getResponsibleunitname() {
        return this.responsibleunitname;
    }

    public OutsourcingContractual responsibleunitname(String responsibleunitname) {
        this.setResponsibleunitname(responsibleunitname);
        return this;
    }

    public void setResponsibleunitname(String responsibleunitname) {
        this.responsibleunitname = responsibleunitname;
    }

    public LocalDate getAudittime() {
        return this.audittime;
    }

    public OutsourcingContractual audittime(LocalDate audittime) {
        this.setAudittime(audittime);
        return this;
    }

    public void setAudittime(LocalDate audittime) {
        this.audittime = audittime;
    }

    public String getAccountbank() {
        return this.accountbank;
    }

    public OutsourcingContractual accountbank(String accountbank) {
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

    public OutsourcingContractual creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public OutsourcingContractual auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Set<Projectwbs> getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Set<Projectwbs> projectwbs) {
        this.projectwbs = projectwbs;
    }

    public OutsourcingContractual projectwbs(Set<Projectwbs> projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public OutsourcingContractual addProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.add(projectwbs);
        return this;
    }

    public OutsourcingContractual removeProjectwbs(Projectwbs projectwbs) {
        this.projectwbs.remove(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingContractual)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingContractual) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingContractual{" +
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
