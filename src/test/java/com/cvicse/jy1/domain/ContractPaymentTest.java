package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractPaymentTestSamples.*;
import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.PaymentApplicationTestSamples.*;
import static com.cvicse.jy1.domain.PaymentCostListTestSamples.*;
import static com.cvicse.jy1.domain.WorkbagTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ContractPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContractPayment.class);
        ContractPayment contractPayment1 = getContractPaymentSample1();
        ContractPayment contractPayment2 = new ContractPayment();
        assertThat(contractPayment1).isNotEqualTo(contractPayment2);

        contractPayment2.setId(contractPayment1.getId());
        assertThat(contractPayment1).isEqualTo(contractPayment2);

        contractPayment2 = getContractPaymentSample2();
        assertThat(contractPayment1).isNotEqualTo(contractPayment2);
    }

    @Test
    void workbagTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        Workbag workbagBack = getWorkbagRandomSampleGenerator();

        contractPayment.setWorkbag(workbagBack);
        assertThat(contractPayment.getWorkbag()).isEqualTo(workbagBack);

        contractPayment.workbag(null);
        assertThat(contractPayment.getWorkbag()).isNull();
    }

    @Test
    void paymentApplicationTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        PaymentApplication paymentApplicationBack = getPaymentApplicationRandomSampleGenerator();

        contractPayment.setPaymentApplication(paymentApplicationBack);
        assertThat(contractPayment.getPaymentApplication()).isEqualTo(paymentApplicationBack);

        contractPayment.paymentApplication(null);
        assertThat(contractPayment.getPaymentApplication()).isNull();
    }

    @Test
    void paymentCostListTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        PaymentCostList paymentCostListBack = getPaymentCostListRandomSampleGenerator();

        contractPayment.addPaymentCostList(paymentCostListBack);
        assertThat(contractPayment.getPaymentCostLists()).containsOnly(paymentCostListBack);

        contractPayment.removePaymentCostList(paymentCostListBack);
        assertThat(contractPayment.getPaymentCostLists()).doesNotContain(paymentCostListBack);

        contractPayment.paymentCostLists(new HashSet<>(Set.of(paymentCostListBack)));
        assertThat(contractPayment.getPaymentCostLists()).containsOnly(paymentCostListBack);

        contractPayment.setPaymentCostLists(new HashSet<>());
        assertThat(contractPayment.getPaymentCostLists()).doesNotContain(paymentCostListBack);
    }

    @Test
    void fundSourceListTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        FundSourceList fundSourceListBack = getFundSourceListRandomSampleGenerator();

        contractPayment.addFundSourceList(fundSourceListBack);
        assertThat(contractPayment.getFundSourceLists()).containsOnly(fundSourceListBack);

        contractPayment.removeFundSourceList(fundSourceListBack);
        assertThat(contractPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);

        contractPayment.fundSourceLists(new HashSet<>(Set.of(fundSourceListBack)));
        assertThat(contractPayment.getFundSourceLists()).containsOnly(fundSourceListBack);

        contractPayment.setFundSourceLists(new HashSet<>());
        assertThat(contractPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
    }
}
