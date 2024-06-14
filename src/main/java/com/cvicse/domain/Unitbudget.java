package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Unitbudget.
 */
@Entity
@Table(name = "unitbudget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Unitbudget implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "unitbudgetid")
    private Long unitbudgetid;

    @Column(name = "subprojectname")
    private String subprojectname;

    @Column(name = "unitbudgername")
    private String unitbudgername;

    @Column(name = "billingunit")
    private Integer billingunit;

    @Column(name = "jhi_number", precision = 21, scale = 2)
    private BigDecimal number;

    @Column(name = "totalbudget", precision = 21, scale = 2)
    private BigDecimal totalbudget;

    @Column(name = "maintainerbudget", precision = 21, scale = 2)
    private BigDecimal maintainerbudget;

    @Column(name = "outsourcingbudget", precision = 21, scale = 2)
    private BigDecimal outsourcingbudget;

    @Column(name = "earmarkedbudget", precision = 21, scale = 2)
    private BigDecimal earmarkedbudget;

    @Column(name = "testbudget", precision = 21, scale = 2)
    private BigDecimal testbudget;

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
    private Officers auditorid;

    @JsonIgnoreProperties(
        value = {
            "progress", "project", "funds", "totalbudget", "unitbudget", "responsibleid", "auditorid", "decument", "coordinationdepartment",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "unitbudget")
    private Comprehensivecontrol comprehensivecontrol;

    @JsonIgnoreProperties(
        value = {
            "totalbudget", "unitbudget", "document", "creatorid", "auditorid", "project", "comprehensivecontrol", "fundsavailability",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "unitbudget")
    private Fundsmanagement fundsmanagement;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Unitbudget id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUnitbudgetid() {
        return this.unitbudgetid;
    }

    public Unitbudget unitbudgetid(Long unitbudgetid) {
        this.setUnitbudgetid(unitbudgetid);
        return this;
    }

    public void setUnitbudgetid(Long unitbudgetid) {
        this.unitbudgetid = unitbudgetid;
    }

    public String getSubprojectname() {
        return this.subprojectname;
    }

    public Unitbudget subprojectname(String subprojectname) {
        this.setSubprojectname(subprojectname);
        return this;
    }

    public void setSubprojectname(String subprojectname) {
        this.subprojectname = subprojectname;
    }

    public String getUnitbudgername() {
        return this.unitbudgername;
    }

    public Unitbudget unitbudgername(String unitbudgername) {
        this.setUnitbudgername(unitbudgername);
        return this;
    }

    public void setUnitbudgername(String unitbudgername) {
        this.unitbudgername = unitbudgername;
    }

    public Integer getBillingunit() {
        return this.billingunit;
    }

    public Unitbudget billingunit(Integer billingunit) {
        this.setBillingunit(billingunit);
        return this;
    }

    public void setBillingunit(Integer billingunit) {
        this.billingunit = billingunit;
    }

    public BigDecimal getNumber() {
        return this.number;
    }

    public Unitbudget number(BigDecimal number) {
        this.setNumber(number);
        return this;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    public BigDecimal getTotalbudget() {
        return this.totalbudget;
    }

    public Unitbudget totalbudget(BigDecimal totalbudget) {
        this.setTotalbudget(totalbudget);
        return this;
    }

    public void setTotalbudget(BigDecimal totalbudget) {
        this.totalbudget = totalbudget;
    }

    public BigDecimal getMaintainerbudget() {
        return this.maintainerbudget;
    }

    public Unitbudget maintainerbudget(BigDecimal maintainerbudget) {
        this.setMaintainerbudget(maintainerbudget);
        return this;
    }

    public void setMaintainerbudget(BigDecimal maintainerbudget) {
        this.maintainerbudget = maintainerbudget;
    }

    public BigDecimal getOutsourcingbudget() {
        return this.outsourcingbudget;
    }

    public Unitbudget outsourcingbudget(BigDecimal outsourcingbudget) {
        this.setOutsourcingbudget(outsourcingbudget);
        return this;
    }

    public void setOutsourcingbudget(BigDecimal outsourcingbudget) {
        this.outsourcingbudget = outsourcingbudget;
    }

    public BigDecimal getEarmarkedbudget() {
        return this.earmarkedbudget;
    }

    public Unitbudget earmarkedbudget(BigDecimal earmarkedbudget) {
        this.setEarmarkedbudget(earmarkedbudget);
        return this;
    }

    public void setEarmarkedbudget(BigDecimal earmarkedbudget) {
        this.earmarkedbudget = earmarkedbudget;
    }

    public BigDecimal getTestbudget() {
        return this.testbudget;
    }

    public Unitbudget testbudget(BigDecimal testbudget) {
        this.setTestbudget(testbudget);
        return this;
    }

    public void setTestbudget(BigDecimal testbudget) {
        this.testbudget = testbudget;
    }

    public Officers getCreatorid() {
        return this.creatorid;
    }

    public void setCreatorid(Officers officers) {
        this.creatorid = officers;
    }

    public Unitbudget creatorid(Officers officers) {
        this.setCreatorid(officers);
        return this;
    }

    public Officers getAuditorid() {
        return this.auditorid;
    }

    public void setAuditorid(Officers officers) {
        this.auditorid = officers;
    }

    public Unitbudget auditorid(Officers officers) {
        this.setAuditorid(officers);
        return this;
    }

    public Comprehensivecontrol getComprehensivecontrol() {
        return this.comprehensivecontrol;
    }

    public void setComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        if (this.comprehensivecontrol != null) {
            this.comprehensivecontrol.setUnitbudget(null);
        }
        if (comprehensivecontrol != null) {
            comprehensivecontrol.setUnitbudget(this);
        }
        this.comprehensivecontrol = comprehensivecontrol;
    }

    public Unitbudget comprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        this.setComprehensivecontrol(comprehensivecontrol);
        return this;
    }

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        if (this.fundsmanagement != null) {
            this.fundsmanagement.setUnitbudget(null);
        }
        if (fundsmanagement != null) {
            fundsmanagement.setUnitbudget(this);
        }
        this.fundsmanagement = fundsmanagement;
    }

    public Unitbudget fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Unitbudget)) {
            return false;
        }
        return getId() != null && getId().equals(((Unitbudget) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Unitbudget{" +
            "id=" + getId() +
            ", unitbudgetid=" + getUnitbudgetid() +
            ", subprojectname='" + getSubprojectname() + "'" +
            ", unitbudgername='" + getUnitbudgername() + "'" +
            ", billingunit=" + getBillingunit() +
            ", number=" + getNumber() +
            ", totalbudget=" + getTotalbudget() +
            ", maintainerbudget=" + getMaintainerbudget() +
            ", outsourcingbudget=" + getOutsourcingbudget() +
            ", earmarkedbudget=" + getEarmarkedbudget() +
            ", testbudget=" + getTestbudget() +
            "}";
    }
}
