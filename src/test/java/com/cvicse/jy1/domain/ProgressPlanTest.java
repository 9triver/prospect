package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.PlanReturnsTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectRiskTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.RiskReturnTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProgressPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgressPlan.class);
        ProgressPlan progressPlan1 = getProgressPlanSample1();
        ProgressPlan progressPlan2 = new ProgressPlan();
        assertThat(progressPlan1).isNotEqualTo(progressPlan2);

        progressPlan2.setId(progressPlan1.getId());
        assertThat(progressPlan1).isEqualTo(progressPlan2);

        progressPlan2 = getProgressPlanSample2();
        assertThat(progressPlan1).isNotEqualTo(progressPlan2);
    }

    @Test
    void planReturnsTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        PlanReturns planReturnsBack = getPlanReturnsRandomSampleGenerator();

        progressPlan.addPlanReturns(planReturnsBack);
        assertThat(progressPlan.getPlanReturns()).containsOnly(planReturnsBack);
        assertThat(planReturnsBack.getProgressPlan()).isEqualTo(progressPlan);

        progressPlan.removePlanReturns(planReturnsBack);
        assertThat(progressPlan.getPlanReturns()).doesNotContain(planReturnsBack);
        assertThat(planReturnsBack.getProgressPlan()).isNull();

        progressPlan.planReturns(new HashSet<>(Set.of(planReturnsBack)));
        assertThat(progressPlan.getPlanReturns()).containsOnly(planReturnsBack);
        assertThat(planReturnsBack.getProgressPlan()).isEqualTo(progressPlan);

        progressPlan.setPlanReturns(new HashSet<>());
        assertThat(progressPlan.getPlanReturns()).doesNotContain(planReturnsBack);
        assertThat(planReturnsBack.getProgressPlan()).isNull();
    }

    @Test
    void responsiblepersonTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        progressPlan.setResponsibleperson(hrManagementBack);
        assertThat(progressPlan.getResponsibleperson()).isEqualTo(hrManagementBack);

        progressPlan.responsibleperson(null);
        assertThat(progressPlan.getResponsibleperson()).isNull();
    }

    @Test
    void cooperatingpersonTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        progressPlan.setCooperatingperson(hrManagementBack);
        assertThat(progressPlan.getCooperatingperson()).isEqualTo(hrManagementBack);

        progressPlan.cooperatingperson(null);
        assertThat(progressPlan.getCooperatingperson()).isNull();
    }

    @Test
    void auditoridTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        progressPlan.setAuditorid(hrManagementBack);
        assertThat(progressPlan.getAuditorid()).isEqualTo(hrManagementBack);

        progressPlan.auditorid(null);
        assertThat(progressPlan.getAuditorid()).isNull();
    }

    @Test
    void responsibledepartmentTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        progressPlan.setResponsibledepartment(departmentBack);
        assertThat(progressPlan.getResponsibledepartment()).isEqualTo(departmentBack);

        progressPlan.responsibledepartment(null);
        assertThat(progressPlan.getResponsibledepartment()).isNull();
    }

    @Test
    void cooperatingdepartmentTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        progressPlan.setCooperatingdepartment(departmentBack);
        assertThat(progressPlan.getCooperatingdepartment()).isEqualTo(departmentBack);

        progressPlan.cooperatingdepartment(null);
        assertThat(progressPlan.getCooperatingdepartment()).isNull();
    }

    @Test
    void projectwbsTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        progressPlan.addProjectwbs(projectwbsBack);
        assertThat(progressPlan.getProjectwbs()).containsOnly(projectwbsBack);

        progressPlan.removeProjectwbs(projectwbsBack);
        assertThat(progressPlan.getProjectwbs()).doesNotContain(projectwbsBack);

        progressPlan.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(progressPlan.getProjectwbs()).containsOnly(projectwbsBack);

        progressPlan.setProjectwbs(new HashSet<>());
        assertThat(progressPlan.getProjectwbs()).doesNotContain(projectwbsBack);
    }

    @Test
    void projectRiskTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        ProjectRisk projectRiskBack = getProjectRiskRandomSampleGenerator();

        progressPlan.addProjectRisk(projectRiskBack);
        assertThat(progressPlan.getProjectRisks()).containsOnly(projectRiskBack);

        progressPlan.removeProjectRisk(projectRiskBack);
        assertThat(progressPlan.getProjectRisks()).doesNotContain(projectRiskBack);

        progressPlan.projectRisks(new HashSet<>(Set.of(projectRiskBack)));
        assertThat(progressPlan.getProjectRisks()).containsOnly(projectRiskBack);

        progressPlan.setProjectRisks(new HashSet<>());
        assertThat(progressPlan.getProjectRisks()).doesNotContain(projectRiskBack);
    }

    @Test
    void riskReturnTest() {
        ProgressPlan progressPlan = getProgressPlanRandomSampleGenerator();
        RiskReturn riskReturnBack = getRiskReturnRandomSampleGenerator();

        progressPlan.setRiskReturn(riskReturnBack);
        assertThat(progressPlan.getRiskReturn()).isEqualTo(riskReturnBack);

        progressPlan.riskReturn(null);
        assertThat(progressPlan.getRiskReturn()).isNull();
    }
}
