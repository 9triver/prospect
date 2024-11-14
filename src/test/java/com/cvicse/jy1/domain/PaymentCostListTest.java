package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractPaymentTestSamples.*;
import static com.cvicse.jy1.domain.PaymentCostListTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
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
    void workbagTest() {
        PaymentCostList paymentCostList = getPaymentCostListRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        paymentCostList.setWorkbag(workbagBack);
        assertThat(paymentCostList.getWorkbag()).isEqualTo(workbagBack);

        paymentCostList.workbag(null);
        assertThat(paymentCostList.getWorkbag()).isNull();
    }

    @Test
    void contractPaymentTest() {
        PaymentCostList paymentCostList = getPaymentCostListRandomSampleGenerator();
        ContractPayment contractPaymentBack = getContractPaymentRandomSampleGenerator();

        paymentCostList.addContractPayment(contractPaymentBack);
        assertThat(paymentCostList.getContractPayments()).containsOnly(contractPaymentBack);
        assertThat(contractPaymentBack.getPaymentCostLists()).containsOnly(paymentCostList);

        paymentCostList.removeContractPayment(contractPaymentBack);
        assertThat(paymentCostList.getContractPayments()).doesNotContain(contractPaymentBack);
        assertThat(contractPaymentBack.getPaymentCostLists()).doesNotContain(paymentCostList);

        paymentCostList.contractPayments(new HashSet<>(Set.of(contractPaymentBack)));
        assertThat(paymentCostList.getContractPayments()).containsOnly(contractPaymentBack);
        assertThat(contractPaymentBack.getPaymentCostLists()).containsOnly(paymentCostList);

        paymentCostList.setContractPayments(new HashSet<>());
        assertThat(paymentCostList.getContractPayments()).doesNotContain(contractPaymentBack);
        assertThat(contractPaymentBack.getPaymentCostLists()).doesNotContain(paymentCostList);
    }
}
