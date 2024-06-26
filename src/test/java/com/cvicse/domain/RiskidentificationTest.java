package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.RiskidentificationTestSamples.*;
import static com.cvicse.domain.RiskreportTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskidentificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Riskidentification.class);
        Riskidentification riskidentification1 = getRiskidentificationSample1();
        Riskidentification riskidentification2 = new Riskidentification();
        assertThat(riskidentification1).isNotEqualTo(riskidentification2);

        riskidentification2.setId(riskidentification1.getId());
        assertThat(riskidentification1).isEqualTo(riskidentification2);

        riskidentification2 = getRiskidentificationSample2();
        assertThat(riskidentification1).isNotEqualTo(riskidentification2);
    }

    @Test
    void creatoridTest() throws Exception {
        Riskidentification riskidentification = getRiskidentificationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskidentification.setCreatorid(officersBack);
        assertThat(riskidentification.getCreatorid()).isEqualTo(officersBack);

        riskidentification.creatorid(null);
        assertThat(riskidentification.getCreatorid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Riskidentification riskidentification = getRiskidentificationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskidentification.setResponsibleid(officersBack);
        assertThat(riskidentification.getResponsibleid()).isEqualTo(officersBack);

        riskidentification.responsibleid(null);
        assertThat(riskidentification.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Riskidentification riskidentification = getRiskidentificationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskidentification.setAuditorid(officersBack);
        assertThat(riskidentification.getAuditorid()).isEqualTo(officersBack);

        riskidentification.auditorid(null);
        assertThat(riskidentification.getAuditorid()).isNull();
    }

    @Test
    void riskreportTest() throws Exception {
        Riskidentification riskidentification = getRiskidentificationRandomSampleGenerator();
        Riskreport riskreportBack = getRiskreportRandomSampleGenerator();

        riskidentification.setRiskreport(riskreportBack);
        assertThat(riskidentification.getRiskreport()).isEqualTo(riskreportBack);
        assertThat(riskreportBack.getRiskidentification()).isEqualTo(riskidentification);

        riskidentification.riskreport(null);
        assertThat(riskidentification.getRiskreport()).isNull();
        assertThat(riskreportBack.getRiskidentification()).isNull();
    }
}
