package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Progressplanreturns.
 */
@Entity
@Table(name = "progressplanreturns")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Progressplanreturns implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "planstarttime")
    private LocalDate planstarttime;

    @Column(name = "planendtime")
    private LocalDate planendtime;

    @Column(name = "returnstime")
    private LocalDate returnstime;

    @JsonIgnoreProperties(
        value = { "department", "planreturns", "responsibleid", "creatorid", "auditorid", "comprehensivecontrol", "progressplanreturns" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Progressplan progressplan;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "progressplanreturns", "auditedbudget" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Document document;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Progressplanreturns id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getPlanstarttime() {
        return this.planstarttime;
    }

    public Progressplanreturns planstarttime(LocalDate planstarttime) {
        this.setPlanstarttime(planstarttime);
        return this;
    }

    public void setPlanstarttime(LocalDate planstarttime) {
        this.planstarttime = planstarttime;
    }

    public LocalDate getPlanendtime() {
        return this.planendtime;
    }

    public Progressplanreturns planendtime(LocalDate planendtime) {
        this.setPlanendtime(planendtime);
        return this;
    }

    public void setPlanendtime(LocalDate planendtime) {
        this.planendtime = planendtime;
    }

    public LocalDate getReturnstime() {
        return this.returnstime;
    }

    public Progressplanreturns returnstime(LocalDate returnstime) {
        this.setReturnstime(returnstime);
        return this;
    }

    public void setReturnstime(LocalDate returnstime) {
        this.returnstime = returnstime;
    }

    public Progressplan getProgressplan() {
        return this.progressplan;
    }

    public void setProgressplan(Progressplan progressplan) {
        this.progressplan = progressplan;
    }

    public Progressplanreturns progressplan(Progressplan progressplan) {
        this.setProgressplan(progressplan);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Progressplanreturns document(Document document) {
        this.setDocument(document);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Progressplanreturns)) {
            return false;
        }
        return getId() != null && getId().equals(((Progressplanreturns) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Progressplanreturns{" +
            "id=" + getId() +
            ", planstarttime='" + getPlanstarttime() + "'" +
            ", planendtime='" + getPlanendtime() + "'" +
            ", returnstime='" + getReturnstime() + "'" +
            "}";
    }
}
