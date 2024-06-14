package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.QualityobjectivesTestSamples.*;
import static com.cvicse.domain.QualityreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualityobjectivesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qualityobjectives.class);
        Qualityobjectives qualityobjectives1 = getQualityobjectivesSample1();
        Qualityobjectives qualityobjectives2 = new Qualityobjectives();
        assertThat(qualityobjectives1).isNotEqualTo(qualityobjectives2);

        qualityobjectives2.setId(qualityobjectives1.getId());
        assertThat(qualityobjectives1).isEqualTo(qualityobjectives2);

        qualityobjectives2 = getQualityobjectivesSample2();
        assertThat(qualityobjectives1).isNotEqualTo(qualityobjectives2);
    }

    @Test
    void qualityreturnsTest() throws Exception {
        Qualityobjectives qualityobjectives = getQualityobjectivesRandomSampleGenerator();
        Qualityreturns qualityreturnsBack = getQualityreturnsRandomSampleGenerator();

        qualityobjectives.setQualityreturns(qualityreturnsBack);
        assertThat(qualityobjectives.getQualityreturns()).isEqualTo(qualityreturnsBack);

        qualityobjectives.qualityreturns(null);
        assertThat(qualityobjectives.getQualityreturns()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Qualityobjectives qualityobjectives = getQualityobjectivesRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityobjectives.setCreatorid(officersBack);
        assertThat(qualityobjectives.getCreatorid()).isEqualTo(officersBack);

        qualityobjectives.creatorid(null);
        assertThat(qualityobjectives.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Qualityobjectives qualityobjectives = getQualityobjectivesRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityobjectives.setAuditorid(officersBack);
        assertThat(qualityobjectives.getAuditorid()).isEqualTo(officersBack);

        qualityobjectives.auditorid(null);
        assertThat(qualityobjectives.getAuditorid()).isNull();
    }
}
