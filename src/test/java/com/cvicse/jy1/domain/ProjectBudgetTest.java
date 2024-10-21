package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.ProjectBudgetTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProjectBudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectBudget.class);
        ProjectBudget projectBudget1 = getProjectBudgetSample1();
        ProjectBudget projectBudget2 = new ProjectBudget();
        assertThat(projectBudget1).isNotEqualTo(projectBudget2);

        projectBudget2.setId(projectBudget1.getId());
        assertThat(projectBudget1).isEqualTo(projectBudget2);

        projectBudget2 = getProjectBudgetSample2();
        assertThat(projectBudget1).isNotEqualTo(projectBudget2);
    }

    @Test
    void responsiblepersonTest() {
        ProjectBudget projectBudget = getProjectBudgetRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectBudget.setResponsibleperson(hrManagementBack);
        assertThat(projectBudget.getResponsibleperson()).isEqualTo(hrManagementBack);

        projectBudget.responsibleperson(null);
        assertThat(projectBudget.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        ProjectBudget projectBudget = getProjectBudgetRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        projectBudget.setAuditorid(hrManagementBack);
        assertThat(projectBudget.getAuditorid()).isEqualTo(hrManagementBack);

        projectBudget.auditorid(null);
        assertThat(projectBudget.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        ProjectBudget projectBudget = getProjectBudgetRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        projectBudget.addProjectwbs(projectwbsBack);
        assertThat(projectBudget.getProjectwbs()).containsOnly(projectwbsBack);

        projectBudget.removeProjectwbs(projectwbsBack);
        assertThat(projectBudget.getProjectwbs()).doesNotContain(projectwbsBack);

        projectBudget.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(projectBudget.getProjectwbs()).containsOnly(projectwbsBack);

        projectBudget.setProjectwbs(new HashSet<>());
        assertThat(projectBudget.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
