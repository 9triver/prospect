package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.QualitymanagementTestSamples.*;
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
    void creatoridTest() throws Exception {
        Qualitymanagement qualitymanagement = getQualitymanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualitymanagement.setCreatorid(officersBack);
        assertThat(qualitymanagement.getCreatorid()).isEqualTo(officersBack);

        qualitymanagement.creatorid(null);
        assertThat(qualitymanagement.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Qualitymanagement qualitymanagement = getQualitymanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        qualitymanagement.setAuditorid(officersBack);
        assertThat(qualitymanagement.getAuditorid()).isEqualTo(officersBack);

        qualitymanagement.auditorid(null);
        assertThat(qualitymanagement.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Qualitymanagement qualitymanagement = getQualitymanagementRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        qualitymanagement.setProject(projectBack);
        assertThat(qualitymanagement.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getQualitymanagement()).isEqualTo(qualitymanagement);

        qualitymanagement.project(null);
        assertThat(qualitymanagement.getProject()).isNull();
        assertThat(projectBack.getQualitymanagement()).isNull();
    }
}
