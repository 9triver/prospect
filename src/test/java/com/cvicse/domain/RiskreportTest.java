package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.RiskreportTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskreportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Riskreport.class);
        Riskreport riskreport1 = getRiskreportSample1();
        Riskreport riskreport2 = new Riskreport();
        assertThat(riskreport1).isNotEqualTo(riskreport2);

        riskreport2.setId(riskreport1.getId());
        assertThat(riskreport1).isEqualTo(riskreport2);

        riskreport2 = getRiskreportSample2();
        assertThat(riskreport1).isNotEqualTo(riskreport2);
    }

    @Test
    void riskmanagementTest() throws Exception {
        Riskreport riskreport = getRiskreportRandomSampleGenerator();
        Riskmanagement riskmanagementBack = getRiskmanagementRandomSampleGenerator();

        riskreport.setRiskmanagement(riskmanagementBack);
        assertThat(riskreport.getRiskmanagement()).isEqualTo(riskmanagementBack);

        riskreport.riskmanagement(null);
        assertThat(riskreport.getRiskmanagement()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Riskreport riskreport = getRiskreportRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskreport.setCreatorid(officersBack);
        assertThat(riskreport.getCreatorid()).isEqualTo(officersBack);

        riskreport.creatorid(null);
        assertThat(riskreport.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Riskreport riskreport = getRiskreportRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskreport.setAuditorid(officersBack);
        assertThat(riskreport.getAuditorid()).isEqualTo(officersBack);

        riskreport.auditorid(null);
        assertThat(riskreport.getAuditorid()).isNull();
    }
}
