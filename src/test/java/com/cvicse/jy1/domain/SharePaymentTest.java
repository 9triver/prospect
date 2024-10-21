package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.FundSourceListTestSamples.*;
import static com.cvicse.jy1.domain.SharePaymentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class SharePaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SharePayment.class);
        SharePayment sharePayment1 = getSharePaymentSample1();
        SharePayment sharePayment2 = new SharePayment();
        assertThat(sharePayment1).isNotEqualTo(sharePayment2);

        sharePayment2.setId(sharePayment1.getId());
        assertThat(sharePayment1).isEqualTo(sharePayment2);

        sharePayment2 = getSharePaymentSample2();
        assertThat(sharePayment1).isNotEqualTo(sharePayment2);
    }

    @Test
    void fundSourceListTest() {
        SharePayment sharePayment = getSharePaymentRandomSampleGenerator();
        FundSourceList fundSourceListBack = getFundSourceListRandomSampleGenerator();

        sharePayment.addFundSourceList(fundSourceListBack);
        assertThat(sharePayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getSharePayment()).isEqualTo(sharePayment);

        sharePayment.removeFundSourceList(fundSourceListBack);
        assertThat(sharePayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getSharePayment()).isNull();

        sharePayment.fundSourceLists(new HashSet<>(Set.of(fundSourceListBack)));
        assertThat(sharePayment.getFundSourceLists()).containsOnly(fundSourceListBack);
        assertThat(fundSourceListBack.getSharePayment()).isEqualTo(sharePayment);

        sharePayment.setFundSourceLists(new HashSet<>());
        assertThat(sharePayment.getFundSourceLists()).doesNotContain(fundSourceListBack);
        assertThat(fundSourceListBack.getSharePayment()).isNull();
    }
}
