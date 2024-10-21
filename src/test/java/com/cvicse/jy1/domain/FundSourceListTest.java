package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractPaymentTestSamples.*;
import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.SharePaymentTestSamples.*;
import static com.cvicse.jy1.domain.SporadicPurchasePaymentTestSamples.*;
import static com.cvicse.jy1.domain.TransactionPaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class FundSourceListTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(FundSourceList.class);
        FundSourceList fundSourceList1 = getFundSourceListSample1();
        FundSourceList fundSourceList2 = new FundSourceList();
        assertThat(fundSourceList1).isNotEqualTo(fundSourceList2);

        fundSourceList2.setId(fundSourceList1.getId());
        assertThat(fundSourceList1).isEqualTo(fundSourceList2);

        fundSourceList2 = getFundSourceListSample2();
        assertThat(fundSourceList1).isNotEqualTo(fundSourceList2);
    }

    @Test
    void transactionPaymentTest() {
        FundSourceList fundSourceList = getFundSourceListRandomSampleGenerator();
        TransactionPayment transactionPaymentBack = getTransactionPaymentRandomSampleGenerator();

        fundSourceList.setTransactionPayment(transactionPaymentBack);
        assertThat(fundSourceList.getTransactionPayment()).isEqualTo(transactionPaymentBack);

        fundSourceList.transactionPayment(null);
        assertThat(fundSourceList.getTransactionPayment()).isNull();
    }

    @Test
    void sporadicPurchasePaymentTest() {
        FundSourceList fundSourceList = getFundSourceListRandomSampleGenerator();
        SporadicPurchasePayment sporadicPurchasePaymentBack = getSporadicPurchasePaymentRandomSampleGenerator();

        fundSourceList.setSporadicPurchasePayment(sporadicPurchasePaymentBack);
        assertThat(fundSourceList.getSporadicPurchasePayment()).isEqualTo(sporadicPurchasePaymentBack);

        fundSourceList.sporadicPurchasePayment(null);
        assertThat(fundSourceList.getSporadicPurchasePayment()).isNull();
    }

    @Test
    void sharePaymentTest() {
        FundSourceList fundSourceList = getFundSourceListRandomSampleGenerator();
        SharePayment sharePaymentBack = getSharePaymentRandomSampleGenerator();

        fundSourceList.setSharePayment(sharePaymentBack);
        assertThat(fundSourceList.getSharePayment()).isEqualTo(sharePaymentBack);

        fundSourceList.sharePayment(null);
        assertThat(fundSourceList.getSharePayment()).isNull();
    }

    @Test
    void contractPaymentTest() {
        FundSourceList fundSourceList = getFundSourceListRandomSampleGenerator();
        ContractPayment contractPaymentBack = getContractPaymentRandomSampleGenerator();

        fundSourceList.setContractPayment(contractPaymentBack);
        assertThat(fundSourceList.getContractPayment()).isEqualTo(contractPaymentBack);

        fundSourceList.contractPayment(null);
        assertThat(fundSourceList.getContractPayment()).isNull();
    }
}
