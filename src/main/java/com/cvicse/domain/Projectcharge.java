package com.cvicse.domain;

import com.cvicse.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectcharge.
 */
@Entity
@Table(name = "projectcharge")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectcharge implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "formid")
    private String formid;

    @Column(name = "starttime")
    private LocalDate starttime;

    @Column(name = "endtime")
    private LocalDate endtime;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "requestdeportment")
    private String requestdeportment;

    @Column(name = "chargetype")
    private Integer chargetype;

    @Column(name = "chargecontent")
    private String chargecontent;

    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Officers creatorid;

    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectcharge")
    private Cycleplan cycleplan;

    @JsonIgnoreProperties(value = { "document", "monthplan", "projectcharge", "creatorid", "auditorid", "cycleplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectcharge")
    private Annualplan annualplan;

    @JsonIgnoreProperties(
        value = { "document", "planreturns", "projectcharge", "creatorid", "auditorid", "cycleplan", "annualplan" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectcharge")
    private Monthplan monthplan;

    @JsonIgnoreProperties(value = { "projectcharge" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectcharge")
    private Pbsbaseline pbsbaseline;

    @JsonIgnoreProperties(value = { "projectcharge" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectcharge")
    private Wbsbaseline wbsbaseline;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Projectcharge id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Projectcharge projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getFormid() {
        return this.formid;
    }

    public Projectcharge formid(String formid) {
        this.setFormid(formid);
        return this;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }

    public LocalDate getStarttime() {
        return this.starttime;
    }

    public Projectcharge starttime(LocalDate starttime) {
        this.setStarttime(starttime);
        return this;
    }

    public void setStarttime(LocalDate starttime) {
        this.starttime = starttime;
    }

    public LocalDate getEndtime() {
        return this.endtime;
    }

    public Projectcharge endtime(LocalDate endtime) {
        this.setEndtime(endtime);
        return this;
    }

    public void setEndtime(LocalDate endtime) {
        this.endtime = endtime;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Projectcharge secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getRequestdeportment() {
        return this.requestdeportment;
    }

    public Projectcharge requestdeportment(String requestdeportment) {
        this.setRequestdeportment(requestdeportment);
        return this;
    }

    public void setRequestdeportment(String requestdeportment) {
        this.requestdeportment = requestdeportment;
    }

    public Integer getChargetype() {
        return this.chargetype;
    }

    public Projectcharge chargetype(Integer chargetype) {
        this.setChargetype(chargetype);
        return this;
    }

    public void setChargetype(Integer chargetype) {
        this.chargetype = chargetype;
    }

    public String getChargecontent() {
        return this.chargecontent;
    }

    public Projectcharge chargecontent(String chargecontent) {
        this.setChargecontent(chargecontent);
        return this;
    }

    public void setChargecontent(String chargecontent) {
        this.chargecontent = chargecontent;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Projectcharge creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        if (this.cycleplan != null) {
            this.cycleplan.setProjectcharge(null);
        }
        if (cycleplan != null) {
            cycleplan.setProjectcharge(this);
        }
        this.cycleplan = cycleplan;
    }

    public Projectcharge cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Annualplan getAnnualplan() {
        return this.annualplan;
    }

    public void setAnnualplan(Annualplan annualplan) {
        if (this.annualplan != null) {
            this.annualplan.setProjectcharge(null);
        }
        if (annualplan != null) {
            annualplan.setProjectcharge(this);
        }
        this.annualplan = annualplan;
    }

    public Projectcharge annualplan(Annualplan annualplan) {
        this.setAnnualplan(annualplan);
        return this;
    }

    public Monthplan getMonthplan() {
        return this.monthplan;
    }

    public void setMonthplan(Monthplan monthplan) {
        if (this.monthplan != null) {
            this.monthplan.setProjectcharge(null);
        }
        if (monthplan != null) {
            monthplan.setProjectcharge(this);
        }
        this.monthplan = monthplan;
    }

    public Projectcharge monthplan(Monthplan monthplan) {
        this.setMonthplan(monthplan);
        return this;
    }

    public Pbsbaseline getPbsbaseline() {
        return this.pbsbaseline;
    }

    public void setPbsbaseline(Pbsbaseline pbsbaseline) {
        if (this.pbsbaseline != null) {
            this.pbsbaseline.setProjectcharge(null);
        }
        if (pbsbaseline != null) {
            pbsbaseline.setProjectcharge(this);
        }
        this.pbsbaseline = pbsbaseline;
    }

    public Projectcharge pbsbaseline(Pbsbaseline pbsbaseline) {
        this.setPbsbaseline(pbsbaseline);
        return this;
    }

    public Wbsbaseline getWbsbaseline() {
        return this.wbsbaseline;
    }

    public void setWbsbaseline(Wbsbaseline wbsbaseline) {
        if (this.wbsbaseline != null) {
            this.wbsbaseline.setProjectcharge(null);
        }
        if (wbsbaseline != null) {
            wbsbaseline.setProjectcharge(this);
        }
        this.wbsbaseline = wbsbaseline;
    }

    public Projectcharge wbsbaseline(Wbsbaseline wbsbaseline) {
        this.setWbsbaseline(wbsbaseline);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectcharge)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectcharge) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectcharge{" +
            "id=" + getId() +
            ", projectname='" + getProjectname() + "'" +
            ", formid='" + getFormid() + "'" +
            ", starttime='" + getStarttime() + "'" +
            ", endtime='" + getEndtime() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", requestdeportment='" + getRequestdeportment() + "'" +
            ", chargetype=" + getChargetype() +
            ", chargecontent='" + getChargecontent() + "'" +
            "}";
    }
}
