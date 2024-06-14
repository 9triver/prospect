package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PbssubmanageTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static com.cvicse.domain.WbsmanageTestSamples.*;
import static com.cvicse.domain.WbssubmanageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WbsmanageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wbsmanage.class);
        Wbsmanage wbsmanage1 = getWbsmanageSample1();
        Wbsmanage wbsmanage2 = new Wbsmanage();
        assertThat(wbsmanage1).isNotEqualTo(wbsmanage2);

        wbsmanage2.setId(wbsmanage1.getId());
        assertThat(wbsmanage1).isEqualTo(wbsmanage2);

        wbsmanage2 = getWbsmanageSample2();
        assertThat(wbsmanage1).isNotEqualTo(wbsmanage2);
    }

    @Test
    void wbssubmanageTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Wbssubmanage wbssubmanageBack = getWbssubmanageRandomSampleGenerator();

        wbsmanage.setWbssubmanage(wbssubmanageBack);
        assertThat(wbsmanage.getWbssubmanage()).isEqualTo(wbssubmanageBack);

        wbsmanage.wbssubmanage(null);
        assertThat(wbsmanage.getWbssubmanage()).isNull();
    }

    @Test
    void pbssubmanageTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Pbssubmanage pbssubmanageBack = getPbssubmanageRandomSampleGenerator();

        wbsmanage.setPbssubmanage(pbssubmanageBack);
        assertThat(wbsmanage.getPbssubmanage()).isEqualTo(pbssubmanageBack);

        wbsmanage.pbssubmanage(null);
        assertThat(wbsmanage.getPbssubmanage()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        wbsmanage.setProject(projectBack);
        assertThat(wbsmanage.getProject()).isEqualTo(projectBack);

        wbsmanage.project(null);
        assertThat(wbsmanage.getProject()).isNull();
    }

    @Test
    void administratoridTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        wbsmanage.setAdministratorid(officersBack);
        assertThat(wbsmanage.getAdministratorid()).isEqualTo(officersBack);

        wbsmanage.administratorid(null);
        assertThat(wbsmanage.getAdministratorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        wbsmanage.setAuditorid(officersBack);
        assertThat(wbsmanage.getAuditorid()).isEqualTo(officersBack);

        wbsmanage.auditorid(null);
        assertThat(wbsmanage.getAuditorid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Wbsmanage wbsmanage = getWbsmanageRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        wbsmanage.setResponsibleid(officersBack);
        assertThat(wbsmanage.getResponsibleid()).isEqualTo(officersBack);

        wbsmanage.responsibleid(null);
        assertThat(wbsmanage.getResponsibleid()).isNull();
    }
}
