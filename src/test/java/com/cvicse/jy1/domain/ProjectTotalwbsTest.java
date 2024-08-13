package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTotalwbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectTotalwbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectTotalwbs.class);
        ProjectTotalwbs projectTotalwbs1 = getProjectTotalwbsSample1();
        ProjectTotalwbs projectTotalwbs2 = new ProjectTotalwbs();
        assertThat(projectTotalwbs1).isNotEqualTo(projectTotalwbs2);

        projectTotalwbs2.setId(projectTotalwbs1.getId());
        assertThat(projectTotalwbs1).isEqualTo(projectTotalwbs2);

        projectTotalwbs2 = getProjectTotalwbsSample2();
        assertThat(projectTotalwbs1).isNotEqualTo(projectTotalwbs2);
    }

    @Test
    void projectwbsTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectTotalwbs.setProjectwbs(projectwbsBack);
        assertThat(projectTotalwbs.getProjectwbs()).isEqualTo(projectwbsBack);

        projectTotalwbs.projectwbs(null);
        assertThat(projectTotalwbs.getProjectwbs()).isNull();
    }

    @Test
    void responsiblepersonTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectTotalwbs.setResponsibleperson(officersBack);
        assertThat(projectTotalwbs.getResponsibleperson()).isEqualTo(officersBack);

        projectTotalwbs.responsibleperson(null);
        assertThat(projectTotalwbs.getResponsibleperson()).isNull();
    }

    @Test
    void technicaldirectorTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectTotalwbs.setTechnicaldirector(officersBack);
        assertThat(projectTotalwbs.getTechnicaldirector()).isEqualTo(officersBack);

        projectTotalwbs.technicaldirector(null);
        assertThat(projectTotalwbs.getTechnicaldirector()).isNull();
    }

    @Test
    void administrativedirectorTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectTotalwbs.setAdministrativedirector(officersBack);
        assertThat(projectTotalwbs.getAdministrativedirector()).isEqualTo(officersBack);

        projectTotalwbs.administrativedirector(null);
        assertThat(projectTotalwbs.getAdministrativedirector()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectTotalwbs.setKnowingpeople(officersBack);
        assertThat(projectTotalwbs.getKnowingpeople()).isEqualTo(officersBack);

        projectTotalwbs.knowingpeople(null);
        assertThat(projectTotalwbs.getKnowingpeople()).isNull();
    }

    @Test
    void auditoridTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectTotalwbs.setAuditorid(officersBack);
        assertThat(projectTotalwbs.getAuditorid()).isEqualTo(officersBack);

        projectTotalwbs.auditorid(null);
        assertThat(projectTotalwbs.getAuditorid()).isNull();
    }

    @Test
    void responsibledepartmentTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectTotalwbs.setResponsibledepartment(departmentBack);
        assertThat(projectTotalwbs.getResponsibledepartment()).isEqualTo(departmentBack);

        projectTotalwbs.responsibledepartment(null);
        assertThat(projectTotalwbs.getResponsibledepartment()).isNull();
    }

    @Test
    void relevantdepartmentTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectTotalwbs.setRelevantdepartment(departmentBack);
        assertThat(projectTotalwbs.getRelevantdepartment()).isEqualTo(departmentBack);

        projectTotalwbs.relevantdepartment(null);
        assertThat(projectTotalwbs.getRelevantdepartment()).isNull();
    }

    @Test
    void departmentTest() {
        ProjectTotalwbs projectTotalwbs = getProjectTotalwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectTotalwbs.setDepartment(departmentBack);
        assertThat(projectTotalwbs.getDepartment()).isEqualTo(departmentBack);

        projectTotalwbs.department(null);
        assertThat(projectTotalwbs.getDepartment()).isNull();
    }
}
