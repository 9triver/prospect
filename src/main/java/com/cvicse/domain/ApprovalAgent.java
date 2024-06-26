package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ApprovalAgent.
 */
@Entity
@Table(name = "approval_agent")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ApprovalAgent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "agentid")
    private Long agentid;

    @Column(name = "agentname")
    private String agentname;

    @Column(name = "agentstarttime")
    private LocalDate agentstarttime;

    @Column(name = "autocanceltime")
    private LocalDate autocanceltime;

    @Column(name = "agentdepartment")
    private String agentdepartment;

    @Column(name = "originalapprovalname")
    private String originalapprovalname;

    @Column(name = "originaldepartment")
    private String originaldepartment;

    @Column(name = "secrecylevel")
    private Integer secrecylevel;

    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Officers originalapprovalid;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ApprovalAgent id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAgentid() {
        return this.agentid;
    }

    public ApprovalAgent agentid(Long agentid) {
        this.setAgentid(agentid);
        return this;
    }

    public void setAgentid(Long agentid) {
        this.agentid = agentid;
    }

    public String getAgentname() {
        return this.agentname;
    }

    public ApprovalAgent agentname(String agentname) {
        this.setAgentname(agentname);
        return this;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname;
    }

    public LocalDate getAgentstarttime() {
        return this.agentstarttime;
    }

    public ApprovalAgent agentstarttime(LocalDate agentstarttime) {
        this.setAgentstarttime(agentstarttime);
        return this;
    }

    public void setAgentstarttime(LocalDate agentstarttime) {
        this.agentstarttime = agentstarttime;
    }

    public LocalDate getAutocanceltime() {
        return this.autocanceltime;
    }

    public ApprovalAgent autocanceltime(LocalDate autocanceltime) {
        this.setAutocanceltime(autocanceltime);
        return this;
    }

    public void setAutocanceltime(LocalDate autocanceltime) {
        this.autocanceltime = autocanceltime;
    }

    public String getAgentdepartment() {
        return this.agentdepartment;
    }

    public ApprovalAgent agentdepartment(String agentdepartment) {
        this.setAgentdepartment(agentdepartment);
        return this;
    }

    public void setAgentdepartment(String agentdepartment) {
        this.agentdepartment = agentdepartment;
    }

    public String getOriginalapprovalname() {
        return this.originalapprovalname;
    }

    public ApprovalAgent originalapprovalname(String originalapprovalname) {
        this.setOriginalapprovalname(originalapprovalname);
        return this;
    }

    public void setOriginalapprovalname(String originalapprovalname) {
        this.originalapprovalname = originalapprovalname;
    }

    public String getOriginaldepartment() {
        return this.originaldepartment;
    }

    public ApprovalAgent originaldepartment(String originaldepartment) {
        this.setOriginaldepartment(originaldepartment);
        return this;
    }

    public void setOriginaldepartment(String originaldepartment) {
        this.originaldepartment = originaldepartment;
    }

    public Integer getSecrecylevel() {
        return this.secrecylevel;
    }

    public ApprovalAgent secrecylevel(Integer secrecylevel) {
        this.setSecrecylevel(secrecylevel);
        return this;
    }

    public void setSecrecylevel(Integer secrecylevel) {
        this.secrecylevel = secrecylevel;
    }

    public Officers getOriginalapprovalid() {
        return this.originalapprovalid;
    }

    public void setOriginalapprovalid(Officers officers) {
        this.originalapprovalid = officers;
    }

    public ApprovalAgent originalapprovalid(Officers officers) {
        this.setOriginalapprovalid(officers);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApprovalAgent)) {
            return false;
        }
        return getId() != null && getId().equals(((ApprovalAgent) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ApprovalAgent{" +
            "id=" + getId() +
            ", agentid=" + getAgentid() +
            ", agentname='" + getAgentname() + "'" +
            ", agentstarttime='" + getAgentstarttime() + "'" +
            ", autocanceltime='" + getAutocanceltime() + "'" +
            ", agentdepartment='" + getAgentdepartment() + "'" +
            ", originalapprovalname='" + getOriginalapprovalname() + "'" +
            ", originaldepartment='" + getOriginaldepartment() + "'" +
            ", secrecylevel=" + getSecrecylevel() +
            "}";
    }
}
