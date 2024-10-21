package com.cvicse.jy1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CommunicationDictionary.
 */
@Entity
@Table(name = "communication_dictionary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommunicationDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "partiesname")
    private String partiesname;

    @Column(name = "partiestype")
    private String partiestype;

    @Column(name = "partiesduty")
    private String partiesduty;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public CommunicationDictionary id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartiesname() {
        return this.partiesname;
    }

    public CommunicationDictionary partiesname(String partiesname) {
        this.setPartiesname(partiesname);
        return this;
    }

    public void setPartiesname(String partiesname) {
        this.partiesname = partiesname;
    }

    public String getPartiestype() {
        return this.partiestype;
    }

    public CommunicationDictionary partiestype(String partiestype) {
        this.setPartiestype(partiestype);
        return this;
    }

    public void setPartiestype(String partiestype) {
        this.partiestype = partiestype;
    }

    public String getPartiesduty() {
        return this.partiesduty;
    }

    public CommunicationDictionary partiesduty(String partiesduty) {
        this.setPartiesduty(partiesduty);
        return this;
    }

    public void setPartiesduty(String partiesduty) {
        this.partiesduty = partiesduty;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationDictionary)) {
            return false;
        }
        return getId() != null && getId().equals(((CommunicationDictionary) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationDictionary{" +
            "id=" + getId() +
            ", partiesname='" + getPartiesname() + "'" +
            ", partiestype='" + getPartiestype() + "'" +
            ", partiesduty='" + getPartiesduty() + "'" +
            "}";
    }
}
