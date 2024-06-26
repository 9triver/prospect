package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    @GeneratedValue
    @Column(name = "id")
    private String id;

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
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Officers creatorid;

    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid" },
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

    @JsonIgnoreProperties(value = { "progressplan", "document" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Progressplanreturns progressplanreturns;

    @JsonIgnoreProperties(
        value = { "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "fundsavailability" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "document")
    private Auditedbudget auditedbudget;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Document id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
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

    public Progressplanreturns getProgressplanreturns() {
        return this.progressplanreturns;
    }

    public void setProgressplanreturns(Progressplanreturns progressplanreturns) {
        if (this.progressplanreturns != null) {
            this.progressplanreturns.setDocument(null);
        }
        if (progressplanreturns != null) {
            progressplanreturns.setDocument(this);
        }
        this.progressplanreturns = progressplanreturns;
    }

    public Document progressplanreturns(Progressplanreturns progressplanreturns) {
        this.setProgressplanreturns(progressplanreturns);
        return this;
    }

    public Auditedbudget getAuditedbudget() {
        return this.auditedbudget;
    }

    public void setAuditedbudget(Auditedbudget auditedbudget) {
        if (this.auditedbudget != null) {
            this.auditedbudget.setDocument(null);
        }
        if (auditedbudget != null) {
            auditedbudget.setDocument(this);
        }
        this.auditedbudget = auditedbudget;
    }

    public Document auditedbudget(Auditedbudget auditedbudget) {
        this.setAuditedbudget(auditedbudget);
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
            ", documentname='" + getDocumentname() + "'" +
            ", documenttype=" + getDocumenttype() +
            ", documentsize=" + getDocumentsize() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            "}";
    }
}
