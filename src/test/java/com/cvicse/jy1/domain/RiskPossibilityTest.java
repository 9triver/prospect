package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.RiskPossibilityTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskPossibilityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskPossibility.class);
        RiskPossibility riskPossibility1 = getRiskPossibilitySample1();
        RiskPossibility riskPossibility2 = new RiskPossibility();
        assertThat(riskPossibility1).isNotEqualTo(riskPossibility2);

        riskPossibility2.setId(riskPossibility1.getId());
        assertThat(riskPossibility1).isEqualTo(riskPossibility2);

        riskPossibility2 = getRiskPossibilitySample2();
        assertThat(riskPossibility1).isNotEqualTo(riskPossibility2);
    }
}
