package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
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

    @Column(name = "url")
    private String url;

    @Column(name = "createtime")
    private LocalDate createtime;

    @Column(name = "creatorname")
    private String creatorname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement creatorid;

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
    private Projectwbs projectwbs;

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

    public String getUrl() {
        return this.url;
    }

    public Document url(String url) {
        this.setUrl(url);
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public HrManagement getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(HrManagement hrManagement) {
        this.creatorid = hrManagement;
    }

    public Document creatorid(HrManagement hrManagement) {
        this.setCreatorid(hrManagement);
        return this;
    }

    public Projectwbs getProjectwbs() {
        return this.projectwbs;
    }

    public void setProjectwbs(Projectwbs projectwbs) {
        this.projectwbs = projectwbs;
    }

    public Document projectwbs(Projectwbs projectwbs) {
        this.setProjectwbs(projectwbs);
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
            ", url='" + getUrl() + "'" +
            ", createtime='" + getCreatetime() + "'" +
            ", creatorname='" + getCreatorname() + "'" +
            "}";
    }
}
