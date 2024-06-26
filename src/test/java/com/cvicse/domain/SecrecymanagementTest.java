package com.cvicse.domain;

import static com.cvicse.domain.SecrecymanagementTestSamples.*;
import static com.cvicse.domain.SecrecymanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecrecymanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secrecymanagement.class);
        Secrecymanagement secrecymanagement1 = getSecrecymanagementSample1();
        Secrecymanagement secrecymanagement2 = new Secrecymanagement();
        assertThat(secrecymanagement1).isNotEqualTo(secrecymanagement2);

        secrecymanagement2.setId(secrecymanagement1.getId());
        assertThat(secrecymanagement1).isEqualTo(secrecymanagement2);

        secrecymanagement2 = getSecrecymanagementSample2();
        assertThat(secrecymanagement1).isNotEqualTo(secrecymanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Secrecymanagement secrecymanagement = getSecrecymanagementRandomSampleGenerator();
        SecrecymanagementWbs secrecymanagementWbsBack = getSecrecymanagementWbsRandomSampleGenerator();

        secrecymanagement.setWbs(secrecymanagementWbsBack);
        assertThat(secrecymanagement.getWbs()).isEqualTo(secrecymanagementWbsBack);

        secrecymanagement.wbs(null);
        assertThat(secrecymanagement.getWbs()).isNull();
    }
}
