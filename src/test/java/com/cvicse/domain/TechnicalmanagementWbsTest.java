package com.cvicse.domain;

import static com.cvicse.domain.TechnicalConditionTestSamples.*;
import static com.cvicse.domain.TechnicalmanagementTestSamples.*;
import static com.cvicse.domain.TechnicalmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TechnicalmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TechnicalmanagementWbs.class);
        TechnicalmanagementWbs technicalmanagementWbs1 = getTechnicalmanagementWbsSample1();
        TechnicalmanagementWbs technicalmanagementWbs2 = new TechnicalmanagementWbs();
        assertThat(technicalmanagementWbs1).isNotEqualTo(technicalmanagementWbs2);

        technicalmanagementWbs2.setId(technicalmanagementWbs1.getId());
        assertThat(technicalmanagementWbs1).isEqualTo(technicalmanagementWbs2);

        technicalmanagementWbs2 = getTechnicalmanagementWbsSample2();
        assertThat(technicalmanagementWbs1).isNotEqualTo(technicalmanagementWbs2);
    }

    @Test
    void technicalConditionTest() throws Exception {
        TechnicalmanagementWbs technicalmanagementWbs = getTechnicalmanagementWbsRandomSampleGenerator();
        TechnicalCondition technicalConditionBack = getTechnicalConditionRandomSampleGenerator();

        technicalmanagementWbs.setTechnicalCondition(technicalConditionBack);
        assertThat(technicalmanagementWbs.getTechnicalCondition()).isEqualTo(technicalConditionBack);

        technicalmanagementWbs.technicalCondition(null);
        assertThat(technicalmanagementWbs.getTechnicalCondition()).isNull();
    }

    @Test
    void technicalmanagementTest() throws Exception {
        TechnicalmanagementWbs technicalmanagementWbs = getTechnicalmanagementWbsRandomSampleGenerator();
        Technicalmanagement technicalmanagementBack = getTechnicalmanagementRandomSampleGenerator();

        technicalmanagementWbs.setTechnicalmanagement(technicalmanagementBack);
        assertThat(technicalmanagementWbs.getTechnicalmanagement()).isEqualTo(technicalmanagementBack);
        assertThat(technicalmanagementBack.getWbs()).isEqualTo(technicalmanagementWbs);

        technicalmanagementWbs.technicalmanagement(null);
        assertThat(technicalmanagementWbs.getTechnicalmanagement()).isNull();
        assertThat(technicalmanagementBack.getWbs()).isNull();
    }
}
