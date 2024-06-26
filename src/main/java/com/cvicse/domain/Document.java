package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "documentid", unique = true)
    private Long documentid;

    @Column(name = "documentname")
    private String documentname;

    @Column(name = "documenttype")
    private Integer documenttype;

    @Column(name = "documentsize")
    private Long documentsize;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Officers creatorid;

    @JsonIgnoreProperties(
        value = {
            "cycleplan",
            "progressmanagement",
            "qualitymanagement",
            "fundsmanagement",
            "technicalCondition",
            "contractualfunds",
            "outsourcingmPurchaseExecute",
            "resourcemanagement",
            "riskmanagement",
            "document",
            "safetycheck",
            "department",
            "evaluationCriteria",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingmPurchasePlan",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Project project;

    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid", "project" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Cycleplan cycleplan;

    @JsonIgnoreProperties(value = { "document", "monthplan", "projectcharge", "creatorid", "auditorid", "cycleplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Annualplan annualplan;

    @JsonIgnoreProperties(
        value = { "document", "planreturns", "projectcharge", "creatorid", "auditorid", "cycleplan", "annualplan" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Monthplan monthplan;

    @JsonIgnoreProperties(
        value = {
            "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "project", "comprehensivecontrol", "fundsavailability",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Fundsmanagement fundsmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Document id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentid() {
        return this.documentid;
    }

    public Document documentid(Long documentid) {
        this.setDocumentid(documentid);
        return this;
    }

    public void setDocumentid(Long documentid) {
        this.documentid = documentid;
    }

    public String getDocumentname() {
        return this.documentname;
    }

    public Document documentname(String documentname) {
        this.setDocumentname(documentname);
        return this;
    }

    public void setDocumentname(String documentname) {
        this.documentname = documentname;
    }

    public Integer getDocumenttype() {
        return this.documenttype;
    }

    public Document documenttype(Integer documenttype) {
        this.setDocumenttype(documenttype);
        return this;
    }

    public void setDocumenttype(Integer documenttype) {
        this.documenttype = documenttype;
    }

    public Long getDocumentsize() {
        return this.documentsize;
    }

    public Document documentsize(Long documentsize) {
        this.setDocumentsize(documentsize);
        return this;
    }

    public void setDocumentsize(Long documentsize) {
        this.documentsize = documentsize;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Document secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public LocalDate getCreatetime() {
        return this.createtime;
    }

    public Document createtime(LocalDate createtime) {
        this.setCreatetime(createtime);
        return this;
    }

    public void setCreatetime(LocalDate createtime) {
        this.createtime = createtime;
    }

    public String getCreatorname() {
        return this.creatorname;
    }

    public Document creatorname(String creatorname) {
        this.setCreatorname(creatorname);
        return this;
    }

    public void setCreatorname(String creatorname) {
        this.creatorname = creatorname;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Document creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setDocument(null);
        }
        if (project != null) {
            project.setDocument(this);
        }
        this.project = project;
    }

    public Document project(Project project) {
        this.setProject(project);
        return this;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        if (this.cycleplan != null) {
            this.cycleplan.setDocument(null);
        }
        if (cycleplan != null) {
            cycleplan.setDocument(this);
        }
        this.cycleplan = cycleplan;
    }

    public Document cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Annualplan getAnnualplan() {
        return this.annualplan;
    }

    public void setAnnualplan(Annualplan annualplan) {
        if (this.annualplan != null) {
            this.annualplan.setDocument(null);
        }
        if (annualplan != null) {
            annualplan.setDocument(this);
        }
        this.annualplan = annualplan;
    }

    public Document annualplan(Annualplan annualplan) {
        this.setAnnualplan(annualplan);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        if (this.monthplan != null) {
            this.monthplan.setDocument(null);
        }
        if (monthplan != null) {
            monthplan.setDocument(this);
        }
        this.monthplan = monthplan;
    }

    public Document monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        if (this.fundsmanagement != null) {
            this.fundsmanagement.setDocument(null);
        }
        if (fundsmanagement != null) {
            fundsmanagement.setDocument(this);
        }
        this.fundsmanagement = fundsmanagement;
    }

    public Document fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return getId() != null && getId().equals(((Document) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", documentid=" + getDocumentid() +
            ", documentname='" + getDocumentname() + "'" +
            ", documenttype=" + getDocumenttype() +
            ", documentsize=" + getDocumentsize() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            "}";
    }
}
