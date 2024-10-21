package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.SporadicPurchasePaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SporadicPurchasePaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SporadicPurchasePayment.class);
        SporadicPurchasePayment sporadicPurchasePayment1 = getSporadicPurchasePaymentSample1();
        SporadicPurchasePayment sporadicPurchasePayment2 = new SporadicPurchasePayment();
        assertThat(sporadicPurchasePayment1).isNotEqualTo(sporadicPurchasePayment2);

        sporadicPurchasePayment2.setId(sporadicPurchasePayment1.getId());
        assertThat(sporadicPurchasePayment1).isEqualTo(sporadicPurchasePayment2);

        sporadicPurchasePayment2 = getSporadicPurchasePaymentSample2();
        assertThat(sporadicPurchasePayment1).isNotEqualTo(sporadicPurchasePayment2);
    }

    @Test
    void fundSourceListTest() {
        SporadicPurchasePayment sporadicPurchasePayment = getSporadicPurchasePaymentRandomSampleGenerator();
        FundSourceList fundSourceListBack = getFundSourceListRandomSampleGenerator();

        sporadicPurchasePayment.addFundSourceList(fundSourceListBack);
        assertThat(sporadicPurchasePayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getSporadicPurchasePayment()).isEqualTo(sporadicPurchasePayment);

        sporadicPurchasePayment.removeFundSourceList(fundSourceListBack);
        assertThat(sporadicPurchasePayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getSporadicPurchasePayment()).isNull();

        sporadicPurchasePayment.fundSourceLists(new HashSet<>(Set.of(fundSourceListBack)));
        assertThat(sporadicPurchasePayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getSporadicPurchasePayment()).isEqualTo(sporadicPurchasePayment);

        sporadicPurchasePayment.setFundSourceLists(new HashSet<>());
        assertThat(sporadicPurchasePayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getSporadicPurchasePayment()).isNull();
    }
}
