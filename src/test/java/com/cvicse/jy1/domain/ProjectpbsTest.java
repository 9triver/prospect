package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
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
    void technicaldirectorTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectpbs.setTechnicaldirector(officersBack);
        assertThat(projectpbs.getTechnicaldirector()).isEqualTo(officersBack);

        projectpbs.technicaldirector(null);
        assertThat(projectpbs.getTechnicaldirector()).isNull();
    }

    @Test
    void administrativedirectorTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectpbs.setAdministrativedirector(officersBack);
        assertThat(projectpbs.getAdministrativedirector()).isEqualTo(officersBack);

        projectpbs.administrativedirector(null);
        assertThat(projectpbs.getAdministrativedirector()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        projectpbs.setKnowingpeople(officersBack);
        assertThat(projectpbs.getKnowingpeople()).isEqualTo(officersBack);

        projectpbs.knowingpeople(null);
        assertThat(projectpbs.getKnowingpeople()).isNull();
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
    void responsibledepartmentTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectpbs.setResponsibledepartment(departmentBack);
        assertThat(projectpbs.getResponsibledepartment()).isEqualTo(departmentBack);

        projectpbs.responsibledepartment(null);
        assertThat(projectpbs.getResponsibledepartment()).isNull();
    }

    @Test
    void relevantdepartmentTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        projectpbs.setRelevantdepartment(departmentBack);
        assertThat(projectpbs.getRelevantdepartment()).isEqualTo(departmentBack);

        projectpbs.relevantdepartment(null);
        assertThat(projectpbs.getRelevantdepartment()).isNull();
    }

    @Test
    void projectwbsTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectpbs.addProjectwbs(projectwbsBack);
        assertThat(projectpbs.getProjectwbs()).containsOnly(projectwbsBack);

        projectpbs.removeProjectwbs(projectwbsBack);
        assertThat(projectpbs.getProjectwbs()).doesNotContain(projectwbsBack);

        projectpbs.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(projectpbs.getProjectwbs()).containsOnly(projectwbsBack);

        projectpbs.setProjectwbs(new HashSet<>());
        assertThat(projectpbs.getProjectwbs()).doesNotContain(projectwbsBack);
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
