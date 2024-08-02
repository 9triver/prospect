package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.TechnicalTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TechnicalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Technical.class);
        Technical technical1 = getTechnicalSample1();
        Technical technical2 = new Technical();
        assertThat(technical1).isNotEqualTo(technical2);

        technical2.setId(technical1.getId());
        assertThat(technical1).isEqualTo(technical2);

        technical2 = getTechnicalSample2();
        assertThat(technical1).isNotEqualTo(technical2);
    }

    @Test
    void creatoridTest() {
        Technical technical = getTechnicalRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technical.setCreatorid(officersBack);
        assertThat(technical.getCreatorid()).isEqualTo(officersBack);

        technical.creatorid(null);
        assertThat(technical.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() {
        Technical technical = getTechnicalRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        technical.setAuditorid(officersBack);
        assertThat(technical.getAuditorid()).isEqualTo(officersBack);

        technical.auditorid(null);
        assertThat(technical.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        Technical technical = getTechnicalRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        technical.addProjectwbs(projectwbsBack);
        assertThat(technical.getProjectwbs()).containsOnly(projectwbsBack);

        technical.removeProjectwbs(projectwbsBack);
        assertThat(technical.getProjectwbs()).doesNotContain(projectwbsBack);

        technical.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(technical.getProjectwbs()).containsOnly(projectwbsBack);

        technical.setProjectwbs(new HashSet<>());
        assertThat(technical.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
