package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.SecrecymanagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecrecymanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secrecymanagement.class);
        Secrecymanagement secrecymanagement1 = getSecrecymanagementSample1();
        Secrecymanagement secrecymanagement2 = new Secrecymanagement();
        assertThat(secrecymanagement1).isNotEqualTo(secrecymanagement2);

        secrecymanagement2.setId(secrecymanagement1.getId());
        assertThat(secrecymanagement1).isEqualTo(secrecymanagement2);

        secrecymanagement2 = getSecrecymanagementSample2();
        assertThat(secrecymanagement1).isNotEqualTo(secrecymanagement2);
    }

    @Test
    void creatoridTest() throws Exception {
        Secrecymanagement secrecymanagement = getSecrecymanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        secrecymanagement.setCreatorid(officersBack);
        assertThat(secrecymanagement.getCreatorid()).isEqualTo(officersBack);

        secrecymanagement.creatorid(null);
        assertThat(secrecymanagement.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Secrecymanagement secrecymanagement = getSecrecymanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        secrecymanagement.setAuditorid(officersBack);
        assertThat(secrecymanagement.getAuditorid()).isEqualTo(officersBack);

        secrecymanagement.auditorid(null);
        assertThat(secrecymanagement.getAuditorid()).isNull();
    }

    @Test
    void projectSecrecyTest() throws Exception {
        Secrecymanagement secrecymanagement = getSecrecymanagementRandomSampleGenerator();
        ProjectSecrecy projectSecrecyBack = getProjectSecrecyRandomSampleGenerator();

        secrecymanagement.setProjectSecrecy(projectSecrecyBack);
        assertThat(secrecymanagement.getProjectSecrecy()).isEqualTo(projectSecrecyBack);
        assertThat(projectSecrecyBack.getSecrecymanagement()).isEqualTo(secrecymanagement);

        secrecymanagement.projectSecrecy(null);
        assertThat(secrecymanagement.getProjectSecrecy()).isNull();
        assertThat(projectSecrecyBack.getSecrecymanagement()).isNull();
    }
}
