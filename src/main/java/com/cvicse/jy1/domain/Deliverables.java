package com.cvicse.jy1.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Deliverables.
 */
@Entity
@Table(name = "deliverables")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Deliverables implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "parentcode")
    private String parentcode;

    @Column(name = "jhi_level")
    private String level;

    @Column(name = "status")
    private String status;

    @Column(name = "description")
    private String description;

    @JsonIgnoreProperties(value = { "code", "belongwbsids", "belongworkbagids" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "code")
    private Projectdeliverables projectdeliverables;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Deliverables id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Deliverables code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public Deliverables name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentcode() {
        return this.parentcode;
    }

    public Deliverables parentcode(String parentcode) {
        this.setParentcode(parentcode);
        return this;
    }

    public void setParentcode(String parentcode) {
        this.parentcode = parentcode;
    }

    public String getLevel() {
        return this.level;
    }

    public Deliverables level(String level) {
        this.setLevel(level);
        return this;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getStatus() {
        return this.status;
    }

    public Deliverables status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public Deliverables description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Projectdeliverables getProjectdeliverables() {
        return this.projectdeliverables;
    }

    public void setProjectdeliverables(Projectdeliverables projectdeliverables) {
        if (this.projectdeliverables != null) {
            this.projectdeliverables.setCode(null);
        }
        if (projectdeliverables != null) {
            projectdeliverables.setCode(this);
        }
        this.projectdeliverables = projectdeliverables;
    }

    public Deliverables projectdeliverables(Projectdeliverables projectdeliverables) {
        this.setProjectdeliverables(projectdeliverables);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deliverables)) {
            return false;
        }
        return getId() != null && getId().equals(((Deliverables) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Deliverables{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", parentcode='" + getParentcode() + "'" +
            ", level='" + getLevel() + "'" +
            ", status='" + getStatus() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
