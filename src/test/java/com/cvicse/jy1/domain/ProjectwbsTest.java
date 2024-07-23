package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectwbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectwbs.class);
        Projectwbs projectwbs1 = getProjectwbsSample1();
        Projectwbs projectwbs2 = new Projectwbs();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);

        projectwbs2.setId(projectwbs1.getId());
        assertThat(projectwbs1).isEqualTo(projectwbs2);

        projectwbs2 = getProjectwbsSample2();
        assertThat(projectwbs1).isNotEqualTo(projectwbs2);
    }

    @Test
    void responsibleidTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setResponsibleid(officersBack);
        assertThat(projectwbs.getResponsibleid()).isEqualTo(officersBack);

        projectwbs.responsibleid(null);
        assertThat(projectwbs.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectwbs.setAuditorid(officersBack);
        assertThat(projectwbs.getAuditorid()).isEqualTo(officersBack);

        projectwbs.auditorid(null);
        assertThat(projectwbs.getAuditorid()).isNull();
    }

    @Test
    void departmentTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectwbs.setDepartment(departmentBack);
        assertThat(projectwbs.getDepartment()).isEqualTo(departmentBack);

        projectwbs.department(null);
        assertThat(projectwbs.getDepartment()).isNull();
    }

    @Test
    void projectTest() {
        Projectwbs projectwbs = getProjectwbsRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectwbs.addProject(projectBack);
        assertThat(projectwbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.removeProject(projectBack);
        assertThat(projectwbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectwbs()).doesNotContain(projectwbs);

        projectwbs.projects(new HashSet<>(Set.of(projectBack)));
        assertThat(projectwbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectwbs()).containsOnly(projectwbs);

        projectwbs.setProjects(new HashSet<>());
        assertThat(projectwbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectwbs()).doesNotContain(projectwbs);
    }
}
