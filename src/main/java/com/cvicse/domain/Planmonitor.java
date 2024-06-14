package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Planmonitor.
 */
@Entity
@Table(name = "planmonitor")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Planmonitor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "month")
    private LocalDate month;

    @Column(name = "jhi_type")
    private String type;

    @Column(name = "jhi_year")
    private Long year;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "status")
    private Integer status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Planmonitor id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getMonth() {
        return this.month;
    }

    public Planmonitor month(LocalDate month) {
        this.setMonth(month);
        return this;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public String getType() {
        return this.type;
    }

    public Planmonitor type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getYear() {
        return this.year;
    }

    public Planmonitor year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Planmonitor secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Planmonitor status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Planmonitor)) {
            return false;
        }
        return getId() != null && getId().equals(((Planmonitor) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Planmonitor{" +
            "id=" + getId() +
            ", month='" + getMonth() + "'" +
            ", type='" + getType() + "'" +
            ", year=" + getYear() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
