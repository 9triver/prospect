package com.cvicse.domain;

import static com.cvicse.domain.SecuritymanagementTestSamples.*;
import static com.cvicse.domain.SecuritymanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecuritymanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Securitymanagement.class);
        Securitymanagement securitymanagement1 = getSecuritymanagementSample1();
        Securitymanagement securitymanagement2 = new Securitymanagement();
        assertThat(securitymanagement1).isNotEqualTo(securitymanagement2);

        securitymanagement2.setId(securitymanagement1.getId());
        assertThat(securitymanagement1).isEqualTo(securitymanagement2);

        securitymanagement2 = getSecuritymanagementSample2();
        assertThat(securitymanagement1).isNotEqualTo(securitymanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Securitymanagement securitymanagement = getSecuritymanagementRandomSampleGenerator();
        SecuritymanagementWbs securitymanagementWbsBack = getSecuritymanagementWbsRandomSampleGenerator();

        securitymanagement.setWbs(securitymanagementWbsBack);
        assertThat(securitymanagement.getWbs()).isEqualTo(securitymanagementWbsBack);

        securitymanagement.wbs(null);
        assertThat(securitymanagement.getWbs()).isNull();
    }
}
