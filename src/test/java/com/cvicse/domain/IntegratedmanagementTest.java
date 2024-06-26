package com.cvicse.domain;

import static com.cvicse.domain.IntegratedmanagementTestSamples.*;
import static com.cvicse.domain.IntegratedmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IntegratedmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Integratedmanagement.class);
        Integratedmanagement integratedmanagement1 = getIntegratedmanagementSample1();
        Integratedmanagement integratedmanagement2 = new Integratedmanagement();
        assertThat(integratedmanagement1).isNotEqualTo(integratedmanagement2);

        integratedmanagement2.setId(integratedmanagement1.getId());
        assertThat(integratedmanagement1).isEqualTo(integratedmanagement2);

        integratedmanagement2 = getIntegratedmanagementSample2();
        assertThat(integratedmanagement1).isNotEqualTo(integratedmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Integratedmanagement integratedmanagement = getIntegratedmanagementRandomSampleGenerator();
        IntegratedmanagementWbs integratedmanagementWbsBack = getIntegratedmanagementWbsRandomSampleGenerator();

        integratedmanagement.setWbs(integratedmanagementWbsBack);
        assertThat(integratedmanagement.getWbs()).isEqualTo(integratedmanagementWbsBack);

        integratedmanagement.wbs(null);
        assertThat(integratedmanagement.getWbs()).isNull();
    }
}
