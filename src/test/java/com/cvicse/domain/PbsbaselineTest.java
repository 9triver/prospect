package com.cvicse.domain;

import static com.cvicse.domain.PbsbaselineTestSamples.*;
import static com.cvicse.domain.ProjectchargeTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PbsbaselineTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Pbsbaseline.class);
        Pbsbaseline pbsbaseline1 = getPbsbaselineSample1();
        Pbsbaseline pbsbaseline2 = new Pbsbaseline();
        assertThat(pbsbaseline1).isNotEqualTo(pbsbaseline2);

        pbsbaseline2.setId(pbsbaseline1.getId());
        assertThat(pbsbaseline1).isEqualTo(pbsbaseline2);

        pbsbaseline2 = getPbsbaselineSample2();
        assertThat(pbsbaseline1).isNotEqualTo(pbsbaseline2);
    }

    @Test
    void projectchargeTest() throws Exception {
        Pbsbaseline pbsbaseline = getPbsbaselineRandomSampleGenerator();
        Projectcharge projectchargeBack = getProjectchargeRandomSampleGenerator();

        pbsbaseline.setProjectcharge(projectchargeBack);
        assertThat(pbsbaseline.getProjectcharge()).isEqualTo(projectchargeBack);

        pbsbaseline.projectcharge(null);
        assertThat(pbsbaseline.getProjectcharge()).isNull();
    }
}
