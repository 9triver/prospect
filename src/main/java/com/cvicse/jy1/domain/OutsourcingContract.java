package com.cvicse.jy1.domain;

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
 * A OutsourcingContract.
 */
@Entity
@Table(name = "outsourcing_contract")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OutsourcingContract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
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

    @Column(name = "deliverycontent")
    private String deliverycontent;

    @Column(name = "warrantyrequirement")
    private String warrantyrequirement;

    @Column(name = "purchaseplanno")
    private String purchaseplanno;

    @Column(name = "purchaseplandate")
    private LocalDate purchaseplandate;

    @Column(name = "purchaseplanamount", precision = 21, scale = 2)
    private BigDecimal purchaseplanamount;

    @Column(name = "purchasemethod")
    private String purchasemethod;

    @Column(name = "purchasesecretlevel")
    private String purchasesecretlevel;

    @Column(name = "reviewmethod")
    private String reviewmethod;

    @Column(name = "requirementdepartment")
    private String requirementdepartment;

    @Column(name = "requirementperson")
    private String requirementperson;

    @Column(name = "undertaker")
    private String undertaker;

    @Column(name = "undertakingdepartment")
    private String undertakingdepartment;

    @Column(name = "workbagid")
    private String workbagid;

    @Column(name = "projectmanager")
    private String projectmanager;

    @Column(name = "fundsource")
    private String fundsource;

    @Column(name = "thesisname")
    private String thesisname;

    @Column(name = "contractauxiliaryno")
    private String contractauxiliaryno;

    @Column(name = "reasonfornosuppliers")
    private String reasonfornosuppliers;

    @Column(name = "reasonforchange")
    private String reasonforchange;

    @Column(name = "negotiationfiletime")
    private LocalDate negotiationfiletime;

    @Column(name = "bidopeningtime")
    private LocalDate bidopeningtime;

    @Column(name = "judges")
    private String judges;

    @Column(name = "responsevendorname")
    private String responsevendorname;

    @Column(name = "finalquoteandscore")
    private String finalquoteandscore;

    @Column(name = "noticeofcompletiontime")
    private LocalDate noticeofcompletiontime;

    @Column(name = "signingdate")
    private LocalDate signingdate;

    @Column(name = "contractenddate")
    private LocalDate contractenddate;

    @Column(name = "actualcompletiontime")
    private LocalDate actualcompletiontime;

    @Column(name = "issubmitsecrecyagreement")
    private String issubmitsecrecyagreement;

    @Column(name = "issubmitsecurityagreement")
    private String issubmitsecurityagreement;

    @Column(name = "remark")
    private String remark;

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
            "paymentApplications",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Workbag workbag;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_outsourcing_contract__milestone_node",
        joinColumns = @JoinColumn(name = "outsourcing_contract_id"),
        inverseJoinColumns = @JoinColumn(name = "milestone_node_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "outsourcingContracts" }, allowSetters = true)
    private Set<MilestoneNode> milestoneNodes = new HashSet<>();

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

    public String getDeliverycontent() {
        return this.deliverycontent;
    }

    public OutsourcingContract deliverycontent(String deliverycontent) {
        this.setDeliverycontent(deliverycontent);
        return this;
    }

    public void setDeliverycontent(String deliverycontent) {
        this.deliverycontent = deliverycontent;
    }

    public String getWarrantyrequirement() {
        return this.warrantyrequirement;
    }

    public OutsourcingContract warrantyrequirement(String warrantyrequirement) {
        this.setWarrantyrequirement(warrantyrequirement);
        return this;
    }

    public void setWarrantyrequirement(String warrantyrequirement) {
        this.warrantyrequirement = warrantyrequirement;
    }

    public String getPurchaseplanno() {
        return this.purchaseplanno;
    }

    public OutsourcingContract purchaseplanno(String purchaseplanno) {
        this.setPurchaseplanno(purchaseplanno);
        return this;
    }

    public void setPurchaseplanno(String purchaseplanno) {
        this.purchaseplanno = purchaseplanno;
    }

    public LocalDate getPurchaseplandate() {
        return this.purchaseplandate;
    }

    public OutsourcingContract purchaseplandate(LocalDate purchaseplandate) {
        this.setPurchaseplandate(purchaseplandate);
        return this;
    }

    public void setPurchaseplandate(LocalDate purchaseplandate) {
        this.purchaseplandate = purchaseplandate;
    }

    public BigDecimal getPurchaseplanamount() {
        return this.purchaseplanamount;
    }

    public OutsourcingContract purchaseplanamount(BigDecimal purchaseplanamount) {
        this.setPurchaseplanamount(purchaseplanamount);
        return this;
    }

    public void setPurchaseplanamount(BigDecimal purchaseplanamount) {
        this.purchaseplanamount = purchaseplanamount;
    }

    public String getPurchasemethod() {
        return this.purchasemethod;
    }

    public OutsourcingContract purchasemethod(String purchasemethod) {
        this.setPurchasemethod(purchasemethod);
        return this;
    }

    public void setPurchasemethod(String purchasemethod) {
        this.purchasemethod = purchasemethod;
    }

    public String getPurchasesecretlevel() {
        return this.purchasesecretlevel;
    }

    public OutsourcingContract purchasesecretlevel(String purchasesecretlevel) {
        this.setPurchasesecretlevel(purchasesecretlevel);
        return this;
    }

    public void setPurchasesecretlevel(String purchasesecretlevel) {
        this.purchasesecretlevel = purchasesecretlevel;
    }

    public String getReviewmethod() {
        return this.reviewmethod;
    }

    public OutsourcingContract reviewmethod(String reviewmethod) {
        this.setReviewmethod(reviewmethod);
        return this;
    }

    public void setReviewmethod(String reviewmethod) {
        this.reviewmethod = reviewmethod;
    }

    public String getRequirementdepartment() {
        return this.requirementdepartment;
    }

    public OutsourcingContract requirementdepartment(String requirementdepartment) {
        this.setRequirementdepartment(requirementdepartment);
        return this;
    }

    public void setRequirementdepartment(String requirementdepartment) {
        this.requirementdepartment = requirementdepartment;
    }

    public String getRequirementperson() {
        return this.requirementperson;
    }

    public OutsourcingContract requirementperson(String requirementperson) {
        this.setRequirementperson(requirementperson);
        return this;
    }

    public void setRequirementperson(String requirementperson) {
        this.requirementperson = requirementperson;
    }

    public String getUndertaker() {
        return this.undertaker;
    }

    public OutsourcingContract undertaker(String undertaker) {
        this.setUndertaker(undertaker);
        return this;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getUndertakingdepartment() {
        return this.undertakingdepartment;
    }

    public OutsourcingContract undertakingdepartment(String undertakingdepartment) {
        this.setUndertakingdepartment(undertakingdepartment);
        return this;
    }

    public void setUndertakingdepartment(String undertakingdepartment) {
        this.undertakingdepartment = undertakingdepartment;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public OutsourcingContract workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getProjectmanager() {
        return this.projectmanager;
    }

    public OutsourcingContract projectmanager(String projectmanager) {
        this.setProjectmanager(projectmanager);
        return this;
    }

    public void setProjectmanager(String projectmanager) {
        this.projectmanager = projectmanager;
    }

    public String getFundsource() {
        return this.fundsource;
    }

    public OutsourcingContract fundsource(String fundsource) {
        this.setFundsource(fundsource);
        return this;
    }

    public void setFundsource(String fundsource) {
        this.fundsource = fundsource;
    }

    public String getThesisname() {
        return this.thesisname;
    }

    public OutsourcingContract thesisname(String thesisname) {
        this.setThesisname(thesisname);
        return this;
    }

    public void setThesisname(String thesisname) {
        this.thesisname = thesisname;
    }

    public String getContractauxiliaryno() {
        return this.contractauxiliaryno;
    }

    public OutsourcingContract contractauxiliaryno(String contractauxiliaryno) {
        this.setContractauxiliaryno(contractauxiliaryno);
        return this;
    }

    public void setContractauxiliaryno(String contractauxiliaryno) {
        this.contractauxiliaryno = contractauxiliaryno;
    }

    public String getReasonfornosuppliers() {
        return this.reasonfornosuppliers;
    }

    public OutsourcingContract reasonfornosuppliers(String reasonfornosuppliers) {
        this.setReasonfornosuppliers(reasonfornosuppliers);
        return this;
    }

    public void setReasonfornosuppliers(String reasonfornosuppliers) {
        this.reasonfornosuppliers = reasonfornosuppliers;
    }

    public String getReasonforchange() {
        return this.reasonforchange;
    }

    public OutsourcingContract reasonforchange(String reasonforchange) {
        this.setReasonforchange(reasonforchange);
        return this;
    }

    public void setReasonforchange(String reasonforchange) {
        this.reasonforchange = reasonforchange;
    }

    public LocalDate getNegotiationfiletime() {
        return this.negotiationfiletime;
    }

    public OutsourcingContract negotiationfiletime(LocalDate negotiationfiletime) {
        this.setNegotiationfiletime(negotiationfiletime);
        return this;
    }

    public void setNegotiationfiletime(LocalDate negotiationfiletime) {
        this.negotiationfiletime = negotiationfiletime;
    }

    public LocalDate getBidopeningtime() {
        return this.bidopeningtime;
    }

    public OutsourcingContract bidopeningtime(LocalDate bidopeningtime) {
        this.setBidopeningtime(bidopeningtime);
        return this;
    }

    public void setBidopeningtime(LocalDate bidopeningtime) {
        this.bidopeningtime = bidopeningtime;
    }

    public String getJudges() {
        return this.judges;
    }

    public OutsourcingContract judges(String judges) {
        this.setJudges(judges);
        return this;
    }

    public void setJudges(String judges) {
        this.judges = judges;
    }

    public String getResponsevendorname() {
        return this.responsevendorname;
    }

    public OutsourcingContract responsevendorname(String responsevendorname) {
        this.setResponsevendorname(responsevendorname);
        return this;
    }

    public void setResponsevendorname(String responsevendorname) {
        this.responsevendorname = responsevendorname;
    }

    public String getFinalquoteandscore() {
        return this.finalquoteandscore;
    }

    public OutsourcingContract finalquoteandscore(String finalquoteandscore) {
        this.setFinalquoteandscore(finalquoteandscore);
        return this;
    }

    public void setFinalquoteandscore(String finalquoteandscore) {
        this.finalquoteandscore = finalquoteandscore;
    }

    public LocalDate getNoticeofcompletiontime() {
        return this.noticeofcompletiontime;
    }

    public OutsourcingContract noticeofcompletiontime(LocalDate noticeofcompletiontime) {
        this.setNoticeofcompletiontime(noticeofcompletiontime);
        return this;
    }

    public void setNoticeofcompletiontime(LocalDate noticeofcompletiontime) {
        this.noticeofcompletiontime = noticeofcompletiontime;
    }

    public LocalDate getSigningdate() {
        return this.signingdate;
    }

    public OutsourcingContract signingdate(LocalDate signingdate) {
        this.setSigningdate(signingdate);
        return this;
    }

    public void setSigningdate(LocalDate signingdate) {
        this.signingdate = signingdate;
    }

    public LocalDate getContractenddate() {
        return this.contractenddate;
    }

    public OutsourcingContract contractenddate(LocalDate contractenddate) {
        this.setContractenddate(contractenddate);
        return this;
    }

    public void setContractenddate(LocalDate contractenddate) {
        this.contractenddate = contractenddate;
    }

    public LocalDate getActualcompletiontime() {
        return this.actualcompletiontime;
    }

    public OutsourcingContract actualcompletiontime(LocalDate actualcompletiontime) {
        this.setActualcompletiontime(actualcompletiontime);
        return this;
    }

    public void setActualcompletiontime(LocalDate actualcompletiontime) {
        this.actualcompletiontime = actualcompletiontime;
    }

    public String getIssubmitsecrecyagreement() {
        return this.issubmitsecrecyagreement;
    }

    public OutsourcingContract issubmitsecrecyagreement(String issubmitsecrecyagreement) {
        this.setIssubmitsecrecyagreement(issubmitsecrecyagreement);
        return this;
    }

    public void setIssubmitsecrecyagreement(String issubmitsecrecyagreement) {
        this.issubmitsecrecyagreement = issubmitsecrecyagreement;
    }

    public String getIssubmitsecurityagreement() {
        return this.issubmitsecurityagreement;
    }

    public OutsourcingContract issubmitsecurityagreement(String issubmitsecurityagreement) {
        this.setIssubmitsecurityagreement(issubmitsecurityagreement);
        return this;
    }

    public void setIssubmitsecurityagreement(String issubmitsecurityagreement) {
        this.issubmitsecurityagreement = issubmitsecurityagreement;
    }

    public String getRemark() {
        return this.remark;
    }

    public OutsourcingContract remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Set<MilestoneNode> getMilestoneNodes() {
        return this.milestoneNodes;
    }

    public void setMilestoneNodes(Set<MilestoneNode> milestoneNodes) {
        this.milestoneNodes = milestoneNodes;
    }

    public OutsourcingContract milestoneNodes(Set<MilestoneNode> milestoneNodes) {
        this.setMilestoneNodes(milestoneNodes);
        return this;
    }

    public OutsourcingContract addMilestoneNode(MilestoneNode milestoneNode) {
        this.milestoneNodes.add(milestoneNode);
        return this;
    }

    public OutsourcingContract removeMilestoneNode(MilestoneNode milestoneNode) {
        this.milestoneNodes.remove(milestoneNode);
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
            ", deliverycontent='" + getDeliverycontent() + "'" +
            ", warrantyrequirement='" + getWarrantyrequirement() + "'" +
            ", purchaseplanno='" + getPurchaseplanno() + "'" +
            ", purchaseplandate='" + getPurchaseplandate() + "'" +
            ", purchaseplanamount=" + getPurchaseplanamount() +
            ", purchasemethod='" + getPurchasemethod() + "'" +
            ", purchasesecretlevel='" + getPurchasesecretlevel() + "'" +
            ", reviewmethod='" + getReviewmethod() + "'" +
            ", requirementdepartment='" + getRequirementdepartment() + "'" +
            ", requirementperson='" + getRequirementperson() + "'" +
            ", undertaker='" + getUndertaker() + "'" +
            ", undertakingdepartment='" + getUndertakingdepartment() + "'" +
            ", workbagid='" + getWorkbagid() + "'" +
            ", projectmanager='" + getProjectmanager() + "'" +
            ", fundsource='" + getFundsource() + "'" +
            ", thesisname='" + getThesisname() + "'" +
            ", contractauxiliaryno='" + getContractauxiliaryno() + "'" +
            ", reasonfornosuppliers='" + getReasonfornosuppliers() + "'" +
            ", reasonforchange='" + getReasonforchange() + "'" +
            ", negotiationfiletime='" + getNegotiationfiletime() + "'" +
            ", bidopeningtime='" + getBidopeningtime() + "'" +
            ", judges='" + getJudges() + "'" +
            ", responsevendorname='" + getResponsevendorname() + "'" +
            ", finalquoteandscore='" + getFinalquoteandscore() + "'" +
            ", noticeofcompletiontime='" + getNoticeofcompletiontime() + "'" +
            ", signingdate='" + getSigningdate() + "'" +
            ", contractenddate='" + getContractenddate() + "'" +
            ", actualcompletiontime='" + getActualcompletiontime() + "'" +
            ", issubmitsecrecyagreement='" + getIssubmitsecrecyagreement() + "'" +
            ", issubmitsecurityagreement='" + getIssubmitsecurityagreement() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
