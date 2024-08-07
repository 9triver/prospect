package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.OfficersStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * 人员
 * @author A true hipster
 */
@Schema(description = "人员\n@author A true hipster")
@Entity
@Table(name = "officers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Officers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    @Column(name = "name")
    private String name;

    /**
     * fieldName *
     * id String,
     * name String required,
     * password String,
     * email String,
     * phone Long,
     * /** 入职时间
     */
    @Schema(description = "fieldName *\nid String,\nname String required,\npassword String,\nemail String,\nphone Long,\n/** 入职时间")
    @Column(name = "hiredate")
    private LocalDate hiredate;

    /**
     * 合同年限
     */
    @Schema(description = "合同年限")
    @Column(name = "years")
    private Integer years;

    /**
     * 状态
     */
    @Schema(description = "状态")
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OfficersStatus status;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_officers__departments",
        joinColumns = @JoinColumn(name = "officers_id"),
        inverseJoinColumns = @JoinColumn(name = "departments_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "superior", "officers" }, allowSetters = true)
    private Set<Department> departments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_officers__role",
        joinColumns = @JoinColumn(name = "officers_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "permissions", "officers" }, allowSetters = true)
    private Set<Role> roles = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Officers id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Officers name(Long name) {
        this.setName(name);
        return this;
    }

    public void setName(Long name) {
        this.id = name;
    }

    public LocalDate getHiredate() {
        return this.hiredate;
    }

    public Officers hiredate(LocalDate hiredate) {
        this.setHiredate(hiredate);
        return this;
    }

    public void setHiredate(LocalDate hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getYears() {
        return this.years;
    }

    public Officers years(Integer years) {
        this.setYears(years);
        return this;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public OfficersStatus getStatus() {
        return this.status;
    }

    public Officers status(OfficersStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(OfficersStatus status) {
        this.status = status;
    }

    public Set<Department> getDepartments() {
        return this.departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }

    public Officers departments(Set<Department> departments) {
        this.setDepartments(departments);
        return this;
    }

    public Officers addDepartments(Department department) {
        this.departments.add(department);
        return this;
    }

    public Officers removeDepartments(Department department) {
        this.departments.remove(department);
        return this;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Officers roles(Set<Role> roles) {
        this.setRoles(roles);
        return this;
    }

    public Officers addRole(Role role) {
        this.roles.add(role);
        return this;
    }

    public Officers removeRole(Role role) {
        this.roles.remove(role);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Officers)) {
            return false;
        }
        return getId() != null && getId().equals(((Officers) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Officers{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", hiredate='" + getHiredate() + "'" +
            ", years=" + getYears() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
