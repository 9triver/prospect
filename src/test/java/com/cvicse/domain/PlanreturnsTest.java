package com.cvicse.domain;

import static com.cvicse.domain.PlanexecuteTestSamples.*;
import static com.cvicse.domain.PlanreturnsTestSamples.*;
import static com.cvicse.domain.ProgressplanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanreturnsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planreturns.class);
        Planreturns planreturns1 = getPlanreturnsSample1();
        Planreturns planreturns2 = new Planreturns();
        assertThat(planreturns1).isNotEqualTo(planreturns2);

        planreturns2.setId(planreturns1.getId());
        assertThat(planreturns1).isEqualTo(planreturns2);

        planreturns2 = getPlanreturnsSample2();
        assertThat(planreturns1).isNotEqualTo(planreturns2);
    }

    @Test
    void planexecuteTest() throws Exception {
        Planreturns planreturns = getPlanreturnsRandomSampleGenerator();
        Planexecute planexecuteBack = getPlanexecuteRandomSampleGenerator();

        planreturns.setPlanexecute(planexecuteBack);
        assertThat(planreturns.getPlanexecute()).isEqualTo(planexecuteBack);
        assertThat(planexecuteBack.getPlanreturns()).isEqualTo(planreturns);

        planreturns.planexecute(null);
        assertThat(planreturns.getPlanexecute()).isNull();
        assertThat(planexecuteBack.getPlanreturns()).isNull();
    }

    @Test
    void progressplanTest() throws Exception {
        Planreturns planreturns = getPlanreturnsRandomSampleGenerator();
        Progressplan progressplanBack = getProgressplanRandomSampleGenerator();

        planreturns.setProgressplan(progressplanBack);
        assertThat(planreturns.getProgressplan()).isEqualTo(progressplanBack);
        assertThat(progressplanBack.getPlanreturns()).isEqualTo(planreturns);

        planreturns.progressplan(null);
        assertThat(planreturns.getProgressplan()).isNull();
        assertThat(progressplanBack.getPlanreturns()).isNull();
    }
}
