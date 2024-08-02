package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.QualityObjectivesTestSamples.*;
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
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityObjectives.setResponsibleperson(officersBack);
        assertThat(qualityObjectives.getResponsibleperson()).isEqualTo(officersBack);

        qualityObjectives.responsibleperson(null);
        assertThat(qualityObjectives.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        QualityObjectives qualityObjectives = getQualityObjectivesRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityObjectives.setAuditorid(officersBack);
        assertThat(qualityObjectives.getAuditorid()).isEqualTo(officersBack);

        qualityObjectives.auditorid(null);
        assertThat(qualityObjectives.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        QualityObjectives qualityObjectives = getQualityObjectivesRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        qualityObjectives.addProjectwbs(projectwbsBack);
        assertThat(qualityObjectives.getProjectwbs()).containsOnly(projectwbsBack);

        qualityObjectives.removeProjectwbs(projectwbsBack);
        assertThat(qualityObjectives.getProjectwbs()).doesNotContain(projectwbsBack);

        qualityObjectives.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(qualityObjectives.getProjectwbs()).containsOnly(projectwbsBack);

        qualityObjectives.setProjectwbs(new HashSet<>());
        assertThat(qualityObjectives.getProjectwbs()).doesNotContain(projectwbsBack);
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
}
