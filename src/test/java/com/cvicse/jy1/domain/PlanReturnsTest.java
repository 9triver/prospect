package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.PlanReturnsTestSamples.*;
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
        Officers officersBack = getOfficersRandomSampleGenerator();

        planReturns.setResponsibleperson(officersBack);
        assertThat(planReturns.getResponsibleperson()).isEqualTo(officersBack);

        planReturns.responsibleperson(null);
        assertThat(planReturns.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        PlanReturns planReturns = getPlanReturnsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        planReturns.setAuditorid(officersBack);
        assertThat(planReturns.getAuditorid()).isEqualTo(officersBack);

        planReturns.auditorid(null);
        assertThat(planReturns.getAuditorid()).isNull();
    }
}
