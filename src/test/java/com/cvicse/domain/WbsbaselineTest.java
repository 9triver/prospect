package com.cvicse.domain;

import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static com.cvicse.domain.WbsbaselineTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class WbsbaselineTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Wbsbaseline.class);
        Wbsbaseline wbsbaseline1 = getWbsbaselineSample1();
        Wbsbaseline wbsbaseline2 = new Wbsbaseline();
        assertThat(wbsbaseline1).isNotEqualTo(wbsbaseline2);

        wbsbaseline2.setId(wbsbaseline1.getId());
        assertThat(wbsbaseline1).isEqualTo(wbsbaseline2);

        wbsbaseline2 = getWbsbaselineSample2();
        assertThat(wbsbaseline1).isNotEqualTo(wbsbaseline2);
    }

    @Test
    void projectchargeTest() throws Exception {
        Wbsbaseline wbsbaseline = getWbsbaselineRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        wbsbaseline.setProjectcharge(projectchargeBack);
        assertThat(wbsbaseline.getProjectcharge()).isEqualTo(projectchargeBack);

        wbsbaseline.projectcharge(null);
        assertThat(wbsbaseline.getProjectcharge()).isNull();
    }
}
