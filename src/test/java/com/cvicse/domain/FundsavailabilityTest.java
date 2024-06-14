package com.cvicse.domain;

import static com.cvicse.domain.FundsavailabilityTestSamples.*;
import static com.cvicse.domain.FundsmanagementTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundsavailabilityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fundsavailability.class);
        Fundsavailability fundsavailability1 = getFundsavailabilitySample1();
        Fundsavailability fundsavailability2 = new Fundsavailability();
        assertThat(fundsavailability1).isNotEqualTo(fundsavailability2);

        fundsavailability2.setId(fundsavailability1.getId());
        assertThat(fundsavailability1).isEqualTo(fundsavailability2);

        fundsavailability2 = getFundsavailabilitySample2();
        assertThat(fundsavailability1).isNotEqualTo(fundsavailability2);
    }

    @Test
    void fundsmanagementTest() throws Exception {
        Fundsavailability fundsavailability = getFundsavailabilityRandomSampleGenerator();
        Fundsmanagement fundsmanagementBack = getFundsmanagementRandomSampleGenerator();

        fundsavailability.setFundsmanagement(fundsmanagementBack);
        assertThat(fundsavailability.getFundsmanagement()).isEqualTo(fundsmanagementBack);

        fundsavailability.fundsmanagement(null);
        assertThat(fundsavailability.getFundsmanagement()).isNull();
    }
}
