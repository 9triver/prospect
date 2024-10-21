package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.DeliveryContentTestSamples.*;
import static com.cvicse.jy1.domain.MilestoneNodeTestSamples.*;
import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static com.cvicse.jy1.domain.PaymentApplicationTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class OutsourcingContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OutsourcingContract.class);
        OutsourcingContract outsourcingContract1 = getOutsourcingContractSample1();
        OutsourcingContract outsourcingContract2 = new OutsourcingContract();
        assertThat(outsourcingContract1).isNotEqualTo(outsourcingContract2);

        outsourcingContract2.setId(outsourcingContract1.getId());
        assertThat(outsourcingContract1).isEqualTo(outsourcingContract2);

        outsourcingContract2 = getOutsourcingContractSample2();
        assertThat(outsourcingContract1).isNotEqualTo(outsourcingContract2);
    }

    @Test
    void workbagTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        outsourcingContract.setWorkbag(workbagBack);
        assertThat(outsourcingContract.getWorkbag()).isEqualTo(workbagBack);

        outsourcingContract.workbag(null);
        assertThat(outsourcingContract.getWorkbag()).isNull();
    }

    @Test
    void deliveryContentTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        DeliveryContent deliveryContentBack = getDeliveryContentRandomSampleGenerator();

        outsourcingContract.addDeliveryContent(deliveryContentBack);
        assertThat(outsourcingContract.getDeliveryContents()).containsOnly(deliveryContentBack);
        assertThat(deliveryContentBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.removeDeliveryContent(deliveryContentBack);
        assertThat(outsourcingContract.getDeliveryContents()).doesNotContain(deliveryContentBack);
        assertThat(deliveryContentBack.getOutsourcingContract()).isNull();

        outsourcingContract.deliveryContents(new HashSet<>(Set.of(deliveryContentBack)));
        assertThat(outsourcingContract.getDeliveryContents()).containsOnly(deliveryContentBack);
        assertThat(deliveryContentBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.setDeliveryContents(new HashSet<>());
        assertThat(outsourcingContract.getDeliveryContents()).doesNotContain(deliveryContentBack);
        assertThat(deliveryContentBack.getOutsourcingContract()).isNull();
    }

    @Test
    void milestoneNodeTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        MilestoneNode milestoneNodeBack = getMilestoneNodeRandomSampleGenerator();

        outsourcingContract.addMilestoneNode(milestoneNodeBack);
        assertThat(outsourcingContract.getMilestoneNodes()).containsOnly(milestoneNodeBack);
        assertThat(milestoneNodeBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.removeMilestoneNode(milestoneNodeBack);
        assertThat(outsourcingContract.getMilestoneNodes()).doesNotContain(milestoneNodeBack);
        assertThat(milestoneNodeBack.getOutsourcingContract()).isNull();

        outsourcingContract.milestoneNodes(new HashSet<>(Set.of(milestoneNodeBack)));
        assertThat(outsourcingContract.getMilestoneNodes()).containsOnly(milestoneNodeBack);
        assertThat(milestoneNodeBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.setMilestoneNodes(new HashSet<>());
        assertThat(outsourcingContract.getMilestoneNodes()).doesNotContain(milestoneNodeBack);
        assertThat(milestoneNodeBack.getOutsourcingContract()).isNull();
    }

    @Test
    void paymentApplicationTest() {
        OutsourcingContract outsourcingContract = getOutsourcingContractRandomSampleGenerator();
        PaymentApplication paymentApplicationBack = getPaymentApplicationRandomSampleGenerator();

        outsourcingContract.addPaymentApplication(paymentApplicationBack);
        assertThat(outsourcingContract.getPaymentApplications()).containsOnly(paymentApplicationBack);
        assertThat(paymentApplicationBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.removePaymentApplication(paymentApplicationBack);
        assertThat(outsourcingContract.getPaymentApplications()).doesNotContain(paymentApplicationBack);
        assertThat(paymentApplicationBack.getOutsourcingContract()).isNull();

        outsourcingContract.paymentApplications(new HashSet<>(Set.of(paymentApplicationBack)));
        assertThat(outsourcingContract.getPaymentApplications()).containsOnly(paymentApplicationBack);
        assertThat(paymentApplicationBack.getOutsourcingContract()).isEqualTo(outsourcingContract);

        outsourcingContract.setPaymentApplications(new HashSet<>());
        assertThat(outsourcingContract.getPaymentApplications()).doesNotContain(paymentApplicationBack);
        assertThat(paymentApplicationBack.getOutsourcingContract()).isNull();
    }
}
