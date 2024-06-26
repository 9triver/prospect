package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fundsmanagement.
 */
@Entity
@Table(name = "fundsmanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fundsmanagement implements Serializable {

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
        value = { "auditedbudget", "totalbudget", "unitbudget", "fundsavailability", "contractualfunds", "fundsmanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private FundsmanagementWbs wbs;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "funds")
    private Comprehensivecontrol comprehensivecontrol;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Fundsmanagement id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Fundsmanagement name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Fundsmanagement description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Fundsmanagement starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Fundsmanagement endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public FundsmanagementWbs getWbs() {
        return this.wbs;
    }

    public void setWbs(FundsmanagementWbs fundsmanagementWbs) {
        this.wbs = fundsmanagementWbs;
    }

    public Fundsmanagement wbs(FundsmanagementWbs fundsmanagementWbs) {
        this.setWbs(fundsmanagementWbs);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setFunds(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setFunds(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Fundsmanagement comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fundsmanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Fundsmanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fundsmanagement{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            "}";
    }
}
