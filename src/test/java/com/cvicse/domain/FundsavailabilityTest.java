package com.cvicse.domain;

import static com.cvicse.domain.AuditedbudgetTestSamples.*;
import static com.cvicse.domain.FundsavailabilityTestSamples.*;
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
    void auditedbudgetTest() throws Exception {
        Fundsavailability fundsavailability = getFundsavailabilityRandomSampleGenerator();
        Auditedbudget auditedbudgetBack = getAuditedbudgetRandomSampleGenerator();

        fundsavailability.setAuditedbudget(auditedbudgetBack);
        assertThat(fundsavailability.getAuditedbudget()).isEqualTo(auditedbudgetBack);

        fundsavailability.auditedbudget(null);
        assertThat(fundsavailability.getAuditedbudget()).isNull();
    }
}
