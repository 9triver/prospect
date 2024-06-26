package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Pbsbaseline.
 */
@Entity
@Table(name = "pbsbaseline")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Pbsbaseline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "requestdeportment")
    private String requestdeportment;

    @Column(name = "chargetype")
    private Integer chargetype;

    @Column(name = "chargecontent")
    private String chargecontent;

    @Column(name = "status")
    private Integer status;

    @Column(name = "version")
    private Integer version;

    @Column(name = "remark")
    private String remark;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "pbsbaseline", "wbsbaseline" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Projectcharge projectcharge;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Pbsbaseline id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Pbsbaseline secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getRequestdeportment() {
        return this.requestdeportment;
    }

    public Pbsbaseline requestdeportment(String requestdeportment) {
        this.setRequestdeportment(requestdeportment);
        return this;
    }

    public void setRequestdeportment(String requestdeportment) {
        this.requestdeportment = requestdeportment;
    }

    public Integer getChargetype() {
        return this.chargetype;
    }

    public Pbsbaseline chargetype(Integer chargetype) {
        this.setChargetype(chargetype);
        return this;
    }

    public void setChargetype(Integer chargetype) {
        this.chargetype = chargetype;
    }

    public String getChargecontent() {
        return this.chargecontent;
    }

    public Pbsbaseline chargecontent(String chargecontent) {
        this.setChargecontent(chargecontent);
        return this;
    }

    public void setChargecontent(String chargecontent) {
        this.chargecontent = chargecontent;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Pbsbaseline status(Integer status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Pbsbaseline version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getRemark() {
        return this.remark;
    }

    public Pbsbaseline remark(String remark) {
        this.setRemark(remark);
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        this.projectcharge = projectcharge;
    }

    public Pbsbaseline projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Pbsbaseline)) {
            return false;
        }
        return getId() != null && getId().equals(((Pbsbaseline) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Pbsbaseline{" +
            "id=" + getId() +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", requestdeportment='" + getRequestdeportment() + "'" +
            ", chargetype=" + getChargetype() +
            ", chargecontent='" + getChargecontent() + "'" +
            ", status=" + getStatus() +
            ", version=" + getVersion() +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
