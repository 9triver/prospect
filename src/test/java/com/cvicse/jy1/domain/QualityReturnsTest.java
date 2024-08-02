package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.QualityObjectivesTestSamples.*;
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
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityReturns.setResponsibleperson(officersBack);
        assertThat(qualityReturns.getResponsibleperson()).isEqualTo(officersBack);

        qualityReturns.responsibleperson(null);
        assertThat(qualityReturns.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityReturns.setAuditorid(officersBack);
        assertThat(qualityReturns.getAuditorid()).isEqualTo(officersBack);

        qualityReturns.auditorid(null);
        assertThat(qualityReturns.getAuditorid()).isNull();
    }

    @Test
    void creatoridTest() {
        QualityReturns qualityReturns = getQualityReturnsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualityReturns.setCreatorid(officersBack);
        assertThat(qualityReturns.getCreatorid()).isEqualTo(officersBack);

        qualityReturns.creatorid(null);
        assertThat(qualityReturns.getCreatorid()).isNull();
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
}
