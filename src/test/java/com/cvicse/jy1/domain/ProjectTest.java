package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ProjectTestSamples.*;
import static com.cvicse.jy1.domain.ProjectpbsTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = getProjectSample1();
        Project project2 = new Project();
        assertThat(project1).isNotEqualTo(project2);

        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);

        project2 = getProjectSample2();
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    void projectpbsTest() {
        Project project = getProjectRandomSampleGenerator();
        Projectpbs projectpbsBack = getProjectpbsRandomSampleGenerator();

        project.addProjectpbs(projectpbsBack);
        assertThat(project.getProjectpbs()).containsOnly(projectpbsBack);

        project.removeProjectpbs(projectpbsBack);
        assertThat(project.getProjectpbs()).doesNotContain(projectpbsBack);

        project.projectpbs(new HashSet<>(Set.of(projectpbsBack)));
        assertThat(project.getProjectpbs()).containsOnly(projectpbsBack);

        project.setProjectpbs(new HashSet<>());
        assertThat(project.getProjectpbs()).doesNotContain(projectpbsBack);
    }

    @Test
    void projectwbsTest() {
        Project project = getProjectRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        project.addProjectwbs(projectwbsBack);
        assertThat(project.getProjectwbs()).containsOnly(projectwbsBack);

        project.removeProjectwbs(projectwbsBack);
        assertThat(project.getProjectwbs()).doesNotContain(projectwbsBack);

        project.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(project.getProjectwbs()).containsOnly(projectwbsBack);

        project.setProjectwbs(new HashSet<>());
        assertThat(project.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
