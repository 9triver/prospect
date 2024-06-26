package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ResourcemanagementWbs.
 */
@Entity
@Table(name = "resourcemanagement_wbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResourcemanagementWbs implements Serializable {

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
    @JsonIgnoreProperties(value = { "project" }, allowSetters = true)
    private ProjectHumanresourcesplan projectHumanresourcesplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "project", "creatorid" }, allowSetters = true)
    private Projectremit projectremit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "project", "creatorid", "auditorid" }, allowSetters = true)
    private Humanresources humanresources;

    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "wbs")
    private Resourcemanagement resourcemanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public ResourcemanagementWbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public ResourcemanagementWbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public ResourcemanagementWbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public ResourcemanagementWbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public ResourcemanagementWbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public ResourcemanagementWbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public ProjectHumanresourcesplan getProjectHumanresourcesplan() {
        return this.projectHumanresourcesplan;
    }

    public void setProjectHumanresourcesplan(ProjectHumanresourcesplan projectHumanresourcesplan) {
        this.projectHumanresourcesplan = projectHumanresourcesplan;
    }

    public ResourcemanagementWbs projectHumanresourcesplan(ProjectHumanresourcesplan projectHumanresourcesplan) {
        this.setProjectHumanresourcesplan(projectHumanresourcesplan);
        return this;
    }

    public Projectremit getProjectremit() {
        return this.projectremit;
    }

    public void setProjectremit(Projectremit projectremit) {
        this.projectremit = projectremit;
    }

    public ResourcemanagementWbs projectremit(Projectremit projectremit) {
        this.setProjectremit(projectremit);
        return this;
    }

    public Humanresources getHumanresources() {
        return this.humanresources;
    }

    public void setHumanresources(Humanresources humanresources) {
        this.humanresources = humanresources;
    }

    public ResourcemanagementWbs humanresources(Humanresources humanresources) {
        this.setHumanresources(humanresources);
        return this;
    }

    public Resourcemanagement getResourcemanagement() {
        return this.resourcemanagement;
    }

    public void setResourcemanagement(Resourcemanagement resourcemanagement) {
        if (this.resourcemanagement != null) {
            this.resourcemanagement.setWbs(null);
        }
        if (resourcemanagement != null) {
            resourcemanagement.setWbs(this);
        }
        this.resourcemanagement = resourcemanagement;
    }

    public ResourcemanagementWbs resourcemanagement(Resourcemanagement resourcemanagement) {
        this.setResourcemanagement(resourcemanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResourcemanagementWbs)) {
            return false;
        }
        return getId() != null && getId().equals(((ResourcemanagementWbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResourcemanagementWbs{" +
            "id=" + getId() +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
