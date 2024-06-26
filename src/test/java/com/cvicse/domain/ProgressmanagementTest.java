package com.cvicse.domain;

import static com.cvicse.domain.ProgressmanagementTestSamples.*;
import static com.cvicse.domain.ProgressmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProgressmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Progressmanagement.class);
        Progressmanagement progressmanagement1 = getProgressmanagementSample1();
        Progressmanagement progressmanagement2 = new Progressmanagement();
        assertThat(progressmanagement1).isNotEqualTo(progressmanagement2);

        progressmanagement2.setId(progressmanagement1.getId());
        assertThat(progressmanagement1).isEqualTo(progressmanagement2);

        progressmanagement2 = getProgressmanagementSample2();
        assertThat(progressmanagement1).isNotEqualTo(progressmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Progressmanagement progressmanagement = getProgressmanagementRandomSampleGenerator();
        ProgressmanagementWbs progressmanagementWbsBack = getProgressmanagementWbsRandomSampleGenerator();

        progressmanagement.setWbs(progressmanagementWbsBack);
        assertThat(progressmanagement.getWbs()).isEqualTo(progressmanagementWbsBack);

        progressmanagement.wbs(null);
        assertThat(progressmanagement.getWbs()).isNull();
    }
}
