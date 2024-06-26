package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Officers.
 */
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

    @Column(name = "officersid")
    private Long officersid;

    @Column(name = "officersname")
    private String officersname;

    @Column(name = "jhi_password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private Long phone;

    @JsonIgnoreProperties(
        value = { "officers", "project", "planstrategy", "progressmanagement", "evaluationCriteria" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Department department;

    @JsonIgnoreProperties(value = { "permission", "officers" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(unique = true)
    private Role role;

    @JsonIgnoreProperties(
        value = { "creatorid", "project", "cycleplan", "annualplan", "monthplan", "fundsmanagement" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "creatorid")
    private Document document;

    @JsonIgnoreProperties(value = { "planreturns", "responsibleid", "monthplan" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "responsibleid")
    private Planexecute planexecute;

    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "pbsbaseline", "wbsbaseline" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "creatorid")
    private Projectcharge projectcharge;

    @JsonIgnoreProperties(value = { "originalapprovalid" }, allowSetters = true)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "originalapprovalid")
    private ApprovalAgent approvalAgent;

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

    public Long getOfficersid() {
        return this.officersid;
    }

    public Officers officersid(Long officersid) {
        this.setOfficersid(officersid);
        return this;
    }

    public void setOfficersid(Long officersid) {
        this.officersid = officersid;
    }

    public String getOfficersname() {
        return this.officersname;
    }

    public Officers officersname(String officersname) {
        this.setOfficersname(officersname);
        return this;
    }

    public void setOfficersname(String officersname) {
        this.officersname = officersname;
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

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Officers department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Officers role(Role role) {
        this.setRole(role);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        if (this.document != null) {
            this.document.setCreatorid(null);
        }
        if (document != null) {
            document.setCreatorid(this);
        }
        this.document = document;
    }

    public Officers document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Planexecute getPlanexecute() {
        return this.planexecute;
    }

    public void setPlanexecute(Planexecute planexecute) {
        if (this.planexecute != null) {
            this.planexecute.setResponsibleid(null);
        }
        if (planexecute != null) {
            planexecute.setResponsibleid(this);
        }
        this.planexecute = planexecute;
    }

    public Officers planexecute(Planexecute planexecute) {
        this.setPlanexecute(planexecute);
        return this;
    }

    public Projectcharge getProjectcharge() {
        return this.projectcharge;
    }

    public void setProjectcharge(Projectcharge projectcharge) {
        if (this.projectcharge != null) {
            this.projectcharge.setCreatorid(null);
        }
        if (projectcharge != null) {
            projectcharge.setCreatorid(this);
        }
        this.projectcharge = projectcharge;
    }

    public Officers projectcharge(Projectcharge projectcharge) {
        this.setProjectcharge(projectcharge);
        return this;
    }

    public ApprovalAgent getApprovalAgent() {
        return this.approvalAgent;
    }

    public void setApprovalAgent(ApprovalAgent approvalAgent) {
        if (this.approvalAgent != null) {
            this.approvalAgent.setOriginalapprovalid(null);
        }
        if (approvalAgent != null) {
            approvalAgent.setOriginalapprovalid(this);
        }
        this.approvalAgent = approvalAgent;
    }

    public Officers approvalAgent(ApprovalAgent approvalAgent) {
        this.setApprovalAgent(approvalAgent);
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
            ", officersid=" + getOfficersid() +
            ", officersname='" + getOfficersname() + "'" +
            ", password='" + getPassword() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone=" + getPhone() +
            "}";
    }
}
