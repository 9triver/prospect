package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.OtherPaymenttype;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OtherPayment.
 */
@Entity
@Table(name = "other_payment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OtherPayment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private OtherPaymenttype type;

    @Column(name = "registertime")
    private LocalDate registertime;

    @Column(name = "subjectid")
    private Integer subjectid;

    @Column(name = "subjectname")
    private String subjectname;

    @Column(name = "paymentamount", precision = 21, scale = 2)
    private BigDecimal paymentamount;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "wbsid")
    private String wbsid;

    @Column(name = "wbsname")
    private String wbsname;

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
            "projectBudgets",
            "workbags",
            "progressPlans",
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
    @JsonIgnoreProperties(value = { "projectwbs", "costControlSystems" }, allowSetters = true)
    private Contract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public OtherPayment id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public OtherPayment name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OtherPaymenttype getType() {
        return this.type;
    }

    public OtherPayment type(OtherPaymenttype type) {
        this.setType(type);
        return this;
    }

    public void setType(OtherPaymenttype type) {
        this.type = type;
    }

    public LocalDate getRegistertime() {
        return this.registertime;
    }

    public OtherPayment registertime(LocalDate registertime) {
        this.setRegistertime(registertime);
        return this;
    }

    public void setRegistertime(LocalDate registertime) {
        this.registertime = registertime;
    }

    public Integer getSubjectid() {
        return this.subjectid;
    }

    public OtherPayment subjectid(Integer subjectid) {
        this.setSubjectid(subjectid);
        return this;
    }

    public void setSubjectid(Integer subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubjectname() {
        return this.subjectname;
    }

    public OtherPayment subjectname(String subjectname) {
        this.setSubjectname(subjectname);
        return this;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public BigDecimal getPaymentamount() {
        return this.paymentamount;
    }

    public OtherPayment paymentamount(BigDecimal paymentamount) {
        this.setPaymentamount(paymentamount);
        return this;
    }

    public void setPaymentamount(BigDecimal paymentamount) {
        this.paymentamount = paymentamount;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public OtherPayment contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return this.contractname;
    }

    public OtherPayment contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getWbsid() {
        return this.wbsid;
    }

    public OtherPayment wbsid(String wbsid) {
        this.setWbsid(wbsid);
        return this;
    }

    public void setWbsid(String wbsid) {
        this.wbsid = wbsid;
    }

    public String getWbsname() {
        return this.wbsname;
    }

    public OtherPayment wbsname(String wbsname) {
        this.setWbsname(wbsname);
        return this;
    }

    public void setWbsname(String wbsname) {
        this.wbsname = wbsname;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public OtherPayment projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
        return this;
    }

    public Contract getContract() {
        return this.contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public OtherPayment contract(Contract contract) {
        this.setContract(contract);
        return this;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public OtherPayment subject(Subject subject) {
        this.setSubject(subject);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OtherPayment)) {
            return false;
        }
        return getId() != null && getId().equals(((OtherPayment) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OtherPayment{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", registertime='" + getRegistertime() + "'" +
            ", subjectid=" + getSubjectid() +
            ", subjectname='" + getSubjectname() + "'" +
            ", paymentamount=" + getPaymentamount() +
            ", contractcode='" + getContractcode() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", wbsid='" + getWbsid() + "'" +
            ", wbsname='" + getWbsname() + "'" +
            "}";
    }
}
