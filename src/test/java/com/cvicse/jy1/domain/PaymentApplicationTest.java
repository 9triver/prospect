package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.PaymentApplicationTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
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
    void workbagTest() {
        PaymentApplication paymentApplication = getPaymentApplicationRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        paymentApplication.setWorkbag(workbagBack);
        assertThat(paymentApplication.getWorkbag()).isEqualTo(workbagBack);

        paymentApplication.workbag(null);
        assertThat(paymentApplication.getWorkbag()).isNull();
    }
}
