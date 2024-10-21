package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FrontlineTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectRiskTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.RiskLevelTestSamples.*;
import static com.cvicse.jy1.domain.RiskPossibilityTestSamples.*;
import static com.cvicse.jy1.domain.RiskReturnTestSamples.*;
import static com.cvicse.jy1.domain.RiskTypeTestSamples.*;
import static com.cvicse.jy1.domain.SystemLevelTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectRiskTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectRisk.class);
        ProjectRisk projectRisk1 = getProjectRiskSample1();
        ProjectRisk projectRisk2 = new ProjectRisk();
        assertThat(projectRisk1).isNotEqualTo(projectRisk2);

        projectRisk2.setId(projectRisk1.getId());
        assertThat(projectRisk1).isEqualTo(projectRisk2);

        projectRisk2 = getProjectRiskSample2();
        assertThat(projectRisk1).isNotEqualTo(projectRisk2);
    }

    @Test
    void wbsidTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectRisk.setWbsid(projectwbsBack);
        assertThat(projectRisk.getWbsid()).isEqualTo(projectwbsBack);

        projectRisk.wbsid(null);
        assertThat(projectRisk.getWbsid()).isNull();
    }

    @Test
    void workbagTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        projectRisk.setWorkbag(workbagBack);
        assertThat(projectRisk.getWorkbag()).isEqualTo(workbagBack);

        projectRisk.workbag(null);
        assertThat(projectRisk.getWorkbag()).isNull();
    }

    @Test
    void frontlineidTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Frontline frontlineBack = getFrontlineRandomSampleGenerator();

        projectRisk.setFrontlineid(frontlineBack);
        assertThat(projectRisk.getFrontlineid()).isEqualTo(frontlineBack);

        projectRisk.frontlineid(null);
        assertThat(projectRisk.getFrontlineid()).isNull();
    }

    @Test
    void systemLevelTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        SystemLevel systemLevelBack = getSystemLevelRandomSampleGenerator();

        projectRisk.setSystemLevel(systemLevelBack);
        assertThat(projectRisk.getSystemLevel()).isEqualTo(systemLevelBack);

        projectRisk.systemLevel(null);
        assertThat(projectRisk.getSystemLevel()).isNull();
    }

    @Test
    void riskTypeTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        RiskType riskTypeBack = getRiskTypeRandomSampleGenerator();

        projectRisk.setRiskType(riskTypeBack);
        assertThat(projectRisk.getRiskType()).isEqualTo(riskTypeBack);

        projectRisk.riskType(null);
        assertThat(projectRisk.getRiskType()).isNull();
    }

    @Test
    void riskLevelTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        RiskLevel riskLevelBack = getRiskLevelRandomSampleGenerator();

        projectRisk.setRiskLevel(riskLevelBack);
        assertThat(projectRisk.getRiskLevel()).isEqualTo(riskLevelBack);

        projectRisk.riskLevel(null);
        assertThat(projectRisk.getRiskLevel()).isNull();
    }

    @Test
    void riskPossibilityTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        RiskPossibility riskPossibilityBack = getRiskPossibilityRandomSampleGenerator();

        projectRisk.setRiskPossibility(riskPossibilityBack);
        assertThat(projectRisk.getRiskPossibility()).isEqualTo(riskPossibilityBack);

        projectRisk.riskPossibility(null);
        assertThat(projectRisk.getRiskPossibility()).isNull();
    }

    @Test
    void returnsTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        RiskReturn riskReturnBack = getRiskReturnRandomSampleGenerator();

        projectRisk.addReturns(riskReturnBack);
        assertThat(projectRisk.getReturns()).containsOnly(riskReturnBack);
        assertThat(riskReturnBack.getRiskid()).isEqualTo(projectRisk);

        projectRisk.removeReturns(riskReturnBack);
        assertThat(projectRisk.getReturns()).doesNotContain(riskReturnBack);
        assertThat(riskReturnBack.getRiskid()).isNull();

        projectRisk.returns(new HashSet<>(Set.of(riskReturnBack)));
        assertThat(projectRisk.getReturns()).containsOnly(riskReturnBack);
        assertThat(riskReturnBack.getRiskid()).isEqualTo(projectRisk);

        projectRisk.setReturns(new HashSet<>());
        assertThat(projectRisk.getReturns()).doesNotContain(riskReturnBack);
        assertThat(riskReturnBack.getRiskid()).isNull();
    }

    @Test
    void progressPlanTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        ProgressPlan progressPlanBack = getProgressPlanRandomSampleGenerator();

        projectRisk.addProgressPlan(progressPlanBack);
        assertThat(projectRisk.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getProjectRisks()).containsOnly(projectRisk);

        projectRisk.removeProgressPlan(progressPlanBack);
        assertThat(projectRisk.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getProjectRisks()).doesNotContain(projectRisk);

        projectRisk.progressPlans(new HashSet<>(Set.of(progressPlanBack)));
        assertThat(projectRisk.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getProjectRisks()).containsOnly(projectRisk);

        projectRisk.setProgressPlans(new HashSet<>());
        assertThat(projectRisk.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getProjectRisks()).doesNotContain(projectRisk);
    }
}
