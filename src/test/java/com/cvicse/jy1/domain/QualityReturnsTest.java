package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.QualityObjectivesTestSamples.*;
import static com.cvicse.jy1.domain.QualityPlanTestSamples.*;
import static com.cvicse.jy1.domain.QualityReturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class QualityReturnsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualityReturns.class);
        QualityReturns qualityReturns1 = getQualityReturnsSample1();
        QualityReturns qualityReturns2 = new QualityReturns();
        assertThat(qualityReturns1).isNotEqualTo(qualityReturns2);

        qualityReturns2.setId(qualityReturns1.getId());
        assertThat(qualityReturns1).isEqualTo(qualityReturns2);

        qualityReturns2 = getQualityReturnsSample2();
        assertThat(qualityReturns1).isNotEqualTo(qualityReturns2);
    }

    @Test
    void responsiblepersonTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        qualityReturns.setResponsibleperson(hrManagementBack);
        assertThat(qualityReturns.getResponsibleperson()).isEqualTo(hrManagementBack);

        qualityReturns.responsibleperson(null);
        assertThat(qualityReturns.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        qualityReturns.setAuditorid(hrManagementBack);
        assertThat(qualityReturns.getAuditorid()).isEqualTo(hrManagementBack);

        qualityReturns.auditorid(null);
        assertThat(qualityReturns.getAuditorid()).isNull();
    }

    @Test
    void qualityObjectivesTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        QualityObjectives qualityObjectivesBack = getQualityObjectivesRandomSampleGenerator();

        qualityReturns.addQualityObjectives(qualityObjectivesBack);
        assertThat(qualityReturns.getQualityObjectives()).containsOnly(qualityObjectivesBack);

        qualityReturns.removeQualityObjectives(qualityObjectivesBack);
        assertThat(qualityReturns.getQualityObjectives()).doesNotContain(qualityObjectivesBack);

        qualityReturns.qualityObjectives(new HashSet<>(Set.of(qualityObjectivesBack)));
        assertThat(qualityReturns.getQualityObjectives()).containsOnly(qualityObjectivesBack);

        qualityReturns.setQualityObjectives(new HashSet<>());
        assertThat(qualityReturns.getQualityObjectives()).doesNotContain(qualityObjectivesBack);
    }

    @Test
    void qualityPlanTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        QualityPlan qualityPlanBack = getQualityPlanRandomSampleGenerator();

        qualityReturns.setQualityPlan(qualityPlanBack);
        assertThat(qualityReturns.getQualityPlan()).isEqualTo(qualityPlanBack);

        qualityReturns.qualityPlan(null);
        assertThat(qualityReturns.getQualityPlan()).isNull();
    }
}
