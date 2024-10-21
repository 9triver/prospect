package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Archives.
 */
@Entity
@Table(name = "archives")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Archives implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "jhi_date")
    private LocalDate date;

    @Column(name = "page")
    private Integer page;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "confidentialityperiod")
    private LocalDate confidentialityperiod;

    @Column(name = "confidentialnumber")
    private String confidentialnumber;

    @Column(name = "storageperiod")
    private LocalDate storageperiod;

    @Column(name = "plannumber")
    private String plannumber;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "receivingnumber")
    private String receivingnumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement responsibleid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Archives id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Archives title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public Archives content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Archives date(LocalDate date) {
        this.setDate(date);
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPage() {
        return this.page;
    }

    public Archives page(Integer page) {
        this.setPage(page);
        return this;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Archives secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public LocalDate getConfidentialityperiod() {
        return this.confidentialityperiod;
    }

    public Archives confidentialityperiod(LocalDate confidentialityperiod) {
        this.setConfidentialityperiod(confidentialityperiod);
        return this;
    }

    public void setConfidentialityperiod(LocalDate confidentialityperiod) {
        this.confidentialityperiod = confidentialityperiod;
    }

    public String getConfidentialnumber() {
        return this.confidentialnumber;
    }

    public Archives confidentialnumber(String confidentialnumber) {
        this.setConfidentialnumber(confidentialnumber);
        return this;
    }

    public void setConfidentialnumber(String confidentialnumber) {
        this.confidentialnumber = confidentialnumber;
    }

    public LocalDate getStorageperiod() {
        return this.storageperiod;
    }

    public Archives storageperiod(LocalDate storageperiod) {
        this.setStorageperiod(storageperiod);
        return this;
    }

    public void setStorageperiod(LocalDate storageperiod) {
        this.storageperiod = storageperiod;
    }

    public String getPlannumber() {
        return this.plannumber;
    }

    public Archives plannumber(String plannumber) {
        this.setPlannumber(plannumber);
        return this;
    }

    public void setPlannumber(String plannumber) {
        this.plannumber = plannumber;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public Archives remarks(String remarks) {
        this.setRemarks(remarks);
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReceivingnumber() {
        return this.receivingnumber;
    }

    public Archives receivingnumber(String receivingnumber) {
        this.setReceivingnumber(receivingnumber);
        return this;
    }

    public void setReceivingnumber(String receivingnumber) {
        this.receivingnumber = receivingnumber;
    }

    public HrManagement getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(HrManagement hrManagement) {
        this.responsibleid = hrManagement;
    }

    public Archives responsibleid(HrManagement hrManagement) {
        this.setResponsibleid(hrManagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Archives)) {
            return false;
        }
        return getId() != null && getId().equals(((Archives) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Archives{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", date='" + getDate() + "'" +
            ", page=" + getPage() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", confidentialityperiod='" + getConfidentialityperiod() + "'" +
            ", confidentialnumber='" + getConfidentialnumber() + "'" +
            ", storageperiod='" + getStorageperiod() + "'" +
            ", plannumber='" + getPlannumber() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", receivingnumber='" + getReceivingnumber() + "'" +
            "}";
    }
}
