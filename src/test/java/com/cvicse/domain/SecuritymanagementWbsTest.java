package com.cvicse.domain;

import static com.cvicse.domain.AnnualSecurityPlanTestSamples.*;
import static com.cvicse.domain.SafetycheckTestSamples.*;
import static com.cvicse.domain.SecuritymanagementTestSamples.*;
import static com.cvicse.domain.SecuritymanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecuritymanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SecuritymanagementWbs.class);
        SecuritymanagementWbs securitymanagementWbs1 = getSecuritymanagementWbsSample1();
        SecuritymanagementWbs securitymanagementWbs2 = new SecuritymanagementWbs();
        assertThat(securitymanagementWbs1).isNotEqualTo(securitymanagementWbs2);

        securitymanagementWbs2.setId(securitymanagementWbs1.getId());
        assertThat(securitymanagementWbs1).isEqualTo(securitymanagementWbs2);

        securitymanagementWbs2 = getSecuritymanagementWbsSample2();
        assertThat(securitymanagementWbs1).isNotEqualTo(securitymanagementWbs2);
    }

    @Test
    void annualSecurityPlanTest() throws Exception {
        SecuritymanagementWbs securitymanagementWbs = getSecuritymanagementWbsRandomSampleGenerator();
        AnnualSecurityPlan annualSecurityPlanBack = getAnnualSecurityPlanRandomSampleGenerator();

        securitymanagementWbs.setAnnualSecurityPlan(annualSecurityPlanBack);
        assertThat(securitymanagementWbs.getAnnualSecurityPlan()).isEqualTo(annualSecurityPlanBack);

        securitymanagementWbs.annualSecurityPlan(null);
        assertThat(securitymanagementWbs.getAnnualSecurityPlan()).isNull();
    }

    @Test
    void safetycheckTest() throws Exception {
        SecuritymanagementWbs securitymanagementWbs = getSecuritymanagementWbsRandomSampleGenerator();
        Safetycheck safetycheckBack = getSafetycheckRandomSampleGenerator();

        securitymanagementWbs.setSafetycheck(safetycheckBack);
        assertThat(securitymanagementWbs.getSafetycheck()).isEqualTo(safetycheckBack);

        securitymanagementWbs.safetycheck(null);
        assertThat(securitymanagementWbs.getSafetycheck()).isNull();
    }

    @Test
    void securitymanagementTest() throws Exception {
        SecuritymanagementWbs securitymanagementWbs = getSecuritymanagementWbsRandomSampleGenerator();
        Securitymanagement securitymanagementBack = getSecuritymanagementRandomSampleGenerator();

        securitymanagementWbs.setSecuritymanagement(securitymanagementBack);
        assertThat(securitymanagementWbs.getSecuritymanagement()).isEqualTo(securitymanagementBack);
        assertThat(securitymanagementBack.getWbs()).isEqualTo(securitymanagementWbs);

        securitymanagementWbs.securitymanagement(null);
        assertThat(securitymanagementWbs.getSecuritymanagement()).isNull();
        assertThat(securitymanagementBack.getWbs()).isNull();
    }
}
