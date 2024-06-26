package com.cvicse.domain;

import static com.cvicse.domain.DepartmentTestSamples.*;
import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.PlanstrategyTestSamples.*;
import static com.cvicse.domain.ProgressplanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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

        department.addOfficers(officersBack);
        assertThat(department.getOfficers()).containsOnly(officersBack);

        department.removeOfficers(officersBack);
        assertThat(department.getOfficers()).doesNotContain(officersBack);

        department.officers(new HashSet<>(Set.of(officersBack)));
        assertThat(department.getOfficers()).containsOnly(officersBack);

        department.setOfficers(new HashSet<>());
        assertThat(department.getOfficers()).doesNotContain(officersBack);
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
    void progressplanTest() throws Exception {
        Department department = getDepartmentRandomSampleGenerator();
        Progressplan progressplanBack = getProgressplanRandomSampleGenerator();

        department.setProgressplan(progressplanBack);
        assertThat(department.getProgressplan()).isEqualTo(progressplanBack);
        assertThat(progressplanBack.getDepartment()).isEqualTo(department);

        department.progressplan(null);
        assertThat(department.getProgressplan()).isNull();
        assertThat(progressplanBack.getDepartment()).isNull();
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
