package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.OutsourcingContractTestSamples.*;
import static com.cvicse.jy1.domain.PaymentApplicationTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentApplicationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentApplication.class);
        PaymentApplication paymentApplication1 = getPaymentApplicationSample1();
        PaymentApplication paymentApplication2 = new PaymentApplication();
        assertThat(paymentApplication1).isNotEqualTo(paymentApplication2);

        paymentApplication2.setId(paymentApplication1.getId());
        assertThat(paymentApplication1).isEqualTo(paymentApplication2);

        paymentApplication2 = getPaymentApplicationSample2();
        assertThat(paymentApplication1).isNotEqualTo(paymentApplication2);
    }

    @Test
    void outsourcingContractTest() {
        PaymentApplication paymentApplication = getPaymentApplicationRandomSampleGenerator();
        OutsourcingContract outsourcingContractBack = getOutsourcingContractRandomSampleGenerator();

        paymentApplication.setOutsourcingContract(outsourcingContractBack);
        assertThat(paymentApplication.getOutsourcingContract()).isEqualTo(outsourcingContractBack);

        paymentApplication.outsourcingContract(null);
        assertThat(paymentApplication.getOutsourcingContract()).isNull();
    }
}
