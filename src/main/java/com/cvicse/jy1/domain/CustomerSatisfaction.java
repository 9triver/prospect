package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CustomerSatisfaction.
 */
@Entity
@Table(name = "customer_satisfaction")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomerSatisfaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "jhi_year")
    private Integer year;

    @Column(name = "satisfactionitem")
    private String satisfactionitem;

    @Column(name = "score")
    private Integer score;

    @Column(name = "opinion")
    private String opinion;

    @Column(name = "totalscore")
    private Integer totalscore;

    @Column(name = "surveytime")
    private LocalDate surveytime;

    @Column(name = "customer")
    private String customer;

    @Column(name = "plonenumber")
    private String plonenumber;

    @Column(name = "filename")
    private String filename;

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
            "workbags",
            "progressPlans",
            "projectBudgets",
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
    private Projectwbs wbsid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public CustomerSatisfaction id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYear() {
        return this.year;
    }

    public CustomerSatisfaction year(Integer year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getSatisfactionitem() {
        return this.satisfactionitem;
    }

    public CustomerSatisfaction satisfactionitem(String satisfactionitem) {
        this.setSatisfactionitem(satisfactionitem);
        return this;
    }

    public void setSatisfactionitem(String satisfactionitem) {
        this.satisfactionitem = satisfactionitem;
    }

    public Integer getScore() {
        return this.score;
    }

    public CustomerSatisfaction score(Integer score) {
        this.setScore(score);
        return this;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getOpinion() {
        return this.opinion;
    }

    public CustomerSatisfaction opinion(String opinion) {
        this.setOpinion(opinion);
        return this;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getTotalscore() {
        return this.totalscore;
    }

    public CustomerSatisfaction totalscore(Integer totalscore) {
        this.setTotalscore(totalscore);
        return this;
    }

    public void setTotalscore(Integer totalscore) {
        this.totalscore = totalscore;
    }

    public LocalDate getSurveytime() {
        return this.surveytime;
    }

    public CustomerSatisfaction surveytime(LocalDate surveytime) {
        this.setSurveytime(surveytime);
        return this;
    }

    public void setSurveytime(LocalDate surveytime) {
        this.surveytime = surveytime;
    }

    public String getCustomer() {
        return this.customer;
    }

    public CustomerSatisfaction customer(String customer) {
        this.setCustomer(customer);
        return this;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPlonenumber() {
        return this.plonenumber;
    }

    public CustomerSatisfaction plonenumber(String plonenumber) {
        this.setPlonenumber(plonenumber);
        return this;
    }

    public void setPlonenumber(String plonenumber) {
        this.plonenumber = plonenumber;
    }

    public String getFilename() {
        return this.filename;
    }

    public CustomerSatisfaction filename(String filename) {
        this.setFilename(filename);
        return this;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Projectwbs getWbsid() {
        return this.wbsid;
    }

    public void setWbsid(Projectwbs projectwbs) {
        this.wbsid = projectwbs;
    }

    public CustomerSatisfaction wbsid(Projectwbs projectwbs) {
        this.setWbsid(projectwbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CustomerSatisfaction)) {
            return false;
        }
        return getId() != null && getId().equals(((CustomerSatisfaction) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomerSatisfaction{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", satisfactionitem='" + getSatisfactionitem() + "'" +
            ", score=" + getScore() +
            ", opinion='" + getOpinion() + "'" +
            ", totalscore=" + getTotalscore() +
            ", surveytime='" + getSurveytime() + "'" +
            ", customer='" + getCustomer() + "'" +
            ", plonenumber='" + getPlonenumber() + "'" +
            ", filename='" + getFilename() + "'" +
            "}";
    }
}
