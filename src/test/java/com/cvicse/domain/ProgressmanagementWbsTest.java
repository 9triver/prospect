package com.cvicse.domain;

import static com.cvicse.domain.ProgressbaselineTestSamples.*;
import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProgressmanagementWbsTestSamples.*;
import static com.cvicse.domain.ProgressplanTestSamples.*;
import static com.cvicse.domain.ProgressplanreturnsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProgressmanagementWbs.class);
        ProgressmanagementWbs progressmanagementWbs1 = getProgressmanagementWbsSample1();
        ProgressmanagementWbs progressmanagementWbs2 = new ProgressmanagementWbs();
        assertThat(progressmanagementWbs1).isNotEqualTo(progressmanagementWbs2);

        progressmanagementWbs2.setId(progressmanagementWbs1.getId());
        assertThat(progressmanagementWbs1).isEqualTo(progressmanagementWbs2);

        progressmanagementWbs2 = getProgressmanagementWbsSample2();
        assertThat(progressmanagementWbs1).isNotEqualTo(progressmanagementWbs2);
    }

    @Test
    void progressplanTest() throws Exception {
        ProgressmanagementWbs progressmanagementWbs = getProgressmanagementWbsRandomSampleGenerator();
        Progressplan progressplanBack = getProgressplanRandomSampleGenerator();

        progressmanagementWbs.setProgressplan(progressplanBack);
        assertThat(progressmanagementWbs.getProgressplan()).isEqualTo(progressplanBack);

        progressmanagementWbs.progressplan(null);
        assertThat(progressmanagementWbs.getProgressplan()).isNull();
    }

    @Test
    void progressplanreturnsTest() throws Exception {
        ProgressmanagementWbs progressmanagementWbs = getProgressmanagementWbsRandomSampleGenerator();
        Progressplanreturns progressplanreturnsBack = getProgressplanreturnsRandomSampleGenerator();

        progressmanagementWbs.setProgressplanreturns(progressplanreturnsBack);
        assertThat(progressmanagementWbs.getProgressplanreturns()).isEqualTo(progressplanreturnsBack);

        progressmanagementWbs.progressplanreturns(null);
        assertThat(progressmanagementWbs.getProgressplanreturns()).isNull();
    }

    @Test
    void progressbaselineTest() throws Exception {
        ProgressmanagementWbs progressmanagementWbs = getProgressmanagementWbsRandomSampleGenerator();
        Progressbaseline progressbaselineBack = getProgressbaselineRandomSampleGenerator();

        progressmanagementWbs.setProgressbaseline(progressbaselineBack);
        assertThat(progressmanagementWbs.getProgressbaseline()).isEqualTo(progressbaselineBack);

        progressmanagementWbs.progressbaseline(null);
        assertThat(progressmanagementWbs.getProgressbaseline()).isNull();
    }

    @Test
    void progressmanagementTest() throws Exception {
        ProgressmanagementWbs progressmanagementWbs = getProgressmanagementWbsRandomSampleGenerator();
        Progressmanagement progressmanagementBack = getProgressmanagementRandomSampleGenerator();

        progressmanagementWbs.setProgressmanagement(progressmanagementBack);
        assertThat(progressmanagementWbs.getProgressmanagement()).isEqualTo(progressmanagementBack);
        assertThat(progressmanagementBack.getWbs()).isEqualTo(progressmanagementWbs);

        progressmanagementWbs.progressmanagement(null);
        assertThat(progressmanagementWbs.getProgressmanagement()).isNull();
        assertThat(progressmanagementBack.getWbs()).isNull();
    }
}
