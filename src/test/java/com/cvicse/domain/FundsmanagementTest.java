package com.cvicse.domain;

import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static com.cvicse.domain.FundsmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsmanagementTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fundsmanagement.class);
        Fundsmanagement fundsmanagement1 = getFundsmanagementSample1();
        Fundsmanagement fundsmanagement2 = new Fundsmanagement();
        assertThat(fundsmanagement1).isNotEqualTo(fundsmanagement2);

        fundsmanagement2.setId(fundsmanagement1.getId());
        assertThat(fundsmanagement1).isEqualTo(fundsmanagement2);

        fundsmanagement2 = getFundsmanagementSample2();
        assertThat(fundsmanagement1).isNotEqualTo(fundsmanagement2);
    }

    @Test
    void wbsTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        FundsmanagementWbs fundsmanagementWbsBack = getFundsmanagementWbsRandomSampleGenerator();

        fundsmanagement.setWbs(fundsmanagementWbsBack);
        assertThat(fundsmanagement.getWbs()).isEqualTo(fundsmanagementWbsBack);

        fundsmanagement.wbs(null);
        assertThat(fundsmanagement.getWbs()).isNull();
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Fundsmanagement fundsmanagement = getFundsmanagementRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        fundsmanagement.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(fundsmanagement.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getFunds()).isEqualTo(fundsmanagement);

        fundsmanagement.comprehensivecontrol(null);
        assertThat(fundsmanagement.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getFunds()).isNull();
    }
}
