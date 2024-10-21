package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractPaymentTestSamples.*;
import static com.cvicse.jy1.domain.PaymentCostListTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PaymentCostListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PaymentCostList.class);
        PaymentCostList paymentCostList1 = getPaymentCostListSample1();
        PaymentCostList paymentCostList2 = new PaymentCostList();
        assertThat(paymentCostList1).isNotEqualTo(paymentCostList2);

        paymentCostList2.setId(paymentCostList1.getId());
        assertThat(paymentCostList1).isEqualTo(paymentCostList2);

        paymentCostList2 = getPaymentCostListSample2();
        assertThat(paymentCostList1).isNotEqualTo(paymentCostList2);
    }

    @Test
    void contractPaymentTest() {
        PaymentCostList paymentCostList = getPaymentCostListRandomSampleGenerator();
        ContractPayment contractPaymentBack = getContractPaymentRandomSampleGenerator();

        paymentCostList.setContractPayment(contractPaymentBack);
        assertThat(paymentCostList.getContractPayment()).isEqualTo(contractPaymentBack);

        paymentCostList.contractPayment(null);
        assertThat(paymentCostList.getContractPayment()).isNull();
    }
}
