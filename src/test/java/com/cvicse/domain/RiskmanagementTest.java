package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.RiskmanagementTestSamples.*;
import static com.cvicse.domain.RiskreportTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RiskmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Riskmanagement.class);
        Riskmanagement riskmanagement1 = getRiskmanagementSample1();
        Riskmanagement riskmanagement2 = new Riskmanagement();
        assertThat(riskmanagement1).isNotEqualTo(riskmanagement2);

        riskmanagement2.setId(riskmanagement1.getId());
        assertThat(riskmanagement1).isEqualTo(riskmanagement2);

        riskmanagement2 = getRiskmanagementSample2();
        assertThat(riskmanagement1).isNotEqualTo(riskmanagement2);
    }

    @Test
    void creatoridTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskmanagement.setCreatorid(officersBack);
        assertThat(riskmanagement.getCreatorid()).isEqualTo(officersBack);

        riskmanagement.creatorid(null);
        assertThat(riskmanagement.getCreatorid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskmanagement.setResponsibleid(officersBack);
        assertThat(riskmanagement.getResponsibleid()).isEqualTo(officersBack);

        riskmanagement.responsibleid(null);
        assertThat(riskmanagement.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        riskmanagement.setAuditorid(officersBack);
        assertThat(riskmanagement.getAuditorid()).isEqualTo(officersBack);

        riskmanagement.auditorid(null);
        assertThat(riskmanagement.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        riskmanagement.setProject(projectBack);
        assertThat(riskmanagement.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getRiskmanagement()).isEqualTo(riskmanagement);

        riskmanagement.project(null);
        assertThat(riskmanagement.getProject()).isNull();
        assertThat(projectBack.getRiskmanagement()).isNull();
    }

    @Test
    void riskreportTest() throws Exception {
        Riskmanagement riskmanagement = getRiskmanagementRandomSampleGenerator();
        Riskreport riskreportBack = getRiskreportRandomSampleGenerator();

        riskmanagement.setRiskreport(riskreportBack);
        assertThat(riskmanagement.getRiskreport()).isEqualTo(riskreportBack);
        assertThat(riskreportBack.getRiskmanagement()).isEqualTo(riskmanagement);

        riskmanagement.riskreport(null);
        assertThat(riskmanagement.getRiskreport()).isNull();
        assertThat(riskreportBack.getRiskmanagement()).isNull();
    }
}
