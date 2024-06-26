package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SecrecymanagementWbs.
 */
@Entity
@Table(name = "secrecymanagement_wbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class SecrecymanagementWbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "wbsspare_1")
    private String wbsspare1;

    @Column(name = "wbsspare_2")
    private String wbsspare2;

    @Column(name = "wbsspare_3")
    private String wbsspare3;

    @Column(name = "wbsspare_4")
    private String wbsspare4;

    @Column(name = "wbsspare_5")
    private String wbsspare5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid", "projectSecrecy" }, allowSetters = true)
    private Secrecysystem secrecysystem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "secrecysystem", "creatorid", "auditorid", "projectid" }, allowSetters = true)
    private ProjectSecrecy projectSecrecy;

    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbs")
    private Secrecymanagement secrecymanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public SecrecymanagementWbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public SecrecymanagementWbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public SecrecymanagementWbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public SecrecymanagementWbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public SecrecymanagementWbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public SecrecymanagementWbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public Secrecysystem getSecrecysystem() {
        return this.secrecysystem;
    }

    public void setSecrecysystem(Secrecysystem secrecysystem) {
        this.secrecysystem = secrecysystem;
    }

    public SecrecymanagementWbs secrecysystem(Secrecysystem secrecysystem) {
        this.setSecrecysystem(secrecysystem);
        return this;
    }

    public ProjectSecrecy getProjectSecrecy() {
        return this.projectSecrecy;
    }

    public void setProjectSecrecy(ProjectSecrecy projectSecrecy) {
        this.projectSecrecy = projectSecrecy;
    }

    public SecrecymanagementWbs projectSecrecy(ProjectSecrecy projectSecrecy) {
        this.setProjectSecrecy(projectSecrecy);
        return this;
    }

    public Secrecymanagement getSecrecymanagement() {
        return this.secrecymanagement;
    }

    public void setSecrecymanagement(Secrecymanagement secrecymanagement) {
        if (this.secrecymanagement != null) {
            this.secrecymanagement.setWbs(null);
        }
        if (secrecymanagement != null) {
            secrecymanagement.setWbs(this);
        }
        this.secrecymanagement = secrecymanagement;
    }

    public SecrecymanagementWbs secrecymanagement(Secrecymanagement secrecymanagement) {
        this.setSecrecymanagement(secrecymanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SecrecymanagementWbs)) {
            return false;
        }
        return getId() != null && getId().equals(((SecrecymanagementWbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SecrecymanagementWbs{" +
            "id=" + getId() +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
