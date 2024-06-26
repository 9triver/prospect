package com.cvicse.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Projectwbs.
 */
@Entity
@Table(name = "projectwbs")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Projectwbs implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private String id;

    @Column(name = "projectwbsname")
    private String projectwbsname;

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
    @JsonIgnoreProperties(
        value = { "document", "annualplan", "monthplan", "projectcharge", "responsibleid", "auditorid" },
        allowSetters = true
    )
    private Cycleplan cycleplan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Progressmanagement progressmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Qualitymanagement qualitymanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs", "comprehensivecontrol" }, allowSetters = true)
    private Fundsmanagement fundsmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Technicalmanagement technicalmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "creatorid", "auditorid" }, allowSetters = true)
    private Contractualfunds contractualfunds;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Outsourcingmanagement outsourcingmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Resourcemanagement resourcemanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Riskmanagement riskmanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "wbs" }, allowSetters = true)
    private Securitymanagement securitymanagement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(
        value = { "creatorid", "cycleplan", "annualplan", "monthplan", "progressplanreturns", "auditedbudget" },
        allowSetters = true
    )
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "auditorid", "responsibleid" }, allowSetters = true)
    private Safetycheck safetycheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "officers", "planstrategy", "progressplan", "evaluationCriteria" }, allowSetters = true)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "department", "managementCapacityEvaluation" }, allowSetters = true)
    private EvaluationCriteria evaluationCriteria;

    @JsonIgnoreProperties(
        value = {
            "projectwbs",
            "responsibleid",
            "auditorid",
            "projectSecrecy",
            "comprehensivecontrol",
            "wbsmanage",
            "outsourcingPurchasePlan",
            "projectHumanresourcesplan",
            "projectremit",
            "humanresources",
            "annualSecurityPlan",
            "managementCapacityEvaluation",
        },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "projectwbs")
    private Project project;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Projectwbs id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectwbsname() {
        return this.projectwbsname;
    }

    public Projectwbs projectwbsname(String projectwbsname) {
        this.setProjectwbsname(projectwbsname);
        return this;
    }

    public void setProjectwbsname(String projectwbsname) {
        this.projectwbsname = projectwbsname;
    }

    public String getWbsspare1() {
        return this.wbsspare1;
    }

    public Projectwbs wbsspare1(String wbsspare1) {
        this.setWbsspare1(wbsspare1);
        return this;
    }

    public void setWbsspare1(String wbsspare1) {
        this.wbsspare1 = wbsspare1;
    }

    public String getWbsspare2() {
        return this.wbsspare2;
    }

    public Projectwbs wbsspare2(String wbsspare2) {
        this.setWbsspare2(wbsspare2);
        return this;
    }

    public void setWbsspare2(String wbsspare2) {
        this.wbsspare2 = wbsspare2;
    }

    public String getWbsspare3() {
        return this.wbsspare3;
    }

    public Projectwbs wbsspare3(String wbsspare3) {
        this.setWbsspare3(wbsspare3);
        return this;
    }

    public void setWbsspare3(String wbsspare3) {
        this.wbsspare3 = wbsspare3;
    }

    public String getWbsspare4() {
        return this.wbsspare4;
    }

    public Projectwbs wbsspare4(String wbsspare4) {
        this.setWbsspare4(wbsspare4);
        return this;
    }

    public void setWbsspare4(String wbsspare4) {
        this.wbsspare4 = wbsspare4;
    }

    public String getWbsspare5() {
        return this.wbsspare5;
    }

    public Projectwbs wbsspare5(String wbsspare5) {
        this.setWbsspare5(wbsspare5);
        return this;
    }

    public void setWbsspare5(String wbsspare5) {
        this.wbsspare5 = wbsspare5;
    }

    public Cycleplan getCycleplan() {
        return this.cycleplan;
    }

    public void setCycleplan(Cycleplan cycleplan) {
        this.cycleplan = cycleplan;
    }

    public Projectwbs cycleplan(Cycleplan cycleplan) {
        this.setCycleplan(cycleplan);
        return this;
    }

    public Progressmanagement getProgressmanagement() {
        return this.progressmanagement;
    }

    public void setProgressmanagement(Progressmanagement progressmanagement) {
        this.progressmanagement = progressmanagement;
    }

    public Projectwbs progressmanagement(Progressmanagement progressmanagement) {
        this.setProgressmanagement(progressmanagement);
        return this;
    }

    public Qualitymanagement getQualitymanagement() {
        return this.qualitymanagement;
    }

    public void setQualitymanagement(Qualitymanagement qualitymanagement) {
        this.qualitymanagement = qualitymanagement;
    }

    public Projectwbs qualitymanagement(Qualitymanagement qualitymanagement) {
        this.setQualitymanagement(qualitymanagement);
        return this;
    }

    public Fundsmanagement getFundsmanagement() {
        return this.fundsmanagement;
    }

    public void setFundsmanagement(Fundsmanagement fundsmanagement) {
        this.fundsmanagement = fundsmanagement;
    }

    public Projectwbs fundsmanagement(Fundsmanagement fundsmanagement) {
        this.setFundsmanagement(fundsmanagement);
        return this;
    }

    public Technicalmanagement getTechnicalmanagement() {
        return this.technicalmanagement;
    }

    public void setTechnicalmanagement(Technicalmanagement technicalmanagement) {
        this.technicalmanagement = technicalmanagement;
    }

    public Projectwbs technicalmanagement(Technicalmanagement technicalmanagement) {
        this.setTechnicalmanagement(technicalmanagement);
        return this;
    }

    public Contractualfunds getContractualfunds() {
        return this.contractualfunds;
    }

    public void setContractualfunds(Contractualfunds contractualfunds) {
        this.contractualfunds = contractualfunds;
    }

    public Projectwbs contractualfunds(Contractualfunds contractualfunds) {
        this.setContractualfunds(contractualfunds);
        return this;
    }

    public Outsourcingmanagement getOutsourcingmanagement() {
        return this.outsourcingmanagement;
    }

    public void setOutsourcingmanagement(Outsourcingmanagement outsourcingmanagement) {
        this.outsourcingmanagement = outsourcingmanagement;
    }

    public Projectwbs outsourcingmanagement(Outsourcingmanagement outsourcingmanagement) {
        this.setOutsourcingmanagement(outsourcingmanagement);
        return this;
    }

    public Resourcemanagement getResourcemanagement() {
        return this.resourcemanagement;
    }

    public void setResourcemanagement(Resourcemanagement resourcemanagement) {
        this.resourcemanagement = resourcemanagement;
    }

    public Projectwbs resourcemanagement(Resourcemanagement resourcemanagement) {
        this.setResourcemanagement(resourcemanagement);
        return this;
    }

    public Riskmanagement getRiskmanagement() {
        return this.riskmanagement;
    }

    public void setRiskmanagement(Riskmanagement riskmanagement) {
        this.riskmanagement = riskmanagement;
    }

    public Projectwbs riskmanagement(Riskmanagement riskmanagement) {
        this.setRiskmanagement(riskmanagement);
        return this;
    }

    public Securitymanagement getSecuritymanagement() {
        return this.securitymanagement;
    }

    public void setSecuritymanagement(Securitymanagement securitymanagement) {
        this.securitymanagement = securitymanagement;
    }

    public Projectwbs securitymanagement(Securitymanagement securitymanagement) {
        this.setSecuritymanagement(securitymanagement);
        return this;
    }

    public Document getDocument() {
        return this.document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Projectwbs document(Document document) {
        this.setDocument(document);
        return this;
    }

    public Safetycheck getSafetycheck() {
        return this.safetycheck;
    }

    public void setSafetycheck(Safetycheck safetycheck) {
        this.safetycheck = safetycheck;
    }

    public Projectwbs safetycheck(Safetycheck safetycheck) {
        this.setSafetycheck(safetycheck);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Projectwbs department(Department department) {
        this.setDepartment(department);
        return this;
    }

    public EvaluationCriteria getEvaluationCriteria() {
        return this.evaluationCriteria;
    }

    public void setEvaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.evaluationCriteria = evaluationCriteria;
    }

    public Projectwbs evaluationCriteria(EvaluationCriteria evaluationCriteria) {
        this.setEvaluationCriteria(evaluationCriteria);
        return this;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        if (this.project != null) {
            this.project.setProjectwbs(null);
        }
        if (project != null) {
            project.setProjectwbs(this);
        }
        this.project = project;
    }

    public Projectwbs project(Project project) {
        this.setProject(project);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Projectwbs)) {
            return false;
        }
        return getId() != null && getId().equals(((Projectwbs) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Projectwbs{" +
            "id=" + getId() +
            ", projectwbsname='" + getProjectwbsname() + "'" +
            ", wbsspare1='" + getWbsspare1() + "'" +
            ", wbsspare2='" + getWbsspare2() + "'" +
            ", wbsspare3='" + getWbsspare3() + "'" +
            ", wbsspare4='" + getWbsspare4() + "'" +
            ", wbsspare5='" + getWbsspare5() + "'" +
            "}";
    }
}
