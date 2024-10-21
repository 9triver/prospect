package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.CommunicationPlanTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommunicationPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommunicationPlan.class);
        CommunicationPlan communicationPlan1 = getCommunicationPlanSample1();
        CommunicationPlan communicationPlan2 = new CommunicationPlan();
        assertThat(communicationPlan1).isNotEqualTo(communicationPlan2);

        communicationPlan2.setId(communicationPlan1.getId());
        assertThat(communicationPlan1).isEqualTo(communicationPlan2);

        communicationPlan2 = getCommunicationPlanSample2();
        assertThat(communicationPlan1).isNotEqualTo(communicationPlan2);
    }

    @Test
    void projectwbsTest() {
        CommunicationPlan communicationPlan = getCommunicationPlanRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        communicationPlan.setProjectwbs(projectwbsBack);
        assertThat(communicationPlan.getProjectwbs()).isEqualTo(projectwbsBack);

        communicationPlan.projectwbs(null);
        assertThat(communicationPlan.getProjectwbs()).isNull();
    }
}
