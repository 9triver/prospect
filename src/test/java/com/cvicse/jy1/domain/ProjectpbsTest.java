package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DepartmentTestSamples.*;
import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
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
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectpbs.setTechnicaldirector(hrManagementBack);
        assertThat(projectpbs.getTechnicaldirector()).isEqualTo(hrManagementBack);

        projectpbs.technicaldirector(null);
        assertThat(projectpbs.getTechnicaldirector()).isNull();
    }

    @Test
    void administrativedirectorTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectpbs.setAdministrativedirector(hrManagementBack);
        assertThat(projectpbs.getAdministrativedirector()).isEqualTo(hrManagementBack);

        projectpbs.administrativedirector(null);
        assertThat(projectpbs.getAdministrativedirector()).isNull();
    }

    @Test
    void knowingpeopleTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectpbs.setKnowingpeople(hrManagementBack);
        assertThat(projectpbs.getKnowingpeople()).isEqualTo(hrManagementBack);

        projectpbs.knowingpeople(null);
        assertThat(projectpbs.getKnowingpeople()).isNull();
    }

    @Test
    void auditoridTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectpbs.setAuditorid(hrManagementBack);
        assertThat(projectpbs.getAuditorid()).isEqualTo(hrManagementBack);

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

        projectpbs.addRelevantdepartment(departmentBack);
        assertThat(projectpbs.getRelevantdepartments()).containsOnly(departmentBack);

        projectpbs.removeRelevantdepartment(departmentBack);
        assertThat(projectpbs.getRelevantdepartments()).doesNotContain(departmentBack);

        projectpbs.relevantdepartments(new HashSet<>(Set.of(departmentBack)));
        assertThat(projectpbs.getRelevantdepartments()).containsOnly(departmentBack);

        projectpbs.setRelevantdepartments(new HashSet<>());
        assertThat(projectpbs.getRelevantdepartments()).doesNotContain(departmentBack);
    }

    @Test
    void projectwbsTest() {
        Projectpbs projectpbs = getProjectpbsRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectpbs.setProjectwbs(projectwbsBack);
        assertThat(projectpbs.getProjectwbs()).isEqualTo(projectwbsBack);
        assertThat(projectwbsBack.getProjectpbs()).isEqualTo(projectpbs);

        projectpbs.projectwbs(null);
        assertThat(projectpbs.getProjectwbs()).isNull();
        assertThat(projectwbsBack.getProjectpbs()).isNull();
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
