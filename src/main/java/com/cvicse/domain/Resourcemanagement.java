package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Resourcemanagement.
 */
@Entity
@Table(name = "resourcemanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Resourcemanagement implements Serializable {

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

    @JsonIgnoreProperties(
        value = { "projectHumanresourcesplan", "projectremit", "humanresources", "resourcemanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private ResourcemanagementWbs wbs;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Resourcemanagement id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Resourcemanagement name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Resourcemanagement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Resourcemanagement starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Resourcemanagement endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public ResourcemanagementWbs getWbs() {
        return this.wbs;
    }

    public void setWbs(ResourcemanagementWbs resourcemanagementWbs) {
        this.wbs = resourcemanagementWbs;
    }

    public Resourcemanagement wbs(ResourcemanagementWbs resourcemanagementWbs) {
        this.setWbs(resourcemanagementWbs);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Resourcemanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Resourcemanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Resourcemanagement{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            "}";
    }
}
