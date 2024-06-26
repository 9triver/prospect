package com.cvicse.domain;

import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.RiskmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Riskmanagement.class);
        Riskmanagement riskmanagement1 = getRiskmanagementSample1();
        Riskmanagement riskmanagement2 = new Riskmanagement();
        assertThat(riskmanagement1).isNotEqualTo(riskmanagement2);

        riskmanagement2.setId(riskmanagement1.getId());
        assertThat(riskmanagement1).isEqualTo(riskmanagement2);

        riskmanagement2 = getRiskmanagementSample2();
        assertThat(riskmanagement1).isNotEqualTo(riskmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        RiskmanagementWbs riskmanagementWbsBack = getRiskmanagementWbsRandomSampleGenerator();

        riskmanagement.setWbs(riskmanagementWbsBack);
        assertThat(riskmanagement.getWbs()).isEqualTo(riskmanagementWbsBack);

        riskmanagement.wbs(null);
        assertThat(riskmanagement.getWbs()).isNull();
    }
}
