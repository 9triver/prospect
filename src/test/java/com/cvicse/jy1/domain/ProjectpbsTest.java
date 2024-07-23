package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectpbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Projectpbs.class);
        Projectpbs projectpbs1 = getProjectpbsSample1();
        Projectpbs projectpbs2 = new Projectpbs();
        assertThat(projectpbs1).isNotEqualTo(projectpbs2);

        projectpbs2.setId(projectpbs1.getId());
        assertThat(projectpbs1).isEqualTo(projectpbs2);

        projectpbs2 = getProjectpbsSample2();
        assertThat(projectpbs1).isNotEqualTo(projectpbs2);
    }

    @Test
    void responsibleidTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectpbs.setResponsibleid(officersBack);
        assertThat(projectpbs.getResponsibleid()).isEqualTo(officersBack);

        projectpbs.responsibleid(null);
        assertThat(projectpbs.getResponsibleid()).isNull();
    }

    @Test
    void auditoridTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectpbs.setAuditorid(officersBack);
        assertThat(projectpbs.getAuditorid()).isEqualTo(officersBack);

        projectpbs.auditorid(null);
        assertThat(projectpbs.getAuditorid()).isNull();
    }

    @Test
    void departmentTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectpbs.setDepartment(departmentBack);
        assertThat(projectpbs.getDepartment()).isEqualTo(departmentBack);

        projectpbs.department(null);
        assertThat(projectpbs.getDepartment()).isNull();
    }

    @Test
    void projectTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectpbs.addProject(projectBack);
        assertThat(projectpbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectpbs()).containsOnly(projectpbs);

        projectpbs.removeProject(projectBack);
        assertThat(projectpbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectpbs()).doesNotContain(projectpbs);

        projectpbs.projects(new HashSet<>(Set.of(projectBack)));
        assertThat(projectpbs.getProjects()).containsOnly(projectBack);
        assertThat(projectBack.getProjectpbs()).containsOnly(projectpbs);

        projectpbs.setProjects(new HashSet<>());
        assertThat(projectpbs.getProjects()).doesNotContain(projectBack);
        assertThat(projectBack.getProjectpbs()).doesNotContain(projectpbs);
    }
}
