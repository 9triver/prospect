package com.cvicse.domain;

import com.cvicse.domain.enumeration.Risklevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Riskmanagement.
 */
@Entity
@Table(name = "riskmanagement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Riskmanagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "riskid", unique = true)
    private Long riskid;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "jhi_year")
    private Long year;

    @Column(name = "nodename")
    private String nodename;

    @Column(name = "risktype")
    private Integer risktype;

    @Column(name = "decumentid")
    private Long decumentid;

    @Column(name = "version")
    private Integer version;

    @Column(name = "usetime")
    private LocalDate usetime;

    @Column(name = "systemlevel")
    private Integer systemlevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "risklevel")
    private Risklevel risklevel;

    @Column(name = "limitationtime")
    private String limitationtime;

    @Column(name = "closetype")
    private Integer closetype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "department", "role", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(
        value = {
            "cycleplan",
            "progressmanagement",
            "qualitymanagement",
            "fundsmanagement",
            "technicalCondition",
            "contractualfunds",
            "outsourcingmPurchaseExecute",
            "resourcemanagement",
            "riskmanagement",
            "document",
            "safetycheck",
            "department",
            "evaluationCriteria",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingmPurchasePlan",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "riskmanagement")
    private Project project;

    @JsonIgnoreProperties(value = { "riskmanagement", "creatorid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "riskmanagement")
    private Riskreport riskreport;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Riskmanagement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRiskid() {
        return this.riskid;
    }

    public Riskmanagement riskid(Long riskid) {
        this.setRiskid(riskid);
        return this;
    }

    public void setRiskid(Long riskid) {
        this.riskid = riskid;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Riskmanagement projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Long getYear() {
        return this.year;
    }

    public Riskmanagement year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getNodename() {
        return this.nodename;
    }

    public Riskmanagement nodename(String nodename) {
        this.setNodename(nodename);
        return this;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getRisktype() {
        return this.risktype;
    }

    public Riskmanagement risktype(Integer risktype) {
        this.setRisktype(risktype);
        return this;
    }

    public void setRisktype(Integer risktype) {
        this.risktype = risktype;
    }

    public Long getDecumentid() {
        return this.decumentid;
    }

    public Riskmanagement decumentid(Long decumentid) {
        this.setDecumentid(decumentid);
        return this;
    }

    public void setDecumentid(Long decumentid) {
        this.decumentid = decumentid;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Riskmanagement version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getUsetime() {
        return this.usetime;
    }

    public Riskmanagement usetime(LocalDate usetime) {
        this.setUsetime(usetime);
        return this;
    }

    public void setUsetime(LocalDate usetime) {
        this.usetime = usetime;
    }

    public Integer getSystemlevel() {
        return this.systemlevel;
    }

    public Riskmanagement systemlevel(Integer systemlevel) {
        this.setSystemlevel(systemlevel);
        return this;
    }

    public void setSystemlevel(Integer systemlevel) {
        this.systemlevel = systemlevel;
    }

    public Risklevel getRisklevel() {
        return this.risklevel;
    }

    public Riskmanagement risklevel(Risklevel risklevel) {
        this.setRisklevel(risklevel);
        return this;
    }

    public void setRisklevel(Risklevel risklevel) {
        this.risklevel = risklevel;
    }

    public String getLimitationtime() {
        return this.limitationtime;
    }

    public Riskmanagement limitationtime(String limitationtime) {
        this.setLimitationtime(limitationtime);
        return this;
    }

    public void setLimitationtime(String limitationtime) {
        this.limitationtime = limitationtime;
    }

    public Integer getClosetype() {
        return this.closetype;
    }

    public Riskmanagement closetype(Integer closetype) {
        this.setClosetype(closetype);
        return this;
    }

    public void setClosetype(Integer closetype) {
        this.closetype = closetype;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Riskmanagement creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Riskmanagement responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Riskmanagement auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setRiskmanagement(null);
        }
        if (project != null) {
            project.setRiskmanagement(this);
        }
        this.project = project;
    }

    public Riskmanagement project(Project project) {
        this.setProject(project);
        return this;
    }

    public Riskreport getRiskreport() {
        return this.riskreport;
    }

    public void setRiskreport(Riskreport riskreport) {
        if (this.riskreport != null) {
            this.riskreport.setRiskmanagement(null);
        }
        if (riskreport != null) {
            riskreport.setRiskmanagement(this);
        }
        this.riskreport = riskreport;
    }

    public Riskmanagement riskreport(Riskreport riskreport) {
        this.setRiskreport(riskreport);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Riskmanagement)) {
            return false;
        }
        return getId() != null && getId().equals(((Riskmanagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Riskmanagement{" +
            "id=" + getId() +
            ", riskid=" + getRiskid() +
            ", projectname='" + getProjectname() + "'" +
            ", year=" + getYear() +
            ", nodename='" + getNodename() + "'" +
            ", risktype=" + getRisktype() +
            ", decumentid=" + getDecumentid() +
            ", version=" + getVersion() +
            ", usetime='" + getUsetime() + "'" +
            ", systemlevel=" + getSystemlevel() +
            ", risklevel='" + getRisklevel() + "'" +
            ", limitationtime='" + getLimitationtime() + "'" +
            ", closetype=" + getClosetype() +
            "}";
    }
}
