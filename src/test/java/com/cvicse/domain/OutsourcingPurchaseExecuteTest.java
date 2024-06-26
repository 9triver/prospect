package com.cvicse.domain;

import static com.cvicse.domain.OfficersTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchasePlanTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
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
    void outsourcingplanidTest() throws Exception {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = getOutsourcingPurchaseExecuteRandomSampleGenerator();
        OutsourcingPurchasePlan outsourcingPurchasePlanBack = getOutsourcingPurchasePlanRandomSampleGenerator();

        outsourcingPurchaseExecute.setOutsourcingplanid(outsourcingPurchasePlanBack);
        assertThat(outsourcingPurchaseExecute.getOutsourcingplanid()).isEqualTo(outsourcingPurchasePlanBack);

        outsourcingPurchaseExecute.outsourcingplanid(null);
        assertThat(outsourcingPurchaseExecute.getOutsourcingplanid()).isNull();
    }

    @Test
    void responsibleidTest() throws Exception {
        OutsourcingPurchaseExecute outsourcingPurchaseExecute = getOutsourcingPurchaseExecuteRandomSampleGenerator();
        Officers officersBack = getOfficersRandomSampleGenerator();

        outsourcingPurchaseExecute.setResponsibleid(officersBack);
        assertThat(outsourcingPurchaseExecute.getResponsibleid()).isEqualTo(officersBack);

        outsourcingPurchaseExecute.responsibleid(null);
        assertThat(outsourcingPurchaseExecute.getResponsibleid()).isNull();
    }
}
