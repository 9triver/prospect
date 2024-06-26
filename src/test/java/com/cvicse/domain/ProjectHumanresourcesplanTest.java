package com.cvicse.domain;

import static com.cvicse.domain.ProjectHumanresourcesplanTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProjectHumanresourcesplanTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectHumanresourcesplan.class);
        ProjectHumanresourcesplan projectHumanresourcesplan1 = getProjectHumanresourcesplanSample1();
        ProjectHumanresourcesplan projectHumanresourcesplan2 = new ProjectHumanresourcesplan();
        assertThat(projectHumanresourcesplan1).isNotEqualTo(projectHumanresourcesplan2);

        projectHumanresourcesplan2.setId(projectHumanresourcesplan1.getId());
        assertThat(projectHumanresourcesplan1).isEqualTo(projectHumanresourcesplan2);

        projectHumanresourcesplan2 = getProjectHumanresourcesplanSample2();
        assertThat(projectHumanresourcesplan1).isNotEqualTo(projectHumanresourcesplan2);
    }

    @Test
    void projectTest() throws Exception {
        ProjectHumanresourcesplan projectHumanresourcesplan = getProjectHumanresourcesplanRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        projectHumanresourcesplan.setProject(projectBack);
        assertThat(projectHumanresourcesplan.getProject()).isEqualTo(projectBack);

        projectHumanresourcesplan.project(null);
        assertThat(projectHumanresourcesplan.getProject()).isNull();
    }
}
