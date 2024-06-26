package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Riskmanagement.
 */
@Entity
@Table(name = "riskmanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Riskmanagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @JsonIgnoreProperties(value = { "riskidentification", "riskreport", "riskmanagement" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private RiskmanagementWbs wbs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Riskmanagement id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Riskmanagement name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Riskmanagement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Riskmanagement starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Riskmanagement endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public RiskmanagementWbs getWbs() {
        return this.wbs;
    }

    public void setWbs(RiskmanagementWbs riskmanagementWbs) {
        this.wbs = riskmanagementWbs;
    }

    public Riskmanagement wbs(RiskmanagementWbs riskmanagementWbs) {
        this.setWbs(riskmanagementWbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Riskmanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Riskmanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Riskmanagement{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            "}";
    }
}
