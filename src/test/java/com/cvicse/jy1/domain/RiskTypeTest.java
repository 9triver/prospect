package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.RiskTypeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskType.class);
        RiskType riskType1 = getRiskTypeSample1();
        RiskType riskType2 = new RiskType();
        assertThat(riskType1).isNotEqualTo(riskType2);

        riskType2.setId(riskType1.getId());
        assertThat(riskType1).isEqualTo(riskType2);

        riskType2 = getRiskTypeSample2();
        assertThat(riskType1).isNotEqualTo(riskType2);
    }
}
