package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.HrManagementTestSamples.*;
import static com.cvicse.jy1.domain.ProjectBudgetTestSamples.*;
import static com.cvicse.jy1.domain.SubjectCostBudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SubjectCostBudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubjectCostBudget.class);
        SubjectCostBudget subjectCostBudget1 = getSubjectCostBudgetSample1();
        SubjectCostBudget subjectCostBudget2 = new SubjectCostBudget();
        assertThat(subjectCostBudget1).isNotEqualTo(subjectCostBudget2);

        subjectCostBudget2.setId(subjectCostBudget1.getId());
        assertThat(subjectCostBudget1).isEqualTo(subjectCostBudget2);

        subjectCostBudget2 = getSubjectCostBudgetSample2();
        assertThat(subjectCostBudget1).isNotEqualTo(subjectCostBudget2);
    }

    @Test
    void projectBudgetTest() {
        SubjectCostBudget subjectCostBudget = getSubjectCostBudgetRandomSampleGenerator();
        ProjectBudget projectBudgetBack = getProjectBudgetRandomSampleGenerator();

        subjectCostBudget.setProjectBudget(projectBudgetBack);
        assertThat(subjectCostBudget.getProjectBudget()).isEqualTo(projectBudgetBack);

        subjectCostBudget.projectBudget(null);
        assertThat(subjectCostBudget.getProjectBudget()).isNull();
    }

    @Test
    void responsiblepersonTest() {
        SubjectCostBudget subjectCostBudget = getSubjectCostBudgetRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        subjectCostBudget.setResponsibleperson(hrManagementBack);
        assertThat(subjectCostBudget.getResponsibleperson()).isEqualTo(hrManagementBack);

        subjectCostBudget.responsibleperson(null);
        assertThat(subjectCostBudget.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        SubjectCostBudget subjectCostBudget = getSubjectCostBudgetRandomSampleGenerator();
        HrManagement hrManagementBack = getHrManagementRandomSampleGenerator();

        subjectCostBudget.setAuditorid(hrManagementBack);
        assertThat(subjectCostBudget.getAuditorid()).isEqualTo(hrManagementBack);

        subjectCostBudget.auditorid(null);
        assertThat(subjectCostBudget.getAuditorid()).isNull();
    }
}
