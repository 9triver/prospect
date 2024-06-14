package com.cvicse.domain;

import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanstrategyTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
        Department department1 = getDepartmentSample1();
        Department department2 = new Department();
        assertThat(department1).isNotEqualTo(department2);

        department2.setId(department1.getId());
        assertThat(department1).isEqualTo(department2);

        department2 = getDepartmentSample2();
        assertThat(department1).isNotEqualTo(department2);
    }

    @Test
    void officersTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        department.setOfficers(officersBack);
        assertThat(department.getOfficers()).isEqualTo(officersBack);
        assertThat(officersBack.getDepartment()).isEqualTo(department);

        department.officers(null);
        assertThat(department.getOfficers()).isNull();
        assertThat(officersBack.getDepartment()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        department.setProject(projectBack);
        assertThat(department.getProject()).isEqualTo(projectBack);
        assertThat(projectBack.getDepartment()).isEqualTo(department);

        department.project(null);
        assertThat(department.getProject()).isNull();
        assertThat(projectBack.getDepartment()).isNull();
    }

    @Test
    void planstrategyTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        Planstrategy planstrategyBack = getPlanstrategyRandomSampleGenerator();

        department.setPlanstrategy(planstrategyBack);
        assertThat(department.getPlanstrategy()).isEqualTo(planstrategyBack);
        assertThat(planstrategyBack.getDecument()).isEqualTo(department);

        department.planstrategy(null);
        assertThat(department.getPlanstrategy()).isNull();
        assertThat(planstrategyBack.getDecument()).isNull();
    }

    @Test
    void progressmanagementTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        Progressmanagement progressmanagementBack = getProgressmanagementRandomSampleGenerator();

        department.setProgressmanagement(progressmanagementBack);
        assertThat(department.getProgressmanagement()).isEqualTo(progressmanagementBack);
        assertThat(progressmanagementBack.getDepartment()).isEqualTo(department);

        department.progressmanagement(null);
        assertThat(department.getProgressmanagement()).isNull();
        assertThat(progressmanagementBack.getDepartment()).isNull();
    }

    @Test
    void evaluationCriteriaTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        EvaluationCriteria evaluationCriteriaBack = getEvaluationCriteriaRandomSampleGenerator();

        department.setEvaluationCriteria(evaluationCriteriaBack);
        assertThat(department.getEvaluationCriteria()).isEqualTo(evaluationCriteriaBack);
        assertThat(evaluationCriteriaBack.getDepartment()).isEqualTo(department);

        department.evaluationCriteria(null);
        assertThat(department.getEvaluationCriteria()).isNull();
        assertThat(evaluationCriteriaBack.getDepartment()).isNull();
    }
}
