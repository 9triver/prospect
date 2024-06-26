package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanreturnsTestSamples.*;
import static com.cvicse.domain.ProgressplanTestSamples.*;
import static com.cvicse.domain.ProgressplanreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressplanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progressplan.class);
        Progressplan progressplan1 = getProgressplanSample1();
        Progressplan progressplan2 = new Progressplan();
        assertThat(progressplan1).isNotEqualTo(progressplan2);

        progressplan2.setId(progressplan1.getId());
        assertThat(progressplan1).isEqualTo(progressplan2);

        progressplan2 = getProgressplanSample2();
        assertThat(progressplan1).isNotEqualTo(progressplan2);
    }

    @Test
    void departmentTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        progressplan.setDepartment(departmentBack);
        assertThat(progressplan.getDepartment()).isEqualTo(departmentBack);

        progressplan.department(null);
        assertThat(progressplan.getDepartment()).isNull();
    }

    @Test
    void planreturnsTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Planreturns planreturnsBack = getPlanreturnsRandomSampleGenerator();

        progressplan.setPlanreturns(planreturnsBack);
        assertThat(progressplan.getPlanreturns()).isEqualTo(planreturnsBack);

        progressplan.planreturns(null);
        assertThat(progressplan.getPlanreturns()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressplan.setResponsibleid(officersBack);
        assertThat(progressplan.getResponsibleid()).isEqualTo(officersBack);

        progressplan.responsibleid(null);
        assertThat(progressplan.getResponsibleid()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressplan.setCreatorid(officersBack);
        assertThat(progressplan.getCreatorid()).isEqualTo(officersBack);

        progressplan.creatorid(null);
        assertThat(progressplan.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressplan.setAuditorid(officersBack);
        assertThat(progressplan.getAuditorid()).isEqualTo(officersBack);

        progressplan.auditorid(null);
        assertThat(progressplan.getAuditorid()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        progressplan.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(progressplan.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getProgress()).isEqualTo(progressplan);

        progressplan.comprehensivecontrol(null);
        assertThat(progressplan.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getProgress()).isNull();
    }

    @Test
    void progressplanreturnsTest() throws Exception {
        Progressplan progressplan = getProgressplanRandomSampleGenerator();
        Progressplanreturns progressplanreturnsBack = getProgressplanreturnsRandomSampleGenerator();

        progressplan.setProgressplanreturns(progressplanreturnsBack);
        assertThat(progressplan.getProgressplanreturns()).isEqualTo(progressplanreturnsBack);
        assertThat(progressplanreturnsBack.getProgressplan()).isEqualTo(progressplan);

        progressplan.progressplanreturns(null);
        assertThat(progressplan.getProgressplanreturns()).isNull();
        assertThat(progressplanreturnsBack.getProgressplan()).isNull();
    }
}
