package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A DeliveryContent.
 */
@Entity
@Table(name = "delivery_content")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DeliveryContent implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id", nullable = false)
    private Integer id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "workbag", "deliveryContents", "milestoneNodes", "paymentApplications" }, allowSetters = true)
    private OutsourcingContract outsourcingContract;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public DeliveryContent id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWarrantyrequirement() {
        return this.warrantyrequirement;
    }

    public DeliveryContent warrantyrequirement(String warrantyrequirement) {
        this.setWarrantyrequirement(warrantyrequirement);
        return this;
    }

    public void setWarrantyrequirement(String warrantyrequirement) {
        this.warrantyrequirement = warrantyrequirement;
    }

    public String getPurchaseplanno() {
        return this.purchaseplanno;
    }

    public DeliveryContent purchaseplanno(String purchaseplanno) {
        this.setPurchaseplanno(purchaseplanno);
        return this;
    }

    public void setPurchaseplanno(String purchaseplanno) {
        this.purchaseplanno = purchaseplanno;
    }

    public LocalDate getPurchaseplandate() {
        return this.purchaseplandate;
    }

    public DeliveryContent purchaseplandate(LocalDate purchaseplandate) {
        this.setPurchaseplandate(purchaseplandate);
        return this;
    }

    public void setPurchaseplandate(LocalDate purchaseplandate) {
        this.purchaseplandate = purchaseplandate;
    }

    public BigDecimal getPurchaseplanamount() {
        return this.purchaseplanamount;
    }

    public DeliveryContent purchaseplanamount(BigDecimal purchaseplanamount) {
        this.setPurchaseplanamount(purchaseplanamount);
        return this;
    }

    public void setPurchaseplanamount(BigDecimal purchaseplanamount) {
        this.purchaseplanamount = purchaseplanamount;
    }

    public String getPurchasemethod() {
        return this.purchasemethod;
    }

    public DeliveryContent purchasemethod(String purchasemethod) {
        this.setPurchasemethod(purchasemethod);
        return this;
    }

    public void setPurchasemethod(String purchasemethod) {
        this.purchasemethod = purchasemethod;
    }

    public String getPurchasesecretlevel() {
        return this.purchasesecretlevel;
    }

    public DeliveryContent purchasesecretlevel(String purchasesecretlevel) {
        this.setPurchasesecretlevel(purchasesecretlevel);
        return this;
    }

    public void setPurchasesecretlevel(String purchasesecretlevel) {
        this.purchasesecretlevel = purchasesecretlevel;
    }

    public String getReviewmethod() {
        return this.reviewmethod;
    }

    public DeliveryContent reviewmethod(String reviewmethod) {
        this.setReviewmethod(reviewmethod);
        return this;
    }

    public void setReviewmethod(String reviewmethod) {
        this.reviewmethod = reviewmethod;
    }

    public String getRequirementdepartment() {
        return this.requirementdepartment;
    }

    public DeliveryContent requirementdepartment(String requirementdepartment) {
        this.setRequirementdepartment(requirementdepartment);
        return this;
    }

    public void setRequirementdepartment(String requirementdepartment) {
        this.requirementdepartment = requirementdepartment;
    }

    public String getRequirementperson() {
        return this.requirementperson;
    }

    public DeliveryContent requirementperson(String requirementperson) {
        this.setRequirementperson(requirementperson);
        return this;
    }

    public void setRequirementperson(String requirementperson) {
        this.requirementperson = requirementperson;
    }

    public String getUndertaker() {
        return this.undertaker;
    }

    public DeliveryContent undertaker(String undertaker) {
        this.setUndertaker(undertaker);
        return this;
    }

    public void setUndertaker(String undertaker) {
        this.undertaker = undertaker;
    }

    public String getUndertakingdepartment() {
        return this.undertakingdepartment;
    }

    public DeliveryContent undertakingdepartment(String undertakingdepartment) {
        this.setUndertakingdepartment(undertakingdepartment);
        return this;
    }

    public void setUndertakingdepartment(String undertakingdepartment) {
        this.undertakingdepartment = undertakingdepartment;
    }

    public String getWorkbagid() {
        return this.workbagid;
    }

    public DeliveryContent workbagid(String workbagid) {
        this.setWorkbagid(workbagid);
        return this;
    }

    public void setWorkbagid(String workbagid) {
        this.workbagid = workbagid;
    }

    public String getProjectmanager() {
        return this.projectmanager;
    }

    public DeliveryContent projectmanager(String projectmanager) {
        this.setProjectmanager(projectmanager);
        return this;
    }

    public void setProjectmanager(String projectmanager) {
        this.projectmanager = projectmanager;
    }

    public String getFundsource() {
        return this.fundsource;
    }

    public DeliveryContent fundsource(String fundsource) {
        this.setFundsource(fundsource);
        return this;
    }

    public void setFundsource(String fundsource) {
        this.fundsource = fundsource;
    }

    public String getThesisname() {
        return this.thesisname;
    }

    public DeliveryContent thesisname(String thesisname) {
        this.setThesisname(thesisname);
        return this;
    }

    public void setThesisname(String thesisname) {
        this.thesisname = thesisname;
    }

    public String getContractauxiliaryno() {
        return this.contractauxiliaryno;
    }

    public DeliveryContent contractauxiliaryno(String contractauxiliaryno) {
        this.setContractauxiliaryno(contractauxiliaryno);
        return this;
    }

    public void setContractauxiliaryno(String contractauxiliaryno) {
        this.contractauxiliaryno = contractauxiliaryno;
    }

    public String getReasonfornosuppliers() {
        return this.reasonfornosuppliers;
    }

    public DeliveryContent reasonfornosuppliers(String reasonfornosuppliers) {
        this.setReasonfornosuppliers(reasonfornosuppliers);
        return this;
    }

    public void setReasonfornosuppliers(String reasonfornosuppliers) {
        this.reasonfornosuppliers = reasonfornosuppliers;
    }

    public String getReasonforchange() {
        return this.reasonforchange;
    }

    public DeliveryContent reasonforchange(String reasonforchange) {
        this.setReasonforchange(reasonforchange);
        return this;
    }

    public void setReasonforchange(String reasonforchange) {
        this.reasonforchange = reasonforchange;
    }

    public LocalDate getNegotiationfiletime() {
        return this.negotiationfiletime;
    }

    public DeliveryContent negotiationfiletime(LocalDate negotiationfiletime) {
        this.setNegotiationfiletime(negotiationfiletime);
        return this;
    }

    public void setNegotiationfiletime(LocalDate negotiationfiletime) {
        this.negotiationfiletime = negotiationfiletime;
    }

    public LocalDate getBidopeningtime() {
        return this.bidopeningtime;
    }

    public DeliveryContent bidopeningtime(LocalDate bidopeningtime) {
        this.setBidopeningtime(bidopeningtime);
        return this;
    }

    public void setBidopeningtime(LocalDate bidopeningtime) {
        this.bidopeningtime = bidopeningtime;
    }

    public String getJudges() {
        return this.judges;
    }

    public DeliveryContent judges(String judges) {
        this.setJudges(judges);
        return this;
    }

    public void setJudges(String judges) {
        this.judges = judges;
    }

    public String getResponsevendorname() {
        return this.responsevendorname;
    }

    public DeliveryContent responsevendorname(String responsevendorname) {
        this.setResponsevendorname(responsevendorname);
        return this;
    }

    public void setResponsevendorname(String responsevendorname) {
        this.responsevendorname = responsevendorname;
    }

    public String getFinalquoteandscore() {
        return this.finalquoteandscore;
    }

    public DeliveryContent finalquoteandscore(String finalquoteandscore) {
        this.setFinalquoteandscore(finalquoteandscore);
        return this;
    }

    public void setFinalquoteandscore(String finalquoteandscore) {
        this.finalquoteandscore = finalquoteandscore;
    }

    public LocalDate getNoticeofcompletiontime() {
        return this.noticeofcompletiontime;
    }

    public DeliveryContent noticeofcompletiontime(LocalDate noticeofcompletiontime) {
        this.setNoticeofcompletiontime(noticeofcompletiontime);
        return this;
    }

    public void setNoticeofcompletiontime(LocalDate noticeofcompletiontime) {
        this.noticeofcompletiontime = noticeofcompletiontime;
    }

    public LocalDate getSigningdate() {
        return this.signingdate;
    }

    public DeliveryContent signingdate(LocalDate signingdate) {
        this.setSigningdate(signingdate);
        return this;
    }

    public void setSigningdate(LocalDate signingdate) {
        this.signingdate = signingdate;
    }

    public LocalDate getContractenddate() {
        return this.contractenddate;
    }

    public DeliveryContent contractenddate(LocalDate contractenddate) {
        this.setContractenddate(contractenddate);
        return this;
    }

    public void setContractenddate(LocalDate contractenddate) {
        this.contractenddate = contractenddate;
    }

    public LocalDate getActualcompletiontime() {
        return this.actualcompletiontime;
    }

    public DeliveryContent actualcompletiontime(LocalDate actualcompletiontime) {
        this.setActualcompletiontime(actualcompletiontime);
        return this;
    }

    public void setActualcompletiontime(LocalDate actualcompletiontime) {
        this.actualcompletiontime = actualcompletiontime;
    }

    public String getIssubmitsecrecyagreement() {
        return this.issubmitsecrecyagreement;
    }

    public DeliveryContent issubmitsecrecyagreement(String issubmitsecrecyagreement) {
        this.setIssubmitsecrecyagreement(issubmitsecrecyagreement);
        return this;
    }

    public void setIssubmitsecrecyagreement(String issubmitsecrecyagreement) {
        this.issubmitsecrecyagreement = issubmitsecrecyagreement;
    }

    public String getIssubmitsecurityagreement() {
        return this.issubmitsecurityagreement;
    }

    public DeliveryContent issubmitsecurityagreement(String issubmitsecurityagreement) {
        this.setIssubmitsecurityagreement(issubmitsecurityagreement);
        return this;
    }

    public void setIssubmitsecurityagreement(String issubmitsecurityagreement) {
        this.issubmitsecurityagreement = issubmitsecurityagreement;
    }

    public String getRemark() {
        return this.remark;
    }

    public DeliveryContent remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OutsourcingContract getOutsourcingContract() {
        return this.outsourcingContract;
    }

    public void setOutsourcingContract(OutsourcingContract outsourcingContract) {
        this.outsourcingContract = outsourcingContract;
    }

    public DeliveryContent outsourcingContract(OutsourcingContract outsourcingContract) {
        this.setOutsourcingContract(outsourcingContract);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeliveryContent)) {
            return false;
        }
        return getId() != null && getId().equals(((DeliveryContent) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeliveryContent{" +
            "id=" + getId() +
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
