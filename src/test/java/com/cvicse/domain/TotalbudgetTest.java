package com.cvicse.domain;

import static com.cvicse.domain.AuditedbudgetTestSamples.*;
import static com.cvicse.domain.ComprehensivecontrolTestSamples.*;
import static com.cvicse.domain.TotalbudgetTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TotalbudgetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Totalbudget.class);
        Totalbudget totalbudget1 = getTotalbudgetSample1();
        Totalbudget totalbudget2 = new Totalbudget();
        assertThat(totalbudget1).isNotEqualTo(totalbudget2);

        totalbudget2.setId(totalbudget1.getId());
        assertThat(totalbudget1).isEqualTo(totalbudget2);

        totalbudget2 = getTotalbudgetSample2();
        assertThat(totalbudget1).isNotEqualTo(totalbudget2);
    }

    @Test
    void comprehensivecontrolTest() throws Exception {
        Totalbudget totalbudget = getTotalbudgetRandomSampleGenerator();
        Comprehensivecontrol comprehensivecontrolBack = getComprehensivecontrolRandomSampleGenerator();

        totalbudget.setComprehensivecontrol(comprehensivecontrolBack);
        assertThat(totalbudget.getComprehensivecontrol()).isEqualTo(comprehensivecontrolBack);
        assertThat(comprehensivecontrolBack.getTotalbudget()).isEqualTo(totalbudget);

        totalbudget.comprehensivecontrol(null);
        assertThat(totalbudget.getComprehensivecontrol()).isNull();
        assertThat(comprehensivecontrolBack.getTotalbudget()).isNull();
    }

    @Test
    void auditedbudgetTest() throws Exception {
        Totalbudget totalbudget = getTotalbudgetRandomSampleGenerator();
        Auditedbudget auditedbudgetBack = getAuditedbudgetRandomSampleGenerator();

        totalbudget.setAuditedbudget(auditedbudgetBack);
        assertThat(totalbudget.getAuditedbudget()).isEqualTo(auditedbudgetBack);
        assertThat(auditedbudgetBack.getTotalbudget()).isEqualTo(totalbudget);

        totalbudget.auditedbudget(null);
        assertThat(totalbudget.getAuditedbudget()).isNull();
        assertThat(auditedbudgetBack.getTotalbudget()).isNull();
    }
}
