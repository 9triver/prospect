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

class QualityObjectivesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualityObjectives.class);
        QualityObjectives qualityObjectives1 = getQualityObjectivesSample1();
        QualityObjectives qualityObjectives2 = new QualityObjectives();
        assertThat(qualityObjectives1).isNotEqualTo(qualityObjectives2);

        qualityObjectives2.setId(qualityObjectives1.getId());
        assertThat(qualityObjectives1).isEqualTo(qualityObjectives2);

        qualityObjectives2 = getQualityObjectivesSample2();
        assertThat(qualityObjectives1).isNotEqualTo(qualityObjectives2);
    }

    @Test
    void responsiblepersonTest() {
        QualityObjectives qualityObjectives = getQualityObjectivesRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        qualityObjectives.setResponsibleperson(hrManagementBack);
        assertThat(qualityObjectives.getResponsibleperson()).isEqualTo(hrManagementBack);

        qualityObjectives.responsibleperson(null);
        assertThat(qualityObjectives.getResponsibleperson()).isNull();
    }

    @Test
    void qualityReturnsTest() {
        QualityObjectives qualityObjectives = getQualityObjectivesRandomSampleGenerator();
        QualityReturns qualityReturnsBack = getQualityReturnsRandomSampleGenerator();

        qualityObjectives.addQualityReturns(qualityReturnsBack);
        assertThat(qualityObjectives.getQualityReturns()).containsOnly(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityObjectives()).containsOnly(qualityObjectives);

        qualityObjectives.removeQualityReturns(qualityReturnsBack);
        assertThat(qualityObjectives.getQualityReturns()).doesNotContain(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityObjectives()).doesNotContain(qualityObjectives);

        qualityObjectives.qualityReturns(new HashSet<>(Set.of(qualityReturnsBack)));
        assertThat(qualityObjectives.getQualityReturns()).containsOnly(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityObjectives()).containsOnly(qualityObjectives);

        qualityObjectives.setQualityReturns(new HashSet<>());
        assertThat(qualityObjectives.getQualityReturns()).doesNotContain(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityObjectives()).doesNotContain(qualityObjectives);
    }

    @Test
    void qualityPlanTest() {
        QualityObjectives qualityObjectives = getQualityObjectivesRandomSampleGenerator();
        QualityPlan qualityPlanBack = getQualityPlanRandomSampleGenerator();

        qualityObjectives.setQualityPlan(qualityPlanBack);
        assertThat(qualityObjectives.getQualityPlan()).isEqualTo(qualityPlanBack);

        qualityObjectives.qualityPlan(null);
        assertThat(qualityObjectives.getQualityPlan()).isNull();
    }
}
