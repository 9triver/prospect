package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DeviationPermitApplicationTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DeviationPermitApplicationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DeviationPermitApplication.class);
        DeviationPermitApplication deviationPermitApplication1 = getDeviationPermitApplicationSample1();
        DeviationPermitApplication deviationPermitApplication2 = new DeviationPermitApplication();
        assertThat(deviationPermitApplication1).isNotEqualTo(deviationPermitApplication2);

        deviationPermitApplication2.setId(deviationPermitApplication1.getId());
        assertThat(deviationPermitApplication1).isEqualTo(deviationPermitApplication2);

        deviationPermitApplication2 = getDeviationPermitApplicationSample2();
        assertThat(deviationPermitApplication1).isNotEqualTo(deviationPermitApplication2);
    }

    @Test
    void projectwbsTest() {
        DeviationPermitApplication deviationPermitApplication = getDeviationPermitApplicationRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        deviationPermitApplication.setProjectwbs(projectwbsBack);
        assertThat(deviationPermitApplication.getProjectwbs()).isEqualTo(projectwbsBack);

        deviationPermitApplication.projectwbs(null);
        assertThat(deviationPermitApplication.getProjectwbs()).isNull();
    }
}
