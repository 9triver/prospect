package com.cvicse.domain;

import static com.cvicse.domain.AnnualSecurityPlanTestSamples.*;
import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.ContractualfundsTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.HumanresourcesTestSamples.*;
import static com.cvicse.domain.ManagementCapacityEvaluationTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingmPurchasePlanTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.QualitymanagementTestSamples.*;
import static com.cvicse.domain.ResourcemanagementTestSamples.*;
import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.SafetycheckTestSamples.*;
import static com.cvicse.domain.TechnicalConditionTestSamples.*;
import static com.cvicse.domain.WbsmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = getProjectSample1();
        Project project2 = new Project();
        assertThat(project1).isNotEqualTo(project2);

        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);

        project2 = getProjectSample2();
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    void cycleplanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        project.setCycleplan(cycleplanBack);
        assertThat(project.getCycleplan()).isEqualTo(cycleplanBack);

        project.cycleplan(null);
        assertThat(project.getCycleplan()).isNull();
    }

    @Test
    void progressmanagementTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Progressmanagement progressmanagementBack = getProgressmanagementRandomSampleGenerator();

        project.setProgressmanagement(progressmanagementBack);
        assertThat(project.getProgressmanagement()).isEqualTo(progressmanagementBack);

        project.progressmanagement(null);
        assertThat(project.getProgressmanagement()).isNull();
    }

    @Test
    void qualitymanagementTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Qualitymanagement qualitymanagementBack = getQualitymanagementRandomSampleGenerator();

        project.setQualitymanagement(qualitymanagementBack);
        assertThat(project.getQualitymanagement()).isEqualTo(qualitymanagementBack);

        project.qualitymanagement(null);
        assertThat(project.getQualitymanagement()).isNull();
    }

    @Test
    void fundsmanagementTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        project.setFundsmanagement(fundsmanagementBack);
        assertThat(project.getFundsmanagement()).isEqualTo(fundsmanagementBack);

        project.fundsmanagement(null);
        assertThat(project.getFundsmanagement()).isNull();
    }

    @Test
    void technicalConditionTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        TechnicalCondition technicalConditionBack = getTechnicalConditionRandomSampleGenerator();

        project.setTechnicalCondition(technicalConditionBack);
        assertThat(project.getTechnicalCondition()).isEqualTo(technicalConditionBack);

        project.technicalCondition(null);
        assertThat(project.getTechnicalCondition()).isNull();
    }

    @Test
    void contractualfundsTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Contractualfunds contractualfundsBack = getContractualfundsRandomSampleGenerator();

        project.setContractualfunds(contractualfundsBack);
        assertThat(project.getContractualfunds()).isEqualTo(contractualfundsBack);

        project.contractualfunds(null);
        assertThat(project.getContractualfunds()).isNull();
    }

    @Test
    void outsourcingmPurchaseExecuteTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        OutsourcingmPurchaseExecute outsourcingmPurchaseExecuteBack = getOutsourcingmPurchaseExecuteRandomSampleGenerator();

        project.setOutsourcingmPurchaseExecute(outsourcingmPurchaseExecuteBack);
        assertThat(project.getOutsourcingmPurchaseExecute()).isEqualTo(outsourcingmPurchaseExecuteBack);

        project.outsourcingmPurchaseExecute(null);
        assertThat(project.getOutsourcingmPurchaseExecute()).isNull();
    }

    @Test
    void resourcemanagementTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Resourcemanagement resourcemanagementBack = getResourcemanagementRandomSampleGenerator();

        project.setResourcemanagement(resourcemanagementBack);
        assertThat(project.getResourcemanagement()).isEqualTo(resourcemanagementBack);

        project.resourcemanagement(null);
        assertThat(project.getResourcemanagement()).isNull();
    }

    @Test
    void riskmanagementTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Riskmanagement riskmanagementBack = getRiskmanagementRandomSampleGenerator();

        project.setRiskmanagement(riskmanagementBack);
        assertThat(project.getRiskmanagement()).isEqualTo(riskmanagementBack);

        project.riskmanagement(null);
        assertThat(project.getRiskmanagement()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        project.setDocument(documentBack);
        assertThat(project.getDocument()).isEqualTo(documentBack);

        project.document(null);
        assertThat(project.getDocument()).isNull();
    }

    @Test
    void safetycheckTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Safetycheck safetycheckBack = getSafetycheckRandomSampleGenerator();

        project.setSafetycheck(safetycheckBack);
        assertThat(project.getSafetycheck()).isEqualTo(safetycheckBack);

        project.safetycheck(null);
        assertThat(project.getSafetycheck()).isNull();
    }

    @Test
    void departmentTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        project.setDepartment(departmentBack);
        assertThat(project.getDepartment()).isEqualTo(departmentBack);

        project.department(null);
        assertThat(project.getDepartment()).isNull();
    }

    @Test
    void evaluationCriteriaTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        EvaluationCriteria evaluationCriteriaBack = getEvaluationCriteriaRandomSampleGenerator();

        project.setEvaluationCriteria(evaluationCriteriaBack);
        assertThat(project.getEvaluationCriteria()).isEqualTo(evaluationCriteriaBack);

        project.evaluationCriteria(null);
        assertThat(project.getEvaluationCriteria()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        project.setResponsibleid(officersBack);
        assertThat(project.getResponsibleid()).isEqualTo(officersBack);

        project.responsibleid(null);
        assertThat(project.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        project.setAuditorid(officersBack);
        assertThat(project.getAuditorid()).isEqualTo(officersBack);

        project.auditorid(null);
        assertThat(project.getAuditorid()).isNull();
    }

    @Test
    void projectSecrecyTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        ProjectSecrecy projectSecrecyBack = getProjectSecrecyRandomSampleGenerator();

        project.setProjectSecrecy(projectSecrecyBack);
        assertThat(project.getProjectSecrecy()).isEqualTo(projectSecrecyBack);

        project.projectSecrecy(null);
        assertThat(project.getProjectSecrecy()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        project.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(project.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getProject()).isEqualTo(project);

        project.comprehensivecontrol(null);
        assertThat(project.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getProject()).isNull();
    }

    @Test
    void wbsmanageTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Wbsmanage wbsmanageBack = getWbsmanageRandomSampleGenerator();

        project.setWbsmanage(wbsmanageBack);
        assertThat(project.getWbsmanage()).isEqualTo(wbsmanageBack);
        assertThat(wbsmanageBack.getProject()).isEqualTo(project);

        project.wbsmanage(null);
        assertThat(project.getWbsmanage()).isNull();
        assertThat(wbsmanageBack.getProject()).isNull();
    }

    @Test
    void outsourcingmPurchasePlanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        OutsourcingmPurchasePlan outsourcingmPurchasePlanBack = getOutsourcingmPurchasePlanRandomSampleGenerator();

        project.setOutsourcingmPurchasePlan(outsourcingmPurchasePlanBack);
        assertThat(project.getOutsourcingmPurchasePlan()).isEqualTo(outsourcingmPurchasePlanBack);
        assertThat(outsourcingmPurchasePlanBack.getProject()).isEqualTo(project);

        project.outsourcingmPurchasePlan(null);
        assertThat(project.getOutsourcingmPurchasePlan()).isNull();
        assertThat(outsourcingmPurchasePlanBack.getProject()).isNull();
    }

    @Test
    void humanresourcesTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        Humanresources humanresourcesBack = getHumanresourcesRandomSampleGenerator();

        project.setHumanresources(humanresourcesBack);
        assertThat(project.getHumanresources()).isEqualTo(humanresourcesBack);
        assertThat(humanresourcesBack.getProject()).isEqualTo(project);

        project.humanresources(null);
        assertThat(project.getHumanresources()).isNull();
        assertThat(humanresourcesBack.getProject()).isNull();
    }

    @Test
    void annualSecurityPlanTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        AnnualSecurityPlan annualSecurityPlanBack = getAnnualSecurityPlanRandomSampleGenerator();

        project.setAnnualSecurityPlan(annualSecurityPlanBack);
        assertThat(project.getAnnualSecurityPlan()).isEqualTo(annualSecurityPlanBack);
        assertThat(annualSecurityPlanBack.getProject()).isEqualTo(project);

        project.annualSecurityPlan(null);
        assertThat(project.getAnnualSecurityPlan()).isNull();
        assertThat(annualSecurityPlanBack.getProject()).isNull();
    }

    @Test
    void managementCapacityEvaluationTest() throws Exception {
        Project project = getProjectRandomSampleGenerator();
        ManagementCapacityEvaluation managementCapacityEvaluationBack = getManagementCapacityEvaluationRandomSampleGenerator();

        project.setManagementCapacityEvaluation(managementCapacityEvaluationBack);
        assertThat(project.getManagementCapacityEvaluation()).isEqualTo(managementCapacityEvaluationBack);
        assertThat(managementCapacityEvaluationBack.getProject()).isEqualTo(project);

        project.managementCapacityEvaluation(null);
        assertThat(project.getManagementCapacityEvaluation()).isNull();
        assertThat(managementCapacityEvaluationBack.getProject()).isNull();
    }
}
