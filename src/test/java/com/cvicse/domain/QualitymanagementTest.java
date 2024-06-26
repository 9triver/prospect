package com.cvicse.domain;

import static com.cvicse.domain.QualitymanagementTestSamples.*;
import static com.cvicse.domain.QualitymanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualitymanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Qualitymanagement.class);
        Qualitymanagement qualitymanagement1 = getQualitymanagementSample1();
        Qualitymanagement qualitymanagement2 = new Qualitymanagement();
        assertThat(qualitymanagement1).isNotEqualTo(qualitymanagement2);

        qualitymanagement2.setId(qualitymanagement1.getId());
        assertThat(qualitymanagement1).isEqualTo(qualitymanagement2);

        qualitymanagement2 = getQualitymanagementSample2();
        assertThat(qualitymanagement1).isNotEqualTo(qualitymanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Qualitymanagement qualitymanagement = getQualitymanagementRandomSampleGenerator();
        QualitymanagementWbs qualitymanagementWbsBack = getQualitymanagementWbsRandomSampleGenerator();

        qualitymanagement.setWbs(qualitymanagementWbsBack);
        assertThat(qualitymanagement.getWbs()).isEqualTo(qualitymanagementWbsBack);

        qualitymanagement.wbs(null);
        assertThat(qualitymanagement.getWbs()).isNull();
    }
}
