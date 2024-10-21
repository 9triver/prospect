package com.cvicse.jy1.domain;

import com.cvicse.jy1.domain.enumeration.OfficersStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "custom-string-id-generator")
    @GenericGenerator(name = "custom-string-id-generator", strategy = "com.cvicse.jy1.service.CustomStringIdGenerator")
    @Column(name = "id")
    private String id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "hiredate")
    private LocalDate hiredate;

    @Column(name = "years")
    private Integer years;

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
    @JsonIgnoreProperties(value = { "superior", "officers", "pbs", "wbs", "workbags" }, allowSetters = true)
    private Set<Department> departments = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_officers__frontline",
        joinColumns = @JoinColumn(name = "officers_id"),
        inverseJoinColumns = @JoinColumn(name = "frontline_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private Set<Frontline> frontlines = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_officers__role",
        joinColumns = @JoinColumn(name = "officers_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "permissions", "officers" }, allowSetters = true)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "officers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "officers" }, allowSetters = true)
    private Set<HrManagement> hrmanagements = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Officers id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Officers name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public Officers password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public Officers email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return this.phone;
    }

    public Officers phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
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

    public Set<Frontline> getFrontlines() {
        return this.frontlines;
    }

    public void setFrontlines(Set<Frontline> frontlines) {
        this.frontlines = frontlines;
    }

    public Officers frontlines(Set<Frontline> frontlines) {
        this.setFrontlines(frontlines);
        return this;
    }

    public Officers addFrontline(Frontline frontline) {
        this.frontlines.add(frontline);
        return this;
    }

    public Officers removeFrontline(Frontline frontline) {
        this.frontlines.remove(frontline);
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

    public Set<HrManagement> getHrmanagements() {
        return this.hrmanagements;
    }

    public void setHrmanagements(Set<HrManagement> hrManagements) {
        if (this.hrmanagements != null) {
            this.hrmanagements.forEach(i -> i.setOfficers(null));
        }
        if (hrManagements != null) {
            hrManagements.forEach(i -> i.setOfficers(this));
        }
        this.hrmanagements = hrManagements;
    }

    public Officers hrmanagements(Set<HrManagement> hrManagements) {
        this.setHrmanagements(hrManagements);
        return this;
    }

    public Officers addHrmanagement(HrManagement hrManagement) {
        this.hrmanagements.add(hrManagement);
        hrManagement.setOfficers(this);
        return this;
    }

    public Officers removeHrmanagement(HrManagement hrManagement) {
        this.hrmanagements.remove(hrManagement);
        hrManagement.setOfficers(null);
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
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            ", hiredate='" + getHiredate() + "'" +
            ", years=" + getYears() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
