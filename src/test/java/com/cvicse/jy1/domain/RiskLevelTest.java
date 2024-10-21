package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.RiskLevelTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskLevelTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskLevel.class);
        RiskLevel riskLevel1 = getRiskLevelSample1();
        RiskLevel riskLevel2 = new RiskLevel();
        assertThat(riskLevel1).isNotEqualTo(riskLevel2);

        riskLevel2.setId(riskLevel1.getId());
        assertThat(riskLevel1).isEqualTo(riskLevel2);

        riskLevel2 = getRiskLevelSample2();
        assertThat(riskLevel1).isNotEqualTo(riskLevel2);
    }
}
