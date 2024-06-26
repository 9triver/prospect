package com.cvicse.domain;

import static com.cvicse.domain.RiskidentificationTestSamples.*;
import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.RiskmanagementWbsTestSamples.*;
import static com.cvicse.domain.RiskreportTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskmanagementWbs.class);
        RiskmanagementWbs riskmanagementWbs1 = getRiskmanagementWbsSample1();
        RiskmanagementWbs riskmanagementWbs2 = new RiskmanagementWbs();
        assertThat(riskmanagementWbs1).isNotEqualTo(riskmanagementWbs2);

        riskmanagementWbs2.setId(riskmanagementWbs1.getId());
        assertThat(riskmanagementWbs1).isEqualTo(riskmanagementWbs2);

        riskmanagementWbs2 = getRiskmanagementWbsSample2();
        assertThat(riskmanagementWbs1).isNotEqualTo(riskmanagementWbs2);
    }

    @Test
    void riskidentificationTest() throws Exception {
        RiskmanagementWbs riskmanagementWbs = getRiskmanagementWbsRandomSampleGenerator();
        Riskidentification riskidentificationBack = getRiskidentificationRandomSampleGenerator();

        riskmanagementWbs.setRiskidentification(riskidentificationBack);
        assertThat(riskmanagementWbs.getRiskidentification()).isEqualTo(riskidentificationBack);

        riskmanagementWbs.riskidentification(null);
        assertThat(riskmanagementWbs.getRiskidentification()).isNull();
    }

    @Test
    void riskreportTest() throws Exception {
        RiskmanagementWbs riskmanagementWbs = getRiskmanagementWbsRandomSampleGenerator();
        Riskreport riskreportBack = getRiskreportRandomSampleGenerator();

        riskmanagementWbs.setRiskreport(riskreportBack);
        assertThat(riskmanagementWbs.getRiskreport()).isEqualTo(riskreportBack);

        riskmanagementWbs.riskreport(null);
        assertThat(riskmanagementWbs.getRiskreport()).isNull();
    }

    @Test
    void riskmanagementTest() throws Exception {
        RiskmanagementWbs riskmanagementWbs = getRiskmanagementWbsRandomSampleGenerator();
        Riskmanagement riskmanagementBack = getRiskmanagementRandomSampleGenerator();

        riskmanagementWbs.setRiskmanagement(riskmanagementBack);
        assertThat(riskmanagementWbs.getRiskmanagement()).isEqualTo(riskmanagementBack);
        assertThat(riskmanagementBack.getWbs()).isEqualTo(riskmanagementWbs);

        riskmanagementWbs.riskmanagement(null);
        assertThat(riskmanagementWbs.getRiskmanagement()).isNull();
        assertThat(riskmanagementBack.getWbs()).isNull();
    }
}
