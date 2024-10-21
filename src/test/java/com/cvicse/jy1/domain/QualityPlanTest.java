package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.QualityObjectivesTestSamples.*;
import static com.cvicse.jy1.domain.QualityPlanTestSamples.*;
import static com.cvicse.jy1.domain.QualityReturnsTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class QualityPlanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QualityPlan.class);
        QualityPlan qualityPlan1 = getQualityPlanSample1();
        QualityPlan qualityPlan2 = new QualityPlan();
        assertThat(qualityPlan1).isNotEqualTo(qualityPlan2);

        qualityPlan2.setId(qualityPlan1.getId());
        assertThat(qualityPlan1).isEqualTo(qualityPlan2);

        qualityPlan2 = getQualityPlanSample2();
        assertThat(qualityPlan1).isNotEqualTo(qualityPlan2);
    }

    @Test
    void qualityObjectivesTest() {
        QualityPlan qualityPlan = getQualityPlanRandomSampleGenerator();
        QualityObjectives qualityObjectivesBack = getQualityObjectivesRandomSampleGenerator();

        qualityPlan.addQualityObjectives(qualityObjectivesBack);
        assertThat(qualityPlan.getQualityObjectives()).containsOnly(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getQualityPlan()).isEqualTo(qualityPlan);

        qualityPlan.removeQualityObjectives(qualityObjectivesBack);
        assertThat(qualityPlan.getQualityObjectives()).doesNotContain(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getQualityPlan()).isNull();

        qualityPlan.qualityObjectives(new HashSet<>(Set.of(qualityObjectivesBack)));
        assertThat(qualityPlan.getQualityObjectives()).containsOnly(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getQualityPlan()).isEqualTo(qualityPlan);

        qualityPlan.setQualityObjectives(new HashSet<>());
        assertThat(qualityPlan.getQualityObjectives()).doesNotContain(qualityObjectivesBack);
        assertThat(qualityObjectivesBack.getQualityPlan()).isNull();
    }

    @Test
    void qualityReturnsTest() {
        QualityPlan qualityPlan = getQualityPlanRandomSampleGenerator();
        QualityReturns qualityReturnsBack = getQualityReturnsRandomSampleGenerator();

        qualityPlan.addQualityReturns(qualityReturnsBack);
        assertThat(qualityPlan.getQualityReturns()).containsOnly(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityPlan()).isEqualTo(qualityPlan);

        qualityPlan.removeQualityReturns(qualityReturnsBack);
        assertThat(qualityPlan.getQualityReturns()).doesNotContain(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityPlan()).isNull();

        qualityPlan.qualityReturns(new HashSet<>(Set.of(qualityReturnsBack)));
        assertThat(qualityPlan.getQualityReturns()).containsOnly(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityPlan()).isEqualTo(qualityPlan);

        qualityPlan.setQualityReturns(new HashSet<>());
        assertThat(qualityPlan.getQualityReturns()).doesNotContain(qualityReturnsBack);
        assertThat(qualityReturnsBack.getQualityPlan()).isNull();
    }

    @Test
    void projectwbsTest() {
        QualityPlan qualityPlan = getQualityPlanRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        qualityPlan.setProjectwbs(projectwbsBack);
        assertThat(qualityPlan.getProjectwbs()).isEqualTo(projectwbsBack);

        qualityPlan.projectwbs(null);
        assertThat(qualityPlan.getProjectwbs()).isNull();
    }

    @Test
    void workbagTest() {
        QualityPlan qualityPlan = getQualityPlanRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        qualityPlan.setWorkbag(workbagBack);
        assertThat(qualityPlan.getWorkbag()).isEqualTo(workbagBack);

        qualityPlan.workbag(null);
        assertThat(qualityPlan.getWorkbag()).isNull();
    }
}
