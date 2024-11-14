package com.cvicse.jy1.domain;

import static com.cvicse.jy1.domain.ContractTestSamples.*;
import static com.cvicse.jy1.domain.OtherPaymentTestSamples.*;
import static com.cvicse.jy1.domain.ProjectwbsTestSamples.*;
import static com.cvicse.jy1.domain.SubjectTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.cvicse.jy1.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OtherPaymentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OtherPayment.class);
        OtherPayment otherPayment1 = getOtherPaymentSample1();
        OtherPayment otherPayment2 = new OtherPayment();
        assertThat(otherPayment1).isNotEqualTo(otherPayment2);

        otherPayment2.setId(otherPayment1.getId());
        assertThat(otherPayment1).isEqualTo(otherPayment2);

        otherPayment2 = getOtherPaymentSample2();
        assertThat(otherPayment1).isNotEqualTo(otherPayment2);
    }

    @Test
    void projectwbsTest() {
        OtherPayment otherPayment = getOtherPaymentRandomSampleGenerator();
        Projectwbs projectwbsBack = getProjectwbsRandomSampleGenerator();

        otherPayment.setProjectwbs(projectwbsBack);
        assertThat(otherPayment.getProjectwbs()).isEqualTo(projectwbsBack);

        otherPayment.projectwbs(null);
        assertThat(otherPayment.getProjectwbs()).isNull();
    }

    @Test
    void contractTest() {
        OtherPayment otherPayment = getOtherPaymentRandomSampleGenerator();
        Contract contractBack = getContractRandomSampleGenerator();

        otherPayment.setContract(contractBack);
        assertThat(otherPayment.getContract()).isEqualTo(contractBack);

        otherPayment.contract(null);
        assertThat(otherPayment.getContract()).isNull();
    }

    @Test
    void subjectTest() {
        OtherPayment otherPayment = getOtherPaymentRandomSampleGenerator();
        Subject subjectBack = getSubjectRandomSampleGenerator();

        otherPayment.setSubject(subjectBack);
        assertThat(otherPayment.getSubject()).isEqualTo(subjectBack);

        otherPayment.subject(null);
        assertThat(otherPayment.getSubject()).isNull();
    }
}
