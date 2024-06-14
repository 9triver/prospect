package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.TechnicalConditionTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
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
    void creatoridTest() throws Exception {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technicalCondition.setCreatorid(officersBack);
        assertThat(technicalCondition.getCreatorid()).isEqualTo(officersBack);

        technicalCondition.creatorid(null);
        assertThat(technicalCondition.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technicalCondition.setAuditorid(officersBack);
        assertThat(technicalCondition.getAuditorid()).isEqualTo(officersBack);

        technicalCondition.auditorid(null);
        assertThat(technicalCondition.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        TechnicalCondition technicalCondition = getTechnicalConditionRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        technicalCondition.setProject(projectBack);
        assertThat(technicalCondition.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getTechnicalCondition()).isEqualTo(technicalCondition);

        technicalCondition.project(null);
        assertThat(technicalCondition.getProject()).isNull();
        assertThat(projectBack.getTechnicalCondition()).isNull();
    }
}
