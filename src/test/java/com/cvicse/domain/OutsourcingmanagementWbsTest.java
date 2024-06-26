package com.cvicse.domain;

import static com.cvicse.domain.OutsourcingPurchaseExecuteTestSamples.*;
import static com.cvicse.domain.OutsourcingPurchasePlanTestSamples.*;
import static com.cvicse.domain.OutsourcingmanagementTestSamples.*;
import static com.cvicse.domain.OutsourcingmanagementWbsTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OutsourcingmanagementWbsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingmanagementWbs.class);
        OutsourcingmanagementWbs outsourcingmanagementWbs1 = getOutsourcingmanagementWbsSample1();
        OutsourcingmanagementWbs outsourcingmanagementWbs2 = new OutsourcingmanagementWbs();
        assertThat(outsourcingmanagementWbs1).isNotEqualTo(outsourcingmanagementWbs2);

        outsourcingmanagementWbs2.setId(outsourcingmanagementWbs1.getId());
        assertThat(outsourcingmanagementWbs1).isEqualTo(outsourcingmanagementWbs2);

        outsourcingmanagementWbs2 = getOutsourcingmanagementWbsSample2();
        assertThat(outsourcingmanagementWbs1).isNotEqualTo(outsourcingmanagementWbs2);
    }

    @Test
    void outsourcingPurchasePlanTest() throws Exception {
        OutsourcingmanagementWbs outsourcingmanagementWbs = getOutsourcingmanagementWbsRandomSampleGenerator();
        OutsourcingPurchasePlan outsourcingPurchasePlanBack = getOutsourcingPurchasePlanRandomSampleGenerator();

        outsourcingmanagementWbs.setOutsourcingPurchasePlan(outsourcingPurchasePlanBack);
        assertThat(outsourcingmanagementWbs.getOutsourcingPurchasePlan()).isEqualTo(outsourcingPurchasePlanBack);

        outsourcingmanagementWbs.outsourcingPurchasePlan(null);
        assertThat(outsourcingmanagementWbs.getOutsourcingPurchasePlan()).isNull();
    }

    @Test
    void outsourcingPurchaseExecuteTest() throws Exception {
        OutsourcingmanagementWbs outsourcingmanagementWbs = getOutsourcingmanagementWbsRandomSampleGenerator();
        OutsourcingPurchaseExecute outsourcingPurchaseExecuteBack = getOutsourcingPurchaseExecuteRandomSampleGenerator();

        outsourcingmanagementWbs.setOutsourcingPurchaseExecute(outsourcingPurchaseExecuteBack);
        assertThat(outsourcingmanagementWbs.getOutsourcingPurchaseExecute()).isEqualTo(outsourcingPurchaseExecuteBack);

        outsourcingmanagementWbs.outsourcingPurchaseExecute(null);
        assertThat(outsourcingmanagementWbs.getOutsourcingPurchaseExecute()).isNull();
    }

    @Test
    void outsourcingmanagementTest() throws Exception {
        OutsourcingmanagementWbs outsourcingmanagementWbs = getOutsourcingmanagementWbsRandomSampleGenerator();
        Outsourcingmanagement outsourcingmanagementBack = getOutsourcingmanagementRandomSampleGenerator();

        outsourcingmanagementWbs.setOutsourcingmanagement(outsourcingmanagementBack);
        assertThat(outsourcingmanagementWbs.getOutsourcingmanagement()).isEqualTo(outsourcingmanagementBack);
        assertThat(outsourcingmanagementBack.getWbs()).isEqualTo(outsourcingmanagementWbs);

        outsourcingmanagementWbs.outsourcingmanagement(null);
        assertThat(outsourcingmanagementWbs.getOutsourcingmanagement()).isNull();
        assertThat(outsourcingmanagementBack.getWbs()).isNull();
    }
}
