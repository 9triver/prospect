package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.SecrecymanagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectSecrecyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectSecrecy.class);
        ProjectSecrecy projectSecrecy1 = getProjectSecrecySample1();
        ProjectSecrecy projectSecrecy2 = new ProjectSecrecy();
        assertThat(projectSecrecy1).isNotEqualTo(projectSecrecy2);

        projectSecrecy2.setId(projectSecrecy1.getId());
        assertThat(projectSecrecy1).isEqualTo(projectSecrecy2);

        projectSecrecy2 = getProjectSecrecySample2();
        assertThat(projectSecrecy1).isNotEqualTo(projectSecrecy2);
    }

    @Test
    void secrecymanagementTest() throws Exception {
        ProjectSecrecy projectSecrecy = getProjectSecrecyRandomSampleGenerator();
        Secrecymanagement secrecymanagementBack = getSecrecymanagementRandomSampleGenerator();

        projectSecrecy.setSecrecymanagement(secrecymanagementBack);
        assertThat(projectSecrecy.getSecrecymanagement()).isEqualTo(secrecymanagementBack);

        projectSecrecy.secrecymanagement(null);
        assertThat(projectSecrecy.getSecrecymanagement()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        ProjectSecrecy projectSecrecy = getProjectSecrecyRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectSecrecy.setCreatorid(officersBack);
        assertThat(projectSecrecy.getCreatorid()).isEqualTo(officersBack);

        projectSecrecy.creatorid(null);
        assertThat(projectSecrecy.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        ProjectSecrecy projectSecrecy = getProjectSecrecyRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectSecrecy.setAuditorid(officersBack);
        assertThat(projectSecrecy.getAuditorid()).isEqualTo(officersBack);

        projectSecrecy.auditorid(null);
        assertThat(projectSecrecy.getAuditorid()).isNull();
    }

    @Test
    void projectidTest() throws Exception {
        ProjectSecrecy projectSecrecy = getProjectSecrecyRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectSecrecy.setProjectid(projectBack);
        assertThat(projectSecrecy.getProjectid()).isEqualTo(projectBack);

        projectSecrecy.projectid(null);
        assertThat(projectSecrecy.getProjectid()).isNull();
    }
}
