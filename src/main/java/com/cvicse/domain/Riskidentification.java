package com.cvicse.domain;

import com.cvicse.domain.enumeration.Risklevel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Riskidentification.
 */
@Entity
@Table(name = "riskidentification")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Riskidentification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

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
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers creatorid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers responsibleid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "role", "departments", "document", "planexecute", "projectcharge", "approvalAgent" },
        allowSetters = true
    )
    private Officers auditorid;

    @JsonIgnoreProperties(value = { "riskidentification", "creatorid", "auditorid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "riskidentification")
    private Riskreport riskreport;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Riskidentification id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectname() {
        return this.projectname;
    }

    public Riskidentification projectname(String projectname) {
        this.setProjectname(projectname);
        return this;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Long getYear() {
        return this.year;
    }

    public Riskidentification year(Long year) {
        this.setYear(year);
        return this;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getNodename() {
        return this.nodename;
    }

    public Riskidentification nodename(String nodename) {
        this.setNodename(nodename);
        return this;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Integer getRisktype() {
        return this.risktype;
    }

    public Riskidentification risktype(Integer risktype) {
        this.setRisktype(risktype);
        return this;
    }

    public void setRisktype(Integer risktype) {
        this.risktype = risktype;
    }

    public Long getDecumentid() {
        return this.decumentid;
    }

    public Riskidentification decumentid(Long decumentid) {
        this.setDecumentid(decumentid);
        return this;
    }

    public void setDecumentid(Long decumentid) {
        this.decumentid = decumentid;
    }

    public Integer getVersion() {
        return this.version;
    }

    public Riskidentification version(Integer version) {
        this.setVersion(version);
        return this;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getUsetime() {
        return this.usetime;
    }

    public Riskidentification usetime(LocalDate usetime) {
        this.setUsetime(usetime);
        return this;
    }

    public void setUsetime(LocalDate usetime) {
        this.usetime = usetime;
    }

    public Integer getSystemlevel() {
        return this.systemlevel;
    }

    public Riskidentification systemlevel(Integer systemlevel) {
        this.setSystemlevel(systemlevel);
        return this;
    }

    public void setSystemlevel(Integer systemlevel) {
        this.systemlevel = systemlevel;
    }

    public Risklevel getRisklevel() {
        return this.risklevel;
    }

    public Riskidentification risklevel(Risklevel risklevel) {
        this.setRisklevel(risklevel);
        return this;
    }

    public void setRisklevel(Risklevel risklevel) {
        this.risklevel = risklevel;
    }

    public String getLimitationtime() {
        return this.limitationtime;
    }

    public Riskidentification limitationtime(String limitationtime) {
        this.setLimitationtime(limitationtime);
        return this;
    }

    public void setLimitationtime(String limitationtime) {
        this.limitationtime = limitationtime;
    }

    public Integer getClosetype() {
        return this.closetype;
    }

    public Riskidentification closetype(Integer closetype) {
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

    public Riskidentification creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getResponsibleid() {
        return this.responsibleid;
    }

    public void setResponsibleid(Officers officers) {
        this.responsibleid = officers;
    }

    public Riskidentification responsibleid(Officers officers) {
        this.setResponsibleid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Riskidentification auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Riskreport getRiskreport() {
        return this.riskreport;
    }

    public void setRiskreport(Riskreport riskreport) {
        if (this.riskreport != null) {
            this.riskreport.setRiskidentification(null);
        }
        if (riskreport != null) {
            riskreport.setRiskidentification(this);
        }
        this.riskreport = riskreport;
    }

    public Riskidentification riskreport(Riskreport riskreport) {
        this.setRiskreport(riskreport);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Riskidentification)) {
            return false;
        }
        return getId() != null && getId().equals(((Riskidentification) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Riskidentification{" +
            "id=" + getId() +
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
