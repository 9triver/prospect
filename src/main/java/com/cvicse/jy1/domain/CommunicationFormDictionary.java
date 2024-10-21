package com.cvicse.jy1.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CommunicationFormDictionary.
 */
@Entity
@Table(name = "communication_form_dictionary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CommunicationFormDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "communicationformname")
    private String communicationformname;

    @Column(name = "communicationformtype")
    private String communicationformtype;

    @Column(name = "status")
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public CommunicationFormDictionary id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommunicationformname() {
        return this.communicationformname;
    }

    public CommunicationFormDictionary communicationformname(String communicationformname) {
        this.setCommunicationformname(communicationformname);
        return this;
    }

    public void setCommunicationformname(String communicationformname) {
        this.communicationformname = communicationformname;
    }

    public String getCommunicationformtype() {
        return this.communicationformtype;
    }

    public CommunicationFormDictionary communicationformtype(String communicationformtype) {
        this.setCommunicationformtype(communicationformtype);
        return this;
    }

    public void setCommunicationformtype(String communicationformtype) {
        this.communicationformtype = communicationformtype;
    }

    public String getStatus() {
        return this.status;
    }

    public CommunicationFormDictionary status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CommunicationFormDictionary)) {
            return false;
        }
        return getId() != null && getId().equals(((CommunicationFormDictionary) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CommunicationFormDictionary{" +
            "id=" + getId() +
            ", communicationformname='" + getCommunicationformname() + "'" +
            ", communicationformtype='" + getCommunicationformtype() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
