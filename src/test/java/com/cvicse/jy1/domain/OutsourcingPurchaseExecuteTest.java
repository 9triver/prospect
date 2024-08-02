package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OfficersTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingPurchaseExecuteTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingPurchasePlanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingPurchaseExecuteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingPurchaseExecute.class);
        OutsourcingPurchaseExecute outsourcingPurchaseExecute1 = getOutsourcingPurchaseExecuteSample1();
        OutsourcingPurchaseExecute outsourcingPurchaseExecute2 = new OutsourcingPurchaseExecute();
        assertThat(outsourcingPurchaseExecute1).isNotEqualTo(outsourcingPurchaseExecute2);

        outsourcingPurchaseExecute2.setId(outsourcingPurchaseExecute1.getId());
        assertThat(outsourcingPurchaseExecute1).isEqualTo(outsourcingPurchaseExecute2);

        outsourcingPurchaseExecute2 = getOutsourcingPurchaseExecuteSample2();
        assertThat(outsourcingPurchaseExecute1).isNotEqualTo(outsourcingPurchaseExecute2);
    }

    @Test
    void responsiblepersonTest() {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = getOutsourcingPurchaseExecuteRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchaseExecute.setResponsibleperson(officersBack);
        assertThat(outsourcingPurchaseExecute.getResponsibleperson()).isEqualTo(officersBack);

        outsourcingPurchaseExecute.responsibleperson(null);
        assertThat(outsourcingPurchaseExecute.getResponsibleperson()).isNull();
    }

    @Test
    void outsourcingplanidTest() {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = getOutsourcingPurchaseExecuteRandomSampleGenerator();
        OutsourcingPurchasePlan outsourcingPurchasePlanBack = getOutsourcingPurchasePlanRandomSampleGenerator();

        outsourcingPurchaseExecute.setOutsourcingplanid(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchaseExecute.getOutsourcingplanid()).isEqualTo(outsourcingPurchasePlanBack);

        outsourcingPurchaseExecute.outsourcingplanid(null);
        assertThat(outsourcingPurchaseExecute.getOutsourcingplanid()).isNull();
    }
}
