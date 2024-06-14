package com.cvicse.domain;

import static com.cvicse.domain.HumanresourcesTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class HumanresourcesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Humanresources.class);
        Humanresources humanresources1 = getHumanresourcesSample1();
        Humanresources humanresources2 = new Humanresources();
        assertThat(humanresources1).isNotEqualTo(humanresources2);

        humanresources2.setId(humanresources1.getId());
        assertThat(humanresources1).isEqualTo(humanresources2);

        humanresources2 = getHumanresourcesSample2();
        assertThat(humanresources1).isNotEqualTo(humanresources2);
    }

    @Test
    void projectTest() throws Exception {
        Humanresources humanresources = getHumanresourcesRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        humanresources.setProject(projectBack);
        assertThat(humanresources.getProject()).isEqualTo(projectBack);

        humanresources.project(null);
        assertThat(humanresources.getProject()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Humanresources humanresources = getHumanresourcesRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        humanresources.setCreatorid(officersBack);
        assertThat(humanresources.getCreatorid()).isEqualTo(officersBack);

        humanresources.creatorid(null);
        assertThat(humanresources.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Humanresources humanresources = getHumanresourcesRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        humanresources.setAuditorid(officersBack);
        assertThat(humanresources.getAuditorid()).isEqualTo(officersBack);

        humanresources.auditorid(null);
        assertThat(humanresources.getAuditorid()).isNull();
    }
}
