package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractPaymentTestSamples.*;
import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.PaymentCostListTestSamples.*;
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
    void paymentCostListTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        PaymentCostList paymentCostListBack = getPaymentCostListRandomSampleGenerator();

        contractPayment.addPaymentCostList(paymentCostListBack);
        assertThat(contractPayment.getPaymentCostLists()).containsOnly(paymentCostListBack);
        assertThat(paymentCostListBack.getContractPayment()).isEqualTo(contractPayment);

        contractPayment.removePaymentCostList(paymentCostListBack);
        assertThat(contractPayment.getPaymentCostLists()).doesNotContain(paymentCostListBack);
        assertThat(paymentCostListBack.getContractPayment()).isNull();

        contractPayment.paymentCostLists(new HashSet<>(Set.of(paymentCostListBack)));
        assertThat(contractPayment.getPaymentCostLists()).containsOnly(paymentCostListBack);
        assertThat(paymentCostListBack.getContractPayment()).isEqualTo(contractPayment);

        contractPayment.setPaymentCostLists(new HashSet<>());
        assertThat(contractPayment.getPaymentCostLists()).doesNotContain(paymentCostListBack);
        assertThat(paymentCostListBack.getContractPayment()).isNull();
    }

    @Test
    void fundSourceListTest() {
        ContractPayment contractPayment = getContractPaymentRandomSampleGenerator();
        FundSourceList fundSourceListBack = getFundSourceListRandomSampleGenerator();

        contractPayment.addFundSourceList(fundSourceListBack);
        assertThat(contractPayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getContractPayment()).isEqualTo(contractPayment);

        contractPayment.removeFundSourceList(fundSourceListBack);
        assertThat(contractPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getContractPayment()).isNull();

        contractPayment.fundSourceLists(new HashSet<>(Set.of(fundSourceListBack)));
        assertThat(contractPayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getContractPayment()).isEqualTo(contractPayment);

        contractPayment.setFundSourceLists(new HashSet<>());
        assertThat(contractPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getContractPayment()).isNull();
    }
}
