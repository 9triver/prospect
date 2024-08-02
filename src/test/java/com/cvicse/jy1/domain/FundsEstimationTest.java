package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FundsEstimationTestSamples.*;
import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class FundsEstimationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundsEstimation.class);
        FundsEstimation fundsEstimation1 = getFundsEstimationSample1();
        FundsEstimation fundsEstimation2 = new FundsEstimation();
        assertThat(fundsEstimation1).isNotEqualTo(fundsEstimation2);

        fundsEstimation2.setId(fundsEstimation1.getId());
        assertThat(fundsEstimation1).isEqualTo(fundsEstimation2);

        fundsEstimation2 = getFundsEstimationSample2();
        assertThat(fundsEstimation1).isNotEqualTo(fundsEstimation2);
    }

    @Test
    void responsiblepersonTest() {
        FundsEstimation fundsEstimation = getFundsEstimationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        fundsEstimation.setResponsibleperson(officersBack);
        assertThat(fundsEstimation.getResponsibleperson()).isEqualTo(officersBack);

        fundsEstimation.responsibleperson(null);
        assertThat(fundsEstimation.getResponsibleperson()).isNull();
    }

    @Test
    void auditoridTest() {
        FundsEstimation fundsEstimation = getFundsEstimationRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        fundsEstimation.setAuditorid(officersBack);
        assertThat(fundsEstimation.getAuditorid()).isEqualTo(officersBack);

        fundsEstimation.auditorid(null);
        assertThat(fundsEstimation.getAuditorid()).isNull();
    }

    @Test
    void projectwbsTest() {
        FundsEstimation fundsEstimation = getFundsEstimationRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        fundsEstimation.addProjectwbs(projectwbsBack);
        assertThat(fundsEstimation.getProjectwbs()).containsOnly(projectwbsBack);

        fundsEstimation.removeProjectwbs(projectwbsBack);
        assertThat(fundsEstimation.getProjectwbs()).doesNotContain(projectwbsBack);

        fundsEstimation.projectwbs(new HashSet<>(Set.of(projectwbsBack)));
        assertThat(fundsEstimation.getProjectwbs()).containsOnly(projectwbsBack);

        fundsEstimation.setProjectwbs(new HashSet<>());
        assertThat(fundsEstimation.getProjectwbs()).doesNotContain(projectwbsBack);
    }
}
