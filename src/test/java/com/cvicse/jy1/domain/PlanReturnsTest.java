package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.PlanReturnsTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanReturnsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PlanReturns.class);
        PlanReturns planReturns1 = getPlanReturnsSample1();
        PlanReturns planReturns2 = new PlanReturns();
        assertThat(planReturns1).isNotEqualTo(planReturns2);

        planReturns2.setId(planReturns1.getId());
        assertThat(planReturns1).isEqualTo(planReturns2);

        planReturns2 = getPlanReturnsSample2();
        assertThat(planReturns1).isNotEqualTo(planReturns2);
    }

    @Test
    void responsiblepersonTest() {
        PlanReturns planReturns = getPlanReturnsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        planReturns.setResponsibleperson(hrManagementBack);
        assertThat(planReturns.getResponsibleperson()).isEqualTo(hrManagementBack);

        planReturns.responsibleperson(null);
        assertThat(planReturns.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        PlanReturns planReturns = getPlanReturnsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        planReturns.setAuditorid(hrManagementBack);
        assertThat(planReturns.getAuditorid()).isEqualTo(hrManagementBack);

        planReturns.auditorid(null);
        assertThat(planReturns.getAuditorid()).isNull();
    }

    @Test
    void progressPlanTest() {
        PlanReturns planReturns = getPlanReturnsRandomSampleGenerator();
        ProgressPlan progressPlanBack = getProgressPlanRandomSampleGenerator();

        planReturns.setProgressPlan(progressPlanBack);
        assertThat(planReturns.getProgressPlan()).isEqualTo(progressPlanBack);

        planReturns.progressPlan(null);
        assertThat(planReturns.getProgressPlan()).isNull();
    }
}
