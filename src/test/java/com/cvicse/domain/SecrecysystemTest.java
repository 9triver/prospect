package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectSecrecyTestSamples.*;
import static com.cvicse.domain.SecrecysystemTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SecrecysystemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Secrecysystem.class);
        Secrecysystem secrecysystem1 = getSecrecysystemSample1();
        Secrecysystem secrecysystem2 = new Secrecysystem();
        assertThat(secrecysystem1).isNotEqualTo(secrecysystem2);

        secrecysystem2.setId(secrecysystem1.getId());
        assertThat(secrecysystem1).isEqualTo(secrecysystem2);

        secrecysystem2 = getSecrecysystemSample2();
        assertThat(secrecysystem1).isNotEqualTo(secrecysystem2);
    }

    @Test
    void creatoridTest() throws Exception {
        Secrecysystem secrecysystem = getSecrecysystemRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        secrecysystem.setCreatorid(officersBack);
        assertThat(secrecysystem.getCreatorid()).isEqualTo(officersBack);

        secrecysystem.creatorid(null);
        assertThat(secrecysystem.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Secrecysystem secrecysystem = getSecrecysystemRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        secrecysystem.setAuditorid(officersBack);
        assertThat(secrecysystem.getAuditorid()).isEqualTo(officersBack);

        secrecysystem.auditorid(null);
        assertThat(secrecysystem.getAuditorid()).isNull();
    }

    @Test
    void projectSecrecyTest() throws Exception {
        Secrecysystem secrecysystem = getSecrecysystemRandomSampleGenerator();
        ProjectSecrecy projectSecrecyBack = getProjectSecrecyRandomSampleGenerator();

        secrecysystem.setProjectSecrecy(projectSecrecyBack);
        assertThat(secrecysystem.getProjectSecrecy()).isEqualTo(projectSecrecyBack);
        assertThat(projectSecrecyBack.getSecrecysystem()).isEqualTo(secrecysystem);

        secrecysystem.projectSecrecy(null);
        assertThat(secrecysystem.getProjectSecrecy()).isNull();
        assertThat(projectSecrecyBack.getSecrecysystem()).isNull();
    }
}
