package com.cvicse.domain;

import static com.cvicse.domain.QualitymanagementTestSamples.*;
import static com.cvicse.domain.QualitymanagementWbsTestSamples.*;
import static com.cvicse.domain.QualityobjectivesTestSamples.*;
import static com.cvicse.domain.QualityreturnsTestSamples.*;
import static com.cvicse.domain.UnQualityAuditTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class QualitymanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualitymanagementWbs.class);
        QualitymanagementWbs qualitymanagementWbs1 = getQualitymanagementWbsSample1();
        QualitymanagementWbs qualitymanagementWbs2 = new QualitymanagementWbs();
        assertThat(qualitymanagementWbs1).isNotEqualTo(qualitymanagementWbs2);

        qualitymanagementWbs2.setId(qualitymanagementWbs1.getId());
        assertThat(qualitymanagementWbs1).isEqualTo(qualitymanagementWbs2);

        qualitymanagementWbs2 = getQualitymanagementWbsSample2();
        assertThat(qualitymanagementWbs1).isNotEqualTo(qualitymanagementWbs2);
    }

    @Test
    void qualityobjectivesTest() throws Exception {
        QualitymanagementWbs qualitymanagementWbs = getQualitymanagementWbsRandomSampleGenerator();
        Qualityobjectives qualityobjectivesBack = getQualityobjectivesRandomSampleGenerator();

        qualitymanagementWbs.setQualityobjectives(qualityobjectivesBack);
        assertThat(qualitymanagementWbs.getQualityobjectives()).isEqualTo(qualityobjectivesBack);

        qualitymanagementWbs.qualityobjectives(null);
        assertThat(qualitymanagementWbs.getQualityobjectives()).isNull();
    }

    @Test
    void qualityreturnsTest() throws Exception {
        QualitymanagementWbs qualitymanagementWbs = getQualitymanagementWbsRandomSampleGenerator();
        Qualityreturns qualityreturnsBack = getQualityreturnsRandomSampleGenerator();

        qualitymanagementWbs.setQualityreturns(qualityreturnsBack);
        assertThat(qualitymanagementWbs.getQualityreturns()).isEqualTo(qualityreturnsBack);

        qualitymanagementWbs.qualityreturns(null);
        assertThat(qualitymanagementWbs.getQualityreturns()).isNull();
    }

    @Test
    void unQualityAuditTest() throws Exception {
        QualitymanagementWbs qualitymanagementWbs = getQualitymanagementWbsRandomSampleGenerator();
        UnQualityAudit unQualityAuditBack = getUnQualityAuditRandomSampleGenerator();

        qualitymanagementWbs.setUnQualityAudit(unQualityAuditBack);
        assertThat(qualitymanagementWbs.getUnQualityAudit()).isEqualTo(unQualityAuditBack);

        qualitymanagementWbs.unQualityAudit(null);
        assertThat(qualitymanagementWbs.getUnQualityAudit()).isNull();
    }

    @Test
    void qualitymanagementTest() throws Exception {
        QualitymanagementWbs qualitymanagementWbs = getQualitymanagementWbsRandomSampleGenerator();
        Qualitymanagement qualitymanagementBack = getQualitymanagementRandomSampleGenerator();

        qualitymanagementWbs.setQualitymanagement(qualitymanagementBack);
        assertThat(qualitymanagementWbs.getQualitymanagement()).isEqualTo(qualitymanagementBack);
        assertThat(qualitymanagementBack.getWbs()).isEqualTo(qualitymanagementWbs);

        qualitymanagementWbs.qualitymanagement(null);
        assertThat(qualitymanagementWbs.getQualitymanagement()).isNull();
        assertThat(qualitymanagementBack.getWbs()).isNull();
    }
}
