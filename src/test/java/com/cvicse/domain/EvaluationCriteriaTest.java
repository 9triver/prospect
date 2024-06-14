package com.cvicse.domain;

import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.ManagementCapacityEvaluationTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EvaluationCriteriaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(EvaluationCriteria.class);
        EvaluationCriteria evaluationCriteria1 = getEvaluationCriteriaSample1();
        EvaluationCriteria evaluationCriteria2 = new EvaluationCriteria();
        assertThat(evaluationCriteria1).isNotEqualTo(evaluationCriteria2);

        evaluationCriteria2.setId(evaluationCriteria1.getId());
        assertThat(evaluationCriteria1).isEqualTo(evaluationCriteria2);

        evaluationCriteria2 = getEvaluationCriteriaSample2();
        assertThat(evaluationCriteria1).isNotEqualTo(evaluationCriteria2);
    }

    @Test
    void departmentTest() throws Exception {
        EvaluationCriteria evaluationCriteria = getEvaluationCriteriaRandomSampleGenerator();
        Department departmentBack = getDepartmentRandomSampleGenerator();

        evaluationCriteria.setDepartment(departmentBack);
        assertThat(evaluationCriteria.getDepartment()).isEqualTo(departmentBack);

        evaluationCriteria.department(null);
        assertThat(evaluationCriteria.getDepartment()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        EvaluationCriteria evaluationCriteria = getEvaluationCriteriaRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        evaluationCriteria.setProject(projectBack);
        assertThat(evaluationCriteria.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getEvaluationCriteria()).isEqualTo(evaluationCriteria);

        evaluationCriteria.project(null);
        assertThat(evaluationCriteria.getProject()).isNull();
        assertThat(projectBack.getEvaluationCriteria()).isNull();
    }

    @Test
    void managementCapacityEvaluationTest() throws Exception {
        EvaluationCriteria evaluationCriteria = getEvaluationCriteriaRandomSampleGenerator();
        ManagementCapacityEvaluation managementCapacityEvaluationBack = getManagementCapacityEvaluationRandomSampleGenerator();

        evaluationCriteria.setManagementCapacityEvaluation(managementCapacityEvaluationBack);
        assertThat(evaluationCriteria.getManagementCapacityEvaluation()).isEqualTo(managementCapacityEvaluationBack);
        assertThat(managementCapacityEvaluationBack.getEvaluationCriteria()).isEqualTo(evaluationCriteria);

        evaluationCriteria.managementCapacityEvaluation(null);
        assertThat(evaluationCriteria.getManagementCapacityEvaluation()).isNull();
        assertThat(managementCapacityEvaluationBack.getEvaluationCriteria()).isNull();
    }
}
