package com.cvicse.domain;

import static com.cvicse.domain.ContractualfundsTestSamples.*;
import static com.cvicse.domain.CycleplanTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.DocumentTestSamples.*;
import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.OutsourcingmanagementTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.ProjectwbsTestSamples.*;
import static com.cvicse.domain.QualitymanagementTestSamples.*;
import static com.cvicse.domain.ResourcemanagementTestSamples.*;
import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.SafetycheckTestSamples.*;
import static com.cvicse.domain.SecuritymanagementTestSamples.*;
import static com.cvicse.domain.TechnicalmanagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectwbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectwbs.class);
        Projectwbs projectwbs1 = getProjectwbsSample1();
        Projectwbs projectwbs2 = new Projectwbs();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);

        projectwbs2.setId(projectwbs1.getId());
        assertThat(projectwbs1).isEqualTo(projectwbs2);

        projectwbs2 = getProjectwbsSample2();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);
    }

    @Test
    void cycleplanTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Cycleplan cycleplanBack = getCycleplanRandomSampleGenerator();

        projectwbs.setCycleplan(cycleplanBack);
        assertThat(projectwbs.getCycleplan()).isEqualTo(cycleplanBack);

        projectwbs.cycleplan(null);
        assertThat(projectwbs.getCycleplan()).isNull();
    }

    @Test
    void progressmanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Progressmanagement progressmanagementBack = getProgressmanagementRandomSampleGenerator();

        projectwbs.setProgressmanagement(progressmanagementBack);
        assertThat(projectwbs.getProgressmanagement()).isEqualTo(progressmanagementBack);

        projectwbs.progressmanagement(null);
        assertThat(projectwbs.getProgressmanagement()).isNull();
    }

    @Test
    void qualitymanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Qualitymanagement qualitymanagementBack = getQualitymanagementRandomSampleGenerator();

        projectwbs.setQualitymanagement(qualitymanagementBack);
        assertThat(projectwbs.getQualitymanagement()).isEqualTo(qualitymanagementBack);

        projectwbs.qualitymanagement(null);
        assertThat(projectwbs.getQualitymanagement()).isNull();
    }

    @Test
    void fundsmanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        projectwbs.setFundsmanagement(fundsmanagementBack);
        assertThat(projectwbs.getFundsmanagement()).isEqualTo(fundsmanagementBack);

        projectwbs.fundsmanagement(null);
        assertThat(projectwbs.getFundsmanagement()).isNull();
    }

    @Test
    void technicalmanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Technicalmanagement technicalmanagementBack = getTechnicalmanagementRandomSampleGenerator();

        projectwbs.setTechnicalmanagement(technicalmanagementBack);
        assertThat(projectwbs.getTechnicalmanagement()).isEqualTo(technicalmanagementBack);

        projectwbs.technicalmanagement(null);
        assertThat(projectwbs.getTechnicalmanagement()).isNull();
    }

    @Test
    void contractualfundsTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Contractualfunds contractualfundsBack = getContractualfundsRandomSampleGenerator();

        projectwbs.setContractualfunds(contractualfundsBack);
        assertThat(projectwbs.getContractualfunds()).isEqualTo(contractualfundsBack);

        projectwbs.contractualfunds(null);
        assertThat(projectwbs.getContractualfunds()).isNull();
    }

    @Test
    void outsourcingmanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Outsourcingmanagement outsourcingmanagementBack = getOutsourcingmanagementRandomSampleGenerator();

        projectwbs.setOutsourcingmanagement(outsourcingmanagementBack);
        assertThat(projectwbs.getOutsourcingmanagement()).isEqualTo(outsourcingmanagementBack);

        projectwbs.outsourcingmanagement(null);
        assertThat(projectwbs.getOutsourcingmanagement()).isNull();
    }

    @Test
    void resourcemanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Resourcemanagement resourcemanagementBack = getResourcemanagementRandomSampleGenerator();

        projectwbs.setResourcemanagement(resourcemanagementBack);
        assertThat(projectwbs.getResourcemanagement()).isEqualTo(resourcemanagementBack);

        projectwbs.resourcemanagement(null);
        assertThat(projectwbs.getResourcemanagement()).isNull();
    }

    @Test
    void riskmanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Riskmanagement riskmanagementBack = getRiskmanagementRandomSampleGenerator();

        projectwbs.setRiskmanagement(riskmanagementBack);
        assertThat(projectwbs.getRiskmanagement()).isEqualTo(riskmanagementBack);

        projectwbs.riskmanagement(null);
        assertThat(projectwbs.getRiskmanagement()).isNull();
    }

    @Test
    void securitymanagementTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Securitymanagement securitymanagementBack = getSecuritymanagementRandomSampleGenerator();

        projectwbs.setSecuritymanagement(securitymanagementBack);
        assertThat(projectwbs.getSecuritymanagement()).isEqualTo(securitymanagementBack);

        projectwbs.securitymanagement(null);
        assertThat(projectwbs.getSecuritymanagement()).isNull();
    }

    @Test
    void documentTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Document documentBack = getDocumentRandomSampleGenerator();

        projectwbs.setDocument(documentBack);
        assertThat(projectwbs.getDocument()).isEqualTo(documentBack);

        projectwbs.document(null);
        assertThat(projectwbs.getDocument()).isNull();
    }

    @Test
    void safetycheckTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Safetycheck safetycheckBack = getSafetycheckRandomSampleGenerator();

        projectwbs.setSafetycheck(safetycheckBack);
        assertThat(projectwbs.getSafetycheck()).isEqualTo(safetycheckBack);

        projectwbs.safetycheck(null);
        assertThat(projectwbs.getSafetycheck()).isNull();
    }

    @Test
    void departmentTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.setDepartment(departmentBack);
        assertThat(projectwbs.getDepartment()).isEqualTo(departmentBack);

        projectwbs.department(null);
        assertThat(projectwbs.getDepartment()).isNull();
    }

    @Test
    void evaluationCriteriaTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        EvaluationCriteria evaluationCriteriaBack = getEvaluationCriteriaRandomSampleGenerator();

        projectwbs.setEvaluationCriteria(evaluationCriteriaBack);
        assertThat(projectwbs.getEvaluationCriteria()).isEqualTo(evaluationCriteriaBack);

        projectwbs.evaluationCriteria(null);
        assertThat(projectwbs.getEvaluationCriteria()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectwbs.setProject(projectBack);
        assertThat(projectwbs.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getProjectwbs()).isEqualTo(projectwbs);

        projectwbs.project(null);
        assertThat(projectwbs.getProject()).isNull();
        assertThat(projectBack.getProjectwbs()).isNull();
    }
}
