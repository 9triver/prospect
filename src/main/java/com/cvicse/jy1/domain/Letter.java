package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Letter.
 */
@Entity
@Table(name = "letter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Letter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Integer id;

    @Column(name = "lettername")
    private String lettername;

    @Column(name = "letternumber")
    private String letternumber;

    @Column(name = "lettertype")
    private String lettertype;

    @Enumerated(EnumType.STRING)
    @Column(name = "secretlevel")
    private Secretlevel secretlevel;

    @Column(name = "lettercontent")
    private String lettercontent;

    @Column(name = "letterstatus")
    private String letterstatus;

    @Column(name = "lettertime")
    private LocalDate lettertime;

    @Column(name = "previousfile")
    private String previousfile;

    @Column(name = "datarecordstatus")
    private String datarecordstatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "projectpbs",
            "responsibleperson",
            "technicaldirector",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "projectdeliverables",
            "relevantdepartments",
            "workbags",
            "progressPlans",
            "projectBudgets",
            "projects",
            "fundsEstimations",
            "contractCostBudgets",
            "costControlSystems",
            "outsourcingContractuals",
            "outsourcingPurchasePlans",
            "technicals",
            "projectTotalwbs",
        },
        allowSetters = true
    )
    private Projectwbs wbsid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = {
            "responsibleperson",
            "projectmanager",
            "knowingpeople",
            "auditorid",
            "responsibledepartment",
            "department",
            "projectdeliverables",
            "relevantdepartments",
            "wbsids",
            "works",
            "outsourcingContract",
        },
        allowSetters = true
    )
    private Workbag workbagid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private Frontline frontlineid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department receivingunit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Department sendingunit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement lettermaker;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement letterreceiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private HrManagement letterhandler;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Integer getId() {
        return this.id;
    }

    public Letter id(Integer id) {
        this.setId(id);
        return this;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLettername() {
        return this.lettername;
    }

    public Letter lettername(String lettername) {
        this.setLettername(lettername);
        return this;
    }

    public void setLettername(String lettername) {
        this.lettername = lettername;
    }

    public String getLetternumber() {
        return this.letternumber;
    }

    public Letter letternumber(String letternumber) {
        this.setLetternumber(letternumber);
        return this;
    }

    public void setLetternumber(String letternumber) {
        this.letternumber = letternumber;
    }

    public String getLettertype() {
        return this.lettertype;
    }

    public Letter lettertype(String lettertype) {
        this.setLettertype(lettertype);
        return this;
    }

    public void setLettertype(String lettertype) {
        this.lettertype = lettertype;
    }

    public Secretlevel getSecretlevel() {
        return this.secretlevel;
    }

    public Letter secretlevel(Secretlevel secretlevel) {
        this.setSecretlevel(secretlevel);
        return this;
    }

    public void setSecretlevel(Secretlevel secretlevel) {
        this.secretlevel = secretlevel;
    }

    public String getLettercontent() {
        return this.lettercontent;
    }

    public Letter lettercontent(String lettercontent) {
        this.setLettercontent(lettercontent);
        return this;
    }

    public void setLettercontent(String lettercontent) {
        this.lettercontent = lettercontent;
    }

    public String getLetterstatus() {
        return this.letterstatus;
    }

    public Letter letterstatus(String letterstatus) {
        this.setLetterstatus(letterstatus);
        return this;
    }

    public void setLetterstatus(String letterstatus) {
        this.letterstatus = letterstatus;
    }

    public LocalDate getLettertime() {
        return this.lettertime;
    }

    public Letter lettertime(LocalDate lettertime) {
        this.setLettertime(lettertime);
        return this;
    }

    public void setLettertime(LocalDate lettertime) {
        this.lettertime = lettertime;
    }

    public String getPreviousfile() {
        return this.previousfile;
    }

    public Letter previousfile(String previousfile) {
        this.setPreviousfile(previousfile);
        return this;
    }

    public void setPreviousfile(String previousfile) {
        this.previousfile = previousfile;
    }

    public String getDatarecordstatus() {
        return this.datarecordstatus;
    }

    public Letter datarecordstatus(String datarecordstatus) {
        this.setDatarecordstatus(datarecordstatus);
        return this;
    }

    public void setDatarecordstatus(String datarecordstatus) {
        this.datarecordstatus = datarecordstatus;
    }

    public Projectwbs getWbsid() {
        return this.wbsid;
    }

    public void setWbsid(Projectwbs projectwbs) {
        this.wbsid = projectwbs;
    }

    public Letter wbsid(Projectwbs projectwbs) {
        this.setWbsid(projectwbs);
        return this;
    }

    public Workbag getWorkbagid() {
        return this.workbagid;
    }

    public void setWorkbagid(Workbag workbag) {
        this.workbagid = workbag;
    }

    public Letter workbagid(Workbag workbag) {
        this.setWorkbagid(workbag);
        return this;
    }

    public Frontline getFrontlineid() {
        return this.frontlineid;
    }

    public void setFrontlineid(Frontline frontline) {
        this.frontlineid = frontline;
    }

    public Letter frontlineid(Frontline frontline) {
        this.setFrontlineid(frontline);
        return this;
    }

    public Department getReceivingunit() {
        return this.receivingunit;
    }

    public void setReceivingunit(Department department) {
        this.receivingunit = department;
    }

    public Letter receivingunit(Department department) {
        this.setReceivingunit(department);
        return this;
    }

    public Department getSendingunit() {
        return this.sendingunit;
    }

    public void setSendingunit(Department department) {
        this.sendingunit = department;
    }

    public Letter sendingunit(Department department) {
        this.setSendingunit(department);
        return this;
    }

    public HrManagement getLettermaker() {
        return this.lettermaker;
    }

    public void setLettermaker(HrManagement hrManagement) {
        this.lettermaker = hrManagement;
    }

    public Letter lettermaker(HrManagement hrManagement) {
        this.setLettermaker(hrManagement);
        return this;
    }

    public HrManagement getLetterreceiver() {
        return this.letterreceiver;
    }

    public void setLetterreceiver(HrManagement hrManagement) {
        this.letterreceiver = hrManagement;
    }

    public Letter letterreceiver(HrManagement hrManagement) {
        this.setLetterreceiver(hrManagement);
        return this;
    }

    public HrManagement getLetterhandler() {
        return this.letterhandler;
    }

    public void setLetterhandler(HrManagement hrManagement) {
        this.letterhandler = hrManagement;
    }

    public Letter letterhandler(HrManagement hrManagement) {
        this.setLetterhandler(hrManagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Letter)) {
            return false;
        }
        return getId() != null && getId().equals(((Letter) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Letter{" +
            "id=" + getId() +
            ", lettername='" + getLettername() + "'" +
            ", letternumber='" + getLetternumber() + "'" +
            ", lettertype='" + getLettertype() + "'" +
            ", secretlevel='" + getSecretlevel() + "'" +
            ", lettercontent='" + getLettercontent() + "'" +
            ", letterstatus='" + getLetterstatus() + "'" +
            ", lettertime='" + getLettertime() + "'" +
            ", previousfile='" + getPreviousfile() + "'" +
            ", datarecordstatus='" + getDatarecordstatus() + "'" +
            "}";
    }
}
