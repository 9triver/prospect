package com.cvicse.domain;

import static com.cvicse.domain.PlanmonitorTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PlanmonitorTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Planmonitor.class);
        Planmonitor planmonitor1 = getPlanmonitorSample1();
        Planmonitor planmonitor2 = new Planmonitor();
        assertThat(planmonitor1).isNotEqualTo(planmonitor2);

        planmonitor2.setId(planmonitor1.getId());
        assertThat(planmonitor1).isEqualTo(planmonitor2);

        planmonitor2 = getPlanmonitorSample2();
        assertThat(planmonitor1).isNotEqualTo(planmonitor2);
    }
}
