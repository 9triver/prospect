package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.TransactionPaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TransactionPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TransactionPayment.class);
        TransactionPayment transactionPayment1 = getTransactionPaymentSample1();
        TransactionPayment transactionPayment2 = new TransactionPayment();
        assertThat(transactionPayment1).isNotEqualTo(transactionPayment2);

        transactionPayment2.setId(transactionPayment1.getId());
        assertThat(transactionPayment1).isEqualTo(transactionPayment2);

        transactionPayment2 = getTransactionPaymentSample2();
        assertThat(transactionPayment1).isNotEqualTo(transactionPayment2);
    }

    @Test
    void fundSourceListTest() {
        TransactionPayment transactionPayment = getTransactionPaymentRandomSampleGenerator();
        FundSourceList fundSourceListBack = getFundSourceListRandomSampleGenerator();

        transactionPayment.addFundSourceList(fundSourceListBack);
        assertThat(transactionPayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getTransactionPayment()).isEqualTo(transactionPayment);

        transactionPayment.removeFundSourceList(fundSourceListBack);
        assertThat(transactionPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getTransactionPayment()).isNull();

        transactionPayment.fundSourceLists(new HashSet<>(Set.of(fundSourceListBack)));
        assertThat(transactionPayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getTransactionPayment()).isEqualTo(transactionPayment);

        transactionPayment.setFundSourceLists(new HashSet<>());
        assertThat(transactionPayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getTransactionPayment()).isNull();
    }
}
