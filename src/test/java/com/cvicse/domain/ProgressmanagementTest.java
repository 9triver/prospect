package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanreturnsTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progressmanagement.class);
        Progressmanagement progressmanagement1 = getProgressmanagementSample1();
        Progressmanagement progressmanagement2 = new Progressmanagement();
        assertThat(progressmanagement1).isNotEqualTo(progressmanagement2);

        progressmanagement2.setId(progressmanagement1.getId());
        assertThat(progressmanagement1).isEqualTo(progressmanagement2);

        progressmanagement2 = getProgressmanagementSample2();
        assertThat(progressmanagement1).isNotEqualTo(progressmanagement2);
    }

    @Test
    void departmentTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        progressmanagement.setDepartment(departmentBack);
        assertThat(progressmanagement.getDepartment()).isEqualTo(departmentBack);

        progressmanagement.department(null);
        assertThat(progressmanagement.getDepartment()).isNull();
    }

    @Test
    void planreturnsTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Planreturns planreturnsBack = getPlanreturnsRandomSampleGenerator();

        progressmanagement.setPlanreturns(planreturnsBack);
        assertThat(progressmanagement.getPlanreturns()).isEqualTo(planreturnsBack);

        progressmanagement.planreturns(null);
        assertThat(progressmanagement.getPlanreturns()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressmanagement.setResponsibleid(officersBack);
        assertThat(progressmanagement.getResponsibleid()).isEqualTo(officersBack);

        progressmanagement.responsibleid(null);
        assertThat(progressmanagement.getResponsibleid()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressmanagement.setCreatorid(officersBack);
        assertThat(progressmanagement.getCreatorid()).isEqualTo(officersBack);

        progressmanagement.creatorid(null);
        assertThat(progressmanagement.getCreatorid()).isNull();
    }

    @Test
    void auditoridTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        progressmanagement.setAuditorid(officersBack);
        assertThat(progressmanagement.getAuditorid()).isEqualTo(officersBack);

        progressmanagement.auditorid(null);
        assertThat(progressmanagement.getAuditorid()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        progressmanagement.setProject(projectBack);
        assertThat(progressmanagement.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getProgressmanagement()).isEqualTo(progressmanagement);

        progressmanagement.project(null);
        assertThat(progressmanagement.getProject()).isNull();
        assertThat(projectBack.getProgressmanagement()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        progressmanagement.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(progressmanagement.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getProgress()).isEqualTo(progressmanagement);

        progressmanagement.comprehensivecontrol(null);
        assertThat(progressmanagement.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getProgress()).isNull();
    }
}
