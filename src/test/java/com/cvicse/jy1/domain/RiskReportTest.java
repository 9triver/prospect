package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.RiskReportTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskReportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RiskReport.class);
        RiskReport riskReport1 = getRiskReportSample1();
        RiskReport riskReport2 = new RiskReport();
        assertThat(riskReport1).isNotEqualTo(riskReport2);

        riskReport2.setId(riskReport1.getId());
        assertThat(riskReport1).isEqualTo(riskReport2);

        riskReport2 = getRiskReportSample2();
        assertThat(riskReport1).isNotEqualTo(riskReport2);
    }

    @Test
    void creatoridTest() {
        RiskReport riskReport = getRiskReportRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskReport.setCreatorid(officersBack);
        assertThat(riskReport.getCreatorid()).isEqualTo(officersBack);

        riskReport.creatorid(null);
        assertThat(riskReport.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() {
        RiskReport riskReport = getRiskReportRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskReport.setAuditorid(officersBack);
        assertThat(riskReport.getAuditorid()).isEqualTo(officersBack);

        riskReport.auditorid(null);
        assertThat(riskReport.getAuditorid()).isNull();
    }
}
