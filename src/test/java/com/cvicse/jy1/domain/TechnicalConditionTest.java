package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.TechnicalConditionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TechnicalConditionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TechnicalCondition.class);
        TechnicalCondition technicalCondition1 = getTechnicalConditionSample1();
        TechnicalCondition technicalCondition2 = new TechnicalCondition();
        assertThat(technicalCondition1).isNotEqualTo(technicalCondition2);

        technicalCondition2.setId(technicalCondition1.getId());
        assertThat(technicalCondition1).isEqualTo(technicalCondition2);

        technicalCondition2 = getTechnicalConditionSample2();
        assertThat(technicalCondition1).isNotEqualTo(technicalCondition2);
    }

    @Test
    void creatoridTest() {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technicalCondition.setCreatorid(officersBack);
        assertThat(technicalCondition.getCreatorid()).isEqualTo(officersBack);

        technicalCondition.creatorid(null);
        assertThat(technicalCondition.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technicalCondition.setAuditorid(officersBack);
        assertThat(technicalCondition.getAuditorid()).isEqualTo(officersBack);

        technicalCondition.auditorid(null);
        assertThat(technicalCondition.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        technicalCondition.addProjectwbs(projectwbsBack);
        assertThat(technicalCondition.getProjectwbs()).containsOnly(projectwbsBack);

        technicalCondition.removeProjectwbs(projectwbsBack);
        assertThat(technicalCondition.getProjectwbs()).doesNotContain(projectwbsBack);

        technicalCondition.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(technicalCondition.getProjectwbs()).containsOnly(projectwbsBack);

        technicalCondition.setProjectwbs(new HashSet<>());
        assertThat(technicalCondition.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
