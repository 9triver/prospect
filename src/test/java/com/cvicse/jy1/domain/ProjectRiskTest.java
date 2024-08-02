package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectRiskTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.RiskReportTestSamples.*;
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
    void riskReportTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        RiskReport riskReportBack = getRiskReportRandomSampleGenerator();

        projectRisk.setRiskReport(riskReportBack);
        assertThat(projectRisk.getRiskReport()).isEqualTo(riskReportBack);

        projectRisk.riskReport(null);
        assertThat(projectRisk.getRiskReport()).isNull();
    }

    @Test
    void creatoridTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectRisk.setCreatorid(officersBack);
        assertThat(projectRisk.getCreatorid()).isEqualTo(officersBack);

        projectRisk.creatorid(null);
        assertThat(projectRisk.getCreatorid()).isNull();
    }

    @Test
    void responsiblepersonTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectRisk.setResponsibleperson(officersBack);
        assertThat(projectRisk.getResponsibleperson()).isEqualTo(officersBack);

        projectRisk.responsibleperson(null);
        assertThat(projectRisk.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectRisk.setAuditorid(officersBack);
        assertThat(projectRisk.getAuditorid()).isEqualTo(officersBack);

        projectRisk.auditorid(null);
        assertThat(projectRisk.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        ProjectRisk projectRisk = getProjectRiskRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectRisk.addProjectwbs(projectwbsBack);
        assertThat(projectRisk.getProjectwbs()).containsOnly(projectwbsBack);

        projectRisk.removeProjectwbs(projectwbsBack);
        assertThat(projectRisk.getProjectwbs()).doesNotContain(projectwbsBack);

        projectRisk.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(projectRisk.getProjectwbs()).containsOnly(projectwbsBack);

        projectRisk.setProjectwbs(new HashSet<>());
        assertThat(projectRisk.getProjectwbs()).doesNotContain(projectwbsBack);
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
