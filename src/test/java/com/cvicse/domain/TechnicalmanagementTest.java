package com.cvicse.domain;

import static com.cvicse.domain.TechnicalmanagementTestSamples.*;
import static com.cvicse.domain.TechnicalmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TechnicalmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Technicalmanagement.class);
        Technicalmanagement technicalmanagement1 = getTechnicalmanagementSample1();
        Technicalmanagement technicalmanagement2 = new Technicalmanagement();
        assertThat(technicalmanagement1).isNotEqualTo(technicalmanagement2);

        technicalmanagement2.setId(technicalmanagement1.getId());
        assertThat(technicalmanagement1).isEqualTo(technicalmanagement2);

        technicalmanagement2 = getTechnicalmanagementSample2();
        assertThat(technicalmanagement1).isNotEqualTo(technicalmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Technicalmanagement technicalmanagement = getTechnicalmanagementRandomSampleGenerator();
        TechnicalmanagementWbs technicalmanagementWbsBack = getTechnicalmanagementWbsRandomSampleGenerator();

        technicalmanagement.setWbs(technicalmanagementWbsBack);
        assertThat(technicalmanagement.getWbs()).isEqualTo(technicalmanagementWbsBack);

        technicalmanagement.wbs(null);
        assertThat(technicalmanagement.getWbs()).isNull();
    }
}
