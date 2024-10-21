package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.ProgressPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectRiskTestSamples.*;
import static com.cvicse.jy1.domain.RiskReturnTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class RiskReturnTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskReturn.class);
        RiskReturn riskReturn1 = getRiskReturnSample1();
        RiskReturn riskReturn2 = new RiskReturn();
        assertThat(riskReturn1).isNotEqualTo(riskReturn2);

        riskReturn2.setId(riskReturn1.getId());
        assertThat(riskReturn1).isEqualTo(riskReturn2);

        riskReturn2 = getRiskReturnSample2();
        assertThat(riskReturn1).isNotEqualTo(riskReturn2);
    }

    @Test
    void progressPlansTest() {
        RiskReturn riskReturn = getRiskReturnRandomSampleGenerator();
        ProgressPlan progressPlanBack = getProgressPlanRandomSampleGenerator();

        riskReturn.addProgressPlans(progressPlanBack);
        assertThat(riskReturn.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getRiskReturn()).isEqualTo(riskReturn);

        riskReturn.removeProgressPlans(progressPlanBack);
        assertThat(riskReturn.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getRiskReturn()).isNull();

        riskReturn.progressPlans(new HashSet<>(Set.of(progressPlanBack)));
        assertThat(riskReturn.getProgressPlans()).containsOnly(progressPlanBack);
        assertThat(progressPlanBack.getRiskReturn()).isEqualTo(riskReturn);

        riskReturn.setProgressPlans(new HashSet<>());
        assertThat(riskReturn.getProgressPlans()).doesNotContain(progressPlanBack);
        assertThat(progressPlanBack.getRiskReturn()).isNull();
    }

    @Test
    void riskidTest() {
        RiskReturn riskReturn = getRiskReturnRandomSampleGenerator();
        ProjectRisk projectRiskBack = getProjectRiskRandomSampleGenerator();

        riskReturn.setRiskid(projectRiskBack);
        assertThat(riskReturn.getRiskid()).isEqualTo(projectRiskBack);

        riskReturn.riskid(null);
        assertThat(riskReturn.getRiskid()).isNull();
    }

    @Test
    void creatoridTest() {
        RiskReturn riskReturn = getRiskReturnRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        riskReturn.setCreatorid(hrManagementBack);
        assertThat(riskReturn.getCreatorid()).isEqualTo(hrManagementBack);

        riskReturn.creatorid(null);
        assertThat(riskReturn.getCreatorid()).isNull();
    }
}
