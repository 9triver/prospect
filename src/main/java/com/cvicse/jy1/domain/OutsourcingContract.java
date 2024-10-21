package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OutsourcingContract.
 */
@Entity
@Table(name = "outsourcing_contract")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "contractid")
    private String contractid;

    @Column(name = "contractcode")
    private String contractcode;

    @Column(name = "contractname")
    private String contractname;

    @Column(name = "contractqualityid")
    private String contractqualityid;

    @Column(name = "contractcostid")
    private String contractcostid;

    @Column(name = "contractfinanceid")
    private String contractfinanceid;

    @Column(name = "projectid")
    private String projectid;

    @Column(name = "projectsecretlevel")
    private String projectsecretlevel;

    @Column(name = "counterpartyunit")
    private String counterpartyunit;

    @Column(name = "negotiationdate")
    private LocalDate negotiationdate;

    @Column(name = "negotiationlocation")
    private String negotiationlocation;

    @Column(name = "negotiator")
    private String negotiator;

    @Column(name = "budgetamount", precision = 21, scale = 2)
    private BigDecimal budgetamount;

    @Column(name = "contractamount", precision = 21, scale = 2)
    private BigDecimal contractamount;

    @Column(name = "approver")
    private String approver;

    @Column(name = "approvaldate")
    private LocalDate approvaldate;

    @Column(name = "contractsecretlevel")
    private String contractsecretlevel;

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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Workbag workbag;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outsourcingContract")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "outsourcingContract" }, allowSetters = true)
    private Set<DeliveryContent> deliveryContents = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outsourcingContract")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "outsourcingContract" }, allowSetters = true)
    private Set<MilestoneNode> milestoneNodes = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "outsourcingContract")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "outsourcingContract" }, allowSetters = true)
    private Set<PaymentApplication> paymentApplications = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public OutsourcingContract id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractid() {
        return this.contractid;
    }

    public OutsourcingContract contractid(String contractid) {
        this.setContractid(contractid);
        return this;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getContractcode() {
        return this.contractcode;
    }

    public OutsourcingContract contractcode(String contractcode) {
        this.setContractcode(contractcode);
        return this;
    }

    public void setContractcode(String contractcode) {
        this.contractcode = contractcode;
    }

    public String getContractname() {
        return this.contractname;
    }

    public OutsourcingContract contractname(String contractname) {
        this.setContractname(contractname);
        return this;
    }

    public void setContractname(String contractname) {
        this.contractname = contractname;
    }

    public String getContractqualityid() {
        return this.contractqualityid;
    }

    public OutsourcingContract contractqualityid(String contractqualityid) {
        this.setContractqualityid(contractqualityid);
        return this;
    }

    public void setContractqualityid(String contractqualityid) {
        this.contractqualityid = contractqualityid;
    }

    public String getContractcostid() {
        return this.contractcostid;
    }

    public OutsourcingContract contractcostid(String contractcostid) {
        this.setContractcostid(contractcostid);
        return this;
    }

    public void setContractcostid(String contractcostid) {
        this.contractcostid = contractcostid;
    }

    public String getContractfinanceid() {
        return this.contractfinanceid;
    }

    public OutsourcingContract contractfinanceid(String contractfinanceid) {
        this.setContractfinanceid(contractfinanceid);
        return this;
    }

    public void setContractfinanceid(String contractfinanceid) {
        this.contractfinanceid = contractfinanceid;
    }

    public String getProjectid() {
        return this.projectid;
    }

    public OutsourcingContract projectid(String projectid) {
        this.setProjectid(projectid);
        return this;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getProjectsecretlevel() {
        return this.projectsecretlevel;
    }

    public OutsourcingContract projectsecretlevel(String projectsecretlevel) {
        this.setProjectsecretlevel(projectsecretlevel);
        return this;
    }

    public void setProjectsecretlevel(String projectsecretlevel) {
        this.projectsecretlevel = projectsecretlevel;
    }

    public String getCounterpartyunit() {
        return this.counterpartyunit;
    }

    public OutsourcingContract counterpartyunit(String counterpartyunit) {
        this.setCounterpartyunit(counterpartyunit);
        return this;
    }

    public void setCounterpartyunit(String counterpartyunit) {
        this.counterpartyunit = counterpartyunit;
    }

    public LocalDate getNegotiationdate() {
        return this.negotiationdate;
    }

    public OutsourcingContract negotiationdate(LocalDate negotiationdate) {
        this.setNegotiationdate(negotiationdate);
        return this;
    }

    public void setNegotiationdate(LocalDate negotiationdate) {
        this.negotiationdate = negotiationdate;
    }

    public String getNegotiationlocation() {
        return this.negotiationlocation;
    }

    public OutsourcingContract negotiationlocation(String negotiationlocation) {
        this.setNegotiationlocation(negotiationlocation);
        return this;
    }

    public void setNegotiationlocation(String negotiationlocation) {
        this.negotiationlocation = negotiationlocation;
    }

    public String getNegotiator() {
        return this.negotiator;
    }

    public OutsourcingContract negotiator(String negotiator) {
        this.setNegotiator(negotiator);
        return this;
    }

    public void setNegotiator(String negotiator) {
        this.negotiator = negotiator;
    }

    public BigDecimal getBudgetamount() {
        return this.budgetamount;
    }

    public OutsourcingContract budgetamount(BigDecimal budgetamount) {
        this.setBudgetamount(budgetamount);
        return this;
    }

    public void setBudgetamount(BigDecimal budgetamount) {
        this.budgetamount = budgetamount;
    }

    public BigDecimal getContractamount() {
        return this.contractamount;
    }

    public OutsourcingContract contractamount(BigDecimal contractamount) {
        this.setContractamount(contractamount);
        return this;
    }

    public void setContractamount(BigDecimal contractamount) {
        this.contractamount = contractamount;
    }

    public String getApprover() {
        return this.approver;
    }

    public OutsourcingContract approver(String approver) {
        this.setApprover(approver);
        return this;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public LocalDate getApprovaldate() {
        return this.approvaldate;
    }

    public OutsourcingContract approvaldate(LocalDate approvaldate) {
        this.setApprovaldate(approvaldate);
        return this;
    }

    public void setApprovaldate(LocalDate approvaldate) {
        this.approvaldate = approvaldate;
    }

    public String getContractsecretlevel() {
        return this.contractsecretlevel;
    }

    public OutsourcingContract contractsecretlevel(String contractsecretlevel) {
        this.setContractsecretlevel(contractsecretlevel);
        return this;
    }

    public void setContractsecretlevel(String contractsecretlevel) {
        this.contractsecretlevel = contractsecretlevel;
    }

    public Workbag getWorkbag() {
        return this.workbag;
    }

    public void setWorkbag(Workbag workbag) {
        this.workbag = workbag;
    }

    public OutsourcingContract workbag(Workbag workbag) {
        this.setWorkbag(workbag);
        return this;
    }

    public Set<DeliveryContent> getDeliveryContents() {
        return this.deliveryContents;
    }

    public void setDeliveryContents(Set<DeliveryContent> deliveryContents) {
        if (this.deliveryContents != null) {
            this.deliveryContents.forEach(i -> i.setOutsourcingContract(null));
        }
        if (deliveryContents != null) {
            deliveryContents.forEach(i -> i.setOutsourcingContract(this));
        }
        this.deliveryContents = deliveryContents;
    }

    public OutsourcingContract deliveryContents(Set<DeliveryContent> deliveryContents) {
        this.setDeliveryContents(deliveryContents);
        return this;
    }

    public OutsourcingContract addDeliveryContent(DeliveryContent deliveryContent) {
        this.deliveryContents.add(deliveryContent);
        deliveryContent.setOutsourcingContract(this);
        return this;
    }

    public OutsourcingContract removeDeliveryContent(DeliveryContent deliveryContent) {
        this.deliveryContents.remove(deliveryContent);
        deliveryContent.setOutsourcingContract(null);
        return this;
    }

    public Set<MilestoneNode> getMilestoneNodes() {
        return this.milestoneNodes;
    }

    public void setMilestoneNodes(Set<MilestoneNode> milestoneNodes) {
        if (this.milestoneNodes != null) {
            this.milestoneNodes.forEach(i -> i.setOutsourcingContract(null));
        }
        if (milestoneNodes != null) {
            milestoneNodes.forEach(i -> i.setOutsourcingContract(this));
        }
        this.milestoneNodes = milestoneNodes;
    }

    public OutsourcingContract milestoneNodes(Set<MilestoneNode> milestoneNodes) {
        this.setMilestoneNodes(milestoneNodes);
        return this;
    }

    public OutsourcingContract addMilestoneNode(MilestoneNode milestoneNode) {
        this.milestoneNodes.add(milestoneNode);
        milestoneNode.setOutsourcingContract(this);
        return this;
    }

    public OutsourcingContract removeMilestoneNode(MilestoneNode milestoneNode) {
        this.milestoneNodes.remove(milestoneNode);
        milestoneNode.setOutsourcingContract(null);
        return this;
    }

    public Set<PaymentApplication> getPaymentApplications() {
        return this.paymentApplications;
    }

    public void setPaymentApplications(Set<PaymentApplication> paymentApplications) {
        if (this.paymentApplications != null) {
            this.paymentApplications.forEach(i -> i.setOutsourcingContract(null));
        }
        if (paymentApplications != null) {
            paymentApplications.forEach(i -> i.setOutsourcingContract(this));
        }
        this.paymentApplications = paymentApplications;
    }

    public OutsourcingContract paymentApplications(Set<PaymentApplication> paymentApplications) {
        this.setPaymentApplications(paymentApplications);
        return this;
    }

    public OutsourcingContract addPaymentApplication(PaymentApplication paymentApplication) {
        this.paymentApplications.add(paymentApplication);
        paymentApplication.setOutsourcingContract(this);
        return this;
    }

    public OutsourcingContract removePaymentApplication(PaymentApplication paymentApplication) {
        this.paymentApplications.remove(paymentApplication);
        paymentApplication.setOutsourcingContract(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OutsourcingContract)) {
            return false;
        }
        return getId() != null && getId().equals(((OutsourcingContract) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OutsourcingContract{" +
            "id=" + getId() +
            ", contractid='" + getContractid() + "'" +
            ", contractcode='" + getContractcode() + "'" +
            ", contractname='" + getContractname() + "'" +
            ", contractqualityid='" + getContractqualityid() + "'" +
            ", contractcostid='" + getContractcostid() + "'" +
            ", contractfinanceid='" + getContractfinanceid() + "'" +
            ", projectid='" + getProjectid() + "'" +
            ", projectsecretlevel='" + getProjectsecretlevel() + "'" +
            ", counterpartyunit='" + getCounterpartyunit() + "'" +
            ", negotiationdate='" + getNegotiationdate() + "'" +
            ", negotiationlocation='" + getNegotiationlocation() + "'" +
            ", negotiator='" + getNegotiator() + "'" +
            ", budgetamount=" + getBudgetamount() +
            ", contractamount=" + getContractamount() +
            ", approver='" + getApprover() + "'" +
            ", approvaldate='" + getApprovaldate() + "'" +
            ", contractsecretlevel='" + getContractsecretlevel() + "'" +
            "}";
    }
}
