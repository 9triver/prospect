package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.ResourcemanagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ResourcemanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Resourcemanagement.class);
        Resourcemanagement resourcemanagement1 = getResourcemanagementSample1();
        Resourcemanagement resourcemanagement2 = new Resourcemanagement();
        assertThat(resourcemanagement1).isNotEqualTo(resourcemanagement2);

        resourcemanagement2.setId(resourcemanagement1.getId());
        assertThat(resourcemanagement1).isEqualTo(resourcemanagement2);

        resourcemanagement2 = getResourcemanagementSample2();
        assertThat(resourcemanagement1).isNotEqualTo(resourcemanagement2);
    }

    @Test
    void creatoridTest() throws Exception {
        Resourcemanagement resourcemanagement = getResourcemanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        resourcemanagement.setCreatorid(officersBack);
        assertThat(resourcemanagement.getCreatorid()).isEqualTo(officersBack);

        resourcemanagement.creatorid(null);
        assertThat(resourcemanagement.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Resourcemanagement resourcemanagement = getResourcemanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        resourcemanagement.setAuditorid(officersBack);
        assertThat(resourcemanagement.getAuditorid()).isEqualTo(officersBack);

        resourcemanagement.auditorid(null);
        assertThat(resourcemanagement.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Resourcemanagement resourcemanagement = getResourcemanagementRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        resourcemanagement.setProject(projectBack);
        assertThat(resourcemanagement.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getResourcemanagement()).isEqualTo(resourcemanagement);

        resourcemanagement.project(null);
        assertThat(resourcemanagement.getProject()).isNull();
        assertThat(projectBack.getResourcemanagement()).isNull();
    }
}
