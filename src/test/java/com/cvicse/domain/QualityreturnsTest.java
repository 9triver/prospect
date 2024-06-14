package com.cvicse.domain;

import static com.cvicse.domain.QualityobjectivesTestSamples.*;
import static com.cvicse.domain.QualityreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualityreturnsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qualityreturns.class);
        Qualityreturns qualityreturns1 = getQualityreturnsSample1();
        Qualityreturns qualityreturns2 = new Qualityreturns();
        assertThat(qualityreturns1).isNotEqualTo(qualityreturns2);

        qualityreturns2.setId(qualityreturns1.getId());
        assertThat(qualityreturns1).isEqualTo(qualityreturns2);

        qualityreturns2 = getQualityreturnsSample2();
        assertThat(qualityreturns1).isNotEqualTo(qualityreturns2);
    }

    @Test
    void qualityobjectivesTest() throws Exception {
        Qualityreturns qualityreturns = getQualityreturnsRandomSampleGenerator();
        Qualityobjectives qualityobjectivesBack = getQualityobjectivesRandomSampleGenerator();

        qualityreturns.setQualityobjectives(qualityobjectivesBack);
        assertThat(qualityreturns.getQualityobjectives()).isEqualTo(qualityobjectivesBack);
        assertThat(qualityobjectivesBack.getQualityreturns()).isEqualTo(qualityreturns);

        qualityreturns.qualityobjectives(null);
        assertThat(qualityreturns.getQualityobjectives()).isNull();
        assertThat(qualityobjectivesBack.getQualityreturns()).isNull();
    }
}
