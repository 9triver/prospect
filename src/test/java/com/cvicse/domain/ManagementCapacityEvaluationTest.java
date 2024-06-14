package com.cvicse.domain;

import static com.cvicse.domain.EvaluationCriteriaTestSamples.*;
import static com.cvicse.domain.ManagementCapacityEvaluationTestSamples.*;
import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.ProjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManagementCapacityEvaluationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ManagementCapacityEvaluation.class);
        ManagementCapacityEvaluation managementCapacityEvaluation1 = getManagementCapacityEvaluationSample1();
        ManagementCapacityEvaluation managementCapacityEvaluation2 = new ManagementCapacityEvaluation();
        assertThat(managementCapacityEvaluation1).isNotEqualTo(managementCapacityEvaluation2);

        managementCapacityEvaluation2.setId(managementCapacityEvaluation1.getId());
        assertThat(managementCapacityEvaluation1).isEqualTo(managementCapacityEvaluation2);

        managementCapacityEvaluation2 = getManagementCapacityEvaluationSample2();
        assertThat(managementCapacityEvaluation1).isNotEqualTo(managementCapacityEvaluation2);
    }

    @Test
    void evaluationCriteriaTest() throws Exception {
        ManagementCapacityEvaluation managementCapacityEvaluation = getManagementCapacityEvaluationRandomSampleGenerator();
        EvaluationCriteria evaluationCriteriaBack = getEvaluationCriteriaRandomSampleGenerator();

        managementCapacityEvaluation.setEvaluationCriteria(evaluationCriteriaBack);
        assertThat(managementCapacityEvaluation.getEvaluationCriteria()).isEqualTo(evaluationCriteriaBack);

        managementCapacityEvaluation.evaluationCriteria(null);
        assertThat(managementCapacityEvaluation.getEvaluationCriteria()).isNull();
    }

    @Test
    void projectTest() throws Exception {
        ManagementCapacityEvaluation managementCapacityEvaluation = getManagementCapacityEvaluationRandomSampleGenerator();
        Project projectBack = getProjectRandomSampleGenerator();

        managementCapacityEvaluation.setProject(projectBack);
        assertThat(managementCapacityEvaluation.getProject()).isEqualTo(projectBack);

        managementCapacityEvaluation.project(null);
        assertThat(managementCapacityEvaluation.getProject()).isNull();
    }

    @Test
    void creatoridTest() throws Exception {
        ManagementCapacityEvaluation managementCapacityEvaluation = getManagementCapacityEvaluationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        managementCapacityEvaluation.setCreatorid(officersBack);
        assertThat(managementCapacityEvaluation.getCreatorid()).isEqualTo(officersBack);

        managementCapacityEvaluation.creatorid(null);
        assertThat(managementCapacityEvaluation.getCreatorid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        ManagementCapacityEvaluation managementCapacityEvaluation = getManagementCapacityEvaluationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        managementCapacityEvaluation.setResponsibleid(officersBack);
        assertThat(managementCapacityEvaluation.getResponsibleid()).isEqualTo(officersBack);

        managementCapacityEvaluation.responsibleid(null);
        assertThat(managementCapacityEvaluation.getResponsibleid()).isNull();
    }

    @Test
    void ratingpersonTest() throws Exception {
        ManagementCapacityEvaluation managementCapacityEvaluation = getManagementCapacityEvaluationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        managementCapacityEvaluation.setRatingperson(officersBack);
        assertThat(managementCapacityEvaluation.getRatingperson()).isEqualTo(officersBack);

        managementCapacityEvaluation.ratingperson(null);
        assertThat(managementCapacityEvaluation.getRatingperson()).isNull();
    }
}
